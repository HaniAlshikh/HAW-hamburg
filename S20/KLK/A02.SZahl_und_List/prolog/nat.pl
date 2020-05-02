% #################################################################
%
% Lösung des zweiten Teils der zweiten Aufgabe
%
% Autor: Jannik Stuckstätte
% Autor: Hani Alshikh
%
% #################################################################


% *******************
% Definition        *
% *******************

%
% Definition der natürlichen Zahlen als S-Zahlen
%
nat(0).
nat(s(X)) :- nat(X).



% *******************
% 1. nat2s          *
% *******************

%
% Hilfsprädikat, um natürliche Zahlen in obige Form der S-Zahlen zu transformieren.
%
nat2s(0, 0).                                                % Rekursionsabbruch
nat2s(N, s(Z)) :-
    Akku is N - 1,                                          % N abzählen, um die Rekursion abzubrechen
    nat2s(Akku, Z).                                         % Rekursiv Klammern einsetzen



% *******************
% 2. s2nat          *
% *******************

%
% Hilfsprädikat, um S-Zahlen in natürliche Zahlen zu transformieren.
%
s2nat(0, 0).                                                % Rekursionsabbruch
s2nat(s(Z), N) :-
  s2nat(Z, Akku),                                           % Rekursiv die Klammern entfernen
  N is Akku + 1.                                            % Enternte Klammern zählen und zurückgeben



% *******************
% 3. add            *
% *******************

%
% Zwei S-Zahlen addieren.
%
add(Z1, 0, Z1).                                             % Rekursionsabbruch
add(Z1, s(Z2), Sum) :- add(s(Z1), Z2, Sum).                 % Z1 solange klammern, bis Z2 keine Klammern mehr hat



% *******************
% 4. sub            *
% *******************

%
% Zwei S-Zahlen subtrahieren. 
% Falls der Subtrahend (Z2) grösser als der Minuend (Z1) ist,
% soll die S-Zahl mit Wert Null berechnet werden.
%
sub(0, _, 0).                                               % Z2 grösser als Z1? dann Null
sub(Z1, 0, Z1).                                             % Rekursionsabbruch
sub(s(Z1), s(Z2), Sub) :- sub(Z1, Z2, Sub).                 % Enferne Klammern von Z1, solange Z2 Klammern hat



% *******************
% 5. mul            *
% *******************

%
% Zwei S-Zahlen multiplizieren.
%
mul(_,0,0).                                                 % Rekursionsabbruch
mul(Z1, s(Z2), Mul) :- 
  % Multiplikation ist x mal die Zahl auf sich selbst addieren
  mul(Z1, Z2, Akku),                                        % Rekursiv x mal (bis Z2 keine Klammern mehr hat)
  add(Akku, Z1, Mul).                                       % die Zahl auf sich selbst addieren



% *******************
% 6. power          *
% *******************

%
% implementiert die Rechnung Z1^Z2 = Power.
%
power(_,0,s(0)).                                            % Rekursionsabbruch
power(Z1, s(Z2), Power) :- 
  % Power ist x mal die Zahl mit sich selbst multiplizieren
  power(Z1, Z2, Akku),                                      % Rekursiv x mal (bis Z2 keine Klammern mehr hat)
  mul(Akku, Z1, Power).                                     % die Zahl mit sich selbst multiplizieren
 


% *******************
% 7. fac            *
% *******************

%
% die Fakultät N! einer S-Zahl (Z) berechnen.
%
fac(0,s(0)).                                                % Rekursionsabbruch
fac(s(N), Z) :- 
  % Fakultät ist die Multiplikation aller natürlichen Zahlen
  % (ohne Null) kleiner und gleich dieser Zahl
  fac(N, Akku),                                             % Rekursiv die natürlichen Zahlen berechnen
  mul(Akku, s(N), Z).                                       % das Ergebniss mit jeder natürlichen Zahl multiplizieren



% *******************
% 8. lt             *
% *******************

%
% implementiert Z1 < Z2 implementiert
%
lt(0, Z2) :- Z2 \= 0, !.                                    % Rekursionsabbruch
lt(s(Z1), s(Z2)) :- lt(Z1, Z2).                             % Abzählen bis Z1 keine klammern mehr hat



% *******************
% 9. mods           *
% *******************

%
% implementiert die Modulo-Operation Z1 mods Z2 = Mods 
%
mods(Z1,Z2,Z1) :- lt(Z1, Z2), !.                            % Rekursionsabbruch
mods(Z1, Z2, Mods) :-
  Z2 \= 0,                                                  % Das Teilen durch 0 ist nicht erlaubt
  sub(Z1, Z2, Akku),                                        % Subtrahiere Z2 von Z1 bis
  mods(Akku, Z2, Mods).                                     % Z1 kleiner Z2 wird rekursiv 