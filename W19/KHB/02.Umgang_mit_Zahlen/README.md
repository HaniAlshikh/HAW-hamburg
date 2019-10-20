Professor:: Dr. Bernd Kahlbrandt  
Author:: Nick Marvin Rattay  
Author:: Hani Alshikh  
<div style="text-align: right">19.10.2019</div>

# Umgang mit Zahlen

Nach Analyse der Aufgabenstellung und deren Voraussetzungen wurde die Entscheidung getroffen, ein Modul zu benutzen, welches die Lesbarkeit verbessert. Dies ist in modules/formula.rb zu finden. Der Script zum durchführen ist im Hauptordner/numbers_run.rb zu finden.

Um die Lösung nicht unnötig komplexer zu machen, wurde die User-Eingabe und das Error handling nicht betrachtet, da dies auch nicht Teil der Aufgabe ist.    

### 01. & 02. Summe und Produkt Methoden

Die Methode inject wurde für die Berechnung benutzt, da dies die Möglichkeit bietet, einen Startwert für die Berechnung elegant einzugeben, statt zu prüfen, ob es einen Startwert gibt, in Fällen wie Z.B. leerer Array. 

Fälle wie Array von Arrays, Hashes usw., werden ignoriert. Und der Startwert 0 bzw. 1 wird zurückgegeben. Das kann gefährlich sein, aber aufgrund der fehlenden Eingabe bei solchen  Situationen wurde dies nicht betrachtet.

### 03. Arbeiten mit Folgen von Zahlen

#### 01. Collatz-Folge

Die Berechnung der Collatz-Folge wird basierend auf einen Startwert solange durchgeführt, bis ein Zyklus zu erkennen ist. 
Dafür wurde der resultierende Array aus der Differenz zwischen dem Sequenz- und Zyklus-Array auf die Leerheit geprüft.

Der Vorteil dieser Prüfung ist, dass man beliebige Zyklus Arrays benutzen könnte.

#### 02.03. Approximationsreihen

Für beide Approximationsreihen wird anhand der Differenz zwischen der letzten zwei Approximationen und dem Wert des Epsilons festgestellt, ob die gewünschte Genauigkeit erreicht wurde.  


#### 04. Die russische Bauern

Hiefür wurde zwei dimensionale Array benutzt um einige Zeilen Code zu sparen. Allerdings könnte man auch zwei Arrays für beide Spalten benutzen.

Extra:
Man kann die absoluten Werte der eingegebenen Zahlen nehmen und am Ende die Summe mit -1 multiplizieren, falls nur einer der Zahlen negativ ist, ansonsten mit +1. Dadurch wurden auch die negativen Zahlen unterstützt.

#### Quellen:
- https://ruby-doc.org
- https://en.wikipedia.org/wiki/Collatz_conjecture
- https://de.wikipedia.org/wiki/Russische_Bauernmultiplikation
