% #################################################################
%
% Lösung des zweiten Teils der ersten Aufgabe
%
% Autor: Jannik Stuckstätte
% Autor: Hani Alshikh
%
% #################################################################

% ***************
% Regelen Laden *
% ***************

:- consult(familie).
:- consult(aufg1_test).

% ************************************
% 1. vorfahre, nachkomme, nachkommen *
% ************************************

% Alle Nachkommen von einer Person nacheinander berechnen.
%
% nachkomme(Ausgabe, Eingabe).
%
nachkomme(Nachkomme, Person) :- elternteil(Person, Nachkomme).      % gibt es einen Nachkomme? 
nachkomme(Nachkomme, Person) :- elternteil(Person, Kind),           % Nachkomme suchen.
                                nachkomme(Nachkomme, Kind).         % rekursiv Nachkomme vom Nachkomme suchen.

% Alle Vorfahren von einer Person nacheinander berechnen.
%
% vorfahre(Ausgabe, Eingabe).
%
vorfahre(Vorfahr, Person) :- elternteil(Vorfahr, Person).           % gibt es einen Vorfahr?
vorfahre(Vorfahr, Person) :- elternteil(ET, Person),                % Vorfahr suchen.
                             vorfahre(Vorfahr, ET).                 % rekursiv Vorfahr vom Vorfahr suchen.

% Alle Nachkommen von <Person> in einer Liste angeben.
%
% nachkommen(Eingabe, Ausgabe).
%
nachkommen(Person, Nachkommen) :- 
  findall(
    NK,                                                             % Output pattern.
    nachkomme(NK, Person),                                          % alle Nachkommen suchen.
    NKD                                                             % Sammelliste aller gefundenen Nachkommen.
  ),
  list_to_set(NKD, Nachkommen).                                     % Duplikate löschen.



% *******************
% 2. eheleute, kind *
% *******************

% Prüfen, ob zwei Personen Eheleute sind.
%
% eheleute(Eingabe, Eingabe).
%
eheleute(Person1, Person2) :- verheiratet(Person1, Person2), !.     % Eheleute (verheiratet)? ja, dann cut.
eheleute(Person1, Person2) :- verheiratet(Person2, Person1).        % Sonst Reihnfolge tauchen.

% Prüfen, ob eine Person kind eines Elternteils.
%
% kind(Eingabe, Ausgabe).
%
kind(Elternteil, Kind) :- elternteil(Elternteil, Kind).             % hat der Elternteil ein Kind?



% ****************
% 3. geschwister *
% ****************

% Prüfen, ob zwei Personen geschwister sind.
%
% eheleute(Eingabe, Eingabe).
%
geschwister(Person1, Person2) :- Person1 \== Person2,               % die selbe Person?
                                 elternteil(Elternteil, Person1),   % wer ist Elternteil der ersten Person?
                                 elternteil(Elternteil, Person2).   % ist er auch von der zweiten?



% **********************
% 4. bruder, schwester *
% **********************

% Prüfen, ob eine Person Bruder von der anderen.
%
% bruder(Eingabe, Eingabe).
%
bruder(Bruder, Person) :- maennlich(Bruder),                        % ist männlich?
                          geschwister(Bruder, Person).              % sind sie Geschwister?

% Prüfen, ob eine Person Schwester von der anderen.
%
% schwester(Eingabe, Eingabe).
%
schwester(Schwester, Person) :- weiblich(Schwester),                % ist weiblich?
                                geschwister(Schwester, Person).     % sind sie Geschwister?



% *********************************************************
% 5. opa, uropa, oma, uroma, mutter, vater, tante, onkel. *
% *********************************************************

% Großeltern einer Person suchen.
%
% grosseltern(Ausgabe, Eingabe).
%
grosseltern(Grosseltern, Person) :- elternteil(ET, Person),         % Elternteile von Person suchen.
                                    elternteil(Grosseltern, ET).    % Elternteile vom Elternteil suchen.

% Urgroßeltern einer Person suchen.
%
% urgrosseltern(Ausgabe, Eingabe).
%
urgrosseltern(Urgrosseltern, Person) :- grosseltern(GE, Person),    % Grosseltern von Person suchen.
                                  elternteil(Urgrosseltern, GE).    % Elternteile von Grosseltern suchen.

% Opa einer Person suchen.
%
% opa(Ausgabe, Eingabe).
%
opa(Opa, Enkel) :- grosseltern(Opa, Enkel),                         % Grosseltern vom Enkel suchen.
                   maennlich(Opa).                                  % nur die mänlichen Grosseltern ausgeben.

% Oma einer Person suchen.
%
% oma(Ausgabe, Eingabe).
%
oma(Oma, Enkel) :- grosseltern(Oma, Enkel),                         % Grosseltern vom Enkel suchen.
                   weiblich(Oma).                                   % nur die weiblichen Grosseltern ausgeben.

% Uropa einer Person suchen.
%
% uropa(Ausgabe, Eingabe).
%
uropa(Uropa, Enkel) :- urgrosseltern(Uropa, Enkel),                 % Urgrosseltern vom Enkel suchen.
                       maennlich(Uropa).                            % nur die mänlichen Urgrosseltern ausgeben.

% Uroma einer Person suchen.
%
% uroma(Ausgabe, Eingabe).
%
uroma(Uroma, Enkel) :- urgrosseltern(Uroma, Enkel),                 % Urgrosseltern vom Enkel suchen.
                       weiblich(Uroma).                             % nur die weiblichen Urgrosseltern ausgeben.

% Mutter einer Person suchen.
%
% mutter(Ausgabe, Eingabe).
%
mutter(Mutter, Kind) :- elternteil(Mutter, Kind),                   % Elternteile vom Kind suchen.
                        weiblich(Mutter).                           % nur den weilbichen Elternteil ausgeben.

% Vater einer Person suchen.
%
% vater(Ausgabe, Eingabe).
%
vater(Vater, Kind) :- elternteil(Vater, Kind),                      % Elternteile vom Kind suchen.
                      maennlich(Vater).                             % nur den männlichen Elternteil ausgeben.

% Tante einer Person suchen.
%
% tante(Ausgabe, Eingabe).
%
tante(Tante, Kind) :- elternteil(ET, Kind),                         % Elternteile vom Kind suchen.
                      schwester(Tante, ET).                         % nur die Schwester der Elternteile ausgeben.

% Onkel einer Person suchen.
%
% onkel(Ausgabe, Eingabe).
%
onkel(Onkel, Kind) :- elternteil(ET, Kind),                         % Elternteile vom Kind suchen.
                      bruder(Onkel, ET).                            % nur die Brüder der Elternteile ausgeben.

% *******************************************
% 6. maenUweibl, verhKor, elterVoll, wurzel *
% *******************************************

% Prüfen, ob es eine Person gibt, die zugleich männlich und weiblich ist?
%
% maenUweibl(Ausgabe).
%
maenUweibl(Liste) :- 
  findall(
    MuW,                                                            % Output pattern.
    (maennlich(MuW), weiblich(MuW)),                                % männlich und weiblich?
    Dliste                                                          % Sammelliste aller gefundenen muw Einträge.
  ),
  list_to_set(Dliste, Liste).                                       % Duplikate löschen.

% Prüfen, ob das Prädikat verheiratet korrekt abgespeichert ist? (links Mann, rechts Frau).
%
% verhKor(Ausgabe).
%
verhKor(Liste) :- 
  findall(
    (P1, P2),                                                       % Output pattern.
    (verheiratet(P1, P2), weiblich(P1), maennlich(P2)),             % links Mann, rechts Frau?
    Dliste                                                          % Sammelliste aller gefundenen muw Einträge.
  ),
  list_to_set(Dliste, Liste).                                       % Duplikate löschen.

% Prüfen, ob alle Personen im Prädikat elternteil auch als männlich oder weiblich registriert sind?
%
% verhKor(Ausgabe).
%
elterVoll(Liste) :- 
  findall(
    (ET, P),                                                        % Output pattern.
    (elternteil(ET, P), \+ (weiblich(ET); maennlich(ET))),          % Elternteil männlich oder weiblich?
    Dliste                                                          % Sammelliste aller gefundenen muw Einträge.
  ),
  list_to_set(Dliste, Liste).                                       % Duplikate löschen.

% Prüfen, ob es jemand ohne Eltern gibt.
%
% wurzel(Ausgabe).
%
wurzel(Liste) :- 
  findall(
    P,                                                              % Output pattern.
    ((maennlich(P); (weiblich(P))), \+ elternteil(_, P)),           % checke alle Personen für einen Elternteil
    Dliste                                                          % Sammelliste aller Personen ohne Elternteil.
  ),
  list_to_set(Dliste, Liste).                                       % Duplikate löschen.