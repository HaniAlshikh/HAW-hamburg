% Autor:
% Datum: 30.07.2015

test(collect_stueck) :-
    write('collect_stueck/1 '),
    collect_stueck([('Concerto grosso op.6 Nr.7', 'B-dur'),
                    ('Feuerwerks-Musik (Concerto grosso)', 'D-dur'),
                    ('Concerto grosso op.6 Nr.8', 'c-moll'),
                    ('Sinfonia e-moll (aus `Der Messias\')', 'e-moll'),
                    ('Konzert fuer Violine und Orchester Nr.1', 'D-dur'),
                    ('Konzert fuer Violine und Orchester Nr.2', 'g-moll'),
                    ('Der Fruehling', 'E-dur'),
                    ('Der Sommer', 'g-moll'),
                    ('Der Herbst', 'F-dur'),
                    ('Der Winter', 'f-moll'),
                    ('Sinfonia D-Dur mit Dudelsack und Drehleier', 'D-dur')|Rest]),
                    length(Rest,81),write('.'),
                    member(('Sinfonie Nr.4 G-dur', 'G-dur'),Rest),write('.'),
                    \+ member(('Slawische Taenze op.46', _TONART),Rest),write('.'),
                    \+ member(('error', 'exception'),Rest),
                    write('.'),!,writeln('ok').

test(collect_gs) :-
    write('collect_gs '),
    collect_gs([(103, 100)|Rest]),
               length(Rest,5),write('.'),
               member((135, 118),Rest),write('.'),
               \+ member((null, 88),Rest),
               write('.'),!,writeln('ok').

test(collect_st) :-
    write('collect_st '),
    collect_st(['Beethoven','Händel'],
               [('Beethoven', 'Die Geschöpfe des Prometheus, Ouvertüre'),
                ('Beethoven', 'Fidelio, Ouvertüre')|Rest]),
                length(Rest,28),write('.'),
                member(('Beethoven', 'Coriolan, Ouvertüre'),Rest),write('.'),
                member(('Händel', 'Concerto grosso op.6 Nr.5'),Rest),write('.'),
                \+ member((_KOMPONIST, 'Cantate Domine - Motet'),Rest),
                write('.'),!,writeln('ok').


test(collect_komp) :-
    write('collect_komp/2 '),
    collect_komp('Violine',
                 [('Serge', 'Prokofiev')|Rest]),
                  length(Rest,6),write('.'),
                  member(('Gustav', 'Mahler'),Rest),write('.'),
                  member(('Felix', 'Mendelssohn Bartholdy'),Rest),write('.'),
                  \+ member(('Benedetto', 'Marcello'),Rest),
                  write('.'),!,writeln('ok').

test(collect_time) :-
    write('collect_time '),
    collect_time([('Georg Friedrich','Händel',455)|Rest]),
                 length(Rest,39),write('.'),
                 member(('Johann Sebastian','Bach',198),Rest),write('.'),
                 member(('Clement','Janequin',63),Rest),write('.'),
                 \+ member(('Leopold', 'Mozart', 344),Rest),
                 \+ member(('Georg Friedrich', 'Händel', 8159),Rest),
                 write('.'),!,writeln('ok').

test(allcds) :-
    test(collect_stueck),
    test(collect_gs),
    test(collect_st),
    test(collect_komp),
    test(collect_time)
    .

test(nachkomme) :-
    %positiv
    write('nachkomme +'),
    nachkomme(helga,eve), write('.'),!,
    nachkomme(susanne,klara),
    %negativ
    write('. -'),!,
    \+ nachkomme(helga,marion), write('.'),!,
    \+ nachkomme(kruemmel,klara),!, writeln('ok').

test(vorfahre) :-
    %positiv
    write('vorfahre +'),
    vorfahre(eve,helga), write('.'),!,
    vorfahre(klara,susanne),
    %negativ
    write('. -'),!,
    \+ vorfahre(marion,helga), write('.'),!,
    \+ vorfahre(klara,kruemmel),!, writeln('ok').

test(nachkommen) :-
    %positiv
    write('nachkommen +'),
    nachkommen(eve,Nachommen), write('.'),!,
    length(Nachommen,46), write('.'),!,
    member(vera,Nachommen), write('.'),!,
    member(bert,Nachommen), write('.'),!,
    nachkommen(bert,[]),
    %negativ
    write('. -'),!,
    \+ length(Nachommen,164), write('.'),!,
    \+ member(kruemmel,Nachommen), write('.'),!,
    \+ nachkommen(elke,[_X|_Y]),!, writeln('ok').

test(geschwister) :-
    %positiv
    write('geschwister +'),
    geschwister(herbert,johanna), write('.'),!,
    geschwister(elsa,anton),
    %negativ
    write('. -'),!,
    \+ geschwister(udo,thea), write('.'),!,
    \+ geschwister(elmo,kruemmel),!, writeln('ok').

test(bruder) :-
    %positiv
    write('bruder +'),
    bruder(hubert,karl), write('.'),!,
    bruder(hubert,inge),
    %negativ
    write('. -'),!,
    \+ bruder(manfred,otto), write('.'),!,
    \+ bruder(thea,anton),!, writeln('ok').

test(schwester) :-
    %positiv
    write('schwester +'),
    schwester(inge,johanna), write('.'),!,
    schwester(inge,hubert),
    %negativ
    write('. -'),!,
    \+ schwester(hubert,inge), write('.'),!,
    \+ schwester(lisa,klara),!, writeln('ok').

test(eheleute) :-
    %positiv
    write('eheleute +'),
    eheleute(xaver,vera), write('.'),!,
    eheleute(lisa,otto),
    %negativ
    write('. -'),!,
    \+ eheleute(xaver,xaver), write('.'),!,
    \+ eheleute(otto,helen), write('.'),!,
    \+ eheleute(helen,otto),!, writeln('ok').

test(onkel) :-
    %positiv
    write('onkel +'),
    onkel(dieter,otto), write('.'),!,
    onkel(anton,christa), write('.'),!,
    onkel(helmut,helen),
    %negativ
    write('. -'),!,
    \+ onkel(wall-e,alois), write('.'),!,
    \+ onkel(ulrike,christa), write('.'),!,
    \+ onkel(alois,hugo),!, writeln('ok').

 test(tante) :-
    %positiv
    write('tante +'),
    tante(renate,otto), write('.'),!,
    tante(ulrike,christa), write('.'),!,
    tante(johanna,helen),
    %negativ
    write('. -'),!,
    \+ tante(eve,alois), write('.'),!,
    \+ tante(anton,christa), write('.'),!,
    \+ tante(thea,susanne),!, writeln('ok').

test(oma) :-
    %positiv
    write('oma +'),
    oma(maria,alois), write('.'),!,
    oma(eve,jakob), write('.'),!,
    oma(helen,steffi),
    %negativ
    write('. -'),!,
    \+ oma(eve,alois), write('.'),!,
    \+ oma(magda,steffi), write('.'),!,
    \+ oma(hubert,thea),!, writeln('ok').

test(opa) :-
    %positiv
    write('opa +'),
    opa(wall-e,manfred), write('.'),!,
    opa(hubert,anton),
    %negativ
    write('. -'),!,
    \+ opa(eve,jakob), write('.'),!,
    \+ opa(xaver,bert), write('.'),!,
    \+ opa(krimhild,magda),!, writeln('ok').

test(uroma) :-
    %positiv
    write('uroma +'),
    uroma(eve,alois), write('.'),!,
    uroma(krimhild,steffi),
    %negativ
    write('. -'),!,
    \+ uroma(wall-e,alois), write('.'),!,
    \+ uroma(vera,steffi), write('.'),!,
    \+ uroma(hubert,lutz),!, writeln('ok').

test(uropa) :-
    %positiv
    write('uropa +'),
    uropa(wall-e,magda), write('.'),!,
    uropa(hubert,lutz),
    %negativ
    write('. -'),!,
    \+ uropa(eve,magda), write('.'),!,
    \+ uropa(xaver,lutz), write('.'),!,
    \+ uropa(krimhild,steffi),!, writeln('ok').

test(maenUweibl) :-
    write('maenUweibl '),
    maenUweibl([zummsel]),!, writeln('ok').

test(verhKor) :-
    write('verhKor '),
    verhKor([ Tupel ]),!,
    member(Tupel,[ (helge,elisa),(elisa,helge)]),!, writeln('ok').

test(elterVoll) :-
    write('elterVoll '),
    elterVoll([ Tupel ]),!,
    member(Tupel,[ (elmo,kruemmel),(kruemmel,elmo)]),!, writeln('ok').

test(wurzel) :-
    write('wurzel '),
    list_to_ord_set([elisa, wall-e, eve, helge, zummsel],SetA),
    wurzel(Liste),!,
    list_to_ord_set(Liste,SetB),
    ord_seteq(SetA,SetB),!, writeln('ok').

test(allfamilie) :-
    test(nachkomme),
    test(vorfahre),
    test(nachkommen),
    test(geschwister),
    test(bruder),
    test(schwester),
    test(eheleute),
    test(onkel),
    test(tante),
    test(oma),
    test(opa),
    test(uroma),
    test(uropa),
    test(maenUweibl),
    test(verhKor),
    test(elterVoll),
    test(wurzel)
    .

% Druckt eine Liste in Zeilen aus
ppList([Elem|Rest]) :- writeln(Elem),ppList(Rest).
ppList([]).