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




% del_element
del_element(_DeletePosition, _DeleteElement, [], OutList) :- OutList = []. % Leere Liste bleibt leer

del_element(DeletePosition, DeleteElement, InList, OutList) :- 
    (DeletePosition = e, del_first_element(DeleteElement, InList, OutList)), !; % Position == e ib das erste Ergebnis in del_first_element und prüfe keine Alternative
    (DeletePosition = l, del_last_element(DeleteElement, InList, OutList)), !; % Position == l ib das erste Ergebnis in del_last_element und prüfe keine Alternative
    (DeletePosition = a, del_all_elements(DeleteElement, InList, OutList)), !. % Position == a ib das erste Ergebnis in del_all_elements und prüfe keine Alternative

del_first_element(DeleteElement, InList, OutList) :- del_first_element(DeleteElement, InList, OutList, [], 0), !. % Initialisierung ; hier nur ein valides Ergebnis, daher Cut.

del_first_element(_DeleteElement, [], OutListAcc, OutListAcc, _ElementRemoved). % Rekursion beenden und Ausgabelist "zurückschreiben"
del_first_element(DeleteElement, [InListHead | InListRest], OutList, OutListAcc, ElementRemoved) :- 
    (
        InListHead = DeleteElement,              % Derzeitiges ELement == Element zu löschen ? 
        ElementRemoved = 0,                      % Bisher noch kein Element gelöscht?
        del_first_element(DeleteElement, InListRest, OutList, OutListAcc, 1) % Dann gehe einfach weiter in der Schleife und setze "ElementRemoved" auf 1
    )
    ;
    (
        % Ansonsten füge derzeitiges Element in Ergebnisliste und gehe dann weiter in der Schleife
        append(OutListAcc, [InListHead], NewOutListAcc),
        del_first_element(DeleteElement, InListRest, OutList, NewOutListAcc, ElementRemoved)
    ).


del_last_element(DeleteElement, InList, OutList) :- 
    count_occurrences(DeleteElement, InList, NumberOfOccurrences), % Zähle Anzahl an Vorkommen von DeleteElement in InList
    del_last_element(DeleteElement, InList, OutList, [], NumberOfOccurrences, 0), !.  % Initialisierung der Akkumulatoren (AusgabeListe und Counter für Vorkommen) und Übergabe Anzahl an Vorkommen; hier nur ein valides Ergebnis, daher Cut.

del_last_element(_DeleteElement, [], OutListAcc, OutListAcc, _NumberOfOccurrences, _CounterAcc). % Rekursion beenden und Ausgabelist "zurückschreiben"

del_last_element(DeleteElement, [InListHead | InListRest], OutList, OutListAcc, NumberOfOccurrences, NumberOfOccurrencesAcc) :- 
    (
        InListHead = DeleteElement, % Derzeitiges Element == zu löschendes Element
        NewNumberOfOccurrencesAcc is NumberOfOccurrencesAcc + 1, % Erhöhe Anzahl der geprüften Vorkommen
        (
            (
                NewNumberOfOccurrencesAcc = NumberOfOccurrences, % Anzahl an geprüften Vorkommen == Anzahl an Vorkommen ?
                del_last_element(DeleteElement, InListRest, OutList, OutListAcc, NumberOfOccurrences, NewNumberOfOccurrencesAcc) % Dann gehe einfach weiter in der Schleife
            )
            ;
            (
                % Wenn nicht, dann füge aktuelles Element an die Ausgabeliste und gehe dann weiter in der Schleife
                append(OutListAcc, [InListHead], NewOutListAcc),
                del_last_element(DeleteElement, InListRest, OutList, NewOutListAcc, NumberOfOccurrences, NewNumberOfOccurrencesAcc)
            )
        )
    )
    ;
    (
        % Wenn derzeitiges Element nicht das zu löschende Element ist, dann füge es in die Ausgabeliste und gehe einfach weiter
        append(OutListAcc, [InListHead], NewOutListAcc),
        del_last_element(DeleteElement, InListRest, OutList, NewOutListAcc, NumberOfOccurrences, NumberOfOccurrencesAcc)
    ).

count_occurrences(ElementToCount, InList, NumberOfOccurrences) :- 
    count_occurrences(ElementToCount, InList, NumberOfOccurrences, 0), !. % Initalisierung d. Akkumulators

count_occurrences(_ElementToCount, [], NumberOfOccurrencesAcc, NumberOfOccurrencesAcc). % Rekursionsabbruch
count_occurrences(ElementToCount, [InListHead | InListRest], NumberOfOccurrences, NumberOfOccurrencesAcc) :- 
    (
        % Eröhe Akkumulator um eins jedes Mal wenn aktuelles Element == zu zählendes Element
        InListHead = ElementToCount,
        NewNumberOfOccurrencesAcc is NumberOfOccurrencesAcc + 1,
        count_occurrences(ElementToCount, InListRest, NumberOfOccurrences, NewNumberOfOccurrencesAcc)
    )
    ;
    % Ansonsten gehe zum nächsten Element
    count_occurrences(ElementToCount, InListRest, NumberOfOccurrences, NumberOfOccurrencesAcc).

del_all_elements(DeleteElement, InList, OutList) :- diffList(InList, [DeleteElement], OutList). % Erstelle eine liste aus DeleteElement und rufe diffList auf.

%_______________________________________________________________________________________________________

% substitute

substitute(_DeletePosition, _DeleteElement, _ReplaceElement, [], OutList) :- OutList = [].

substitute(DeletePosition, DeleteElement, ReplaceElement, InList, OutList) :- 
    (DeletePosition = e, substitute_first_element(DeleteElement, ReplaceElement, InList, OutList)), !;  % Position == e dann ib das erste Ergebnis in  substitute_first_element und prüfe keine Alternativen
    (DeletePosition = l, substitute_last_element(DeleteElement, ReplaceElement, InList, OutList)), !; % Position == l dann gib das erste Ergebnis in substitute_last_element und prüfe keine Alternativen
    (DeletePosition = a, substitute_all_elements(DeleteElement, ReplaceElement, InList, OutList)), !. % Position == a dann gib das erste Ergebnis in substitute_all_elements und prüfe keine Alternativen

substitute_first_element(DeleteElement, ReplaceElement, InList, OutList) :- 
    substitute_first_element(DeleteElement, ReplaceElement, InList, OutList, [], 0), !. % Initialisierung ; hier nur ein valides Ergebnis, daher Cut.

substitute_first_element(_DeleteElement, _ReplaceElement, [], OutListAcc, OutListAcc, _ElementRemoved). % Rekursion beenden

substitute_first_element(DeleteElement, ReplaceElement, [InListHead | InListRest], OutList, OutListAcc, ElementRemoved) :- 
    (
        InListHead = DeleteElement, % Derzeitiges ELement == Element zu löschen ? 
        ElementRemoved = 0, % Bisher noch kein Element gelöscht?
        append(OutListAcc, [ReplaceElement], NewOutListAcc), % Hänge das Ersatzelement an Liste an
        substitute_first_element(DeleteElement, ReplaceElement, InListRest, OutList, NewOutListAcc, 1) % Gehe weiter und setze "ElementRemoved" auf 1
    )
    ;
    (
        % Ansonsten füge derzeitiges Element in Ergebnisliste und gehe dann weiter in der Schleife
        append(OutListAcc, [InListHead], NewOutListAcc),
        substitute_first_element(DeleteElement, ReplaceElement, InListRest, OutList, NewOutListAcc, ElementRemoved)
    ).


substitute_last_element(DeleteElement, ReplaceElement, InList, OutList) :- 
    count_occurrences(DeleteElement, InList, NumberOfOccurrences), % Zähle Anzahl an Vorkommen von DeleteElement in InList
    substitute_last_element(DeleteElement, ReplaceElement, InList, OutList, [], NumberOfOccurrences, 0), !. % Initialisierung der Akkumulatoren (AusgabeListe und Counter für Vorkommen) und Übergabe Anzahl an Vorkommen; hier nur ein valides Ergebnis, daher Cut.

substitute_last_element(_DeleteElement, _ReplaceElement, [], OutListAcc, OutListAcc, _NumberOfOccurrences, _CounterAcc). % Rekursion beenden und Ausgabelist "zurückschreiben"

substitute_last_element(DeleteElement, ReplaceElement, [InListHead | InListRest], OutList, OutListAcc, NumberOfOccurrences, NumberOfOccurrencesAcc) :- 
    (
        InListHead = DeleteElement, % Derzeitiges Element == zu löschendes Element
        NewNumberOfOccurrencesAcc is NumberOfOccurrencesAcc + 1, % Erhöhe Anzahl der geprüften Vorkommen
        (
            (
                NewNumberOfOccurrencesAcc = NumberOfOccurrences, % Anzahl an geprüften Vorkommen == Anzahl an Vorkommen ?
                append(OutListAcc, [ReplaceElement], NewOutListAcc), % Dann füge Ersatzelement an Ausgabeliste an
                substitute_last_element(DeleteElement, ReplaceElement, InListRest, OutList, NewOutListAcc, NumberOfOccurrences, NewNumberOfOccurrencesAcc) % Dann gehe einfach weiter in der Schleife
            )
            ;
            (
                % Wenn nicht, dann füge aktuelles Element an die Ausgabeliste und gehe dann weiter in der Schleife
                append(OutListAcc, [InListHead], NewOutListAcc),
                substitute_last_element(DeleteElement, ReplaceElement, InListRest, OutList, NewOutListAcc, NumberOfOccurrences, NewNumberOfOccurrencesAcc)
            )
        )
    )
    ;
    (
        % Wenn derzeitiges Element nicht das zu löschende Element ist, dann füge es in die Ausgabeliste und gehe einfach weiter
        append(OutListAcc, [InListHead], NewOutListAcc),
        substitute_last_element(DeleteElement, ReplaceElement, InListRest, OutList, NewOutListAcc, NumberOfOccurrences, NumberOfOccurrencesAcc)
    ).

substitute_all_elements(DeleteElement, ReplaceElement, InList, OutList) :- 
    substitute_all_elements(DeleteElement, ReplaceElement, InList, OutList, []), !. % erstelle eine AkkuListe für OutList

substitute_all_elements(_DeleteElement, _ReplaceElement, [], OutListAcc, OutListAcc). % Rekursionsabbruch

substitute_all_elements(DeleteElement, ReplaceElement, [InListHead | InListRest], OutList, OutListAcc) :-
    (
        DeleteElement = InListHead, % Daten des Listenelements von Inliste (InListHead) == DeleteElement?
        append(OutListAcc, [ReplaceElement], NewOutListAcc), % Ja, dann ersetze das Element mit ReplaceElement
        substitute_all_elements(DeleteElement, ReplaceElement, InListRest, OutList, NewOutListAcc) % weiter mit der Rekursion
    )
    ;
    (
        append(OutListAcc, [InListHead], NewOutListAcc), % Nein, lasse das Element InListHead unverändert
        substitute_all_elements(DeleteElement, ReplaceElement, InListRest, OutList, NewOutListAcc) % weiter mit der Rekursion
    ).