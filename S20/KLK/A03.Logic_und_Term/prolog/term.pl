% #################################################################
%
% Lösung des zweiten Teils der dritten Aufgabe 
%
% Autor: Jannik Stuckstätte
% Autor: Hani Alshikh
%
% #################################################################


% *******************
% 1. is_atomic_expr *
% *******************

%
% erkennt, ob Term eine atomare logische Aussage ist
%
is_atomic_expr(Term) :- var(Term).



% *******************
% 2. is_literal     *
% *******************

%
% erkennt, ob Term ein Literal ist.
%
is_literal(Term) :- is_atomic_expr(Term).
is_literal(Term) :- Term =.. [mnot | [A]], is_atomic_expr(A). 



% *******************
% 3. nnf_expr       *
% *******************

%
% vereinfacht einen aussagenlogischen Term zu einem neuen aussagenlogischen Term Simplified,
% der die Negationsnormalform darstellt.
%
nnf_expr(Term, Simplified) :- 
  is_literal(Term), !, Simplified = Term.                             % ist literal? es gibt nichts zu machen. 

nnf_expr(Term, Simplified) :-                                         % Term hat Form (X → Y), (X ↔ Y) oder die Negation davon?
  simply_def(Term, Simplified_Def),                                   % Vereinfachen.
  nnf_simplified(Simplified_Def, Simplified).                         % Nein, weiter auf NNF vereinfachen

nnf_simplified(Term, Simplified) :- 
  is_literal(Term), !, Simplified = Term.                             % ist (die Vereinfachung) literal? es gibt nichts zu machen. 

%------------------------------------------OR-------------------------------------------%

nnf_simplified(Term, Simplified) :- 
  Term =.. [mnot | [Term_Mnot]],                                      % Term hat die Form (¬T)?
  Term_Mnot =.. [or | [X, Y]],                                        % T hat die Form (X V Y)?
  Simplified_Mnot_Or = and(mnot(X), mnot(Y)),                         % Vereinfache zu (¬X ∧ ¬Y) 
  nnf_expr(Simplified_Mnot_Or, Simplified), !.                        % weiter mit der Rekursion

nnf_simplified(Term, Simplified) :- 
  Term =.. [nor | [X, Y]],                                            % Term hat die Form (X nor Y)?
  Simplified_Nor = mnot(or(X, Y)),                                    % Mache daraus ¬(X V Y)
  nnf_expr(Simplified_Nor, Simplified), !.                            % weiter mit der Rekursion

nnf_simplified(Term, Simplified) :- 
  Term =.. [mnot | [Term_Mnot]],                                      % Term hat die Form (¬T)?                      
  Term_Mnot =.. [nor | [X, Y]],                                       % T hat die Form (X nor Y)?                       
  Simplified_Mnot_Nor = or(X, Y),                                     % Vereinfache zu or(X V Y)                      
  nnf_expr(Simplified_Mnot_Nor, Simplified), !.                       % weiter mit der Rekursion                       

nnf_simplified(Term, Simplified) :- 
  Term =.. [or | [X, Y]],                                             % Term hat die Form (X V Y)?                      
  nnf_expr(X, Simplified_X), !,                                       % X ist Term?                      
  nnf_expr(Y, Simplified_Y), !,                                       % Y ist Term?                       
  Simplified = or(Simplified_X, Simplified_Y), !.                     % gebe (X V Y) vereinfacht zurück                    

%------------------------------------------AND-------------------------------------------%

nnf_simplified(Term, Simplified) :- 
  Term =.. [and | [X, Y]],                                            % Term hat die Form (X ∧ Y)?                      
  nnf_expr(X, Simplified_X), !,                                       % X ist Term?                     
  nnf_expr(Y, Simplified_Y), !,                                       % Y ist Term?                      
  Simplified = and(Simplified_X, Simplified_Y), !.                    % gebe (X ∧ Y) vereinfacht zurück                      

nnf_simplified(Term, Simplified) :- 
  Term =.. [mnot | [Term_Mnot]],                                      % Term hat die Form (¬T)?                        
  Term_Mnot =.. [nand | [X, Y]],                                      % T hat die Form (X nand Y)?                        
  Simplified_Mnot_Nand = and(X, Y),                                   % Vereinfache zu (X ∧ Y)                       
  nnf_expr(Simplified_Mnot_Nand, Simplified), !.                      % weiter mit der Rekursion                                

nnf_simplified(Term, Simplified) :- 
  Term =.. [nand | [X, Y]],                                           % Term hat die Form (X nand Y)?                      
  Simplified_Nand = mnot(and(X, Y)),                                  % mache ¬((X ∧ Y)) daraus                    
  nnf_expr(Simplified_Nand, Simplified), !.                           % weiter mit der Rekursion  

nnf_simplified(Term, Simplified) :- 
  Term =.. [mnot | [Term_Mnot]],                                      % Term hat die Form (¬T)?                        
  Term_Mnot =.. [and | [X, Y]],                                       % T hat die Form (X ∧ Y)?                      
  Simplified_Mnot_And = or(mnot(X), mnot(Y)),                         % Vereinfache zu (¬X V ¬Y)                       
  nnf_expr(Simplified_Mnot_And, Simplified), !.                       % weiter mit der Rekursion                                                                

%------------------------------------------XOR-------------------------------------------%

nnf_simplified(Term, Simplified) :- 
  Term =.. [mxor | [X, Y]],                                           % Term hat die Form (X ⊕ Y)?                     
  Simplified_Xor = and(or(mnot(X), mnot(Y)), or(X, Y)),               % vereinfache zu (¬X V ¬Y) ∧ (X V Y)                   
  nnf_expr(Simplified_Xor, Simplified), !.                            % weiter mit der Rekursion                  

nnf_simplified(Term, Simplified) :- 
  Term =.. [mnot | [Term_Mnot]],                                      % Term hat die Form (¬T)?                       
  Term_Mnot =.. [mxor | [X, Y]],                                      % T hat die Form (X ⊕ Y)?                     
  Simplified_Xor = nand(or(mnot(X), mnot(Y)), or(X, Y)),              % Vereinfache zu (¬X V ¬Y) nand (X V Y)                        
  nnf_expr(Simplified_Xor, Simplified), !.                            % weiter mit der Rekursion                       

%------------------------------------------NOT(NOT)--------------------------------------%

nnf_simplified(Term, Simplified) :- 
  Term =.. [mnot | [Term_Mnot]],                                      % Term hat die Form mnot(T)?                      
  Term_Mnot =.. [mnot | [Term_Mnot_Mnot]], !,                         % T hat die Form mnot(TE)?                  
  Simplified_Mnot_Mnot = Term_Mnot_Mnot,                              % Vereinfache zu TE                      
  nnf_expr(Simplified_Mnot_Mnot, Simplified), !.                      % weiter mit der Rekursion


% *******************
% 3.1. simply_def   *
% *******************

%
% Hilfsprädikat, um die Implikation bzw. die Äquivalenz umzuformen.
%
simply_def(Term, Simplified) :- 
  Term =.. [mnot | [Term_Mnot]],                                      % Term hat die Form (¬T)?
  Term_Mnot =.. [impl | [X, Y]],                                      % T hat die Form (X → Y)?
  Simplified = nor(mnot(X), Y), !.                                    % Vereinfache zu (¬X nor Y) 

simply_def(Term, Simplified) :- 
  Term =.. [impl | [X, Y]],                                           % Term hat die Form impl(X, Y)?
  Simplified = or(mnot(X), Y), !.                                     % Vereinfache zu (¬X V Y)

simply_def(Term, Simplified) :- 
  Term =.. [mnot | [Term_Mnot]],                                      % Term hat die Form mnot(T)?
  Term_Mnot =.. [aequiv | [X, Y]],                                    % T hat die Form aequiv(X, Y)?
  Simplified = mnot(or(and(X, Y), and(mnot(X), mnot(Y)))), !.         % Vereinfache zu ¬((X ∧ Y) V (¬X ∧ ¬Y))
  
simply_def(Term, Simplified) :- 
  Term =.. [aequiv | [X, Y]],                                         % Term hat die Form aequiv(X, Y)?
  Simplified = or(and(X, Y), and(mnot(X), mnot(Y))), !.               % Vereinfache zu (X ∧ Y) V (¬X ∧ ¬Y)

simply_def(Simplified, Simplified) :- !.                              % Term ist vereinfacht.



% *******************
% 4. is_clause      *
% *******************

%
% testet, ob Term eine Klausel ist.
%
is_clause(Term) :- is_literal(Term), !.                               % Rekursionabbruch 
is_clause(Term) :- 
  Term =.. [or | [X, Y]],                                             % term hat Form or(x,y) 
  is_clause(X), !,                                                    % Rekursive X prüfen
  is_clause(Y), !.                                                    % Rekursive Y prüfen



% *******************
% 5. is_clause      *
% *******************

%
% testet, ob Term eine Horn-Klausel ist 
%
is_horn_clause(Term) :- 
  is_clause(Term),                                                    % ist Term erstmals in Clause Form?
  clause_count_positive(Term, 1), !.                                  % Zähle die positiven Variablen

clause_count_positive(Term, Positive_Count)  :- 
  clause_count_positive(Term, 0, Positive_Count).                     % Akkumulator initialisieren

clause_count_positive_end(Positive_Count_Accu, Positive_Count_Accu).  % Rekursionabbruch

clause_count_positive(Term, Positive_Count_Accu, Positive_Count) :- 
  is_atomic_expr(Term), !,                                            % ist positive?
  Positive_Count_Accu_New is Positive_Count_Accu + 1,                 % Akkumulator++
  clause_count_positive_end(Positive_Count_Accu_New, Positive_Count). % Setze Positive_Cunt = Akkumulator

clause_count_positive(Term, Positive_Count_Accu, Positive_Count) :- 
  is_literal(Term), !,                                                % ist negiert (ansonsten wäre er in obiges Prädikate gegangen)?
  clause_count_positive_end(Positive_Count_Accu, Positive_Count).     % Setze Positive_Cunt = Akkumulator

clause_count_positive(Term, Positive_Count_Accu, Positive_Count) :- 
  Term =.. [or | [X, Y]],                                             % Ist Term in Form or(X, Y) ? 
  clause_count_positive(X, 0, Positive_Count_Accu_X),                 % Zähle positive Literale in X
  clause_count_positive(Y, 0, Positive_Count_Accu_Y),                 % Zähle positive Literale in Y
  Positive_Count_Accu_New is Positive_Count_Accu + Positive_Count_Accu_X + Positive_Count_Accu_Y,   % summiere alle positive Literale
  clause_count_positive_end(Positive_Count_Accu_New, Positive_Count). % Setze Positive_Cunt = Positive_Count_Accu_New 