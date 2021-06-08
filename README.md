[![Build Status](https://github.com/nimarion/impfensaarland-bot/workflows/Build/badge.svg?branch=main)](https://github.com/nimarion/impfensaarland-bot/actions)
[![GitHub contributors](https://img.shields.io/github/contributors/nimarion/impfensaarland-bot.svg)](https://github.com/nimarion/impfensaarland-bot/graphs/contributors/)
[![GitHub Repo stars](https://img.shields.io/github/stars/nimarion/impfensaarland-bot?style=social)](https://github.com/nimarion/impfensaarland-bot/stargazers)
[![Selenium](https://img.shields.io/badge/Selenium-green.svg?style=flat&logo=Selenium&logoColor=white)](https://www.selenium.dev/)


# ImpfenSaarland - Bot

> Vor der Nutzung der Anwendung sollte man sich über das Impfportal zur Impfung registriert haben. Die Anwendung realisiert nur die Suche nach einem schnelleren Termin (1-7 Tage). Die Registrierung muss manuell über das Portal durchgeführt werden. 

Ermöglicht das automatisierte Suchen nach kurzfristig freigewordenen Nachrückterminen auf dem [saarländischen Impfportal](https://www.impfen-saarland.de/). Das Programmt sucht an den möglichen Impfzentren nach freien Terminen und wählt diese sofort aus wenn ein neuer Termin freigeworden ist. Der Nutzer bekommt dann eine Benachrichtung per Telegram, über einen freien Termin.
Im Schnitt benötigt die Anwendung 30 Minuten für einen freien Termin. Die Geschwindigkeit hängt jedoch von Wochentag und Tageszeit ab!

![Telegram](https://i.imgur.com/6EhRz0Q.png)

Am Computer kann der Nutzer dann seine Telefonnummer/Mail angeben um einen Code zu erhalten, mit dem man den Termin final bestätigen muss. 

[Ablauf](https://i.imgur.com/ZVTV4K4.png)

## Benutzte Software

- [Maven](https://maven.apache.org/)
- [Selenium](https://www.selenium.dev/)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
- [Java Telegram Bot API](https://github.com/pengrad/java-telegram-bot-api)

Inspiriert von https://github.com/TobseF/impf-bot und https://github.com/alfonsrv/impf-botpy
