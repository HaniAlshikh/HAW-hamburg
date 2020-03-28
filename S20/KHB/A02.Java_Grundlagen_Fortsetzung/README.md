Professor:: Dr. Bernd Kahlbrandt  
Author:: Hani Alshikh  
<div style="text-align: right">28.03.2020</div>

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

eine Komplexe Zahl kann sowohl aus den Koordinaten als auch einem Objekt der Klasse Cartesian/Polar erzeugt.

die Berechnung der Komplexen Zahlen erfolgt nach einem bestimmten genauigkeitsgrad, der man definieren kann. By default hat er den Wert 6 (also 6 nachkomme Zahlen).

beim addieren, subtrahieren und multiplizieren werden die kartesische Koordinaten und beim Dividieren die Polarkoordinaten benutzt, da dies jeweils einfache und genauere Formeln anbieten.


##### Quellen
- 