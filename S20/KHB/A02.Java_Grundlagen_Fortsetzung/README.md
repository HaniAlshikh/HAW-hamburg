Professor: Dr. Bernd Kahlbrandt  
Author: Hani Alshikh
<div style="text-align: right">04.04.2020</div>

# A01: Java Grundlagen Forsetzung

## 1. Java Denksportaufgaben

### 1.1 fehlender Hash Methode

es wird false ausgegeben, da die hash Methode nicht überschrieben wurde.

für eine verbesserte Implementierung sollte man die Methode ```hashCode()``` überschreiben, wie folgt ist ein Beispiel.

### 2.2. equals überladen

es wird wieder false ausgegeben, da die equals Methode flasch implementiert ist. die Methode wird in diesem Fall überladen und gar nicht für den Vergleich benutzt, sondern nur die die im Objekt ist.

Zum Verbessern, sollte man den Parameter typ mit ```Objekt``` ersetzen und mindestens prüfen, dass der zu vergleichenden Objekt die Methode ```first()``` und ```last()``` hat.

## 2. Klasse Complex

![UML Diagram](UML/java_grundlagen.svg)

Eine Komplexe Zahl kann sowohl aus den kartesischen Koordinaten als auch den polaren erzeugt werden.

##### Quellen
| - [Komplexe Zahl](https://de.wikipedia.org/wiki/Komplexe_Zahl) | - [komplexe-zahlen](https://www.mathebibel.de/komplexe-zahlen) | - [Correct way to compare floats or doubles in Java](https://howtodoinjava.com/java/basics/correctly-compare-float-double/) | - [Rechner](https://keisan.casio.com/exec/system/1223527679) |