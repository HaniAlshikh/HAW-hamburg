% #################################################################
%
% Lösung des ersten Teils der zweiten Aufgabe
%
% Autor: Jannik Stuckstätte
% Autor: Hani Alshikh
%
% #################################################################



% *******************
% 1.is_a_list      *
% *******************

%
% Testen, ob L (die Eingabe) eine Prolog-Liste ist.
%
is_a_list([]).                                              % Rekursionsabbruch
is_a_list([_|Rest]) :- is_a_list(Rest).                     % Rekursionsaufruf, solang L Daten
                                                            % und Referenz auf nächstes Element enthält



% *******************
% 2. diffList       *
% *******************

%
% Aus L1 und L2 die Differenzenliste L3 bilden.
%
diffList(L1, L2, L3) :- diffList(L1, L2, [], L3).           % Diff/AkkuListe erstellen
diffList([], _, AkkuList, AkkuList).                        % Rekursionsabbruch
diffList([L1|Rest], L2, AkkuList, L3) :-                    % Hole Referenz auf Inhalt des ersten Elements von InList (L1)
    (
        member(L1, L2),                                     % Inhalt des Elements in LoeschListe (L2) enthalten?
        diffList(Rest, L2, AkkuList, L3)                    % Ja, löschen und weiter mit der Rekursion
    ) ;
    (
        \+ member(L1, L2),                                  % Nein,
        append(AkkuList, [L1], NewAkkuList),                % füge Inhalt des Elements in Diff/AkkuListe ein 
        diffList(Rest, L2, NewAkkuList, L3)                 % weiter mit der Rekursion 
    ).



% *******************
% 3. infix          *
% *******************

%
% Testen, ob die Liste I ein echter Infix der Liste L ist.
%
infix(I, [_|L]) :-                                          % Entferne das erste Element aus InListe (L)
    I \== [],                                               % Leere Liste ist kein Infix
    infix(I, I, L).                                         % erstelle eine Kopie von I
infix(_, [], [_|_]).                                        % Rekursionsabbruch
infix(I, [HI|IT], [HL|LT]) :-                               % Wähle das erste Element aus I und L und lösche es daraus
    (
        HI == HL,                                           % sind beide Elemente gleich?
        infix(I, IT, LT)                                    % ja, weiter mit der Rukersion
    ) ;
    (
        infix(I, I, LT)                                     % Nein, I zurücksetzen und weiter mit der Rukersion
    ).



% *******************
% 4. suffix         *
% *******************

%
% Testen, ob die Liste S ein echter Suffix der Liste L ist.
%
suffix(S, [_|L]) :-                                         % Entferne das erste Element aus InListe (L)
    S \== [],                                               % Leere Liste ist kein Suffix
    suffix(S, S, L).                                        % erstelle eine Kopie von S
suffix(_, [], []).                                          % Rekursionsabbruch
suffix(S, [HS|ST], [HL|LT]) :-                              % Wähle das erste Element aus S und L und lösche es daraus 
    (
        HS == HL,                                           % sind beide Elemente gleich?
        suffix(S, ST, LT)                                   % ja, weiter mit der Rukersion
    ) ;
    (
        suffix(S, S, LT)                                    % Nein, S zurücksetzen und weiter mit der Rukersion
    ).



% *******************
% 5. praefix        *
% *******************

%
% Testen, ob die Liste P ein echtes Präfix der Liste L ist
%
praefix(P, L) :-
    P \== [],                                               % Leere Liste ist kein Präfix
    private_praefix(P, L).                                  % Muss gemacht werden, da der Rekursionsabbruch 
                                                            % die vorherige Bedinung wiederspricht.
private_praefix([], [_|_]).                                 % Rekursionsabbruch
private_praefix([HP|PT], [HL|LT]) :-                        % Wähle das erste Element aus P und L und lösche es daraus  
    HP == HL,                                               % sind beide Elemente gleich?
    private_praefix(PT, LT).                                % ja, weiter mit der Rukersion



% *******************
% 6. eo_count       *
% *******************

%
% zählt rekursiv die Anzahl der in der Länge geraden 
% bzw. ungeraden Listen in der Liste L inklusive dieser Listen selbst, 
% also alle möglichen Listen! Eine leere Liste wird als
% Liste mit gerader Länge angesehen. 
% In der Liste können Elemente vorkommen, die keine Liste sind.
%
eo_count(L, Even, Odd) :- eo_count(L, 0, Even, 0, Odd, 0). % AkkuEven, AkkuOdd und Counter mit 0 initialisieren
eo_count([], AkkuEven, AkkuEven, AkkuOdd, AkkuOdd).        % Rekursionsabbruch
eo_count([], AkkuEven, Even, AkkuOdd, Odd, Counter) :-     % Hat die Liste gerade oder ungerade Anzahl an Elemente?
    (
        0 is mod(Counter, 2),                              % gerade
        NewAkkuEven is AkkuEven + 1,                       % Even++
        eo_count([], NewAkkuEven, Even, AkkuOdd, Odd)      % Rekursion beenden
    ) ;                                                    % ungerade
    (
        NewAkkuOdd is AkkuOdd + 1,                         % Odd++
        eo_count([], AkkuEven, Even, NewAkkuOdd, Odd)      % Rekursion beenden

    ).
eo_count([H|T], AkkuEven, Even, AkkuOdd, Odd, Counter) :-
    NewCounter is Counter + 1,                             % die Elemente der Liste zählen Counter++
    (
        (
            is_a_list(H),                                  % Element von InListe (L) Ist eine Liste?
            eo_count(H, InnerListEven, InnerListOdd),      % Ja, Rekursiv eo_count für das Element aufrufen
            NewAkkuEven is AkkuEven + InnerListEven,       % Even += Even vom Element
            NewAkkuOdd is AkkuOdd + InnerListOdd,          % Odd += Odd vom Element
            eo_count(T, NewAkkuEven, Even, NewAkkuOdd, Odd, NewCounter) % weiter mit der Rekusion
        ) ;
        (
            eo_count(T, AkkuEven, Even, AkkuOdd, Odd, NewCounter) % Nein, weiter mit der Rekusion
        )
    ).



% *******************
% 7. del_element    *
% *******************

%
% löscht E aus der Liste L an relativer Position P und gibt die neue Liste R zurück.
% P bezeichnet die Position: e erstes, l letztes, a alle Vorkommen
%
del_element(P, E, L, R) :- 
    (P == e, del_first(E, L, R)) ;                         % Je nach Position
    (P == a, del_all(E, L, R)) ;                           % das richtige Prädikat
    (P == l, del_last(E, L, [], R)).                       % aufrufen

del_first(E, [H|T], T) :- E == H.                          % Gebe L Rest, wenn das erste Element gleich E ist

del_all(E, L, R) :- diffList(L, [E], R).

del_last(_, [], AkkuList, AkkuList).
del_last(E, [H|T], AkkuList, R) :- 
    (
        T \= [],
        append(AkkuList, [H], NewAkkuList),
        del_last(E, T, NewAkkuList, R)
    ) ;
    (
        (
            E == H,
            del_last(E, T, AkkuList, R)
        ) ;
        (
            E \= H,
            append(AkkuList, [H], NewAkkuList),
            del_last(E, T, NewAkkuList, R)
        )
    ).




substitute(P, E1, E2, L, R) :- 
    (P == e, substitute_first(E1, E2, L, R)) ;
    (P == a, substitute_all(E1, E2, L, R)) ;
    (P == l, substitute_last(E1, E2, L, [], R)).


substitute_first(E1, E2, [H|T], [E2|T]) :- E1 == H.

substitute_all(E1, E2, L, R) :- 
    substitute_all(E1, E2, L, [], R).

substitute_all(_, _, [], AkkuList, AkkuList).

substitute_all(E1, E2, [H|T], AkkuList, R) :-
    (
        E1 == H,
        append(AkkuList, [E2], NewAkkuList),
        substitute_all(E1, E2, T, NewAkkuList, R)
    ) ;
    (
        append(AkkuList, [H], NewAkkuList),
        substitute_all(E1, E2, T, NewAkkuList, R)
    ).

substitute_last(_, _, [], AkkuList, AkkuList).
substitute_last(E1, E2, [H|T], AkkuList, R) :- 
    (
        T \= [],
        append(AkkuList, [H], NewAkkuList),
        substitute_last(E1, E2, T, NewAkkuList, R)
    ) ;
    (
        E1 == H,
        append(AkkuList, [E2], NewAkkuList),
        substitute_last(E1, E2, T, NewAkkuList, R)
    ).