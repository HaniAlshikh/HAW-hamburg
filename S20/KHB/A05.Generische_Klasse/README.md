Professor: Dr. Bernd Kahlbrandt  
Author: Hani Alshikh
<div style="text-align: right">04.04.2020</div>

# A05: Generics

## 1. Java Denksportaufgaben

### 1.1. Compere implementierung

die Compare Methode erwartet eine positive Zahl (in diesem Fall), wenn x > y, negative, wenn x < y und 0 wenn x = y.

- 1.1.1. Was gibt diese Program aus?

    UNORDERED
    
- 1.1.2. Wie kommt die Ausgabe zustande?

    durch die falsche implementierung der Compare Methode. Z.B. 10 - -11 ergibt eine positive Zahl, deswegen werden die Zahlen getaucht, obwohl -11 kleiner 10. 
    
- 1.1.3. Wie machen Sie das richtig?

    man könnte entsprechend if bedienungen schreiben, oder besser die Arbeit an die compareTo Methode der Integer Klasse delegieren.

    Außerdem Arrays.sort kann schon mit Integers umgehen und sie sortieren. Man braucht keinen Comparator zu übergeben.
    
### 1.2. Raw Types

- 1.2.1 Was gibt das folgende Programm aus?

    erwartet sollte ```23 skidoo``` ausgegeben werden, allerdings ist das Program noch nicht compile-fähig
 
- 1.2.2. Ist es überhaupt compile-fähig?

    Nein, denn String und Object nicht der selbe Typ sind
 
- 1.2.3. Was ist an diesem Code ggfs. verbesserungswürdig?

    - ```Pair p = new Pair<Object>(23, "skidoo");```  
        Raw-Typ ist ein Name für generische Interfaces oder nicht typisierte Klassen.  
        Da eine raw-typ List jegliche Typen enthalten kann kann man 

### 1.3. Type Parameter Hiding

- 1.3.1 Was ist mit dem folgenden Programm los? Ist etwas falsch?

    es wird zwei generische typisierte Parameter definiert E. Ein für die Klasse LinkedList und der andere für die Klasse Node. Da die Klasse Node in der Klasse LinkedList ist sie vom Scope der Klasse Linklist beeinflusst und der Typ E von der Klasse Node wird zum Typ E von der Klasse LinkedList. Somit ist der Typ E von Node nicht mehr generisch, sondern von der klasse LinkedList spezifiziert. Der Compiler hingegen erwartet einen generisch typisierten Parameter, deswegen ist der Typ E der Variable head nicht gleich mit E der Variable next.
 
- 1.3.2. Wenn ja, was ist falsch und wie macht man das richtig? Wenn nein: Was tut es?

    die Variablen head und next haben unterschiedliche typen und können ein andere nicht referenzieren.
    
    die Klasse LinkedList ist schon typisiert und Alle Node der Klasse sollten den gleichen Typ haben, deswegen braucht man die Klasse Node nicht generisch zu machen. Man kann direkt der Typ der Klasse LinkedList benutzen.

##### Quellen
- [Understanding null in Java](https://dev.to/dj_devjournal/understanding-null-in-java-4o31)
- [Java – static variable with example](https://beginnersbook.com/2013/05/static-variable/)
- [static keyword in java](https://www.geeksforgeeks.org/static-keyword-java/)