# Vaatimusmäärittely

## Sovelluksen tarkoitus:

Sovelluksen ideana on matopeli. Käyttäjä luo tunnuksen (ellei sitä jo ole), ja pääsee pelaamaan. Käyttäjä myös näkee oman ennätyksensä ja sen, milloin se on tullut. Peliä pystyy pelaamaan usea käyttäjä, ja jokaisen henkilökohtainen paras tulos näkyy hänellä itsellään. Kun peli loppuu, näytölle tulee top 10 lista, jossa näkyy pelin 10 korkeinta pistettä saanutta pelaajaa ja heidän pisteensä.

![](https://raw.githubusercontent.com/johannaval/ot-harjoitustyo/master/dokumentaatio/kuvat/ohte.jpg){:height="20%" width="20%"}

## Käyttöliittymäluonnos:
Kun sovellus avataan, aukeaa ensin kirjautumisnäkymä. Siinä pystyään kirjautumaan omalla käyttäjänimellä sekä salasanalla sisään mikäli käyttäjän on jo luonut, tai luomaan uuden käyttäjän. Jos käyttäjä luo uuden tunnuksen, aukeaa uusi kuvake jossa pyydetään syöttämään käyttäjänimi ja salasana, sen jälkeen painetaan OK, josta palataan aikaisempaan tilaan, ja syötetään juuri luodut tunnukset sinne. Kun tämä onnistuu, siirtyy ohjelma peli-tilaan, ja pelin päätyttyä viimeiseen, jossa listataan parhaimmat tulokset sekä on mahdollisuus kirjautua ulos.


## Perusversion tarjoama toiminnallisuus:

#### Ennen kirjautumista:
* jos käyttäjällä on jo tunnus, hän kirjautuu sisään, muuten hän  luo uuden tunnuksen.
* käyttäjänimi ei saa olla jo varattu, sen pituus tulee olla yli 3 merkkiä ja se saa sisältää erikoismerkkejä
* kirjautuminen onnistuu, mikäli käyttäjätunnus on jo rekisteröity. Muuten ohjelma ilmoittaa tästä


#### Kirjautumisen jälkeen:
* käyttäjä pystyy aloittamaan pelin painamalla “aloita”
* toimii matopelin idealla, mato kerää satunnaisesti tulevia “palloja”, joista se saa pisteitä. Samalla mato kasvaa pituutta, ja sen liikenopeus kasvaa. 
* Peli loppuu, kun mato kuolee. Mato voi kuolla jos hän osuu itseensä tai jos mato osuu pelialueen reunaan.


#### Pelin loputtua:
* tulostuu matopelin top 10, eli siihen kootaan kaikista käyttäjistä 10 parhaimmat pisteet saanutta käyttäjää ja heidän pisteensä. 
* Sama käyttäjä voi saada nimensä listaan usemman kerran, mikäli pisteet siihen johtavat.

## Jatkokehitysideoita:

(Kun perusversio on tehty, voin jatkaa sovellusta esim tälläisillä osilla)
* Kun peli loppuu, top 10-listan lisäksi voisi tulostua päivän top 10, jossa näkyisi viimeisen 24h ajan parhaimmat pisteet saaneet pelaajat.
* Pelaaja voisi oman enntyksensä lisäksi nähdä, milloin ennätys on tullut.
* Vaikeustasoja, esim 1-3. Taso 1 on helpoin, 3 vaikein. Helpoimmassa mato liikkuu hitaiten, 3. taas mato liikkuu jo reilusti nopeammin, jolloin pelaaminen on jo vaikeampaa.
* ^ Tällöin myös top 10 listauksissa tulee lukea, millä vaikeustasolla pelaaja on pelannut.

