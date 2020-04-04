Professor: Dr. Bernd Kahlbrandt  
Author: Mateusz Chylewski  
Author: Hani Alshikh
<div style="text-align: right">04.04.2020</div>

# A01: Java Grundlagen Initialisierung

## 1. Java Denksportaufgaben

### 1.1. Referenz Typen und null

null ist ein Literal, das unter anderem als default value für Referenz Typen benutzt wird.

Da Object und Array Referenz Typen sind, und Array unter Object in der Hierarchy steht, wird die zweite Überladung des Konstruktors benutzt. Hätte man z.b. einen primitiven Typ benutzt, würde die erste Überladung benutzt werden.

In der Regel will man null als parameter nicht haben, deswegen wäre eine Verbesserung für diesen Fall, ein Exception zu werfen.

### 1.2. static Reihenfolge und default value

static Variablen werden werden beim Laden der entsprechenden Klasse initialisiert und haben die gleicht default values wie die instanz Variablen. Die werden aber auch nach Reihenfolge ausgeführt. Und da die Erzeugung der Instanz zuerst passiert hat CURRENT_YEAR bei default den Wert 0 und somit ist -1930 zu erwarten.

um auf das "erwartete" Ergebnisse zu kommen, sollte man die Reihnfolge entsprechend so ändern, dass CURRENT_YEAR den benötigten Wert zuerst bekommt und dann die Erzeugung der Instanz, wie folgt:

```java
private static final int CURRENT_YEAR = LocalDate.now().getYear();
public static final Elvis INSTANCE = new Elvis();
```

##### Quellen
- [Understanding null in Java](https://dev.to/dj_devjournal/understanding-null-in-java-4o31)
- [Java – static variable with example](https://beginnersbook.com/2013/05/static-variable/)
- [static keyword in java](https://www.geeksforgeeks.org/static-keyword-java/)