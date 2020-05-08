### Arkkitehtuurikuvaus


## Rakenne

Ohjelman pakkausrakenteeseen kuuluu 3 pakkausta, ui, domain ja dao. 
Snakegame.ui huolehtii ohjelman käyttöliittymästä, joka hyödyntää Java FXML:n tarjoamia controllereita. 
Controllerit vastaavat eri näkymien ominaisuuksista ja niiden toiminnasta. 
Snakegame.domain huolehtii sovelluslogiikasta, johon kuuluu pelilogiikasta vastaavat luokat sekä kirjautuneen käyttäjän toiminnoista vastaavat luokat. 
Snakegame.dao huolehtii käyttäjän tietojen pysyväistallennuksesta tietokannan avulla.

<img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/IMG_0507.jpeg" width="200" height="350">


## Käyttöliittymä

Käyttöliittymä sisältää viisi eri näkymää

* kirjautumisnäkymän
* uuden käyttäjän luomisnäkymän
* pelivalikon
* pelin
* top-listan

Käyttöliittymästä ja näkymien vaihdosta pitää huolta GameUi luokka, mutta itse näkymien toiminnoista vastaa aina kyseisen näkymän controller, joka huolehtii esim. napin painalluksista, käyttäjän syöttämistä teksteistä tai painetuista näppäimistä. Näkymät on toteutettu Scene-olioina, jotka sijoitetaan GameUi:ssa olevaan stageen aina kerrallaan, riippuen näkymästä, minne käyttäjä on siirtymässä.



## Sovelluslogiikka

Pelin aloituksesta ja lopetuksesta vastaa domainin luokka GameService, ja itse pelialueen ominaisuuksista ja päivittämisestä vastaa luokka Area. Käyttäjän toiminnallisuuksista vastaa saman pakkauksen luokka PlayerService. 

Pelilogiikkaan kuuluvat luokat Area, Food, SnakeHead ja SnakePart. 
Area on tärkeä luokka pelin etenemisen kannalta, sillä se huolehtii pelialueen toiminnasta esimerkiksi lisäämällä tai poistamalla ruokia sekä lisäämällä madon ja sille paloja. Area huolehtii myös pelaajan valitsemasta teemasta, jolloin asettaa oikean taustan sekä pelialueelle reunat, mikäli pelaaja haluaa niillä pelata. Area huolehtii myös pisteiden saannista, madon liikkumisesta ja tarkistaa, onko mato osunut itseensä tai reunaan. 
Food luokka vastaa pelialueen ruoista, SnakeHead madon päästä sekä näyttää myös madon suunnan, jota SnakePart luokan palat eli madon vartalo noudattaa.

Pelaajan tiedoista huolehtii luokka PlayerService, johon liittyy kirjautunut käyttäjä (Player-luokan olio). PlayerService hyödyntää dao-pakkauksen rajapintaa DaoPlayer, joka taas kutsuu PlayerSQL:n metodeja esim tarkistamaan, löytyykö kyseisellä nimellä jo käyttäjää, onnistuiko käyttäjän luominen ja sisäänkirjautuminen ja päivittää myös tarvittaessa pelaajan tietoja.  PlayerSQL käyttää tietokantayhteyttä sekä luo tietokantataulun, jonne lisää pelaajia tietoineen.

Sovelluksen rakenne luokka/pakkauskaaviona:

 <img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/999999.jpg" width="400" height="550">
 
 
 ## Tietojen pysyväistallennus
 
 Tietojen tallentamisesta vastaa snakegame.daon luokka PlayerSQL, joka tallentaa tiedot tietokantaan. Luokka toteuttaa rajapinnan DaoPlayer, jolloin domain käyttää PlayerSQL:lää vain rajapinnan kautta. PlayerSQL-luokka hyödyntää siis Data Access Object -suunnittelumallia (eli DAO:ta).
 
 
## Tietokanta

Ohjelma tallentaa käyttäjät tietokantatauluun Players, johon kirjataan pelaajan id, käyttäjänimi, salasana ja ennätys. Id ja käyttäjänimi ovat uniikkeja, joten kellään toisella pelaajalla ei voi olla samaa tunnusta. Ennätys on heti käyttäjän luomisen jälkeen 0, mutta kasvaa, kun pelaaja pelaa peliä ja saa pisteitä. Tauluun talletetaan tiedot muodossa
(player_id INTEGER PRIMARY KEY, username TEXT unique, password TEXT, highscore INTEGER)
Testeille on oma testitietokanta, jottei niiden tulokset vaikuta itse ohjelman käytössä olevaan tietokantaan ja näin sekoita sen tuloksia. Niin ohjelman, kuin testien käytössä olevat tietokannat sijoitetaan ohjelman juuressa sijaitsevaan "config.properties" konfiguraatiotiedostoon jotta niitä voi halutessaan muuttaa.
Tietokannat tallennetaan config.properties-tiedostoon muodossa:

```urlForDao=jdbc:sqlite:sqlConnectionUrl.db```

```urlForDaoUnitTests=jdbc:sqlite:forUnitTests.db```
 

 

## Päätoiminnallisuudet sekvenssikaavioina 

### Uuden käyttäjän luominen:
Uuden käyttäjän luominen, kun kyseisellä tunnuksella ei ole vielä rekisteröity käyttäjää. 
Kun käyttäjä on painanut napista "Create new user", avautuu näkymä uuden käyttäjän luomiseen. 
Kun käyttäjä on kirjoittanut käyttäjänimen ja salasanan, kutsutaan PlayerServicen metodia createUser, jolle annetaan parametreiksi käyttäjänimi sekä salasana. PlayerService selvittää DaoPlayer rajapinnan avulla, onko kyseisellä nimellä jo luotu käyttäjää. Koska kaaviossa käyttäjää ei ollu luotu, se palauttaa "null", ja PlayerService luo uuden käyttäjän. 
Sen jälkeen se kutsuu DaoPlayerin metodia create, jonka avulla uusi käyttäjä lisätään tietokantaan.
Kun uuden käyttäjän luominen on onnistunut ja metodi palauttaa "true", ohjelma asettaa näkymäksi LogInScenen, eli kirjautumisnäkymän.

 <img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/ohte1.jpg" width="650" height="550">
 
 
 
### Sisään kirjautuminen:
Kirjautumisnäkymän toiminnallisuudet, kun käyttäjä kirjoittaa olemassa olevan käyttäjätunnuksen ja kirjautuu sisään.
Kun käyttäjä painaa log in, kutsuu ohjelma PlayerServicen metodia login. Metodille annetaan parametriksi käyttäjätunnus sekä salasana. PlayerService kutsuu rajapinnan DaoPlayer metodia isLogInOk(käyttäjänimi, salasana), joka tarkistaa onko kyseinen tunnus olemassa parametrinaan saadulla käyttäjänimellä ja salasanalla. Jos tunnus löytyy, palauttaa metodi kirjautuneen käyttäjän, jolloin kirjautuminen on onnistunut ja näkymäksi asetetaan sceneGame, eli pelivalikon näkymä. Samalla PlayerService asettaa kirjautuneen käyttäjän kirjautuneeksi.

<img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/ohte2.jpg" width="650" height="550">


### Ohjelman yleinen toiminnallisuus (ilman pelin ominaisuuksia):
Tähän on kuvattu ohjelman yleinen toiminnallisuus (ilman pelin yksityiskohtaisia toimintoja). 
Kun käyttäjä on kirjautunut onnistuneesti sisään, ja painaa napistä "Start game!" avautuu uusi näkymä, 
GameBoardScene. Pelialustasta huolehtiva GameBoardControllerin start metodi luo GameServicen, joka pitää huolta pelin toiminnallisuuksista. 
Start metodi myös kutsuu GameServicen metodia addGameArea(), joka luo pelialueen.
Kun käyttäjä painaa "Enter"-näppäimestä, peli alkaa ja näppäinten kuuntelusta vastaava GameBoardViewController-luokka kutsuu GameServicen metodia move(). Tiedot pelaajan valitsemasta teemasta ja reunoista GameService saa Controllerilta, joka saa tiedot GameUi:lta.
Kun peli päättyy, kutsuu ohjelma gameBoardViewControllerin metodia handleTopList, 
joka saa parametrinaan juuri pelatun pelin pistemäärän. Controlleri kutsuu PlayerServicen metodia setHighscore(pistemäärä), jonka myötä PlayerService tarkistaa, onko kirjautuneen käyttäjän aiempi ennätys pienempi, 
kuin äsken pelatun pelin pistemäärä. Jos on, asettaa se pelaajan uudeksi ennätykseksi uuden pistemäärän ja samalla kutsutaan rajapinnan DaoPlayer metodia update, joka päivittää uuden ennätyksen tietokantaan. Kun tämä on suoritettu, tulee näkymäksi TopListScene, jossa näkyy 10 parasta tulosta ja tulosten saaneiden käyttäjänimet. Top 10-listan TopListViewController saa kutsumalla PlayerServicen metodia topList(topList), jossa parametrin lista on tyhjä. PlayerService kutsuu rajapinnan metodia palauttamaan annetun listan täytettynä oikeilla tiedoilla. 
Jos käyttäjä painaa tässä näkymässä log out napista, PlayerService kirjaa käyttäjän ulos, ja näkymäksi vaihdetaan LogInScene. Jos käyttäjä painaa new game, näkymäksi tulee gameScene ja uuden pelin voi aloittaa.

 <img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/9999.jpg" width="700" height="900">
(Nuolen "Press "ENTER" pitäisi osoittaa GameBoardViewContolleriin, jossa metodi move() on, ei PlayerServiceen)


### Pelin ominaisuudet ja toiminta:
Kun käyttäjä on painanut "Start game!", GameBoardViewController luo GameServicen, ja kutsuu sitä lisäämään pelialueesta vastaavan Arean. Kun käyttäjä painaa "ENTER", GameBoardViewController kutsuu GameServicen metodia move, 
joka lisää pelaajan valitseman teeman ja reunat. Metodi myös kutsuu Arean metodia addFood, joka lisää pelialueelle sattumanvaraiseen kohtaan ensimmäisen ruoan.
Metodi lisää pelialueelle SnakeHead:in, eli madon pään, johon liittyy myös madon vartalo, eli usea SnakePart. Mato lisätään pelialueelle. Sitten kutsutaan metodia startTimer, joka hyödyntää AnimationTimeria. Kokoajan pelin ollessa käynnissä, se kutsuu Arean metodia update. Update esim. päivittää madon suuntaa, pitää sen jatkuvassa liikkeessä, tarkistaa osuuko mato seinään/itseensä/ruokaan. Tässä kaaviossa pelaaja painaa nuolinäppäimestä "UP", jolloin Controller kutsuu GameServiceä, joka kutsuu madon päätä vaihtamaan suunnaksi "UP". Pää huolehtii myös koko vartalon suunnan vaihtamisesta.
Kaaviossa mato osuu ruokaan, jolloin vanha ruoka poistuu ja uusi tulee tilalle, pisteet kasvavat ja madon palojen määrä kasvaa. Update metodi tarkastaa, onko mato osunut itseensä tai reunaan. Jos jompikumpi palauttaa true, peli päättyy. Tässä kaaviossa mato osuu pelialueen reunaan, eli updaten kutsuessa metodia hitWall se palauttaa true, jolloin kutsutaan GameServicen metodia GameIsOver lopettamaan pelin.


<img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/999.jpg" width="700" height="900">



## Ohjelman rakenteeseen jääneet heikkoudet
Ohjelman domain-pakkauksen luokista löytyy FX-elementtejä, sillä niiden eriyttäminen vain UI:n hallintaan oli minulle hankalaa. Pelilogiikkaan liittyviä luokkia on useita (GameService, Area, Food, SnakeHead ja SnakePart), nämä olisi varmasti saanut tiivistettyä vähempään määrään luokkia, jolloin koodin hahmottaminen olisi voinut helpottua. 
