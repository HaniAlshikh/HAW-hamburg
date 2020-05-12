Professor: Dr. Bernd Kahlbrandt  
Author: Hani Alshikh
<div style="text-align: right">11.05.2020</div>

# A04: Sreams

## 1. Denksportaufgaben

### 1.1. Positive Random Number

Zu erwarten sollte eine zufällige Zahle zwischen 0 und die Grenze ```n```. Also keine negative Zahlen.

- Wie macht man das besser?

    Indem man auf 0 und Integer.MIN_VALUE prüft und entsprechend reagiert.
    
- Welche Fehler sind hier gemacht worden?
    
    1. n könnte 0 sein, aber Zahl % 0 ist nicht erlaubt ```ArithmeticException: / by zero```
    
    2. ```nextInt()``` ohne Parameter gibt alle Zahlen zwischen ```Integer.MIN_VALUE``` bis ```Integer.MAX_VALUE``` inklusiv, 
    allerdings gibt ```Math.abs``` nicht immer positive Zahlen zurück. Z.B. bei ```Integer``` ist der lower-bound -2^32 aber der upper-bound 2^32 - 1. D.h. es gibt keine positive Darstellung für -2147483648 deswegen gibt ```Math.abs()``` in diesem Fall die Zahl selbst zurück, also negative Zahl.
    
- Wie macht man das richtig?
      
    Indem man die Grenze ```n``` der Methode ```nextInt()``` direkt übergibt und ```Math.abs()``` weglässt. Allerdings darf ```n``` nicht gleich 0 sein, denn zwischen 0 und sich selbst keine Zahlen gibt, also kein "nextInt".

- Wie ist die aktuelle Empfehlung (seit ca. 10 Jahren), wie Sie das machen sollten?

    Leider sind wir darauf nicht gekommen.

### 1.2. Boxing conversion

- Was fällt Ihnen an der folgenden Methode auf?
    
    == prüft auf identität nicht auf Gleichheit. Bei Zahlen sollte das kein Problem sein aber anscheinend doch.
    
- Ist sie korrekt?

    Korrekt ist sie nicht, allerdings nach unsere tests bei verschiedenen Szenarien hat sie immer funktioniert.

- Wenn nicht, was ist falsch?

    Nach Java Documentation
    
    > "If the value p being boxed is the result of evaluating a constant expression (§15.29) of type boolean, byte, char, short, int, or long, and the result is true, false, a character in the range '\u0000' to '\u007f' inclusive, or an integer in the range -128 to 127 inclusive, then let a and b be the results of any two boxing conversions of p. It is always the case that a == b."
    
    das heißt, sowohl bei == als auch bei > gibt die Methode 1 zurück. Das führt dazu die Zahlen zu vertauschen, auch wenn es nicht notwendig ist. Deswegen funktioniert die Methode trotz der falschen implementierung.
    
    ```java
    System.out.println(naturalOrder().compare(130, 128)); // -> 1, wie erwartet
    System.out.println(naturalOrder().compare(128, 128)); // -> 1, erwartet ist 0
    ```

- und wie macht man das richtig?

    die Arbeit entsprechend and die CompareTo Methode delegieren, allerdings ist die Methode in diesem Fall überflüssig, da Integeres schon Natural Order haben.

## 2. Lambdas und Streams

## 2.4.2. Functional Interface 

- Können Sie das auch mit einem funktionalen Interface aus java.util erreichen? Welches Interface eignet sich ggf. am besten?

    Ja, ```BiPredicate<T,U>```

##### Quellen

- [Count word in file Java8 using reduce method](https://stackoverflow.com/questions/49043471/count-word-in-file-java8-using-reduce-method)
- [Efficient Word Frequency Calculator in Java](https://www.baeldung.com/java-word-frequency)
- [Hierarchy For Package java.util.function](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-tree.html)
- [How to combine all predicates from List<Predicate<MyClass>>](https://stackoverflow.com/questions/47424197/how-to-combine-all-predicates-from-listpredicatemyclass)
- [Java Math.abs random vs. nextInt()](https://stackoverflow.com/questions/39176165/java-math-abs-random-vs-nextint)
- [How to compute absolute value of signed random integer](https://stackoverflow.com/questions/23435875/how-to-compute-absolute-value-of-signed-random-integer)
- [Primitive Type Streams in Java 8](https://www.baeldung.com/java-8-primitive-streams)
- [Java – Generate random integers in a range](https://mkyong.com/java/java-generate-random-integers-in-a-range/)
- [Stream builder() in Java with Examples](https://www.geeksforgeeks.org/stream-builder-java-examples/)
- [Boxing Conversion](https://docs.oracle.com/javase/specs/jls/se14/html/jls-5.html#jls-5.1.7)