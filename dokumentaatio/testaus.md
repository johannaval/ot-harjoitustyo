
# Testausdokumentti

Ohjelman toimintaa testaa JUnit-testit, jotka koostuvat yksikkö- sekä integraatiotesteistä. Ohjelman toimintaa järjestelmätasolla on testattu myös manuaalisesti koko kehityksen ajan.


## Yksikkö- ja integraatiotestaus

#### PlayerSQL (pysyväistallennuksesta vastaava luokka): 

Luokkaa on testattu config.properties tiedostossa olevalla omalla tietokannalla juuri JUnit-testejä varten. Näin tietokannan testaus ei sekoita ohjelman käyttämän tietokannan tietoja. JUnit-testit testaajat kaikkia PlayerSQL:n metodeja


#### Sovelluslogiikka:

Pelilogiikkaa testataan eri testiluokilla, pääosin yksikkötestien avulla.
(Area -> AreaTest, Food -> FoodTest, GameService -> GameServiceTest, SnakeHead -> SnakeHeadTest, SnakePart -> SnakePartTest)
Jokaisella testiluokalla pyritään testaamaan oleellisimpia ja tärkeimpiä asioita kyseisen luokan metodeista.

PlayerService sekä Player-luokkien metodeja on testataan FakePlayerSQL-luokan avulla. Metodit eivät testaa tiedontalletukseen rajapinnan toteuttavaa luokkaa PlayerSQL, vaan tietojen talletuksen testaamiseen käytetään rajapinnan toteuttavaa  FakePlayerSQL luokkaa.
Tietokantataulun sijaan siellä käytetään players nimistä ArrayListia, johon talletetaan Player-tyyppiset pelaajat. Näin kaikki luokan metodit ovat käytettävissä, mutta ne ei sekoita itse ohjelman käytössä olevaa tietokantataulua. 


#### Testikattavuus:

<img src="https://github.com/johannaval/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot%20from%202020-05-08%2015-37-42.png" width="700" height="80">

Testeihin ei ole otettu mukaan käyttöliittymästä vastaavaa pakkausta Snakegame.Ui. 
Sovelluksen rivikattavuus on 85% ja haarautumiskattavuus 80%.

Koska Domain luokkaan liittyy jonkin verran JavaFX-elementtejä, niiden testaus oli hankalaa, joka myös vähentää testikattavuutta. Oleellisimmin testikattavuutta vähentää se, etten saanut GameServicen AnimationTimeria testattua, sekä Arean
metodia setTheme. SetTheme-metodi asettaa pelialueelle teeman, ja koska teemat ovat täysin JavaFX:n elementtejä, oli niiden testaus itselleni hankalaa. Pyrin kuitenkin testaamaan kaikki peliin oleellisesti vaikuttavat asiat, mm. pisteiden kerääntymisen, madon liikkumisen, pelin päättymisen ja ruokian lisäämisen. 

Testeistä jäi uupumaan myös PlayerSQL luokan metodeista mahdollisten SQL-poikkeuksien takia tulevien virheviestien  testaaminen.

Kuitenkin kaikkia näitä testeistä uupuneita kohtia on testattu manuaalisesti, jolloin virheitä ei ole havaittu. Esimerkiksi pelin taustat ja ruoan värit vaihtuvat oikein sen mukaan, minkä teeman pelaaja on valinnut, AnimationTimer toimii, sekä PlayerSQL luokka tulostaa kuvaavat virheviestit, mikäli SQLException ilmenee.


## Järjestelmätestaus

Testikattavuudesta uupuneet kohdat sekä käyttöliittymästä vastaavan pakkauksen UI luokat testattu manuaalisesti. 
 
 
## Asennus ja konfigurointi

 Sovellusta on testattu käyttöohjeen tavalla Linux-ympäristössä. Kuten käyttöohjeissa kerrotaan, tulee ohjelman suoritushakemistossa olla config.properties tiedosto, jotta ohjelma toimii oikein. 
 Sovellusta on testattu myös ilman, että config.properties tiedostoa on suoritushakemistossa. Silloin ohjelma ei
 löydä config.properties tiedostoa, eikä käyttö toimi. Sovellus kyllä avautuu, mutta komentokenttään tulostuu virheviestejä, ettei konfigurointi ole onnistunut.
 
 
 ## Toiminnallisuudet
 
 Kaikkia määrittelydokumentin ja käyttöohjeen toiminnallisuuksia on testattu, ja pyritty estämään mahdollisten virheellisten
 syötteiden antaminen (kuten liian lyhyt käyttäjänimi tai salasana, tyhjät kohdat ja teeman valitsematta jättäminen).
 
 
 ## Sovellukseen jääneet laatuongelmat
 
 Jos config.properties kansiota ei ole ladattu, tai se ei ole ohjelman suoritushakemistossa, ei sovellus löydä sitä eikä ohjelma toimi oikein.
 Myöskään graafisesti (tuplaklikkaamalla jar-tiedostoa) jar ei löydä config.properties tiedostoa, eikä toimi oikein.
 Komentokentän kautta JAR-ongelmaa ei ole, mikäli tiedosto löytyy suoritushakemistosta. 
 
 Pelatessa peliä mato pystyy menemään osittain itsensä päälle, sekä hyvin nopealla vauhdilla mato välillä pysähtyy.
 Mato kuitenkin jatkaa matkaansa pienen hetken kuluttua siitä pisteestä, mihin se pysähtyikin, eikä hyppää kauemmas.
 Siksi se ei automaattisesti esim. törmää seinään ja peli lopu. 
