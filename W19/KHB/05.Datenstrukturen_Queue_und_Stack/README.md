Professor:: Dr. Bernd Kahlbrandt  
Author:: Nick Marvin Rattay  
Author:: Hani Alshikh  
<div style="text-align: right">17.11.2019</div>

# Datenstrukturen Queue und Stack

Für beide Datenstrukturen wurden die folgenden Entscheidungen getroffen

## Methoden Parameter

- ### enqueue(element), push(element)  
  
    da zu wissen ist, was zu dem Queue bzw. Stack eingefügt werden sollte

- ### dequeue, pop  

    da das Verhalten dieser Methode schon vorausgesetzt ist, braucht man nicht zu wissen welches Element entfernt werden sollte.

- ### ==(other), eql?(other)

    da zu wissen ist, womit verglichen werden sollte.

- ### empty?, hash

    da das Verhalten dieser Methode schon vorausgesetzt ist, braucht man keinen Parameter
    
 ## Rückgabe bei enqueue bzw. push
 
 Es wurde entschieden ein Array der Elemente zurückzugeben, da dies:
 - das erwartete Verhalten der für das Einfügen benutzten Methode Push
 - die Möglichkeit ergibt, beispielsweise zu testen, dass das Element an der richtigen Stelle eingefügt wurde, wie Z.B.:  
 queue.enqueue(6).last == 6
 
 ## Quellen
 - [Ruby DOC](https://ruby-doc.org)


