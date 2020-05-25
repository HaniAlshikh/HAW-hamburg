% #################################################################
%
% Extra tests um nand, nor, impl, und xor einzieln zu prüfen
%
% Autor: Jannik Stuckstätte
% Autor: Hani Alshikh
%
% #################################################################

test(nand) :-
  %positiv
  write('nand +'),
  nand(true,fail), write('.'),!,
  nand(fail,true), write('.'),!,
  nand(fail,fail), write('.'),!,
  %negativ
  write('. -'),!,
  \+ nand(true,true), write('.'),!,
  \+ nand(fail,_X), write('.'),!,
  \+ nand(_Y,fail),!, writeln('ok').

test(nor) :-
  %positiv
  write('nor +'),
  nor(fail,fail), write('.'),!,
  %negativ
  write('. -'),!,
  \+ nor(true,true), write('.'),!,
  \+ nor(true,fail), write('.'),!,
  \+ nor(fail,true), write('.'),!,
  \+ or(true,_X), write('.'),!,
  \+ or(_Y,true),!, writeln('ok').

test(impl) :-
  %positiv
  write('impl +'),
  impl(true,true), write('.'),!,
  impl(fail,true), write('.'),!,
  impl(fail,fail), write('.'),!,
  %negativ
  write('. -'),!,
  \+ impl(true,fail), write('.'),!,
  \+ impl(true,_X), write('.'),!,
  \+ impl(_Y,true),!, writeln('ok').

test(xor) :-
  %positiv
  write('xor +'),
  xor(true,fail), write('.'),!,
  xor(fail,true), write('.'),!,
  %negativ
  write('. -'),!,
  \+ xor(true,true), write('.'),!,
  \+ xor(fail,fail), write('.'),!,
  \+ xor(true,_X), write('.'),!,
  \+ xor(_Y,true),!, writeln('ok').

test(logicall) :-
  test(nand),
  test(nor),
  test(impl),
  test(xor)
  . 