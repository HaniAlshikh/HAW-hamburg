% Autor:
% Datum: 30.07.2015

test(and) :-
    %positiv
    write('and +'),
    and(true,true), write('.'),!,
    %negativ
    write('. -'),!,
    \+ and(true,fail), write('.'),!,
    \+ and(fail,true), write('.'),!,
    \+ and(fail,fail), write('.'),!,
    \+ and(fail,_X), write('.'),!,
    \+ and(_Y,fail),!, writeln('ok').

test(or) :-
    %positiv
    write('or +'),
    or(true,true), write('.'),!,
    or(true,fail), write('.'),!,
    or(fail,true), write('.'),!,
    %negativ
    write('. -'),!,
    \+ or(fail,fail), write('.'),!,
    \+ or(true,_X), write('.'),!,
    \+ or(_Y,true),!, writeln('ok').

test(mnot) :-
    %positiv
    write('mnot +'),
    mnot(fail), write('.'),!,
    mnot(and(fail,true)), write('.'),!,
    %negativ
    write('. -'),!,
    \+ mnot(true), write('.'),!,
    \+ mnot(or(fail,true)), write('.'),!,
    \+ mnot(_X),!, writeln('ok').

test(aequiv) :-
    %positiv
    write('aequiv +'),
    aequiv(fail,fail), write('.'),!,
    aequiv(true,true),
    %negativ
    write('. -'),!,
    \+ aequiv(true,fail), write('.'),!,
    \+ aequiv(fail,true), write('.'),!,
    \+ aequiv(X,X),!, writeln('ok').

test(lebkuchen) :-
    %positiv
    write('formeln +'),
    D1 = fail, E1 = fail, Z1 = true,
    and(and(mnot(and(E1,Z1)),impl(D1,Z1)),impl(and(Z1,mnot(E1)),mnot(D1))), write('.'),!,
    D2 = fail, E2 = true, Z2 = fail,
    and(and(mnot(and(E2,Z2)),impl(D2,Z2)),impl(and(Z2,mnot(E2)),mnot(D2))), write('.'),!,
    D3 = true, E3 = fail, Z3 = fail,
    impl(and(and(mnot(and(E3,Z3)),impl(D3,Z3)),impl(and(Z3,mnot(E3)),mnot(D3))),mnot(D3)), write('.'),!,
    D2 = fail, E2 = true, Z2 = fail,
    aequiv(mnot(impl(and(and(mnot(and(E2,Z2)),impl(D2,Z2)),impl(and(Z2,mnot(E2)),mnot(D2))),mnot(D2))),
           and(and(or(mnot(E2),mnot(Z2)),and(or(mnot(D2),Z2),or(mnot(D2),or(E2,mnot(Z2))))),D2)), write('.'),!,
    %negativ
    write('. -'),!,
    D3 = true, E3 = fail, Z3 = fail,
    \+ and(and(mnot(and(E3,Z3)),impl(D3,Z3)),impl(and(Z3,mnot(E3)),mnot(D3))), write('.'),!,
    D4 = true, E4 = true, Z4 = true,
    \+ and(and(mnot(and(E4,Z4)),impl(D4,Z4)),impl(and(Z4,mnot(E4)),mnot(D4))), !, writeln('ok').

test(avarListe) :-
    %positiv
    write('avarListe +'),
    avarListe(and(and(mnot(and(E1,Z1)),impl(D1,Z1)),impl(and(Z1,mnot(E1)),mnot(D1))),VL1), write('.'),!,
    length(VL1, 3), write('.'),!,
    avarListe(and(and(mnot(and(E2,Z2)),impl(D2,Z2)),impl(and(Z2,mnot(E2)),mnot(D2))),VL2), write('.'),!,
    length(VL2, 3),
    %negativ
    write('. -'),!,
    avarListe(impl(and(and(mnot(and(E3,Z3)),impl(D3,Z3)),impl(and(Z3,mnot(E3)),mnot(D3))),mnot(D3)),VL3), write('.'),!,
    \+ length(VL3, 8), write('.'),!,
    avarListe(aequiv(mnot(impl(and(and(mnot(and(E2,Z2)),impl(D2,Z2)),impl(and(Z2,mnot(E2)),mnot(D2))),mnot(D2))),
           and(and(or(mnot(E2),mnot(Z2)),and(or(mnot(D2),Z2),or(mnot(D2),or(E2,mnot(Z2))))),D2)),VL4), write('.'),!,
    \+ length(VL4, 1), !, writeln('ok').

test(alllogic) :-
    test(and),
    test(or),
    test(mnot),
    test(aequiv),
    test(lebkuchen),
    test(avarListe),
    formelA,
    formelB
    .


test(is_atomic_expr) :-
    %positiv
    write('is_atomic_expr +'),
    is_atomic_expr(_X), write('.'),!,
    %negativ
    write('. -'),!,
    \+ is_atomic_expr(x),!, writeln('ok').

test(is_literal) :-
    %positiv
    write('is_literal +'),
    is_literal(_X), write('.'),!,
    is_literal(mnot(_Y)), write('.'),!,
    %negativ
    write('. -'),!,
    \+ is_literal(x), write('.'),!,
    \+ is_literal(mnot(y)),!, writeln('ok').

test(is_clause) :-
    %positiv
    write('is_clause +'),
    is_clause(_X), write('.'),!,
    is_clause(or(_A,or(_B,or(or(mnot(_C),or(_D,_E)),_F)))), write('.'),!,
    %negativ
    write('. -'),!,
    \+ is_clause(impl(_G,_H)), write('.'),!,
    \+ is_clause(mnot(and(_I,_J))), write('.'),!,
    \+ is_clause(x), write('.'),!,
    \+ is_clause(mnot(and(or(_K,_L),_M))), write('.'),!,
    \+ is_clause(or(_N,or(_O,or(or(_P,or(q,r)),_S)))),!, writeln('ok').

test(is_horn_clause) :-
    %positiv
    write('is_horn_clause +'),
    is_horn_clause(_Z), write('.'),!,
    is_horn_clause(or(mnot(_A),or(_B,or(or(mnot(_C),or(mnot(_D),mnot(_E))),mnot(_F))))), write('.'),!,
    %negativ
    write('. -'),!,
    \+ is_horn_clause(impl(_G,_H)), write('.'),!,
    \+ is_horn_clause(mnot(and(_I,_J))), write('.'),!,
    \+ is_horn_clause(or(_T,or(_U,or(or(mnot(_V),or(_W,_X)),_Y)))), write('.'),!,
    \+ is_horn_clause(or(mnot(_A2),or(_B1,or(or(mnot(_C2),or(mnot(_D2),_E1)),mnot(_F2))))), write('.'),!,
    \+ is_horn_clause(x), write('.'),!,
    \+ is_horn_clause(mnot(and(or(_K,_L),_M))), write('.'),!,
    \+ is_horn_clause(or(_N,or(_O,or(or(_P,or(q,r)),_S)))),!, writeln('ok').

test(nnf_expr) :-
    write('nnf_expr '),
    nnf_expr(impl(and(and(mnot(and(E3,Z3)),impl(D3,Z3)),impl(and(Z3,mnot(E3)),mnot(D3))),mnot(D3)),
             or(or(or(and(E3, Z3), and(D3, mnot(Z3))), and(and(Z3, mnot(E3)), D3)), mnot(D3))), write('.'),!,
    nnf_expr(mnot(impl(and(and(mnot(and(E2,Z2)),impl(D2,Z2)),impl(and(Z2,mnot(E2)),mnot(D2))),mnot(D2))),
             and(and(and(or(mnot(E2), mnot(Z2)), or(mnot(D2), Z2)), or(or(mnot(Z2), E2), mnot(D2))), D2)), write('.'),!,
    nnf_expr(and(or(mnot(mnot(B)),A),or(and(mnot(A),mnot(and(B,mnot(C)))),and(A,and(B,mnot(C))))),
             and(or(B, A), or(and(mnot(A), or(mnot(B), C)), and(A, and(B, mnot(C)))))), write('.'),!,
    nnf_expr(X,X), write('.'),!,
    nnf_expr(or(A,or(B,or(or(mnot(C),or(D,E)),F))),
             or(A,or(B,or(or(mnot(C),or(D,E)),F)))), write('.'),!,
    nnf_expr(aequiv(and(mxor(nand(E1,Z1),D1),Z1),nor(E1,Z1)),
             or(and(and(and(or(and(E1, Z1), mnot(D1)), or(or(mnot(E1), mnot(Z1)), D1)), Z1), and(mnot(E1), mnot(Z1))), and(or(or(and(or(mnot(E1), mnot(Z1)), D1), and(and(E1, Z1), mnot(D1))), mnot(Z1)), or(E1, Z1)))), write('.'),!,
    nnf_expr(mnot(X),mnot(X)),!, writeln('ok').

test(allterm) :-
    test(is_atomic_expr),
    test(is_literal),
    test(nnf_expr),
    test(is_clause),
    test(is_horn_clause)
    .