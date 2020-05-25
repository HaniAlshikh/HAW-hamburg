# Aufgabe 3 - Nachweis - Version 1

<div style="text-align: right">24.05.2020</div>

**Team:** 30, Hani Alshikh, Jannik Stuckstätte

## Programablauf

<center><img src="entwurf/nnf_expr.svg"/></center>

<div class="page"/>

## Nachweis PROLOG

```prolog
nnf_expr(Term, Simplified) :-
  is_literal(Term), !,                                   % ist literal? 
  Simplified = Term.                                     % es gibt nichts zu machen. (1)

nnf_expr(Term, Simplified) :-                            % Term hat Form (X → Y), (X ↔ Y)
                                                         % oder die Negation davon? (2)
  simply_def(Term, Simplified_Def),                      % Vereinfache.
  nnf_simplified(Simplified_Def, Simplified).            % Nein, weiter auf NNF vereinfachen

nnf_simplified(Term, Simplified) :-
  is_literal(Term), !,                                   % ist (die Vereinfachung) literal?
  Simplified = Term.                                     % es gibt nichts zu machen. (3)

%---------------------------------------OR----------------------------------------%

nnf_simplified(Term, Simplified) :-
  Term =.. [mnot | [Term_Mnot]],                         % Term hat die Form (¬T)? (4)
  Term_Mnot =.. [or | [X, Y]],                           % T hat die Form (X V Y)? (4)
  Simplified_Mnot_Or = and(mnot(X), mnot(Y)),            % Vereinfache zu (¬X ∧ ¬Y) (5.1)
  nnf_expr(Simplified_Mnot_Or, Simplified), !.           % weiter mit der Rekursion (5.1)

nnf_simplified(Term, Simplified) :-
  Term =.. [nor | [X, Y]],                               % Term hat die Form (X nor Y)? (4.1)
  Simplified_Nor = mnot(or(X, Y)),                       % Mache daraus ¬(X V Y) (5.2)
  nnf_expr(Simplified_Nor, Simplified), !.               % weiter mit der Rekursion (5.2)

nnf_simplified(Term, Simplified) :-
  Term =.. [mnot | [Term_Mnot]],                         % Term hat die Form (¬T)? (4.2)
  Term_Mnot =.. [nor | [X, Y]],                          % T hat die Form (X nor Y)? (4.2)
  Simplified_Mnot_Nor = or(X, Y),                        % Vereinfache zu or(X V Y) (5.3)
  nnf_expr(Simplified_Mnot_Nor, Simplified), !.          % weiter mit der Rekursion (5.3)

nnf_simplified(Term, Simplified) :-
  Term =.. [or | [X, Y]],                                % Term hat die Form (X V Y)? (4.3)
  nnf_expr(X, Simplified_X), !,                          % X ist Term? (5.X)
  nnf_expr(Y, Simplified_Y), !,                          % Y ist Term? (5.Y)
  Simplified = or(Simplified_X, Simplified_Y), !.        % gebe (X V Y) vereinfacht zurück (6)
  
%---------------------------------------AND----------------------------------------%

nnf_simplified(Term, Simplified) :-
  Term =.. [and | [X, Y]],                               % Term hat die Form (X ∧ Y)? (4.4)
  nnf_expr(X, Simplified_X), !,                          % X ist Term? (5.X)
  nnf_expr(Y, Simplified_Y), !,                          % Y ist Term? (5.Y)
  Simplified = and(Simplified_X, Simplified_Y), !.       % gebe (X ∧ Y) vereinfacht zurück (6)

nnf_simplified(Term, Simplified) :-
  Term =.. [mnot | [Term_Mnot]],                         % Term hat die Form (¬T)? (4.5)
  Term_Mnot =.. [nand | [X, Y]],                         % T hat die Form (X nand Y)? (4.5)
  Simplified_Mnot_Nand = and(X, Y),                      % Vereinfache zu (X ∧ Y) (5.4)
  nnf_expr(Simplified_Mnot_Nand, Simplified), !.         % weiter mit der Rekursion (5.4)

nnf_simplified(Term, Simplified) :-
  Term =.. [nand | [X, Y]],                              % Term hat die Form (X nand Y)? (4.6)
  Simplified_Nand = mnot(and(X, Y)),                     % mache ¬((X ∧ Y)) daraus (5.5)
  nnf_expr(Simplified_Nand, Simplified), !.              % weiter mit der Rekursion (5.5)

nnf_simplified(Term, Simplified) :-
  Term =.. [mnot | [Term_Mnot]],                         % Term hat die Form (¬T)? (4.7)
  Term_Mnot =.. [and | [X, Y]],                          % T hat die Form (X ∧ Y)? (4.7)
  Simplified_Mnot_And = or(mnot(X), mnot(Y)),            % Vereinfache zu (¬X V ¬Y) (5.6)
  nnf_expr(Simplified_Mnot_And, Simplified), !.          % weiter mit der Rekursion (5.6)
```

<div class="page"/>

```prolog
%---------------------------------------XOR---------------------------------------%

nnf_simplified(Term, Simplified) :-
  Term =.. [mxor | [X, Y]],                              % Term hat die Form (X ⊕ Y)? (4.8)
  Simplified_Xor = and(or(mnot(X), mnot(Y)), or(X, Y)),  % vereinfache zu (¬X V ¬Y) ∧ (X V Y) (5.7)
  nnf_expr(Simplified_Xor, Simplified), !.               % weiter mit der Rekursion (5)

nnf_simplified(Term, Simplified) :-
  Term =.. [mnot | [Term_Mnot]],                         % Term hat die Form (¬T)? (4.9)
  Term_Mnot =.. [mxor | [X, Y]],                         % T hat die Form (X ⊕ Y)? (4.9)
  Simplified_Xor = nand(or(mnot(X), mnot(Y)), or(X, Y)), % Vereinfache zu (¬X V ¬Y) nand (X V Y) (5.8)
  nnf_expr(Simplified_Xor, Simplified), !.               % weiter mit der Rekursion (5)

%-------------------------------------NOT(NOT)-------------------------------------%

nnf_simplified(Term, Simplified) :-
  Term =.. [mnot | [Term_Mnot]],                         % Term hat die Form mnot(T)? (4.N)
  Term_Mnot =.. [mnot | [Term_Mnot_Mnot]], !,            % T hat die Form mnot(TE)? (4.N)
  Simplified_Mnot_Mnot = Term_Mnot_Mnot,                 % Vereinfache zu TE (5.N)
  nnf_expr(Simplified_Mnot_Mnot, Simplified), !.         % weiter mit der Rekursion (5)
```

<div class="page"/>

## Nachweis JAVA

```java
public static Term nnf_expr(Term termToSimplify) {

  // ist literal? es gibt nichts zu machen. (1)
  if (TermUtility.isLiteral(termToSimplify)) {
    return termToSimplify;
  }

  // Term hat Form (X → Y), (X ↔ Y) oder die Negation davon? (2)
  termToSimplify = simplyDef(termToSimplify); // vereinfache

  // ist (die Vereinfachung) literal? es gibt nichts zu machen. (3)
  if (TermUtility.isLiteral(termToSimplify)) {
    return termToSimplify;
  }

  // simplifiedTerm zunächst mit übergebenen Term initialisieren
  Term simplifiedTerm = termToSimplify;

  // Term hat die Form (X nor Y)? (4.1)
  if (termToSimplify.getClass() == Nor.class) {
    // Mache daraus ¬(X V Y) (5.2)
    Term simplified_Nor = new Not(new Or(termToSimplify.TermLinks, termToSimplify.TermRechts));
    // weiter mit der Rekursion (5.2)
    simplifiedTerm = nnf_expr(simplified_Nor);
  }
  // Term hat die Form (X V Y)? (4.3)
  else if (termToSimplify.getClass() == Or.class) {
    // X ist Term? (5.X)
    Term simplified_X = nnf_expr(termToSimplify.TermLinks);
    // Y ist Term? (5.Y)
    Term simplified_Y = nnf_expr(termToSimplify.TermRechts);
    // gebe (X V Y) vereinfacht zurück (6)
    simplifiedTerm = new Or(simplified_X, simplified_Y);
  }

  // Term hat die Form (X ∧ Y)? (4.4)
  else if (termToSimplify.getClass() == And.class) {
    // X ist Term? (5.X)
    Term simplified_X = nnf_expr(termToSimplify.TermLinks);
    // Y ist Term? (5.Y)
    Term simplified_Y = nnf_expr(termToSimplify.TermRechts);
    // gebe (X ∧ Y) vereinfacht zurück (6)
    simplifiedTerm = new And(simplified_X, simplified_Y);
  }

  // Term hat die Form (X nand Y)? (4.6)
  else if (termToSimplify.getClass() == Nand.class) {
    // mache ¬((X ∧ Y)) daraus (5.5)
    Term simplified_Nand = new Not(new And(termToSimplify.TermLinks, termToSimplify.TermRechts));
    // weiter mit der Rekursion (5.5)
    simplifiedTerm = nnf_expr(simplified_Nand);
  }
```

<div class="page"/>

```java
  // Term hat die Form (X ⊕ Y)? (4.8)
  else if (termToSimplify.getClass() == Xor.class) {
    // vereinfache zu (¬X V ¬Y) ∧ (X V Y) (5.7)
    Term simplified_Xor = new And(
        new Or(new Not(termToSimplify.TermLinks), new Not(termToSimplify.TermRechts)),
        new Or(termToSimplify.TermLinks, termToSimplify.TermRechts));
    // weiter mit der Rekursion (5)
    simplifiedTerm = nnf_expr(simplified_Xor);
  }

  // Term hat die Form mnot(T)? (4.N)
  else if (termToSimplify.getClass() == Not.class) {

    // Merke notTerm für bessere Lesbarkeit
    Term notTerm = termToSimplify.TermLinks;

    // T hat die Form (X V Y)? (4)
    if (notTerm.getClass() == Or.class) {
      // Vereinfache zu (¬X ∧ ¬Y) (5.1)
      Term Simplified_Not_Or = new And(new Not(notTerm.TermLinks),
          new Not(notTerm.TermRechts));
      // weiter mit der Rekursion (5.1)
      simplifiedTerm = nnf_expr(Simplified_Not_Or);
    }
    // T hat die Form (X nor Y)? (4.2)
    else if (notTerm.getClass() == Nor.class) {
      // Vereinfache zu or(X V Y) (5.3)
      Term simplified_Not_Nor = new Or(notTerm.TermLinks,
          notTerm.TermRechts);
      // weiter mit der Rekursion (5.3)
      simplifiedTerm = nnf_expr(simplified_Not_Nor);
    }

    // T hat die Form (X nand Y)? (4.5)
    else if (notTerm.getClass() == Nand.class) {
      // Vereinfache zu (X ∧ Y) (5.4)
      Term simplified_Not_Nand = new And(notTerm.TermLinks,
          notTerm.TermRechts);
      // weiter mit der Rekursion (5.4)
      simplifiedTerm = nnf_expr(simplified_Not_Nand);
      }
    // T ha t die Form (X ∧ Y)? (4.7)
    else if (notTerm.getClass() == And.class) {
      // Vereinfache zu (¬X V ¬Y) (5.6)
      Term simplified_Not_And = new Or(new Not(notTerm.TermLinks),
          new Not(notTerm.TermRechts));
      // weiter mit der Rekursion (5.6)
      simplifiedTerm = nnf_expr(simplified_Not_And);
    }

```

<div class="page"/>

```java
    // T hat die Form (X ⊕ Y)? (4.9)
    else if (notTerm.getClass() == Xor.class) {
      // Vereinfache zu (¬X V ¬Y) nand (X V Y) (5.8)
      Term simplified_Not_Xor = new Nand(new Or(new Not(notTerm.TermLinks),
          new Not(notTerm.TermRechts)),
          new Or(notTerm.TermLinks, notTerm.TermRechts));
      // weiter mit der Rekursion (5)
      simplifiedTerm = nnf_expr(simplified_Not_Xor);
    }

    // T hat die Form mnot(TE)? (4.N)
    else if (notTerm.getClass() == Not.class) {
      // Vereinfache zu TE (5.N)
      Term simplified_Not_Not = notTerm.TermLinks;
      // weiter mit der Rekursion (5)
      simplifiedTerm = nnf_expr(simplified_Not_Not);
    }
  }
  return simplifiedTerm;
}
```