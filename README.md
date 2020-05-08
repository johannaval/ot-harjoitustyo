## Ohjelmistotekniikka, harjoitystyö:

###  :snake: Snake game AKA Matopeli  :bug:
Sovellus toteutettu Helsingin yliopiston Ohjelmistotekniikan harjoitustyönä.

Peliin luodaan ensin omat tunnukset, joilla kirjaudutaan sisään. Peli toimii kuten kaikkien tuntema matopeli, jossa tarkoituksena on saada mahdollisimman paljon pisteitä keräämällä "ruokia". Peli päättyy, kun mato kuolee. Mato kuolee jos se
osuu itseensä, tai jos pelaaja on valinnut pelin reunoilla, peli päättyy myös madon osuessa reunaan. Lopuksi peli näyttää top 10 listan, jonne vain parhaimmat pisteet keränneet pääsevät. Pelissä on mahdollista valita, haluaako pelata reunoilla vai ilman, sekä yhden kolmesta teemasta.


### Dokumentaatio:
* [Käyttöohje](https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)
* [Vaativuusmäärittely](https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)
* [Arkkitehtuurikuvaus](https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)
* [Testausdokumentti](https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)
* [Työaikakirjanpito](https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)


### Releaset: 
* [Viikko 5](https://github.com/johannaval/ot-harjoitustyo/releases/tag/viikko5)
* [Viikko 6](https://github.com/johannaval/ot-harjoitustyo/releases/tag/2)
* [Loppupalautus](https://github.com/johannaval/ot-harjoitustyo/releases/tag/3)


## Komentorivin toiminnot:

 * Ohjelman suoritus 
 
   ```mvn compile exec:java -Dexec.mainClass=snakegame.Main```
   
   
* Testaus 

   ```mvn test```
   
   
* Testikattavuusraportti (raportti löytyy avaamalla target/site/jacoco/index.html)

   ```mvn test jacoco:report```
   
   
* Jar ohjelman luominen 

   ```mvn package```
   
   
* Jar ohjelman suorittaminen onnistuu JOKO juurikansiosta

  ```java -jar target/Snakegame-1.0-SNAPSHOT.jar```


* TAI target-kansion sisällä (jolloin vaatii, että suorituskansiossa on config.properties-tiedosto! Voit esim kopioida sen juurikansiosta target-kansioon)

   ```java -jar Snakegame-1.0-SNAPSHOT.jar```
   
   
* Checkstyle

   ```mvn jxr:jxr checkstyle:checkstyle```
   
   
* Java Doc (kuvaukset löytyvät avaamalla target/site/apidocs/index.html)

   ```mvn javadoc:javadoc```
