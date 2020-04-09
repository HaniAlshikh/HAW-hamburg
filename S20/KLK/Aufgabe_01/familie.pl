% elternteil/2
% elternteil(X,Y), X ist Elternteil (Mutter/Vater) von Y
% Ebene 2
elternteil(alois,udo).
elternteil(alois,heinz).
elternteil(thea,udo).
elternteil(thea,heinz).
elternteil(anton,hugo).
elternteil(anton,lutz).
elternteil(anton,steffi).
elternteil(magda,hugo).
elternteil(magda,lutz).
elternteil(magda,steffi).
elternteil(fritz,ulrich).
elternteil(fritz,susanne).
elternteil(fritz,lena).
elternteil(ulrike,ulrich).
elternteil(ulrike,susanne).
elternteil(ulrike,lena).
elternteil(jakob,bert).
elternteil(jakob,christa).
elternteil(jakob,isolde).
elternteil(elsa,bert).
elternteil(elsa,christa).
elternteil(elsa,isolde).
elternteil(jens,elke).
elternteil(jens,helga).
elternteil(jens,erika).
elternteil(anna,elke).
elternteil(anna,helga).
elternteil(anna,erika).
% Ebene 3
elternteil(gunter,ulrike).
elternteil(gunter,jakob).
elternteil(klara,ulrike).
elternteil(klara,jakob).
elternteil(manfred,thea).
elternteil(manfred,elsa).
elternteil(manfred,anton).
elternteil(helen,thea).
elternteil(helen,elsa).
elternteil(helen,anton).
elternteil(otto,alois).
elternteil(otto,fritz).
elternteil(otto,jens).
elternteil(lisa,alois).
elternteil(lisa,fritz).
elternteil(lisa,jens).
elternteil(xaver,anna).
elternteil(xaver,magda).
elternteil(vera,anna).
elternteil(vera,magda).
elternteil(elmo,kruemmel).
% Ebene 4
elternteil(dieter,lisa).
elternteil(ruth,lisa).
elternteil(helmut,xaver).
elternteil(renate,xaver).
elternteil(herbert,otto).
elternteil(maria,otto).
elternteil(hubert,helen).
elternteil(johanna,helen).
elternteil(karl,manfred).
elternteil(inge,manfred).
elternteil(siegfried,vera).
elternteil(krimhild,vera).
% Ebene 5
elternteil(wall-e,dieter).
elternteil(wall-e,ruth).
elternteil(wall-e,gunter).
elternteil(wall-e,klara).
elternteil(wall-e,helmut).
elternteil(wall-e,renate).
elternteil(wall-e,herbert).
elternteil(wall-e,maria).
elternteil(wall-e,hubert).
elternteil(wall-e,johanna).
elternteil(wall-e,karl).
elternteil(wall-e,inge).
elternteil(wall-e,pablo).
elternteil(wall-e,marion).
elternteil(wall-e,siegfried).
elternteil(wall-e,krimhild).
elternteil(eve,dieter).
elternteil(eve,ruth).
elternteil(eve,gunter).
elternteil(eve,klara).
elternteil(eve,helmut).
elternteil(eve,renate).
elternteil(eve,herbert).
elternteil(eve,maria).
elternteil(eve,hubert).
elternteil(eve,johanna).
elternteil(eve,karl).
elternteil(eve,inge).
elternteil(eve,pablo).
elternteil(eve,marion).
elternteil(eve,siegfried).
elternteil(eve,krimhild).


% verheiratet/2
% verheiratet(X,Y), maennlich X ist mit weiblich Y verheiratet
verheiratet(alois,thea).
verheiratet(anton,magda).
verheiratet(dieter,ruth).
verheiratet(fritz,ulrike).
verheiratet(gunter,klara).
verheiratet(helmut,renate).
verheiratet(herbert,maria).
verheiratet(hubert,johanna).
verheiratet(jakob,elsa).
verheiratet(jens,anna).
verheiratet(karl,inge).
verheiratet(manfred,helen).
verheiratet(otto,lisa).
verheiratet(pablo,marion).
verheiratet(siegfried,krimhild).
verheiratet(helge,elisa).
verheiratet(wall-e,eve).
verheiratet(xaver,vera).

% weiblich/1
% Frauen
weiblich(anna).
weiblich(christa).
weiblich(elke).
weiblich(elsa).
weiblich(erika).
weiblich(eve).
weiblich(helen).
weiblich(helga).
weiblich(helge).
weiblich(inge).
weiblich(isolde).
weiblich(johanna).
weiblich(klara).
weiblich(krimhild).
weiblich(lena).
weiblich(lisa).
weiblich(magda).
weiblich(maria).
weiblich(marion).
weiblich(renate).
weiblich(ruth).
weiblich(steffi).
weiblich(susanne).
weiblich(thea).
weiblich(ulrike).
weiblich(vera).
weiblich(zummsel).

% maennlich/1
% Maenner
maennlich(alois).
maennlich(anton).
maennlich(bert).
maennlich(dieter).
maennlich(elisa).
maennlich(fritz).
maennlich(gunter).
maennlich(heinz).
maennlich(helmut).
maennlich(herbert).
maennlich(hubert).
maennlich(hugo).
maennlich(jakob).
maennlich(jens).
maennlich(karl).
maennlich(lutz).
maennlich(manfred).
maennlich(otto).
maennlich(pablo).
maennlich(siegfried).
maennlich(udo).
maennlich(ulrich).
maennlich(wall-e).
maennlich(xaver).
maennlich(zummsel).

%
% 1. vorfahre, nachkomme, nachkommen
%

vorfahr(Vorfahr, Person) :- elternteil(Vorfahr, Person).
vorfahr(Vorfahr, Person) :- elternteil(X, Person), vorfahr(Vorfahr, X).

nachkomme(Person, Nachkomme) :- elternteil(Person, Nachkomme).
nachkomme(Person, Nachkomme) :- elternteil(Person, X), nachkomme(X, Nachkomme).

nachkommen(Person, Nachkommen) :- 
  findall(Nachkomme, nachkomme(Person, Nachkomme), DNachkommen),
  list_to_set(DNachkommen, Nachkommen).

%
% 2. eheleute, kind
%

eheleute(EheTeil1, EheTeil2) :- verheiratet(EheTeil1, EheTeil2),!.
eheleute(EheTeil1, EheTeil2) :- verheiratet(EheTeil2, EheTeil1).

kind(Elternteil, Kind) :- elternteil(Elternteil, Kind).

%
% 3. geschwister
%

geschwister(Person1, Person2) :- Person1 \== Person2,
  elternteil(Elternteil, Person1), elternteil(Elternteil, Person2).

%
% 4. bruder, schwester
%

bruder(Bruder, Person) :- maennlich(Bruder), geschwister(Bruder, Person).

schwester(Schwester, Person) :- weiblich(Schwester), geschwister(Schwester, Person).

%
% 5. opa, uropa, oma, uroma, mutter, vater, tante, onkel.
%

opa(Opa, Enkel) :- maennlich(Opa), elternteil(Elternteil, Enkel), elternteil(Opa, Elternteil).

