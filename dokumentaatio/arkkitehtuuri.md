
# Arkkitehtuuri

## Rakenne

Ohjelman rakenne on seuraavan näköinen.

![imgage](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/blob/master/dokumentaatio/kuvat/pakkauskaavio.jpg)

## Käyttöliittymä

Pelin käyttöliittymässä on kolme erilaista näkymää.

Tervetulonäkymä:
- Näkymässä kysytään kuinka monta pelaajaa pelaa peliä (1-4).
- Pelaaja voi nappia painamalla valita pelaajien määrän.

Pelinäkymä:
- Näkymässä on
  -  pelaajien määrän mukaan alustettu pistetaulukko
  -  Tietolaatikko, jossa kerrotaan tietoa pelin tilasta
  -  Nopat, sekä niiden hallinnointiin käytettävät napit

Lopetusnäkymä:
- Näkymässä näytetään
  - kunkin pelaajan pistetulos
  - Kuka voitti pelin

Käyttöliittymä on suurimmilta osin eriytetty sovelluslogiikasta. Käyttöliittymä saa sovelluslogiikalta nappien tilat ja pelaajien pisteet ja päivittää näiden perusteella pistetaulukkoa ja noppien tilaa. Käyttöliittymä on luokassa  [yatzy.ui.Yatzyui](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/blob/master/Yatzy-game/src/main/java/yatzy/ui/YatzyUi.java).

## Sovelluslogiikka

Sovelluslogiikka koostuu neljästä luokasta
- [yatzy.domain.Category](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/blob/master/Yatzy-game/src/main/java/yatzy/domain/Category.java)
- [yatzy.domain.Dice](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/blob/master/Yatzy-game/src/main/java/yatzy/domain/Dice.java)
- [yatzy.domain.Game](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/blob/master/Yatzy-game/src/main/java/yatzy/domain/Game.java)
- [yatzy.domain.Player](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/blob/master/Yatzy-game/src/main/java/yatzy/domain/Player.java)

Category on enum-luokka, jossa määritetään pisteisiin liittyvät kategoriat.

Dice luokassa on toteutettu noppien heittämiseen liittyvä toiminnallisuus.

Player luokassa luodaan pelaaja-oliot joiden tehtäviin kuuluu pääasiassa pisteiden tallennus.

Game luokka vastaa pelin logiikasta.


## Päätoiminnallisuudet

- Sekvenssikaavio nopan heittämisestä

![image](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/blob/master/dokumentaatio/kuvat/sekvenssikaavio1.jpg)

- Sekvenssikaavio pisteiden lisäämisestä

![image](https://github.com/olevaltt/ot-harjoitustyo_syksy_2021/blob/master/dokumentaatio/kuvat/sekvenssikaavio_pisteiden_tallennus.jpg)
