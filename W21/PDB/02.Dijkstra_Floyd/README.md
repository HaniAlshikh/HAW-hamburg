# GKA Aufgabe 02 - Team C - Hani Alshikh & Jannik Stuckstätte

## Aufgabenaufteilung

| Aufgabe                    | Teammitglied/er                   |
| -------------------------- | --------------------------------- |
| Projektaufbau              | Hani Alshikh                      |
| Dijstra-Algorithmus        | Hani Alshikh                      |
| Floyd-Warshall-Algorithmus | Jannik Stuckstätte                |
| Testfälle                  | Hani Alshikh & Jannik Stuckstätte |
| Graph-Generator            | Hani Alshikh                      |
| Dokumentation              | Jannik Stuckstätte                |

<br>

## Quellenangaben
- Folien zur "Optimalen Wege" von Prof. Padberg
- [GraphStream Dokumentation](https://graphstream-project.org/doc/)
- [Java API Specification](https://docs.oracle.com/en/java/javase/14/docs/api/index.html)
- [Swing](https://de.wikipedia.org/wiki/Swing_(Java))
- [JavaFX API Reference](https://openjfx.io/javadoc/11/)

<br>

## Bearbeitungszeitraum
| Bearbeitungszeitraum      | Teammitglied/er                   | Aufgabe             |
| ------------------------- | --------------------------------- | ------------------- |
| 24.11.2020  13:00 - 17:00 | Hani Alshikh                      | Projektaufbau       |
| 27.11.2020  21:00 - 03:00 | Hani Alshikh                      | Dijstra-Algorithmus |
| 28.11.2020  14:00 - 17:00 | Hani Alshikh                      | Refactoring         |
| 28.11.2020  13:00 - 19:00 | Jannik Stuckstätte                | Floyd-Algorithmus   |
| 28.11.2020  19:00 - 23:00 | Jannik Stuckstätte                | Refactoring         |
| 29.11.2020  13:00 - 21:00 | Hani Alshikh & Jannik Stuckstätte | Tests schreiben     |
| 01.12.2020  14:00 - 17:00 | Hani Alshikh                      | Graph-Generator     |
| 05.12.2020  15:00 - 18:00 | Jannik Stuckstätte                | Dokumentation       |


### Bearbeitung Gesamt
- Hani Alshikh: 23 Std.
- Jannik Stuckstätte: 21 Std.
- Gesamt: 44 Std.

**Hinweis**: Die hier angegebenen Bearbeitungszeiten sind nicht zwingendermaßen als reine Arbeitszeiten zu verstehen. Es wurden von beiden Teampartnern viele Pausen gemacht, die nicht protokolliert wurden. Daher ist eine genaue Aussage über die reine Bearbeitungszeit schwierig. Während der gesamten Bearbeitungszeit gab es außerdem immer wieder regen Austausch über den Stand und die weitere Vorgehensweise des Projektes über z.B. Messaging Dienste, welche ebenfalls nicht protokolliert wurden.

<br>

## Algorithmen und Datenstrukturen

### Dijkstra-Algorithmus

Wir haben uns für den Einsatz der [PriorityQueue](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/PriorityQueue.html#%3Cinit%3E())-Klasse entschieden. Diese stellt eine Queue dar, dessen Elemente nach der über ihnen definierten natürlichen Ordnung sortiert. Hierdurch "markieren" wir Knoten als besucht, indem wir sie aus der Queue entfernen und suchen den Knoten mit der derzeit kürzesten Distanz, indem wir die natürliche Ordnung der Elemente hierüber definieren.

Auch für diese öffentlichen Methoden zur Berechnung eines Pfades haben wir uns erneut für die Rückgabe eines [Optional](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/Optional.html) entschieden. Hierdurch werden Prüfungen auf nicht vorhandene Pfade deutlich lesbarer und die Gefahr eines unerwarteten NullPointers deutlich reduziert.  

<br>

### Floyd-Warshall-Algorithmus

Zur Implementierung des Floyd-Warshall-Algorithmus haben wir uns so strikt wie möglich an den, auf dem Aufgabenblatt beschriebenen, Vorgehen orientiert. Es gab jedoch eine kleine Änderung, die wir vornehmen mussten:

- Die Elemente Transitions-Matrix, welche laut Aufgabenblatt mit 0 initialisiert werden, um zu signalisieren, das hier kein Zwischenschritt nötig ist (z.B. bei direkten Verbindungen), initialisieren wir mit -1, da es sonst nicht mehr möglich ist, zwischen den Pfaden mit einem Zwischenschritt über Knoten mit dem Index 0 zu unterscheiden.

Auch hier entschieden wir uns für die Nutzung der [Optional](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/Optional.html)-Klasse als Rückgabewert der öffentlichen Methoden aufgrund der oben genannten Gründe.

<br>

## Entwurfsentscheidungen

Sowohl bei der Implementierung des Dijkstra-Algorithmus als auch bei der des Floyd-Warshall-Algorithmus haben wir uns dazu entschieden, dass wir Kanten ohne Gewicht mit einem Gewicht von 1 interpretieren.

### Dijkstra-Algorithmus

Die Dijkstra-Implementierung ist bei uns Teil einer Utility-Klasse, da der Dijkstra-Algorithmus ohnehin für jeden Pfad, der einen anderen Startknoten besitzt als in der vorangegangenen Berechnung, erneut durchgeführt werden muss.

Zur Implementierung des Dijktra-Algorithmus zur Findung eines kürzesten Weges über gewichtete Kanten, haben wir uns zur Nutzung einer "ExtendedNode"-Klasse entschieden. Dies ermöglicht es uns, für den Algorithmus Notwendige Abbildungen wie die der Knoten auf deren Vorgänger und die bisher zurückgelegte Distanz / Gewichtssume vom Startpunkt, direkt im Knotenobjekt zu speichern. Des Weiteren konnten wir innerhalb dieser Klasse ebenfalls die natürliche Ordnung definieren, was uns zunächst als sehr sinnvoll erschien. Dies führte zu Beginn unter Anderem zu deutlich lesbareren Quellcode. Dies führte jedoch auch dazu, dass wir alle Nodes im Graphen als Instanz der "ExtendedNode"-Klasse definieren mussten. Im Nachhinein wäre es wahrscheinlich sinnvoller gewesen, die benötigten Attribute über die GraphStream-Attribute zu speichern und die natürliche Ordnung der Knoten über deren zugeordnete Distanz in einem statischen Comparator abzubilden.

<br>

### Floyd-Warshall-Algorithmus

Da die Berechnung eines einzigen Pfades auf einem gegebnen Graphen bei der Nutzung des Floyd-Warshall-Algorithmus zwangsläufig zur Berechnung der Transitions- und Distanzmatrix für den gesamten Graphen führt, haben wir uns hier dafür entschieden eine instanziierbare Klasse zu erstellen. Hierdurch werden diese Matrizen bei der Berechnung mehrerer Pfade auf einem gegebenen Graphen lediglich einmal berechnet. 

Des Weiteren war das erwartete Verhalten bei Mehrfachkanten nicht definiert. Hierbei haben wir uns dazu entschieden, dass immer die jeweils kleiner gewichtete Kante für die Berechnung herangezogen wird. Dies führte bei dem Vergleich mit den Ergebnissen einer Referenz-Implementierung der GraphStream-Bibliothek zu einigen Problemen (mehr hierzu im Abschnitt Testfälle).

Zur Implmentierung des Pfadbaus auf Basis der Transitionsmatrix haben wir uns für eine rekursive Lösung entschieden. Dies ist zwar für Java eher ungeeignet, jedoch führt dies dennoch zu einer besseren Nachvollziehbarkeit der Vorgehensweise der Lösung. Bei einer Implementierung für eine wiederverwendbaren Bibliothek sollte hiervon jedoch abgesehen werden, da es insbesondere bei sehr großen Graphen zu einer großen Belastung des Call-Stacks und u.U. zu einem StackOverflow-Errors führen kann.


<br>

## Testfälle

<br>

### "Reguläre" Testfälle

Wir haben zunächst einige vordefinierte Testfälle geschrieben, um unsere Implementierung auf unkomplizierten und einfach nachzuvollziehbaren Pfaden zu testen. Dies führt gerade zu Beginn der Entwicklung dazu, dass bei Fehlern die Analyse dieser deutlich vereinfacht wird. Hierfür haben wir, wie bei unseren Tests der vorangegangenen Aufgabe, unter Anderem alle möglichen Pfade, auf einem gegebenen Graphen, gegen die Ergebnisse einer Referenz-Implementierung der GraphStream-Bibliothek getestet.

<br>

### Randomisierte Testfälle unter Anwendung eines Graph-Generators

Nachdem diese einfachen Testfälle von unseren Implementierungen bestanden worden sind, haben wir, wie in der Aufgabenstellung vorgesehen, einen Graph-Generator implementiert, welcher für eine gegebene Anzahl an Knoten und Kanten einen zufälligen Graphen erstellt. Hierbei stießen wir jedoch darauf, dass der Referenz-Algorithmus der GraphStream-Bibliothek bei Mehrfachkanten das Gewicht der Kante mit dem niedrigeren Index zur Berechnung heranzieht. Dies führte dazu, dass auf diversen Pfaden die Gewichtssumme des berechneten Pfades unserer Implementierungen kleiner ausfällt, als die der Ergebnisse des Referenz-Algorithmus. Daher entschieden wir uns dazu, die Ergebnisse der beiden von uns implementierten Algorithmen miteinander zu vergleichen.

Die Verwendung von randomisierten Testfällen ist äußerst sinnvoll, da sie verhindert, dass unbewusste Annahmen über die Beschaffenheit der Graphen mit in die Erstellung der Implementierung selbst und der Testfälle einfließt und Fehler hierdurch nicht erkannt werden. 

<br>

## Fragen der Aufgabenstellung

a) Zum Algorithmus:
- Bekommen Sie fur einen Graphen immer den gleichen kürzesten Weg? Warum?

  - Bei der Nutzung des gleichen Algorithmus, ja. Bei Verwendung unterschiedlicher Algorithmen, nein.
    Da es auf einem Graphen zwischen zwei gegebenen Knoten mehrere gleichlange, Pfade geben kann, kann es auch zwei unterschiedliche kürzeste Wege geben. 

- Was passiert, wenn der Eingabegraph negative Kantengewichte hat?

    - Negative Kantengewichtungen an sich sind so lange kein Problem, solange sie keinen Kreis mit einer negativen Kantengewichtssumme bilden. Wenn ein solcher Kreis besteht, wird der Floyd-Warshall Algorithmus kein Pfad, auf dem dieser Kreis liegt, berechnen können.

- Wie müussten Sie Ihre Lösung erweitern, um die Menge der kürzesten Wege zu bekommen?

    - Dijkstra:
        - Hier müssten mehrere Vorgänger in einer "ExtendedNode" gespeichert werden können. Diese werden nun auch dann gesetzt, wenn die Distanz gleich groß ist.

    - Floyd-Warshall: 
      - Die Transitionsmatrix müsste auch hier mehrere Knoten-Indizes halten können und ebenfalls bereits dann gesetzt werden, wenn der neu berechnete Pfad gleich lang ist.

- Wie müssten Sie Ihre Lösung erweitern, damit die Suche nicht-deterministisch ist?
    - Nach einiger Diskussion sind wir zu folgendem Schluss gekommen: Uns fällt aktuell keine sinnvolle Anpassung der Algortithmen ein, um sie nicht-deterministisch zu machen, die keinen Zufallsgenerator enthält. Jede Anpassungen zur Wahl eines bestimmten Pfades (z.B. auf Basis der Knotenanzahl) würde die Lösungsfindung wieder deterministisch machen. 

b) Zum randomisierten Graphen:
- Wie allgemein ist Ihre Konstruktion von BIG, kann jeder beliebige, gerichtete Graph erzeugt werden?

    - Ja, da die Erzeugung vollständig zufällig erzeugt und lediglich die angegebenen Parameter als Grenzwerte fungieren, kann unter Angabe der entsprechenden Parameter jeder beliebige Graph erzeugt werden.

- Wie testen Sie für BIG, ob Ihre Implementierung den kürzesten Weg gefunden hat?
  
  - Wie im Abschnitt Testfälle beschrieben, hatten wir bei dem Test gegen die Referenzimplementierung der GraphStream-Bibliothek einige Schwierigkeiten, da diese bei Mehrfachkanten immer die erste Kante wählt, unabhängig davon, ob es eine kürzere gibt. Daher haben wir uns dazu entschieden zum Testen des BIG-Graphen die Ergebnisse usere Dijkstra-Implementierung gegen die der FloydWarshall-Implementierung zu testen. Hierfür generieren einen BIG-Graphen ohne negative Kanten und vergleichen die Gesamtgewichtung der gefundenen Pfade des Dijkstra- und FloydWarshall-Alogrithmus.

c) Zur Implementierung
- Welche der von Joschua Bloch empfohlenen Techniken haben Sie eingesetzt?
    
    - Verwendung von Streams
    - Verwendung von "static" in inneren Klassen
    - Verwendung von Optional
    - Vermeidung von Wrapper-Klassen soweit möglich / sinnvoll
    - Verwendung von privaten Konstruktoren in Utility-Klassen

- Haben Sie einen explizieten Korrekturschritt zur Verbesserung des Codes durchgeführt?
    - Nutzung von Double.POSITIVE_INFINITY anstelle von Double.MAX_VALUE zur Vermeidung von Overflows
    - Allgemeines Code Refactoring zur Verbesserung der Lesbarkeit
    - Veränderung des Wertes 0 in der Transitionsmatrix zur Signalisierung eines direkten Weges auf -1
    - Umbau der FloydWarshall-Klasse von einer Utility-Klasse hin zu einer regulären Klasse um bei Berechnung mehrerer Pfade des gleichen Graphens deutlich an Performance zu gewinnen.