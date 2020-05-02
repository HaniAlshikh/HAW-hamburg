# Aufgabe 1 - Abgabe - Version 1

<div style="text-align: right">11.04.2020</div>

**Team:** 30, Hani Alshikh, Jannik Stuckstätte

**Aufgabenaufteilung:**

- Jannik Stuckstätte
  - Alle Datenbankentwürfe für den ersten Teil der Aufgabe
  - Programabläufe 2, 3 und 4 des ersten Teils
  - Entwürfe der 5.ten und 6.ten Teilaufgabe des zweiten Teils
  - Prolog implementierung des ersten Teils der Aufgabe
  - alles überprüfen vor der Abgabe
- Hani Alshikh
  - Programabläufe 1 und 5 des ersten Teils der Aufgabe
  - Entwürfe der ersten und zweiten Teilaufgabe des zweiten Teils
  - Pflege der PDF-Datei
  - Prolog implementierung des zweiten Teils der Aufgabe
  - Code Dokumentation
  - Darstellung der fehlenden Algorithmen des zweiten Teil

Jeder von uns hat zunächst alle Programabläufe/Teile der Aufgabe für sich entworfen/impliementiert. Im Anschluss haben wir uns dann in Absprache für eines entschieden oder sie zu einem Ergebnis zusammengeführt.

**Quellenangaben:**

- Verschiedene Wikipedia-Artikel für die Definitionen der Verwandschaftsverhältnisse
- [Syntax in Prolog](http://rigaux.org/language-study/syntax-across-languages-per-language/Prolog.html)
- die Folien "LB_Prolog"

**Bearbeitungszeitraum:**  

- 02.04. Jannik Stuckstätte 4 Std.  
- 04.04. Hani Alshikh 6 Std.
- 05.04. Jannik Stuckstätte 3 Std.  
- 05.04. Hani Alshikh 3 Std.  
- 08.04. Hani Alshikh 5 Std.
- 09.04. Jannik Stuckstätte 3 Std.
- 10.04. Jannik Stuckstätte 1 Std.
- 10.04. Hani Alshikh 3 Std.

**Aktueller Stand:**

- Die Entwürfe aller Aufgaben sind fertig
- Der erster Teil ist in Prolog implementiert
- Der zweiter Teil ist in Prolog implementiert

**Änderungen des Entwurfs:**

- collect_stuck: keine InputListe wurde gebraucht, die Duplikate mussten doch gelöscht werden.
- collect_gs: keine InputListe wurde gebraucht, die Duplikate mussten doch gelöscht werden.
- collect_st: nichts wurde geändert.
- collect_komp: nichts wurde geändert.
- collect_time: Das Erstellen der Liste mit allen CDs jedes Komponisten und das anschließende Aufsummieren der Gesamtspielzeiten wurde entgegen dem Entwurf in zwei separaten Schritten durchgeführt.
- bei der gesamten Aufgabe 6 des zweiten Teils wurde als einziger Parameter eine Ausgabeliste benötigt.

**Entwurf:**

1. Wie viele Stunden je Woche üben Sie ca. LB?  
    ~10 Std. (inklusive Bearbeitung des Praktikums)

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

## Ausgabe der Tests

```
?- test(allcds).
collect_stueck/1 ....ok
collect_gs ...ok
collect_st ....ok
collect_komp/2 ....ok
collect_time ....ok
true.

?- test(allfamilie).
nachkomme +.. -.ok
vorfahre +.. -.ok
nachkommen +..... -..ok
geschwister +.. -.ok
bruder +.. -.ok
schwester +.. -.ok
eheleute +.. -..ok
onkel +... -..ok
tante +... -..ok
oma +... -..ok
opa +.. -..ok
uroma +.. -..ok
uropa +.. -..ok
maenUweibl ok
verhKor ok
elterVoll ok
wurzel ok
true.
```