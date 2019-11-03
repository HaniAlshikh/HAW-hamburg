Professor:: Dr. Bernd Kahlbrandt  
Author:: Nick Marvin Rattay  
Author:: Hani Alshikh  
<div style="text-align: right">03.11.2019</div>

# Mastermind

Das Spiel Mastermind wurde hier simuliert. Der Nutzer kann sowohl der CodeMaker als auch der CodeBreaker sein.
Es wurden Buchstaben als Farben benutzt. Bei der Bewertung wird B für die direkten Treffer und W für die indirekten Treffer benutzt.
Der Nutzer kann zwischen Knuth's Algorithmus und Mastermind basic AI auswählen.

## Interaktion

Alle Interaktionen passieren über die Konsole.

- Es wird sichergestellt, dass alle Ein- und Ausgabe richtig sind
- Der Nutzer kann so viel spielen, wie er mag und wird danach gefragt
- Der Nutzer hat die Wahl die Konfigurationen vor jedem Spiel zu ändern
- Der Nutzer kann auswählen, gegen welchen Algorithm er spielt
- Es wird am Ende eine Zusammenfassung aller Spiele gezeigt

## Algorithmus/KI

es wird eine basic KI geschrieben die in ~ 12.5 Versuche, den Code erraten.
<p float="left">
    <img src="graph/Mastermind AI.svg" alt="Tests Result" width="320"/>
    <img src="graph/Mastermind AI Guesses Average.svg" alt="Average" width="320"/>
</p>

Knuth's Algorithmus wurde auch dank [zebogen](https://github.com/zebogen) implementiert 

<div style="page-break-after: always;"></div>

### Struktur

Eine Übersicht der Struktur kann man vom folgenden Diagramm entnehmen

![UML Diagram](UML/Mastermind.svg)

##### Quellen
- [Ruby DOC](https://ruby-doc.org)
- [Mastermind Wiki](https://en.wikipedia.org/wiki/Mastermind_(board_game)#Five-guess_algorithm)
- [method generator trick (config.rb)](https://stackoverflow.com/questions/14257979/create-multiple-identical-methods-in-ruby)
- [Beating Mastermind](https://github.com/agfor/talks/tree/master/beating_mastermind)
- [Mastermind-rb](https://github.com/zebogen/mastermind-rb/blob/master/mastermind.rb)
- [RubyConf 2018 - Beating Mastermind: Winning with the help of Donald Knuth by Adam Forsyth](https://www.youtube.com/watch?v=Okm_t5T1PiA&t=1423s)
- [efficient solutions for mastermind using genetic algorithm](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.496.276&rep=rep1&type=pdf)