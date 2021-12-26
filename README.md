# Yatzy-game

Tällä sovelluksella voi pelata noppapeli Yatzya "hot-seat"-muotoisesti 1-4 pelaajan kanssa.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/blob/master/dokumentaatio/kayttoohje.md)

[Testausdokumentti](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/blob/master/dokumentaatio/testaus.md)

## Releaset

[viikko 5](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/releases/tag/viikko5)

[Loppupalautus](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/releases/tag/Loppupalautus)

## Ohjeet ja komentorivikomennot
- Jar-tiedoston luonti onnistuu komennolla
  - mvn package
  - tiedosto Yatzy-game-1.0-SNAPSHOT.jar generoituu kansioon target
  - tiedoston voi ajaa komennolla 
    - java -jar Yatzy-game-1.0-SNAPSHOT.jar

- Testit voi ajaa komennolla mvn test

- Testikattavuusraportin voi generoida komennolla
  - mvn test jacoco:report
    - Raportti generoituu nimellä index.html kansioon jacoco, joka löytyy kansiosta site, kansion target sisältä.
- Checkstyletarkastuksen voi suorittaa komennolla 
  - mvn jxr:jxr checkstyle:checkstyle
    - checkstyle.html niminen raportti generoituu kansioon site, kansion target sisälle.
