# Vaatimusmäärittely

- [x] Kirjautumisnäkymä
- [x] Pystytään luomaan uusi käyttäjä tai kirjautua sisään jo olemassa olevalla tunnuksella
- [x] Pelaaja voi valita yhden kolmesta teemasta
- [x] Pelaaja voi valita, haluaako pelata pelin reunoilla vai ilman reunoja
- [x] Voidaan aloittaa peli
- [x] Matoa pystyy liikuttaa ja se pystyy syömään ruokaa
- [x] Mato kuolee osuessaan itseensä tai seinään
- [x] Näkyviin tulee top10 lista sisältöineen

## Sovelluksen tarkoitus:

Sovelluksen ideana on matopeli. Käyttäjä luo tunnuksen (ellei sitä jo ole), ja pääsee pelaamaan. Käyttäjä myös näkee oman ennätyksensä ja peliä pelatessa kasvavat pisteet. Peliä pystyy pelaamaan usea käyttäjä omalla tunnuksellaan, ja jokaisen oma henkilökohtainen paras tulos näkyy hänellä itsellään. Kun peli loppuu, näytölle tulee top 10 lista, jossa näkyy pelissa saadut 10 korkeinta pistettä ja kyseiset pisteet saaneet pelaajat.

## Käyttäjät

Kaikki käyttäjät yhdenlaisia käyttäjiä

## Käyttöliittymäluonnos:

 <img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/99999.jpg" width="600" height="500">
 
Kun sovellus avataan, aukeaa ensin kirjautumisnäkymä. Siinä pystyään kirjautumaan omalla käyttäjänimellä sekä salasanalla sisään mikäli käyttäjän on jo luonut. Jos käyttäjää ei ole vielä luotu, käyttäjä painaa kohdasta "Create new user", jolloin aukeaa uusi näkymä, jossa pyydetään syöttämään käyttäjänimi ja salasana. Tämän jälkeen painetaan Create user, josta palataan aikaisempaan tilaan, ja syötetään juuri luodut tunnukset sinne. Kun tämä onnistuu, eli tunnukset ovat kelvolliset, siirtyy ohjelma pelivalikkoon, jossa käyttäjä saa valita itselleen yhden teeman kolmesta, ja sen, haluaako pelata pelin reunoilla vai ilman reunoja. Painamalla Start game!, peli käynnistyy. Pelin päätyttyä siirrytään Top-lista näkymään, johon listamaan  parhaimmat tulokset. Tässä näkymässä on myös mahdollisuus kirjautua ulos tai palata pelivalikkoon, josta aloittaa uusi peli.



## Perusversion tarjoama toiminnallisuus:


#### Ennen kirjautumista: :white_check_mark:
* jos käyttäjällä on jo tunnus, hän kirjautuu sisään, muuten hän  luo uuden tunnuksen.
* käyttäjänimi ei saa olla jo varattu, sen pituus tulee olla yli 3 merkkiä ja se saa sisältää erikoismerkkejä
* kirjautuminen onnistuu, mikäli käyttäjätunnus on rekisteröity. Muuten ohjelma ilmoittaa, ettei käyttäjää vielä ole.


#### Kirjautumisen jälkeen: :white_check_mark:
* Käyttäjälle avautuu pelivalikko, jossa hän saa valita yhden kolmesta teemasta, sekä valita, pelaako reunoilla vai ilman.
* Pelaaja voi aloittaa pelin painamalla "Start game!", tai kirjautua ulos napilla "Log out".


#### Pelin aloitettua:  :white_check_mark:
* peli toimii matopelin idealla, mato kerää satunnaisesti tulevia “ruokia”, joista se saa pisteitä. Samalla mato kasvaa pituutta, ja sen liikenopeus kasvaa. 
* Peli päättyy, kun mato kuolee. Riippuen onko pelaaja halunnut pelata reunojen kanssa, mato kuolee osuessaan itseensä tai osuessaan reunaan.



#### Pelin loputtua: :white_check_mark:
* tulostuu matopelin top 10, eli siihen kootaan pelistä saadut 10 kaikkein suurinta pistettä ja pisteiden saaneiden käyttäjänmimet. Jokainen käyttäjä voi olla ennätys-taulussa korkeintaan kerran, eikä siihen pääse, jos pisteet on 0.



#### Jatkokehitysideoita:

- Kun perusversio on tehty, voisi sovellusta esimerkiksi jatkaa tälläisillä osilla:

* Kun peli loppuu, top 10-listan lisäksi voisi tulostua päivän top 10, jossa näkyisi viimeisen 24h ajan parhaimmat pisteet saaneet pelaajat.
* Pelaaja voisi oman ennätyksen lisäksi nähdä, milloin ennätys on tullut.
* Vaikeustasoja, esim 1-3. Taso 1 on helpoin, 3 vaikein. Helpoimmassa mato liikkuu hitaiten, 3. taas mato liikkuu jo reilusti nopeammin, jolloin pelaaminen on jo vaikeampaa.
* Reunojen lisäksi pelialueen sisälle tulevia seiniä, jolloin pelialue olisi "sokkelo", jossa väistellään seiniä.

