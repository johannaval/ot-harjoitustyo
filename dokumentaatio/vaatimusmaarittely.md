# Vaatimusmäärittely

- [x] Kirjautumisnäkymä
- [x] Pystytään luomaan uusi käyttäjä tai kirjautua sisään jo olemassa olevalla tunnuksella
- [x] Voidaan aloittaa peli
- [ ] Matoa pystyy liikuttaa ja se pystyy syömään ruokaa
- [ ] Mato kuolee osuessaan itseensä tai seinään
- [ ] Näkyviin tulee top10 lista sisältöineen

## Sovelluksen tarkoitus:

Sovelluksen ideana on matopeli. Käyttäjä luo tunnuksen (ellei sitä jo ole), ja pääsee pelaamaan. Käyttäjä myös näkee oman ennätyksensä ja peliä pelatessa kasvavat pisteet. Peliä pystyy pelaamaan usea käyttäjä, ja jokaisen oma henkilökohtainen paras tulos näkyy hänellä itsellään. Kun peli loppuu, näytölle tulee top 10 lista, jossa näkyy pelissa saadut 10 korkeinta pistettä ja kyseiset pisteet saaneet pelaajat.

## Käyttäjät

Kaikki käyttäjät yhdenlaisia käyttäjiä

## Käyttöliittymäluonnos:

 <img src="https://raw.githubusercontent.com/johannaval/ot-harjoitustyo/master/dokumentaatio/kuvat/ohte.jpg" width="300" height="400">
 
 // Kuvan teon jälkeen päätin vielä lisätä sisään kirjautumisen jälkeen näkymän, jossa pelaaja voi aloittaa pelin painamalla nappia. Näin itse pelialue ei tarvitse ylimääräisiä nappeja viemään tilaa ja on selkeämpi. 

Kun sovellus avataan, aukeaa ensin kirjautumisnäkymä. Siinä pystyään kirjautumaan omalla käyttäjänimellä sekä salasanalla sisään mikäli käyttäjän on jo luonut. Jos käyttäjää ei ole vielä luotu, käyttäjä painaa kohdasta "Create new user", jolloin aukeaa uusi kuvake, jossa pyydetään syöttämään käyttäjänimi ja salasana. Tämän jälkeen painetaan OK, josta palataan aikaisempaan tilaan, ja syötetään juuri luodut tunnukset sinne. Kun tämä onnistuu, eli tunnukset ovat kelvolliset, siirtyy ohjelma pelin alkuun, ja painamalla START peli käynnistyy. Pelin päätyttyä siirrytään viimeiseen näkymään, jossa listataan parhaimmat tulokset. Tässä kohdassa on myös mahdollisuus kirjautua ulos.



## Perusversion tarjoama toiminnallisuus:


#### Ennen kirjautumista: :white_check_mark:
* jos käyttäjällä on jo tunnus, hän kirjautuu sisään, muuten hän  luo uuden tunnuksen.
* käyttäjänimi ei saa olla jo varattu, sen pituus tulee olla yli 3 merkkiä ja se saa sisältää erikoismerkkejä
* kirjautuminen onnistuu, mikäli käyttäjätunnus on jo rekisteröity. Muuten ohjelma ilmoittaa tästä.


#### Kirjautumisen jälkeen: :white_check_mark:
* käyttäjä pystyy aloittamaan pelin painamalla “aloita”

### Pelin aloitettua:
* peli toimii matopelin idealla, mato kerää satunnaisesti tulevia “palloja”, joista se saa pisteitä. Samalla mato kasvaa pituutta, ja sen liikenopeus kasvaa. 
* Peli päättyy, kun mato kuolee. Mato voi kuolla jos se osuu itseensä tai jos mato osuu pelialueen reunaan.


#### Pelin loputtua:
* tulostuu matopelin top 10, eli siihen kootaan pelistä saadut 10 kaikkein suurinta pistettä ja pisteiden saaneiden käyttäjänmimet. 
* Sama käyttäjä voi saada nimensä listaan useamman kerran, mikäli pisteet siihen johtavat.


## Jatkokehitysideoita:

- Kun perusversio on tehty, voin jatkaa sovellusta esim tälläisillä osilla

* Kun peli loppuu, top 10-listan lisäksi voisi tulostua päivän top 10, jossa näkyisi viimeisen 24h ajan parhaimmat pisteet saaneet pelaajat.
* Pelaaja voisi oman ennätyksensä lisäksi nähdä, milloin ennätys on tullut.
* Vaikeustasoja, esim 1-3. Taso 1 on helpoin, 3 vaikein. Helpoimmassa mato liikkuu hitaiten, 3. taas mato liikkuu jo reilusti nopeammin, jolloin pelaaminen on jo vaikeampaa.
   * Tällöin myös top 10 listauksissa tulee lukea, millä vaikeustasolla pelaaja on pelannut.

