# Aufgabe 3 - Abgabe - Version 1

<div style="text-align: right">24.05.2020</div>

**Team:** 30, Hani Alshikh, Jannik Stuckstätte

**Aufgabenaufteilung:**

- Jannik Stuckstätte
  - Programabläufe des ersten Teils der Aufgabe
  - Pflege der PDF-Datei
  - Prolog Implementierung des zweiten Teils
  - Java implementierung des Prädikates ```nnf_expr```
  - Abgabe Überprüfen
  
- Hani Alshikh
  - Programabläufe des zweiten Teils der Aufgabe
  - Pflege der PDF-Datei
  - Prolog Implementierung des ersten Teils der Aufgabe
  - Code Dokumentation
  - ```nnf_expr``` Nachweise

Jeder von uns hat zunächst alle Programabläufe/Teile der Aufgabe für sich entworfen/implementiert. Im Anschluss haben wir uns dann in Absprache für eines entschieden oder sie zu einem Ergebnis zusammengeführt.

**Quellenangaben:**

- [Logikgatter](https://de.wikipedia.org/wiki/Logikgatter)
- [Negation](https://de.wikipedia.org/wiki/Negation#Die_Negation_in_der_zweiwertigen_Logik)
- [Konjunktion](https://de.wikipedia.org/wiki/Konjunktion_(Logik))
- [Disjunktion](https://de.wikipedia.org/wiki/Disjunktion)
- [NOR-Gatter](https://de.wikipedia.org/wiki/NOR-Gatter)
- [Implikation](https://de.wikipedia.org/wiki/Implikation#Wahrheitsfunktionale_Implikation)
- [Kontravalenz/XOR](https://de.wikipedia.org/wiki/Kontravalenz)
- [Logische Äquivalenz](https://de.wikipedia.org/wiki/Logische_%C3%84quivalenz)
- [Aussage (Logik)](https://de.wikipedia.org/wiki/Aussage_(Logik))
- [Literal](https://de.wikipedia.org/wiki/Literal)

**Bearbeitungszeitraum:**  

- 08.05. Jannik Stuckstätte 09 std.
- 08.05. Hani Alshikh 10 std.
- 15.05. Hani Alshikh 05 Std.
- 15.05. Jannik Stuckstätte 06 Std.
- 22.05. Hani Alshikh 10 std.
- 22.05. Jannik Stuckstätte 10 Std.
- 23.05. Hani Alshikh 05 std.

**Aktueller Stand:**

- Die Entwürfe aller Aufgaben sind fertig
- Der zweite Teil ist in Prolog implementiert
- Der zweite Teil ist in Java implementiert
- Der erste Teil ist in Prolog implementiert

**Änderungen des Entwurfs:**

- tafel: Tabellenkopf wird nicht mehr geschrieben, um die Ausgabe in der Aufgabenstellung zu entsprechen.
- tafel: InTerm muss auf Belegung geprüft werden
- nnf_expr: die Prüfung, ob Term die Form impl(X,Y) oder aequiv(X,Y) bzw not(impl(X,Y)) oder not(aequiv(X, Y)) hat, ist überflüssig.
- nnf_expr: der Fall mnot(mnot(T)) wurde ergänzed
- nnf_expr: xor musst zu mxor umbenannt werden, da Prolog schon ein eigenes Prädikat xor hat.
- simplifyDef: mnot(aequiv), mnot(impl) wurden ergänzed

**Entwurf:**

1. Wie viele Stunden je Woche üben Sie ca. LB?  
    ~25 Std. (inklusive Bearbeitung des Praktikums)

2. Mit welchen digitalen Medien tauschen Sie sich im Team aus?  
   - Whatsapp, Discord und Gitlab

3. Welche Quellen haben Sie bisher für Aufgaben/Übungen/Erklärungen genutzt?  
   - Vorlesungsaufzeichnungen
   - [Prolog Tutorial](https://www.youtube.com/watch?v=SykxWpFwMGs&)
   - [bSimple](https://www.youtube.com/channel/UCCTii-_z90kaxio8AdGF3Bw)

4. Wie messen Sie Ihren Lernfortschritt?  
   - Anhand der Bearbeitbarkeit der Praktikumsaufgaben

5. Wie dokumentieren Sie Ihr Lernen?  
    Die Frage ist uns nicht ganz klar

<div class="page"/>

## Aussagenlogik

a). Stellen Sie für die beschriebenen Regeln eine (unmittelbar aus dem Text modellierte, noch nicht transformierte) aussagenlogische Formel auf und formulieren das Erfüllbarkeitsproblem.

- Von den beiden ersten Lebkuchen darf höchstens einer entfernt werden.  
   ```F1 = L1 ⊕ L2```
- Wenn man den dritten entfernt, muss man auch den zweiten entfernen.  
   ```F2 = ¬L3 → ¬L2```
- Wenn man den zweiten entfernt und den ersten nicht, dann darf man den dritten nicht entfernen.  
   ```F3 = (¬L2 ∧ L1) → L3```
- Da Krete in Logik aufgepasst hat, weiss sie, dass man vom dritten Lebkuchen besser die Finger lässt.  
   ```F4 = L3```  
   ```F = F1 ∧ F2 ∧ F3 → F4```

b). Prüfen Sie das Erfüllbarkeitsproblem bzw. ob Krete Recht hat mittels Resolutions-Kalkül.

  **KNF**

  ```prolog
  F1 = (L1 V L2) ∧ (¬L1 V ¬L2)
  F2 = (L3 V ¬L2)
  F3 = (¬(¬L2 ∧ L1) V L3) = ((L2 V ¬L1) V L3) = (L2 V ¬L1 V L3)
  F4 = ¬L3 wegen Resulation (die Frage)
  ```

**Resolutions-Kalkül**

  ```prolog
  {L1, L2}1, {¬L1, ¬L2}2, {L3, ¬L2}3, {L2, ¬L1, L3}4, {¬L3}5}
    \7                       \6{¬L2}6      /7           /6
     \7           {L2, L3}7      |9       /7           /8
                        \8{L2}8 /9                    /8
                          |9{}9/9
  ```

c). Prüfen Sie mittels Wahrheitstafel, ob Krete Recht hat.

  ```prolog
   L1      L2      L3     |  formelA  % Krete hat Recht
  fail	fail	fail	|   fail
  fail	fail	true	|   fail
  fail	true	fail	|   fail
  fail	true	true	|   true    % nur wo L3 wahr ist (nicht entfernt)
  true	fail	fail	|   fail
  true	fail	true	|   true    % ist der Ausdruck wahr
  true	true	fail	|   fail
  true	true	true	|   fail
  ```

<div class="page"/>

## Ausgabe der Tests

```shell
?- test(alllogic).
and +.. -...
Ungebundene Variable in and(fail,_7330)!
.
Ungebundene Variable in and(_7328,fail)!
ok
or +.... -.
Ungebundene Variable in or(true,_7330)!
.
Ungebundene Variable in or(_7328,true)!
ok
mnot +... -..
Ungebundene Variable in mnot(_7334)!
ok
aequiv +.. -..
Ungebundene Variable in aequiv(_7334,_7334)!
ok
formeln +..... -.ok
avarListe +.... -...ok
fail	fail	fail	|   fail
fail	fail	true	|   fail
fail	true	fail	|   fail
fail	true	true	|   true
true	fail	fail	|   fail
true	fail	true	|   true
true	true	fail	|   fail
true	true	true	|   fail

fail	fail	fail	|   fail
fail	fail	true	|   fail
fail	true	fail	|   fail
fail	true	true	|   fail
true	fail	fail	|   fail
true	fail	true	|   fail
true	true	fail	|   fail
true	true	true	|   fail
true.

```

<div class="page"/>

```shell
?- test(logicall).
nand +.... -.
Ungebundene Variable in nand(fail,_7164)!
.
Ungebundene Variable in nand(_7162,fail)!
ok
nor +.. -...
Ungebundene Variable in or(true,_7170)!
.
Ungebundene Variable in or(_7168,true)!
ok
impl +.... -.
Ungebundene Variable in impl(true,_7182)!
.
Ungebundene Variable in impl(_7180,true)!
ok
xor +... -..
Ungebundene Variable in true xor _7198!
.
Ungebundene Variable in _7196 xor true!
ok
true.

?- test(allterm).
is_atomic_expr +.. -ok
is_literal +... -.ok
nnf_expr ......ok
is_clause +... -....ok
is_horn_clause +... -......ok
true.
```
