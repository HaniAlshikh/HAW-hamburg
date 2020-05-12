Professor: Dr. Bernd Kahlbrandt  
Author: Hani Alshikh
<div style="text-align: right">02.05.2020</div>

# A05: Generics

## 1. Java Denksportaufgaben

### 1.1. Integer Overflow and Underflow

Die Compare Methode erwartet eine positive Zahl, wenn x > y, negative, wenn x < y und 0 wenn x = y.  

Um die Reihenfolge zu vertauschen, vertauscht man einfach x mit y

- 1.1.1. Was gibt diese Program aus?

    UNORDERED

- 1.1.2. Wie kommt die Ausgabe zustande?

    da die Compare Methode die Zahlen einfach subtrahiert, kann es bei Zahlen, deren Differenz größer ```Integer.MAX_VALUE``` 2^31 - 1 oder kleiner ```Integer.MIN_VALUE``` -2^31 zu Overflow bzw. Underflow kommen.

- 1.1.3. Wie machen Sie das richtig?

    zu erwarten sollte DESCENDING sein.

    Man könnte entsprechend if bedienungen schreiben, oder besser die Arbeit an die compareTo Methode der Integer Klasse delegieren.

### 1.2. Raw Types

- 1.2.1 Was gibt das folgende Programm aus?

    Erwartet sollte ```23 skidoo``` ausgegeben werden, allerdings ist das Program noch nicht compile-fähig

- 1.2.2. Ist es überhaupt compile-fähig?

    Nein, denn String und Object nicht der selbe Typ sind.

<div class="page"/>

- 1.2.3. Was ist an diesem Code ggfs. verbesserungswürdig?

  - ```Pair p = new Pair<Object>(23, "skidoo");```  
        Da der Type-Erasure beim Kompilieren den daten Typ T der Klasse Pair löscht/mit Objekt ersetzt wird auch die Rückgabe der Methode stringList() gelöscht/mit Objekt ersetzt (obwohl das nichts mit T zu tun hat). Somit wäre eine Lösung

    ```java
    for (Object s : p.stringList()) {
                System.out.print(s + " ");
            }
    ```

  - Allerdings ist ```s``` jetzt eine direkte Instanz von Objekt und beim Aufrufen jeglicher String Methoden kommt es zu Kompiler-Fehler. Eine bessere Lösung wäre:  
  ```Pair<Object> p = new Pair<>(23, "skidoo");```  
  denn eine raw-type Liste kann jegliche Typen enthalten, deswegen kann der Kompiler nicht mehr wissen was drin ist und beim Casting könnte es zum ```ClassCastException``` kommen "Not Type-Safe". Während beim ```Pair<Object>``` der Kompiler schon weiß, dass die Liste verschiedenen Objekte enthält und kann jetzt richtig damit umgehen.

  - Auch das Initialisieren einer raw-type List mit einem parametrisierten Typ ```new Pair<Object>``` bringt nichts, denn raw-type ist für alte Versionen von Java gedacht, die gar nicht verstehen, was Generics sind, deswegen wird der typ auch gelöscht.

### 1.3. Type Parameter Hiding & static nested classes

- 1.3.1 Was ist mit dem folgenden Programm los? Ist etwas falsch?

    Es wird zwei generische typisierte Parameter definiert E. Ein für die Klasse LinkedList und der andere für die Klasse Node. Da die Klasse Node in der Klasse LinkedList ist sie vom Scope der Klasse Linklist beeinflusst und der Typ E von der Klasse Node wird zum Typ E von der Klasse LinkedList. Somit ist der Typ E von Node nicht mehr generisch, sondern von der klasse LinkedList spezifiziert. Der Compiler hingegen erwartet einen generisch typisierten Parameter, deswegen ist der Typ E der Variable head nicht gleich mit E der Variable next.

- 1.3.2. Wenn ja, was ist falsch und wie macht man das richtig? Wenn nein: Was tut es?

  - ~~die Variablen head und next haben unterschiedliche typen und können ein andere nicht referenzieren.~~

  - ~~die Klasse LinkedList ist schon typisiert und Alle Nodes der Klasse sollten den gleichen Typ haben, deswegen braucht man die Klasse Node nicht generisch zu machen. Man kann direkt der Typ der Klasse LinkedList benutzen.~~

  - Die Klasse Node braucht keinen Zugang auf die LinkedList klasse, deswegen sollte sie static sein (nested static class), Allerdings heißt das jetzt das Node nicht mehr head zugreifen/ändern kann, was eigentlich besser ist, denn eine Inner-Klasse darf keine Side-Effects auf die äußere klasse haben.
  
  - Das E von LinkedList ist nicht das gleiche E von Node und ein best practise ist, dass man ein anderes für die nested klasse auswählt z.B. N.

##### Quellen
- [Overflow And Underflow of Data Types in Java](https://dzone.com/articles/overflow-and-underflow-data)
- [The Basics of Java Generics](https://www.baeldung.com/java-generics)
- [Type Erasure](https://www.youtube.com/watch?v=eWDJP6iHgLA)
- [Raw Types in Java](https://www.baeldung.com/raw-types-java)
- [Generics And Parameter Type Hiding](https://kodelog.wordpress.com/2013/04/23/generics-and-parameter-type-hiding/)
- [Java inner class and static nested class](https://stackoverflow.com/questions/70324/java-inner-class-and-static-nested-class)
- [TypeParameterShadowing](https://errorprone.info/bugpattern/TypeParameterShadowing)
- [Nested Classes](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html)
- [Nested Classes in Java](https://www.geeksforgeeks.org/nested-classes-java/)