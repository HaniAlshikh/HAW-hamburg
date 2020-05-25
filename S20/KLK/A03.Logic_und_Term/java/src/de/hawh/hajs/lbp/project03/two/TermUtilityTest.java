package de.hawh.hajs.lbp.project03.two;

import static org.junit.jupiter.api.Assertions.*;

import de.hawh.hajs.lbp.project03.two.Operants.*;

class TermUtilityTest {

  Impl myImpl;
  And myAnd;
  Atom myTrueAtom;
  Atom myFalseAtom;


  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    myTrueAtom = new Atom(true);
    myFalseAtom = new Atom(false);
    myAnd = new And(new Atom(true), new Atom(false));
    myImpl = new Impl(new Or(new Atom(true), new Atom(false)),
        new And(new Atom(true), new Atom(true)));
  }

  @org.junit.jupiter.api.Test
  void isAtomic() {
    assertFalse(TermUtility.isAtomic(myImpl));
    assertTrue(TermUtility.isAtomic(myTrueAtom));
  }

  @org.junit.jupiter.api.Test
  void isLiteral() {
    assertFalse(TermUtility.isLiteral(myImpl));
    assertTrue(TermUtility.isLiteral(myTrueAtom));
    assertTrue(TermUtility.isLiteral(new Not(myTrueAtom)));
  }

  @org.junit.jupiter.api.Test
  void nnf_expr() {
    // Tests aus bereitgestelltem Testsystem:
    Term myInput = new Impl(new And(new And(new Not(new And(new Atom(true), new Atom(true))),
        new Impl(new Atom(true), new Atom(true))),
        new Impl(new And(new Atom(true), new Not(new Atom(true))), new Not(new Atom(true)))),
        new Not(new Atom(true)));
    Term myExpectedOutput = new Or(new Or(new Or(new And(new Atom(true), new Atom(true)),
        new And(new Atom(true), new Not(new Atom(true)))),
        new And(new And(new Atom(true), new Not(new Atom(true))), new Atom(true))),
        new Not(new Atom(true)));

    assertEquals(myExpectedOutput, TermUtility.nnf_expr(myInput));

    myInput = new Not(new Impl(new And(new And(new Not(new And(new Atom(true), new Atom(true))),
        new Impl(new Atom(true), new Atom(true))),
        new Impl(new And(new Atom(true), new Not(new Atom(true))), new Not(new Atom(true)))),
        new Not(new Atom(true))));
    myExpectedOutput = new And(new And(
        new And(new Or(new Not(new Atom(true)), new Not(new Atom(true))),
            new Or(new Not(new Atom(true)), new Atom(true))),
        new Or(new Or(new Not(new Atom(true)), new Atom(true)), new Not(new Atom(true)))),
        new Atom(true));

    assertEquals(myExpectedOutput, TermUtility.nnf_expr(myInput));

    myInput = new And(new Or(new Not(new Not(new Atom(true))), new Atom(true)), new Or(
        new And(new Not(new Atom(true)), new Not(new And(new Atom(true), new Not(new Atom(true))))),
        new And(new Atom(true), new And(new Atom(true), new Not(new Atom(true))))));
    myExpectedOutput = new And(new Or(new Atom(true), new Atom(true)),
        new Or(new And(new Not(new Atom(true)), new Or(new Not(new Atom(true)), new Atom(true))),
            new And(new Atom(true), new And(new Atom(true), new Not(new Atom(true))))));

    assertEquals(myExpectedOutput, TermUtility.nnf_expr(myInput));

    myInput = new Atom(true);
    myExpectedOutput = new Atom(true);

    assertEquals(myExpectedOutput, TermUtility.nnf_expr(myInput));

    myInput = new Or(new Atom(true), new Or(new Atom(true),
        new Or(new Or(new Not(new Atom(true)), new Or(new Atom(true), new Atom(true))),
            new Atom(true))));
    myExpectedOutput = new Or(new Atom(true), new Or(new Atom(true),
        new Or(new Or(new Not(new Atom(true)), new Or(new Atom(true), new Atom(true))),
            new Atom(true))));

    assertEquals(myExpectedOutput, TermUtility.nnf_expr(myInput));

    myInput = new Aequiv(
        new And(new Xor(new Nand(new Atom(true), new Atom(true)), new Atom(true)), new Atom(true)),
        new Nor(new Atom(true), new Atom(true)));
    myExpectedOutput = new Or(new And(new And(
        new And(new Or(new And(new Atom(true), new Atom(true)), new Not(new Atom(true))),
            new Or(new Or(new Not(new Atom(true)), new Not(new Atom(true))), new Atom(true))),
        new Atom(true)), new And(new Not(new Atom(true)), new Not(new Atom(true)))), new And(new Or(
        new Or(new And(new Or(new Not(new Atom(true)), new Not(new Atom(true))), new Atom(true)),
            new And(new And(new Atom(true), new Atom(true)), new Not(new Atom(true)))),
        new Not(new Atom(true))), new Or(new Atom(true), new Atom(true))));

    assertEquals(myExpectedOutput, TermUtility.nnf_expr(myInput));

    myInput = new Not(new Atom(true));
    myExpectedOutput = new Not(new Atom(true));

    assertEquals(myExpectedOutput, TermUtility.nnf_expr(myInput));
  }
}