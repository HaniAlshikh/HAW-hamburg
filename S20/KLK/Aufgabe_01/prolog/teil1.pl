% #################################################################
%
% Lösung des ersten Teils der ersten Aufgabe
%
% Autor: Jannik Stuckstätte
% Autor: Hani Alshikh
%
% #################################################################

% ***************
% Regelen Laden *
% ***************

:- consult(cd).
:- consult(aufg1_test).



% *******************
% 1. collect_stueck *
% *******************

% gibt das Titel und Tonart aller Stücke als Tupel in einer Liste aus.
%
% collect_stueck(Ausgabe).
%
collect_stueck(OutListe) :- 
    findall(
        (TITEL, TONART),                                            % Ausgabe muster. 
        (
            stueck(KNR, SNR, TITEL, TONART, _OPUS),                 % TITEL, TONART suchen.
            KNR \== null,                                           % 
            SNR \== null,                                           % prüfe auf mögliche null-Werte.
            TITEL \== null,                                         % um null-pointer Exception zu verhindern
            TONART \== null                                         %
        ),
        OutListeDop                                                 % Sammelliste aller passenden Tupelen
    ),
    list_to_set(OutListeDop, OutListe).                             % Duplikate löschen.



% ***************
% 2. collect_gs *
% ***************

% gibt alle CD's, deren Gesamtspielzeit zwischen 88 und 342 liegt, 
% als Tupel mit ihrer Nummer in einer Liste ausg.
%
% collect_gs(Ausgabe).
%
collect_gs(OutListe) :- 
    findall(
        (CDNR, GESAMTSPIELZEIT),                                    % Ausgabe muster.
        (
            cd(CDNR, _Name, _HERSTELLER, _ANZ_CDS, GESAMTSPIELZEIT),% Gesamtspielzeit suchen.
            CDNR \== null,                                          % prüfe auf mögliche null-Werte.
            GESAMTSPIELZEIT \== null,                               % um null-pointer Exception zu verhindern
            GESAMTSPIELZEIT >= 88,                                  % sicherstellen, dass die Gesamtspielzeit
            GESAMTSPIELZEIT =< 342                                  % zwischen 88 und 342 liegt
        ),
        OutListeDop                                                 % Sammelliste aller passenden Tupelen
    ),
    list_to_set(OutListeDop, OutListe).                             % Duplikate löschen.



% ***************
% 3. collect_st *
% ***************

% gibt alle Titel der Stücke als Tupel aus,
% die von bestimmten Komponisten komponiert wurden
%
% collect_st(Eingabe, Ausgabe).
%
collect_st(KListe, OutListe) :- 
    findall(
        (NAME, TITEL),                                              % Ausgabe muster.
        (
            member(NAME, KListe),                                   % "iteriere" durch alle gegebnen Komponisten.
            komponist(KNR, _VORNAME, NAME, _GEBOREN, _GESTORBEN),   % den Komponist in der Datenbank suchen.
            KNR \== null,                                           % die Gültigkeit des Eintrags vom Komponist prüfen.
            stueck(SNR, KNR, TITEL, _TONART, _OPUS),                % Stücke des Komponiste anhand deren Nummer suchen.
            SNR \==null                                             % die Gültigkeit des Eintrags vom Stück prüfen.
        ),
        OutListeDop                                                 % Sammelliste aller passenden Tupelen
    ),
    list_to_set(OutListeDop, OutListe).                             % Duplikate löschen.



% *****************
% 4. collect_komp *
% *****************

% gibt alle Komponisten als Tupel aus,
% bei deren Werke ein bestimmtes Instrument mitgewirkt hat.
%
% collect_komp(Eingabe, Ausgabe).
%
collect_komp(Instrument, OutListe) :- 
    findall(
        (VORNAME, NAME),                                            % Ausgabe muster.
        (
            solist(CDNR, SNR, _NAME, Instrument),                   % Suche alle Stücke eines Komponists anhand des Instruments.
            CDNR \== null,                                          % sicherstellen, dass der Eintrag gültig ist.
            SNR \== null,                                           % 
            stueck(SNR, KNR, _TITEL, _TONART, _OPUS),               % Suche die Komponistnummer anhand der Stücknummer.
            KNR \== null,                                           % Sicherstellen, dass der Eintrag gültig ist.
            komponist(KNR, VORNAME, NAME, _GEBOREN, _GESTORBEN),    % Suche den Komponist anhand deren Nummer
            VORNAME \== null,                                       % 
            NAME \== null                                           % sicherstellen, dass der Eintrag Gültigist.
        ),
        (OutListeDop)                                               % Sammelliste aller passenden Tupel
    ),
    list_to_set(OutListeDop, OutListe).                             % Duplikate löschen.



% *****************
% 5. collect_time *
% *****************

% Gibt für alle Komponisten die Gesamtspielzeit aller
% mit ihnen über die Daten in Beziehung stehenden CDs aufaddiert
% und als Tupel in einer Liste aus.
%
% collect_time(Ausgabe).
%
collect_time(OutListe) :- 
    findall(
        (VORNAME, NAME, CDNR, GESAMTSPIELZEIT),                           % Ausgabe muster.
        (
            komponist(KNR, VORNAME, NAME, _GEBOREN, _GESTORBEN),    % Suche alle komponisten in der Datenbank
            KNR \== null,                                           % die Gültigkeit des Eintrags prüfen.
            VORNAME \== null,                                       %
            NAME \== null,                                          %
            stueck(SNR, KNR, _TITEL, _TONART, _OPUS),               % Suche alle Stücker eines Komponisten.
            SNR \== null,                                           % Die Gültigkeit des Eintrags prüfen.
            aufnahme(CDNR, SNR, _ORCHESTER, _LEITUNG),              % Suche alle CDs eines Stücks.
            CDNR \== null,                                          % Die Gültigkeit des Eintrags prüfen.
            cd(CDNR, _NAME, _HERSTELLER, _ANZ_CDS, GESAMTSPIELZEIT),% Gesamtspielzeit einer CD suchen.
            GESAMTSPIELZEIT \== null                                % Die Gültigkeit des Eintrags prüfen.
        ),
        OutListeSingle                                              % Sammelliste aller passenden Tupelen
    ),
    list_to_set(OutListeSingle, OutListeSIngleOhneDop),             % Duplikate löschen.
    sum_time(OutListeSIngleOhneDop, OutListe).                      % Gesamtspielzeit aller in Beziehung stehenden CDs rechnen                  


% sum_time helfendes Pradikat, 
% das die Variablen für die Rekursion vorbereitet.
%
% sum_time(Eingabe, Ausgabe).
%
sum_time(InListe, OutListe) :- sum_time(InListe, [], OutListe, 0).  % Akkumulator Vorbereiten 

% sum_time helfendes Pradikat, 
% das als Abbruchbedingung für die Rekursion dient.
%
% sum_time(Eingabe, Ausgabe).
%
sum_time([], AkkuListe, AkkuListe, _Akku).                          % Eingabe leer? ja, dann abbrechen und die AkkuListe der OutListe zuweisen

% collect_time helfendes Prädikat, 
% das die Spielzeiten der CDS eines Komponisten aufsummiert.
%
% sum_time(Eingabe, AkkuListe, Ausgabe, Akku).
%
sum_time([(VORNAME, NAME, _CDNR, GESAMTSPIELZEIT) | Rest], AkkuListe, OutListe, Akku) :- 
    % Anhand des Komponists (VORNAME, NAME) wird geprüft,
    % ob er noch in Relation mit anderen Werte der GESAMTSPIELZEIT steht.
    % Die Werte der GESAMTSPIELZEIT werden Rekursiv im Akkumulator aufaddiert.
    % Bei der letzten Relation wird dann den Komponist mit der 
    % anhand des Akkumulators entstehenden gesamt Summe 
    % in einer temporären List im Akkumulator gesammelt. 
    % Sobald alle Komponisten geprüft sind (Rest ist dann leer) 
    % ist die Rukursion zu ende.
    (
        member((VORNAME, NAME, _CDNRZwei, _GESAMTSPIELZEITZWEI), Rest), !,     % gibt es weitere Relationen? wenn ja
        NeuAkku is Akku + GESAMTSPIELZEIT,                          % Kalkuliere den neunen Akku. Wert
        sum_time(Rest, AkkuListe, OutListe, NeuAkku)                % und gebe den der Rekursion weiter
    ) ;
    (
        NeuAkku is Akku + GESAMTSPIELZEIT,                          % wenn keine (mehr), Kalkuliere die gesamt Summe
        append(AkkuListe,[(VORNAME, NAME, NeuAkku)],NeuAkkuListe ), % füge die endgültige Relation im Akkumulator ein
        sum_time(Rest, NeuAkkuListe, OutListe, 0)                   % starte die Rekursion neu für den nächsten Komponist
    ).