## Ohjelmistotekniikka, harjoitystyö:

###  :snake: Snake game AKA Matopeli  :bug:
Peliin luodaan ensin omat tunnukset, joilla kirjaudutaan sisään. Pelissä kerätään pisteitä nappaamalla "ruokia" ja selviämällä mahdollisimman pitkään elossa. Peli loppuu kun mato kuolee, joka voi olla seuraus joko itseensä osumisesta tai seinään päin menemisestä. Lopuksi peli näyttää top10 listan, jonne vain parhaimmat pisteet keränneet pääsevät. Pelissä on myös mahdollista valita, haluaako pelata reunoilla vai ilman, sekä yhden kolmesta teemasta.


### Dokumentaatio:
* [Työaikakirjanpito](https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)
* [Vaativuusmäärittely](https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)
* [Arkkitehtuuri](https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)


### Releaset: 
* (Jotta Jar-tiedoston saa auki, lataa releasen jar-tiedosto, hyväksy tiedoston permissions kohdasta "Allow executing file as program", ja voit nyt avata jarin joko graafisesti tai komentokentän kautta ~/Downloads kansiossa komennolla "java -jar snakegame.jar")
* [Viikko 5](https://github.com/johannaval/ot-harjoitustyo/releases/tag/viikko5)


## Komentorivin toiminnot:

 * Ohjelman suoritus 
 
   ```mvn compile exec:java -Dexec.mainClass=snakegame.Main```
   
   
* Testaus 

   ```mvn test```
   
   
* Testikattavuusraportti (raportti löytyy avaamalla target/site/jacoco/index.html)

   ```mvn test jacoco:report```
   
   
* Jar ohjelman luominen 

   ```mvn package```
   
   
* Jar ohjelman suorittaminen target-kansion sisällä 

   ```java -jar Snakegame-1.0-SNAPSHOT.jar```
   
   
* Checkstyle

   ```mvn jxr:jxr checkstyle:checkstyle```
