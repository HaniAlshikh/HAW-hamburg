Professor: Dr. Bernd Kahlbrandt  
Author: Hani Alshikh
<div style="text-align: right">20.04.2020</div>

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
    test.greet(); // -> Null.greet()
    Null myVar = new Null();
    myVar.greet(); -> Null.greet();
    ```
  
- Warum „tut man das nicht“? Nur weil man dickfällig genug ist, Compiler- Warnungen zu ignorieren?

    weil das sehr verwierend ist, besonders für Fremdleser und gegen der Konvention. Static Methode sollten durch die Klasse aufgerufen werden und nicht durch die Instanzen.

##### Quellen
- [Understanding null in Java](https://dev.to/dj_devjournal/understanding-null-in-java-4o31)
- [Java – static variable with example](https://beginnersbook.com/2013/05/static-variable/)
- [static keyword in java](https://www.geeksforgeeks.org/static-keyword-java/)