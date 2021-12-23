# Käyttöohje

Lataa aluksi tiedosto [Yatzy-game-1.0-SNAPSHOT.jar](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/releases/tag/Loppupalautus)

## Ohjelman käynnistys

Käynnistääksesi ohjelman, mene kansioon johon olet tallentanut jar-tiedoston, ja käytä komentoa
```
java -jar Yatzy-game-1.0-SNAPSHOT.jar
```

## Alkuvalikko

Kun ohjelma avataan, se kysyy ensimmäisessä valikossa peliin osallistuvien pelaajien määrää (1-4). Käyttäjän tulee valita oikea pelaajamäärä painamalla pelaajien lukumäärää vastaavasta napista.

## Pelin pelaaminen

### Pelin säännöt ovat seuraavat:

- Vuorollaan pelaaja heittää viittä noppaa yhdestä kolmeen kertaa.
- Ensimmäisellä kerralla on heitettävä kaikki viisi noppaa ja seuraavilla kahdella kerralla pelaaja saa valita kuinka monta noppaa hän heittää, jos hän ylipäänsä heittää.
- Kun pelaaja on tyytyväinen heittämäänsä tulokseen, tai hänen heittonsa loppuvat kesken, tulee pelaajan asettaa heittämänsä tulos johonkin kohtaan tulostaululla.
- Mikäli tulos ei sovi valittuun kohtaan, asetetaan siihen 0 pistettä. Pisteiden asettamisen jälkeen vuoro siirtyy seuraavalle pelaajalle.
- Pelaajat jatkavat pelaamista kunnes jokainen pelaaja on täyttänyt oman pistetaulukkonsa.

### Kategorioihin sopivat tulokset

- Yläkertaan (ennen yht. ja bonus kenttiä) heitetään kuhunkin kohtaan yhtä nopan silmälukua mahdollisimman suuri määrä. 
  - Esim jos pelaaja heittää neljä kolmosta ja yhden kakkosen, hän voi asettaa tuloksensa kohtaan 3 ja saa pisteitä 4 * 3 = 12p.
- Pelaaja saa bonuksen (= 50p), jos hän heittää yläkerrasta tulokseksi 63p tai yli. 

Alakertaan Heitetään järjestyksessä ylhäältä alas tulokset:
- yksi pari
- kaksi paria
- kolmiluku
- neliluku
- pieni suora
- suuri suora
- mökki
- sattuma
- yatzy

Tuloksia laskiessa jokaisen heitetyn nopan silmäluku lisätään yhteen poikkeuksena yatzy, jossa silmälukujen summaan lisätään vielä 50 pistettä.

## Pelin loppuminen

Peli loppuu kun jokainen pelaaja on täyttäny pistetaulukkonsa. Tämän jälkeen pelaajien nähtäväksi avautuu ikkuna, jossa on näkyvillä pelaajien loppupisteet ja pelin voittaja.
