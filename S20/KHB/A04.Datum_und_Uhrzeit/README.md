Professor: Dr. Bernd Kahlbrandt  
Author: Hani Alshikh
<div style="text-align: right">28.04.2020</div>

# A04: Datum und Uhrzeit

## 1.2. Static Methoden durch Instanzen aufrufen

- 1.2.1. Was passiert, wenn Sie dieses Programm ausführen?

    "Hello world!" wird ausgegeben.

- 1.2.2 Wie kommt die Ausgabe zustande?

    wie schon in der letzten Aufgabe erwähnt ist null der default Value aller Referenz Typen in Java und somit ist es auch erlaubt instanzen mit null zu erzeugen.  
    durch das Casting wird eine Instanz von der Klasse Null erzeugt.  
    das Aufrufen von Klassen Methoden durch eine Instanz der Klasse ist erlaubt, da der Kompiler der Aufruf zu Klasse.staticMethode() übersetzt.  
    z.B.:
    
    ```java
    ((Null) null).greet(); // -> Null.greet();
    Null test = null;
    test.greet(); // -> Null.greet();
    Null myVar = new Null();
    myVar.greet(); // -> Null.greet();
    ```
  
- Warum „tut man das nicht“? Nur weil man dickfällig genug ist, Compiler- Warnungen zu ignorieren?

    weil das sehr verwierend ist, besonders für Fremdleser und gegen der Konvention. Static Methode sollten durch die Klasse aufgerufen werden und nicht durch die Instanzen.

##### Quellen

- [Java Docs](https://docs.oracle.com/javase/8/docs/api/index.html)
- [Introduction to the Java 8 Date/Time API](https://www.baeldung.com/java-8-date-time-intro)
- [How to convert a string to date in Java](https://attacomsian.com/blog/java-convert-string-to-date)
- [Effektiver Jahreszins](https://de.wikipedia.org/wiki/Effektiver_Jahreszins#Berechnung_des_eff._Jahreszinssatzes_bei_Anleihen)
- [Beispiel für flexible Zahlung](https://www.zinsen-berechnen.de/darlehensrechner.php?paramid=gdl8e53adb)
- [überprüfen von Ergebnisse](https://www.finanzen-rechner.net/kreditrechner.php)
- [Java 8 DateTimeFormatter](https://howtodoinjava.com/java/date-time/java8-datetimeformatter-example/)
- [Java date time in arabic](https://stackoverflow.com/questions/19923498/java-date-time-in-arabic)
- [Java SimpleDateFormat – Java Date Format](https://www.journaldev.com/17899/java-simpledateformat-java-date-format)