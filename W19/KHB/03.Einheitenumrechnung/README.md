Professor:: Dr. Bernd Kahlbrandt  
Author:: Nick Marvin Rattay  
Author:: Hani Alshikh  
<div style="text-align: right">27.10.2019</div>

# Einheiten umrechnen

Dieses Programm wurde möglichst objektorientiert entworfen. Eine Übersicht der Struktur kann man vom folgenden Diagramm entnehmen  

![Einheitenumrechenr UML Diagram](UML/Einheitenumrechner.png)


### 01. Leistung

Das Programm kann zwischen den genannten Einheiten umrechnen und anhand von Nutzereingaben oder eines vorgegebenen Werts runden und diese angemessen darstellen.

Die Einheiten werden aus einer YAML Datei gelesen und in einem separaten Modul verarbeitet, welches die Erweiterbarkeit sehr erleichtert.

Es wird angenommen, dass die YAML Datei richtig formatiert und angemessen erweitert, da es keine Angaben diesbezüglich gab. Deswegen wurde auch das Error handling nicht betrachtet.

### 02. Eingabe

Die Eingabe erfolgt über die Konsole. Es wurde ein relativ intoleranter Input handler verbaut, der sicherstellt, dass der Nutzer die richtigen Eingaben macht, ohne ihm das Leben schwer zu machen oder das ganze Programm zu brechen.


### 03. Werten mit zugehörigen Einheiten

Verschiedene Optionen der Eingaben sind dem Nutzer gestellt, davon die Möglichkeit numerische Werte mit zugehörigen Einheiten und einer Ausgabeeinheit einzugeben.

### 04. Ausgabe

Ausgegeben wird sowohl das Ergebnis jeder Umrechnung direkt nach der Eingabe, als auch eine tabellarische Formatierung der gesamten Umrechnung ganz am Ende.

### 05. Geeignete Umrechnungen

Bei der Eingabe wird wie erwähnt solange nach der richtigen Eingabe gefragt, bis sie die Voraussetzungen erfüllt. Darunter fällt die Sicherstellung, dass nur geeigneten Einheiten eingegeben werden, die untereinander umgerechnet werden können. 

### 06. Testfälle

Die Hauptfunktion des Programms wird getestet und dafür wurden Testfälle erstellt. Testfälle der Umrechnung der jeweiligen Einheiten, als auch mögliche Nutzereingabe, sind jeweils in einer Testklasse zu finden. 

Testfälle für die Struktur und die einzelnen Methoden des Programms, wurden nicht geschrieben, da dies nicht Teil der Aufgabe war.  

##### Quellen
- [Ruby DOC](https://ruby-doc.org)
- [Potrzebie unit system (Names)](https://tex.stackexchange.com/questions/369070/can-one-use-the-potrzebie-unit-system-in-latex)
- [Potrzebie System of Weights and Measures](https://groups.google.com/forum/#!topic/rec.humor/Emh_2wOsDtA)
- [Stdin trick for ruby testing](https://stackoverflow.com/questions/16948645/how-do-i-test-a-function-with-gets-chomp-in-it)
- [Google's Unit Converter](https://www.google.com/search?q=unit+converter&oq=unit+conver&aqs=chrome.3.69i59l3j0j69i60l2.6186j0j1&sourceid=chrome&ie=UTF-8)
- [Wolfram alpha](https://wolframalpha.com)

