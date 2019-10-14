Professor:: Dr. Bernd Kahlbrandt  
Author:: Nick Marvin Rattay  
Author:: Hani Alshikh  
<div style="text-align: right">13.10.2019</div>

# Umgang mit Strings

Nach Analyse der Aufgabenstellung und deren Voraussetzungen wurde die Entscheidung getroffen, keine Klassen zu benutzen.

## 01. Aufruf von Methoden

Für diesen Teil der Aufgabe verfügt die Klasse String auf die benötigten Methoden, um die Aufgaben zu erledigen, wie in dem Script "part_01.rb" zu entnehmen.

Um einen String umzudrehen, kann man beispielsweise die Methode .reverse benutzen.

Bei den ersetzen Aufgaben wurde die Methode .gsub benutzt. Man könnte auch die Methode .replace benutzen, allerdings bietet die Methode .gsub mehr Flexibilität dank der Nutzung von Regex. 

## 02. Einlesen und Verarbeiten von Textdateien

Bei dieser Aufgabe wird mehr Logik benötigt, allerdings nicht so viel, dass man es in Methoden unterteilt.

Error handling wurde hier auch nicht betrachtet, da dies nicht Teil der Aufgabe ist.

Bei der ersten Teilaufgabe wurde wie vorausgesetzt File.open mit der Übergabe in einen Block benutzt, man könnte sich auch ein paar Code Zeilen sparen und IO.read bzw. File.read benutzen.


Das Regex /[:alpha:]/ wurde in beiden Aufgaben anstatt  /\w+/ benutzt, da es nicht ASCII Buchstaben wie die Umlaute unterstützt.


### Quellen:
- https://ruby-doc.org
