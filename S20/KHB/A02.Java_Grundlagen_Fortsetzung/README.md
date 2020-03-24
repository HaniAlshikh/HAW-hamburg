Professor:: Dr. Bernd Kahlbrandt  
Author:: Hani Alshikh  
<div style="text-align: right">21.03.2020</div>

# A01: Java Grundlagen Forsetzung

## 1. Java Denksportaufgaben

### 1.1 Fehlender Hash Methode

warum es zu der Ausgabe kommt?
es wird false ausgegeben, da die hash Methode nicht überschrieben wurde.

für eine korekte Impliementierung sollte man die Mathode überschreiben, wie folgt ist ein Beispiel

```java
public int hashCode() {
    return 31 * first.hashCode() + last.hashCode();
}
``` 

##### Quellen
- [What is a NullPointerException, and how do I fix it?](https://stackoverflow.com/questions/218384/what-is-a-nullpointerexception-and-how-do-i-fix-it)
- [Kredit Rechner](https://www.finanzen-rechner.net/kreditrechner.php)
- [PAngV](http://www.gesetze-im-internet.de/pangv/anlage.html)
- [Bisection method](https://en.wikipedia.org/wiki/Bisection_method)
- [Effektiver Jahreszins](https://de.wikipedia.org/wiki/Effektiver_Jahreszins)