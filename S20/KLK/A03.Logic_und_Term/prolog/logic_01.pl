% #################################################################
%
% Lösung des ersten Teils der dritten Aufgabe 
% 1.1. direkt in Prolog impliementiert
%
% Autor: Jannik Stuckstätte
% Autor: Hani Alshikh
%
% #################################################################


% *******************
% 0. err          *
% *******************

%
% Hilfsprädikat, um eine Exception zu werfen.
%
err(Predicate) :- 
  nl, write('Ungebundene Variable in '), write(Predicate), write('!'), nl, fail.



% *******************
% 1. mnot           *
% *******************

mnot(X) :- (
  nonvar(X), \+ X, !
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
  (\+ (X, Y), !)
) ; (
  (var(X), !; var(Y)),
  err(nand(X,Y))
).



% *******************
% 5. nor            *
% *******************

nor(X, Y) :- (
  nonvar(X), nonvar(Y),
  (\+ (X ; Y), !)
) ; (
  (var(X), !; var(Y)),
  err(nor(X,Y))
).



% *******************
% 6. impl           *
% *******************

impl(X, Y) :- (
  nonvar(X), nonvar(Y),
  ((\+X ; Y), !)
) ; (
  (var(X), !; var(Y)),
  err(impl(X,Y))  
).



% *******************
% 7. xor           *
% *******************

xor(X, Y) :- (
  nonvar(X), nonvar(Y),
  (X, ! ; Y) , (\+X, ! ; \+Y)
) ; (
  (var(X), !; var(Y)),
  err(xor(X,Y))
).



% *******************
% 8. aequiv         *
% *******************

aequiv(X, Y) :- (
  nonvar(X), nonvar(Y),
  ((X, Y) ; (\+X, \+Y)), ! % muss den Entwurf entsprechen.
) ; (
  (var(X), !; var(Y)),
  err(aequiv(X,Y))
).
