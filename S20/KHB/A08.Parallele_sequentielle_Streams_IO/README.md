Professor: Dr. Bernd Kahlbrandt  
Author: Hani Alshikh
<div style="text-align: right">02.05.2020</div>

# A05: Generics

## 1. Java Denksportaufgaben

### 1.1. Grenzen der Parallelisierung

Was passiert hier bei Parallelisierung?

Ineffiziente bearbeitung und sehr lange Laufzeiten, da: 

- die statefule Methode ```limit()``` benutzt wird. ```limit()``` muss wissen, wie viele Elemente sind schon bearbeitet wurden und ob die Grenze erreicht ist. Das ist sehr schwer, wenn die Elemente gleichzeitig bearbeitet werden.

- Außerdem wird die Methode ```iterate()``` benutzt um den Stream zu generieren. Da der nächste Elemente immer von dem vorherigen abhängig ist, hat man hier einen unendlichen Ordered-Stream, der nicht so einfach geteilt werden kann, wenn man parallelisiert. Besser wäre die Methode ```generate()``` zu benutzen, da dies einen Unordered-Stream generiert.

- In der Parallelisierung besteht keine Reihenfolge. Und da limit nicht nur n Elemente zurückgeben muss, sondern sogar die ersten n Elemente wäre es hilfreich ```BaseStream.unordered()``` zu nutzen, um die Bearbeitung zu beschleunigen.

- noch schneller ist der Stream sequentiell zu bearbeiten ```BaseStream.sequential() // bracht man nicht unbedigt bei Collection.stream(). Er ist bei default sequential```

außerdem werden alle Kerne der Prozessor benutzt, und bei dieser ineffizienten Parallelisierung kann das zu hohem Druck auf dem Rechner führen. Das konnten wir an unserem Rechner beobachten und sager hören, wie der Lüfter viel schneller arbeiten musste, um die Prozessor Temperatur runter zu bringen.
    
 

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