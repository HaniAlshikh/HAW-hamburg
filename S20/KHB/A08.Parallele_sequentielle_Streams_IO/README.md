Professor: Dr. Bernd Kahlbrandt  
Author: Hani Alshikh
<div style="text-align: right">02.06.2020</div>

# A05: Generics

## 1. Java Denksportaufgaben

### 1.1. Grenzen der Parallelisierung

Was passiert hier bei Parallelisierung?

Ineffiziente bearbeitung und sehr lange Laufzeiten, da: 

- die statefule Methode ```limit()``` benutzt wird. ```limit()``` muss wissen, wie viele Elemente sind schon bearbeitet wurden und ob die Grenze erreicht ist. Das ist sehr schwer, wenn die Elemente gleichzeitig bearbeitet werden.

- Außerdem wird die Methode ```iterate()``` benutzt um den Stream zu generieren. Da der nächste Elemente immer von dem vorherigen abhängig ist, hat man hier einen unendlichen Ordered-Stream, der nicht so einfach geteilt werden kann, wenn man parallelisiert. Besser wäre die Methode ```generate()``` zu benutzen, da dies einen Unordered-Stream generiert.

- In der Parallelisierung besteht keine Reihenfolge. Und da limit nicht nur n Elemente zurückgeben muss, sondern sogar die ersten n Elemente wäre es hilfreich ```BaseStream.unordered()``` zu nutzen, um die Bearbeitung zu beschleunigen.

- noch schneller ist der Stream sequentiell zu bearbeiten ```BaseStream.sequential() // bracht man nicht unbedigt bei Collection.stream(). Er ist bei default sequential```

außerdem werden alle Kerne der Prozessor benutzt, und bei dieser ineffizienten Parallelisierung kann das zu hohem Druck auf dem Rechner führen. Das konnten wir an unserem Rechner beobachten und sogar hören, wie der Lüfter viel schneller arbeiten musste, um die Prozessor Temperatur runter zu bringen.
    
 

##### Quellen
- [Interface Stream<T>](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#collect-java.util.function.Supplier-java.util.function.BiConsumer-java.util.function.BiConsumer-)
- [Trapezregel](https://de.wikipedia.org/wiki/Trapezregel)
- [Numerical integration](https://planetcalc.com/5494/)
- [Object Serialization in Java](https://www.youtube.com/watch?v=6B6vp0jZnb0&t=0s)
- [SerialVersionUID And Versioning in Java Serialization](https://www.youtube.com/watch?v=4EQ8XJO7PBQ&t=0s)
- [The Serialization Proxy Pattern](https://blog.codefx.org/design/patterns/serialization-proxy-pattern/)
- [Java Serialization Magic Methods and Use Cases](https://dzone.com/articles/java-serialization-magic-methods-and-use-cases)
- [Java ObjectInputStream registerValidation() Method](https://www.javatpoint.com/java-objectinputstream-registervalidation-method)
- [Serialization Proxy Pattern in Java](https://www.netjstech.com/2017/04/serialization-proxy-pattern-in-java.html)
- [Serialization and Deserialization in Java](https://www.netjstech.com/2017/04/serialization-in-java.html)
- [SerialVersionUID And Versioning in Java Serialization](https://www.netjstech.com/2017/04/serialversionuid-and-versioning-in-java-serialization.html)
- [Java custom serialization using readObject and writeObject](https://howtodoinjava.com/java/serialization/custom-serialization-readobject-writeobject/)
- [Interface Serializable](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html)
- [Interface ObjectInputValidation](https://docs.oracle.com/javase/8/docs/api/java/io/ObjectInputValidation.html)
- [Class FileOutputStream](https://docs.oracle.com/javase/8/docs/api/java/io/FileOutputStream.html)
- [Class ObjectOutputStream](https://docs.oracle.com/javase/8/docs/api/java/io/ObjectOutputStream.html)