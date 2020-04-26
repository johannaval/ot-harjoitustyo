# Käyttöohje

Lataa tiedosto


## Konfigurointi
---


## Ohjelman käynnistäminen

Ohjelma käynnistyy komennolla:

```mvn compile exec:java -Dexec.mainClass=snakegame.Main```

tai releasen ladattua komennolla:

```java -jar snakegame.jar```



## Kirjautuminen
Kun sovellus avataan, aukeaa ensin kirjautumisnäkymä. Jos tunnukset ovat jo luotu, kirjoitetaan käyttäjänimi kohtaan username ja salasana kohtaan password.
Painamalla "Log in", kirjaudutaan sisään ja näkymäksi tulee pelivalikko, mikäli sisäänkirjautuminen on onnistunut.
Jos tunnuksia ei vielä ole, päästään luomaan tunnukset painamalla "Create new user"

<img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot%20from%202020-04-25%2019-30-15.png" width="300" height="200">



## Käyttäjän luominen
Uusi käyttäjä luodaan kirjoittamalla käyttäjänimi ja salasana. Sen jälkeen painetaan napista "Create user", joka ohjaa takaisin kirjautumisnäkymään, mikäli käyttäjän luominen onnistui.
Siellä juuri luodut tunnukset kirjoitetaan uudelleen. Jos taas näkymässä ei halutakkaan luoda uusia tunnuksia, päästään takaisin kirjautumisnäkymään painamalla napista "Return".

<img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot%20from%202020-04-25%2019-38-51.png" width="300" height="200">



## Pelivalikko
Kun sisäänkirjautuminen on onnistunut, avautuu pelivalikko. Pelaaja saa valita, haluaako pelata pelin reunoilla vai ilman kohdasta "With borders?".
Mikäli pelaaja haluaa reunat, jättää hän raksin ruutuun, jos taas ilman reunoja, painetaan napista, jolloin raksi häviää.
Teeman pelaaja valitsee kohdasta "Theme?". Valittavana on 3 erilaista teemaa, jotka muuttavat esim. pelialueen taustan, reunojen, madon ja ruokien väriä.
Teeman saa päälle valitsemalla napin jonkun vaihtoehdoista. Peli aloitetaan painamalla "Start game!", jolloin siirrytään pelitilaan. Ilman teeman valintaa peli ei kuitenkaan käynnisty, vaan ilmestyy ohjeviesti, jossa pyydetään valitsemaan jonkin teemoista ensin.

<img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot%20from%202020-04-25%2019-39-52.png" width="300" height="200">



## Peli
Peli alkaa, kun käyttäjä painaa ENTER-näppäimestä. Ruudulle ilmestyy mato, jota ohjataan nuolinäppäimillä. Tarkoituksena on  saada mahdollisimman paljon pisteitä, joita kerätään nappaamalla ruokia, eli pelialueelle ilmestyviä neliöitä, jolloin myös madon nopeus kasvaa. Mato kuolee, jos osuu itseensä, tai jos pelaaja on valinnut pelin reunoilla, kuolee mato osuessaan myös pelialueen reunoihin. 

<img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot%20from%202020-04-25%2019-35-56.png" width="300" height="200">



## Top-lista
Kun peli on päättynyt, tulee näkymäksi top-lista. Top-listaan tulee pelin 10 parhainta pistettä saanutta pelaajaa. Mikäli pelaajia ei ole täyttä kymmentä,
ovat loput ruudut tyhjiä. Myöskään 0 pisteellä pelaaja ei pääse top-listalle.

<img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot%20from%202020-04-25%2019-42-02.png" width="300" height="200">
