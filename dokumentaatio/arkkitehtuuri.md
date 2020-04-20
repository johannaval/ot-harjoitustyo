## Harjoitustyön alustava rakenne luokka/pakkauskaaviona

 <img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/IMG_0149.jpeg" width="400" height="550">

## Päätoiminnallisuudet sekvenssikaavioina 

### Uuden käyttäjän luominen:
Uuden käyttäjän luominen, kun kyseisellä tunnuksella ei ole vielä rekisteröity käyttäjää. 
Kun käyttäjä on painanut napista "Create new user", avautuu näkymä uuden käyttäjän luomiseen. Kun käyttäjä on kirjoittanut käyttäjänimen ja salasanan, kutsutaan PlayerServicen metodia createUser, jolle annetaan parametreiksi käyttäjänimi sekä salasana. PlayerService käyttää apunaan DaoPlayer rajapintaa, ja kutsuu sen findUser metodia, jolle annetaan parametriksi käyttäjänimi. Jos kyseisellä nimellä ei ole vielä luotu käyttäjää, luo PlayerService uuden käyttäjän ja kutsuu DaoPlayerin metodia create, joka saa parametrinaan uuden käyttäjän ja lisää sen siten tietokantaan. Kun uuden käyttäjän luominen on onnistunut, ohjelma asettaa näkymäksi LogInScenen, eli kirjautumisnäkymän.

 <img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/ohte1.jpg" width="500" height="550">
 
 
 
### Sisään kirjautuminen:
Kirjautumisnäkymän toiminnallisuudet, kun käyttäjä kirjoittaa olemassa olevan käyttäjätunnuksen ja kirjautuu sisään.
Kun käyttäjä painaa log in, tarkistaa ohjelma PlayerServicen metodia login, jolle annetaan parametriksi käyttäjätunnus sekä salasana. PlayerService käyttää apumaan rajapintaa DaoPlayer, joka tarkistaa PlayerSQL luokalta, onko kyseinen tunnus olemassa parametrinaan saadulla käyttäjänimellä ja salasanalla. Jos tunnus löytyy, kirjautuminen onnistuu ja näkymäksi asetetaan sceneGame, eli pelin aloituksen näkymä. Samalla PlayerService asettaa kirjautuneen käyttäjän kirjautuneeksi.

<img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/ohte2.jpg" width="500" height="550">


### Ohjelman yleinen toiminnallisuus (ilman pelin ominaisuuksia):
Tähän on kuvattu ohjelman yleinen toiminnallisuus. Kun käyttäjä on kirjautunut onnistuneesti sisään ja painaa napistä "Start game!" avautuu uusi näkymä, GameBoardScene. Pelialustasta huolehtiva GameBoardControllerin start metodi alustaa GameServicen, joka pitää huolta pelin toiminnallisuuksista. Alustuksen jälkeen ohjelma kutsuu GameServisen metodia startGame, joka  alustaa pelialueen. GameBoardViewController huolehtii käyttäjän napin painalluksista, ja aloittaa pelin kutsumalla gameServicen metodia move, kun käyttäjä painaa Enteristä. Kun peli päättyy, kutsuu ohjelma gameBoardViewControllerin metodia handleTopList, joka saa parametrinaan juuri pelatun pelin pistemäärän. Controlleri kutsuu PlayerServicen metodia setHighscore, jossa PlayerService tarkistaa, onko kirjautuneen käyttäjän aiempi ennätys pienempi kuin äsken pelatun pelin pistemäärä. Jos on, asettaa se pelaajan uudeksi ennätykseksi uuden pistemäärän ja samalla kutsutaan rajapinnan DaoPlayer metodia update, joka päivittää uuden ennätyksen myös tietokantaan. Kun tämä on suoritettu, tulee näkymäksi TopListScene, jossa näkyy 10 parasta tulosta ja tulosten saaneiden käyttäjänimet. Jos käyttäjä painaa tässä näkymässä log out napista, PlayerService kirjaa käyttäjän ulos, ja näkymäksi vaihdetaan LogInScene. Jos käyttäjä painaa new game, näkymäksi tulee gameScene ja uuden pelin voi aloittaa.

 <img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/IMG_0303.jpeg" width="600" height="700">


### Pelin ominaisuudet ja toiminta:
Kun peli aloitetaan, kutsuu GameBoardViewController GameServicen metodia startGame, jossa alustetaan pelialue. GameBoardViewController myös huolehtii käyttäjän painamista napeista. Kun käyttäjä painaa Enter, kutsuu controller GameServicen metodia move, jossa lisätään ensimmäinen ruoka sattumanvaraiseen kohtaan ja luodaan SnakeHead, eli mato. Mato koostuu päästä sekä "paloista" eli vartalosta. Mato lisätään pelialueelle, ja kun pelaaja painaa nuolinäppäimistä, asettaa madon pää suunnakseen juuri painetun suunnan sekä asettaa suunnan myös jokaiselle palalleen. Jokainen pala seuraa siis päätä, joka näyttää suunnan. Suunnan pää saa käyttäjän näppäimiltä. Riippuen suunnasta, aina joko madon x tai y arvo pienenee tai kasvaa. Samalla kun peli on käynnissä, on myös pelialueen metodi update toiminnassa, jossa se jatkuvasti tarkistaa, onko mato osunut ruokaan, itseensä tai seinään. Ruokaan osuessaan pisteet kasvavat 50p, ja madon koko kasvaa. Jos mato törmää itseensä tai seinään, peli päättyy. (kaavio tulossa myöhemmin)
