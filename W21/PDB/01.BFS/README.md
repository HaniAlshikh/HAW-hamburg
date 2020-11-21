# GKA Aufgabe 01 - Team C - Hani Alshikh & Jannik Stuckstätte

## Aufgabenaufteilung

| Aufgabe              | Teammitglied/er                   |
| -------------------- | --------------------------------- |
| Graph-Visualisierung | Jannik Stuckstätte                |
| Graph-Parser         | Hani Alshikh                      |
| BFS-Algorithmus      | Jannik Stuckstätte                |
| J-Unit Tests         | Hani Alshikh & Jannik Stuckstätte |
| App GUI              | Hani Alshikh                      |

<br>

## Quellenangaben
- Folien zur "Kürzeste Wege" von Prof. Padberg
- [GraphStream Dokumentation](https://graphstream-project.org/doc/)
- [Java API Specification](https://docs.oracle.com/en/java/javase/14/docs/api/index.html)
- [Swing](https://de.wikipedia.org/wiki/Swing_(Java))
- [JavaFX API Reference](https://openjfx.io/javadoc/11/)

<br>

## Bearbeitungszeitraum
| Bearbeitungszeitraum     | Teammitglied/er                   | Aufgabe                                                            |
| ------------------------ | --------------------------------- | ------------------------------------------------------------------ |
| 24.10.20 20:00 - 23:00   | Jannik Stuckstätte                | Projektstruktur & Evaluierung Bibliotheken                         |
| 29.10.20 11:00 - 12:00   | Jannik Stuckstätte                | Regulärer Ausdruck für Graph-Parser                                |
| 07.11.20 14:00 - 17:00   | Hanik Alshikh                     | Implementierung Graph-Parser & Graph-Visualisierung                |
| 07.11.20 20:00 - 03:00   | Jannik Stuckstätte                | BFS-Algorithmus & Pfadfindung                                      |
| 08.11.20 13:00 - 16:00   | Jannik Stuckstätte                | Tests & Bugfixes                                                   |
| 09.11.20 11:00 - 12:00   | Jannik Stuckstätte                | Refactoring                                                        |
| 10.11.20 13:00 - 14:00   | Jannik Stuckstätte                | Kleine Bugfixes                                                    |
| 11.11.20 19:00 - 20:00   | Jannik Stuckstätte                | Tests & Bugfixes Pfadsuche                                         |
| 13/14.11.20 15:00 - 3:00 | Hani Alshikh                      | Tests, UI & Graph Persistierung                                    |
| 14.11.20 15:00 - 17:00   | Jannik Stuckstätte                | Dokumentation                                                      |
| 14/15.11.20 18:00 - 4:00 | Hani Alshikh                      | UI Verbesserungen, refactoring, javadoc & Dokumentation überprüfen |
| 15.11.20 15:00 - 17:00   | Jannik Stuckstätte                | Dokumentation                                                      |
| 15.11.20 17:00 - 18:00   | Jannik Stuckstätte & Hani Alshikh | Finale Besprechung & Abgabe                                        |


### Bearbeitung Gesamt
- Jannik Stuckstätte: 22 Std.
- Hani Alshikh: 26 Std.
- Gesamt: 48 Std.

**Hinweis**: Die hier angegebenen Bearbeitungszeiten sind nicht zwingendermaßen als reine Arbeitszeiten zu verstehen. Es wurden von beiden Teampartnern viele Pausen gemacht, die nicht protokolliert wurden. Daher ist eine genaue Aussage über die reine Bearbeitungszeit schwierig. Während der gesamten Bearbeitungszeit gab es außerdem immer wieder regen Austausch über den Stand und die weitere Vorgehensweise des Projektes über z.B. Messaging Dienste, welche ebenfalls nicht protokolliert wurden.

<br>

## Algorithmen und Datenstrukturen

### Graph-Visualisierung: Reguläre Ausdrücke
Zum Parsen der Graph-Dateien haben wir uns direkt zur Nutzung von Regulären Ausdrücken entschieden. Hierdurch erhalten wir bei korrekter Nutzung einen hohen Grad an Flexibilität bei der Entscheidung welche Bestandteile der Syntax wir als notwendig erachten und welche lediglich Optional sind.

### Pfadsuche: BFS-Algorithmus
Zur Traversierung des Graphens und der Ermittlung kürzester Wege, haben wir, wie von der Aufgabenstellung vorausgesetzt, eine Breitensuche implementiert, welche sich sehr an den von Prof. Padberg vorstellten BFS-Algorithmus orientiert. Hierbei wird von dem Startknoten ausgehend alle Knoten mit der Anzahl an mindestens zu traversierenden Kanten versehen, bis ein definierter Endknoten erreicht wurde oder keine weiteren neuen Knoten gefunden werden. Im Anschluss wird der Graph von dem Endknoten startend traversiert, wobei immer zu dem Nachbarknoten gegangen wird, welcher mit genau einem Schritt weniger gekennzeichnet wurde. 

### Pfadsuche: HashMap
Zur Implementierung der Zuweisung von Knoten zu den benötigten Schritten bis zu diesem jeweiligen Knoten haben wir uns zur Nutzung einer [HashMap](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/HashMap.html) entschieden. Sie ermöglicht uns die Speicherung der Relation zwischen Knoten-IDs und Schrittanzahl.
Hierfür bildet sie für jeden Key (Knoten-Ids in unserem Fall) einen Hashcode über den sehr schnell auf den zugeordneten Wert zugegriffen werden kann. 

## Entwurfsentscheidungen

### Graph-Visualiserung

**Java GUI**
Die Wahl der GUI-Bibliothek fiel direkt auf JavaFX. Aus dem einfachen Grund, dass es als Nachfolger von Swing gilt und es aus unserer Sicht daher nicht sehr sinnvoll gewesen wäre, uns weiter mit Swing zu beschäftigen. Außerdem ist es seit einiger Zeit Teil des Lieferumfangs der Oracle JDK: [Quelle](https://de.wikipedia.org/wiki/Swing_(Java)).

<br>

Wir haben uns außerdem dazu entschieden, eine Reihe von Menüansichten in JavaFX zu implementieren, was die Demonstration des Projektes erleichtert. Hierüber lassen sich Graph-Dateien einlesen, kürzeste Wege über den BFS-Algorithmus ermitteln und Graph-Dateien im Dateisystem persistieren.

**Graph-Visualiserungs-Bibliothek**

Zu Beginn des Projektes haben wir einige von den von Prof. Padberg genannten Bibliotheken zur Visualiserung von Graphen angesehen und uns nach kurzer Evaluierung für [GraphStream](https://graphstream-project.org/) entschieden. Der Hauptgrund für diese Entscheidung lag in dem simplen Aufbau der Bibliothek was unserer Ansicht nach den kleinsten Aufwand für Einarbeitung in eben diese nach sich ziehen würde. Im Laufe des Projektes stellte sich heraus, dass die Bibliothek zwar recht intuitiv zu benutzen ist, dessen Dokumentation aber leider an vielen Stellen viel zu knapp ausfällt.

Wir haben einige wenige Layout-Attribute genutzt, welche die Graphen etwas schöner aussehen lassen sollen. Außerdem haben wir sowohl für Knoten als auch Kanten Labels hinzugefügt, damit das Visualisierungs-Ergebnis verständlich und nachvollziehbar ist.


### Graph-Parser

**Regulärer Ausruck**:

Bei der Implementierung des regulären Ausdruckes haben wir uns 
dazu entschieden, Kommentare an den Zeilenenden, beginnend mit "//",ebenfalls zu berücksichtigen. Dadurch haben wir die Möglichkeit, die Zeilen in den Graph-Dateien zu kommentieren. Das ist vor Allem zur Erstellung / Nachvollziehbarkeit von Graph-Dateien zu Testzwecken äußerst hilfreich.


### BFS-Algorithmus / Pfadfindung
Wie in [Pfadsuche: BFS-Algorithmus](#pfadsuche-bfs-algorithmus) beschrieben, haben wir uns bei der Implementierung des BFS-Algorithmus und der rückwärtigen Traversierung zur Bildung des Pfades stark an dem von Prof. Padberg gezeigten Algorithmus orientiert. 
Wir entschieden uns außerdem dafür einen [Optional](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/Optional.html) als Rückgabetyp zu nutzen, um, im Falle des Nichtvorhandenseins eines Pfades, trotzdem ein gut zu prüfendes Objekt zu erhalten. Für die Implementierung des BFS-Algorithmus haben wir uns für eine Mischung aus einer Schleife und Java-[Streams](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/stream/Stream.html) entschieden, da es unserer Ansicht nach äußerst positive Auswirkungen auf die Lesbarkeit und Nachvollziehbarkeit des Quellcodes hatte.

Die von uns erstellte Utility-Klasse "TraversalHelper" bietet für einen gegebenen Start- und Endknoten auf einem gegebenen Graphen sowohl die Möglichkeit lediglich die Anzahl der benötigten Schritte, als auch den dabei abgelaufenen Pfad ermitteln zu lassen. Bei beiden Varianten wird jedoch dieselbe private BFS-Methode genutzt.

<br>

## Testfälle

### Graph-Parser

Zum Testen der korrekten Arbeitsweise des Parsers für Graph-Dateien haben wir eine Test-Datei erstellt, welche viele Grenzfälle enthält.
Innerhalb der Datei haben wir als Kommentar an jede Zeile ihren jeweiligen zu testenden Grenzfall geschrieben:
```
a -> b (ab) : 1;           // 01. directed
c -- d (cd) : 2;           // 02. notDirected
e -- f : 3;                // 03. no edgeName
h -> i (hi) :;             // 04. no weight
g -- k : ;                 // 05. no edgeName & weight
L -- M;                    // 06. basic
N;                         // 07. free node
O -- O : 0;                // 08. loop
b -> a;                    // 09. multigraph
d   -- c(dc)    :2;        // 10. weired spaces
a -- X (ab);               // 11. duplicated id
                           // not valid
a -- b (ab) : 10           // 01. no semi-colleen
-> b;                      // 02. no starting node
--;                        // 03. no nodes
;                          // 04. empty
N;                         // 05. duplicate free node
```
Hierbei erwarten wir folgende Ergebnisse:

Valide Zeilen:
1. Benannte gerichtete Kante mit Gewichtung
2. Benannte ungerichtete Kante mit Gewichtung
3. Unbenannte ungerichtete Kante mit Gewichtung
4. Benannte gerichtete Kante ohne Gewichtung
5. Unbenannte ungerichtete Kante ohne Gewichtung (Mit Doppelpunkt)
6. Unbenannte ungerichtete Kante ohne Gewichtung (Ohne Doppelpunkt)
7. Freier Knoten ohne Kante
8. Unbenannte ungerichtete Schlinge mit Gewichtung von Null
9. Unbenannte gerichtete Kante ohne Gewichtung zwischen Zwei Knoten zwischen denen bereits eine Kante existiert.
10. Benannte ungerichtete Kante mit Gewichtung und einigen überschüssigen Leerzeichen
11. Benannte ungerichtete Kante mit einem bereits vergebenen Namen. Hierbei wird ab den angegebenen Namen ein "_dupN" gehängt, wobei N fortlaufend ist.

Nicht valide Zeilen:

1. Unbenannte ungerichtete Kante mit Gewicht mit fehlendem Semikolon
2. Kante ohne Startknoten
3. Kante ohne Knoten
4. Zeile bestehend aus einem Semikolon
5. Duplikat eines freien Knotens, wird von GraphStream standardmäßig ignoriert.


### Graph-Persistierung

Zum Testen der Persistierung parsen wir jede der gestellten Testdateien und merken uns die Anzahl an Kanten und Knoten. Im Anschluss persistieren wir jeden Beispielgraph um ihn im Anschluss wieder zu parsen und vergleichen dann alle wichtigen Eigenschaften:
- Anzahl der Knoten
- Anzahl der Kanten
- Namen der Knoten
- Namen der Kanten
- Richtung der Kanten
- Gewichtung der Kanten

### BFS und Pfadfindung

Zum Testen der Korrektheit des BFS-Algorithmus haben wir folgende Testfälle definiert:

- Ermittlung der Schritte zwischen zwei adjazenten Knoten
    - Hier prüfen wir den einfachsten Fall eines zu findenen Weges. Der zu findene Weg ist der zwischen zwei adjazenten Knoten: a - b
    Hier prüfen wir lediglich die ermittelte Schrittzahl.
- Ermittlung des Weges zwischen zwei adjazenten Knoten
  - Gleicher Test wie der vorgehende jedoch mit Prüfung auf den ermittelten Weg: [a, b] 
- Ermittlung eines nicht vorhandenen Weges
  - Hiermit prüfen wir, ob der Algorithmus erfolgreich abbricht, sobald alle verbundenen Knoten abgelaufen sind und kein Weg gefunden werden konnte. 
- Prüfung der Wegfindung ohne Berücksichtigung der Länge des Weges
  - Für diese Tests nutzen wir die Beispieldatei "graph03.gka" und prüfen für jeden Knoten, ob der Algorithmus einen Weg zu jedem anderen Knoten finden kann. Der tatsächlich gefunden Weg und seine Länge wird hierbei nicht geprüft, sondern lediglich das Vorhandensein jeds Weges.
- Vergleichen aller ermittelten kürzesten Wege mit denen ermittelt von einer Referenzimplmentierung auf einem Graphen
  - Gleicher Test wie der vorhergehende jedoch werden hier tatsächlich alle gefundenen Wege anhand ihrer Länge mit dem des, in der GraphStream-Bibliothek enthaltenen, APSP Algorithmus verglichen. Hierdurch wird sichergestellt, dass die gefundenen Wege tatsächlich kürzeste Wege sind. Da je nach gewählten Start- und Endknoten mehrere Wege existieren können, die als kürzeste Wege in Frage kommen (da sie gleich viele Schritte benötigen), kann hier nur auf die Länge des ermittelten Weges geprüft werden.

<br>

## Fragen der Aufgabenstellung

1. Was passiert wenn Knotennamen mehrfach auftreten?

Tritt ein freier Knoten mehrfach auf, wird dieser nur einmalig zu dem Graph hinzugefügt. Tritt ein Knotenname bei verschiedenen Kanten mehrmals auf, ist dies kein Problem, da ein Knoten selbstverständlich inzident zu mehreren Kanten sein kann. 

2. Wie unterscheidet sich der BFS für gerichtete und ungerichtete Graphen?

Bei gerichteten Graphen muss sowohl bei der Zuweisung der Schrittzahlen als auch bei der anschließenden rückwärtigen Traversierung zum Bilden des Pfades auf die Richtung der jeweiligen Kante geachtet werden. Bei ungerichteten Kanten, muss dies nicht berücksichtigt werden. 

In unserem speziellen Fall muss jedoch keinerlei Unterscheidung in dem Algorithmus gemacht werden, da die Methode "leavingEdges" der GraphStream Klasse Node dies bereits berücksichtigt. Sie gibt alle inzidenten ungerichteten Kanten und alle gerichteten ausgehenden Kanten zurück. Somit ist von unserer Seite keine Unterscheidung der beiden Fälle mehr notwendig.

3. Wie können Sie testen, dass Ihre Implementierung auch für sehr große Graphen funktioniert? 

Die von uns getesteten Graphen können als Untergraph betrachtet werden. Da unsere Implementierung für diese Untergraph funktioniert, funktioniert sie auch für sehr große Graphen (benötigter Speicher wird bei dieser Überlegung ignoriert).

Ein möglicher Beweisansatz wäre hier einen der getesteten Graphen immer wieder aneinanderzureihen und durch eine Kante zu verbinden. Da die Implementierung für jeden dieser durch eine Kante verbundenen Untergraphen funktioniert, wird sie auch für eine Anzahl an beliebig oft aneinander gereihten Kopien dieses Untergraphen funktionieren.

<br>