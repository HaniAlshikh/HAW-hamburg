% #################################################################
%
% Lösung des ersten Teils der dritten Aufgabe 
% 1.2. modifiziert
%
% Autor: Jannik Stuckstätte
% Autor: Hani Alshikh
%
% #################################################################


% *******************
% 0. err          *
% *******************

%
% Hilfsprädikat, um einen Fehler zu schreiben.
%
err(Predicate) :- 
  nl, write('Ungebundene Variable in '), write(Predicate), write('!'), nl, fail.



% *******************
% 1. mnot           *
% *******************

mnot(X) :- (
  nonvar(X), ((X, !, fail) ; (true, !))
) ; (
  var(X), err(mnot(X))
).



% *******************
% 2. and            *
% *******************

and(X, Y) :- (
  nonvar(X), nonvar(Y),
  (X, Y, !)
) ; (
  (var(X), !; var(Y)),
  err(and(X,Y))
).



% *******************
% 3. or             *
% *******************

or(X, Y) :- (
  nonvar(X), nonvar(Y),
  (X, ! ; Y)
) ; (
  (var(X), !; var(Y)),
  err(or(X,Y))
).



% *******************
% 4. nand           *
% *******************

nand(X, Y) :- (
  nonvar(X), nonvar(Y),
  mnot(and(X, Y))
) ; (
  (var(X), !; var(Y)),
  err(nand(X,Y))
).



% *******************
% 5. nor            *
% *******************

nor(X, Y) :- (
  nonvar(X), nonvar(Y),
  mnot(or(X, Y))
) ; (
  (var(X), !; var(Y)),
  err(nor(X,Y))
).



% *******************
% 6. impl           *
% *******************

impl(X, Y) :- (
  nonvar(X), nonvar(Y),
  or(mnot(X), Y)
) ; (
  (var(X), !; var(Y)),
  err(impl(X,Y))  
).



% *******************
% 7. xor           *
% *******************

xor(X, Y) :- (
  nonvar(X), nonvar(Y),
  or(X, Y) , or(mnot(X), mnot(Y))
) ; (
  (var(X), !; var(Y)),
  err(xor(X,Y))
).



% *******************
% 8. aequiv         *
% *******************

aequiv(X, Y) :- (
  nonvar(X), nonvar(Y),
  ((X, Y) ; (\+X, \+Y)), !
) ; (
  (var(X), !; var(Y)),
  err(aequiv(X,Y))
).



% *******************
% 9. tafel          *
% *******************


%
% die Wahrheitstafel eines bestimmten logischen Ausdrucks
% für n aussagenlogische Variablen angeben
% 
%
% tafel 2 und 3 auf tafeln delegiert.
%
% tafel2(X, Y, InTerm) :- tafeln([X,Y], InTerm).
% tafel3(X, Y, Z, InTerm) :- tafeln([X,Y,Z], InTerm).

tafeln(VarList, InTerm) :- 
  nonvar(InTerm),                                                     % Sicherstellen, dass InTerm nicht leer ist
  bindn(VarList),                                                     % Bilde Permutation mit Wiederholung für Belegungen WAHR und FALSCH für alle Variablen in VarList
  pprint(VarList),                                                    % Schreibe die Belegungen in die Ausgabe
  do(InTerm),                                                         % Auswertugn der Belegung in InTerm in die Ausgabe schreiben
  fail.                                                               % Permutation hat weitere Belegung?
tafeln(_,_).                                                          % Nein, gebe Wahr zurück

%
% tafel 2 und 3 auf ohne Delegation.
%
tafel2(X, Y, InTerm) :-
  nonvar(InTerm),
  bind(X), bind(Y),
  pprint(X,Y),
  do(InTerm),
  fail.
tafel2(_, _, _).

tafel3(X, Y, Z, InTerm) :-
  nonvar(InTerm),
  bind(X), bind(Y), bind(Z),
  pprint(X,Y, Z),
  do(InTerm),
  fail.
tafel3(_, _, _, _).

%
% Hilfsprädikat, um eine Variabele mit True oder Fail zu binden.
%
bind(fail).
bind(true).
bindn([A|Rest]) :- bind(A), bindn(Rest).                                      
bindn([]).

%
% Hilfsprädikat, um die tafeln auszudrücken.
%
pprint(X, Y) :- 
  write(X),write('\t'),write(Y),write('\t|   ').

pprint(X, Y, Z) :- 
  write(X),write('\t'),write(Y),write('\t'),write(Z),write('\t|   ').

pprint([A|Rest]) :- 
  write(A), write("\t"), 
  pprint(Rest).

pprint([]) :- 
  write('|'), write('   ').

%
% Hilfsprädikat, um eine Formel auszuwerten
% und die Auswertung auszudrüken
%
do(Formel) :- Formel, !, writeln('true').
do(_) :- writeln('fail'), fail.



% *******************
% 10. avarListe     *
% *******************


%
% alle ungebundenen Variablen eines logischen Ausdrucks extrahieren
%
avarListe(InTerm, VarList) :- 
  nonvar(InTerm),                                                     % Sicherstellen, dass InTerm nicht leer ist
  InTerm =.. [_|Parameter],                                           % PararamList des Ausdrucks extrahieren
  var_collect(Parameter, DupVarList),                                 % Rein in die Rekursion
  list_to_set(DupVarList, VarList).                                   % duplikate entfernen und VarList zurückgeben
  
var_collect(Parameter, VarList) :- var_collect(Parameter,[],VarList). % AkkuList initialisieren 
var_collect([], AkkuList, AkkuList).                                  % Rekursionabbruch
var_collect([V|R], AkkuList, VarList) :-
  (
    var(V),                                                           % ist Variable?
    append(AkkuList, [V], NewAkkuList),                               % in Akkuliste sichern
    var_collect(R, NewAkkuList, VarList)                              % weiter mit der Rekursion
  ) ;
  (
    avarListe(V, InnerVarList),                                       % Nein, prüfe in innerer Rekusion auf Variablen
    append(AkkuList, InnerVarList, NewAkkuList),                      % sicher die in Akkuliste 
    var_collect(R, NewAkkuList, VarList)                              % weiter mit der Rekursion
  ).



% *******************
% 11. Aussagenlogik *
% *******************

%
% Erstellen Sie für die in 1.(a) gefundene Formel der Modellierung
% (vor der Transformation in eine KNF) mittels tafel3/4,
% eine Wahrheitstafel (als ausführbares Prädikat formelA/0 bitte dem Code beifügen).
%
formelA :- tafel3(L1,L2,L3, and(xor(L1,L2), and(impl(mnot(L3),mnot(L2)), impl(and(mnot(L2),L1), L3)))).

%
% Testen Sie mittels tafel3/4 und dem Prädikat aequiv/2 ob Ihre gefundene KNF (aus 1.(b)) aequivalent
%
formelKNF :- tafel3(L1,L2,L3, and(or(L1,L2), and(or(mnot(L1),mnot(L2)), and(or(L3, mnot(L2)), or(L2,or(mnot(L1),L3)))))).
aequivAKNF :- tafel3(L1,L2,L3, 
  aequiv(and(xor(L1,L2), and(impl(mnot(L3),mnot(L2)), impl(and(mnot(L2),L1), L3))),
  and(or(L1,L2), and(or(mnot(L1),mnot(L2)), and(or(L3, mnot(L2)), or(L2,or(mnot(L1),L3))))))
).

%
% zu der ursprünglichen Formel (Negation der Formel aus 
% 1.(a) ((F1 und F2 und F3) impliziert F4)) ist (als ausführbares Prädikat
% formelB/0 bitte dem Code beifügen). 
%
formelB :- tafel3(L1,L2,L3, mnot(impl(and(xor(L1,L2), and(impl(mnot(L3),mnot(L2)), impl(and(mnot(L2),L1), L3))), L3))).
