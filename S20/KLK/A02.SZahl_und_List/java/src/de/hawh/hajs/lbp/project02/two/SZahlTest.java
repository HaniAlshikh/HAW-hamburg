package de.hawh.hajs.lbp.project02.two;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SZahlTest {

  public SZahl mySZahlZero;
  public SZahl mySZahlOne;
  public SZahl mySZahlTwo;
  public SZahl mySZahlThree;
  public SZahl mySZahlThousand;

  @BeforeEach
  void setUp() {
    mySZahlZero = SZahl.nat2s(0);
    mySZahlOne = SZahl.nat2s(1);
    mySZahlTwo = SZahl.nat2s(2);
    mySZahlThree = SZahl.nat2s(3);
    mySZahlThousand = SZahl.nat2s(1000);
  }

  @org.junit.jupiter.api.Test
  void nat2s() {
    assertEquals("0", mySZahlZero.s_zahl);
    assertEquals("s(0)", mySZahlOne.s_zahl);
    assertEquals("s(s(0))", mySZahlTwo.s_zahl);
  }

  @org.junit.jupiter.api.Test
  void s2nat() {
    assertEquals(0, SZahl.s2nat(mySZahlZero));
    assertEquals(1, SZahl.s2nat(mySZahlOne));
    assertEquals(2, SZahl.s2nat(mySZahlTwo));
  }

  @org.junit.jupiter.api.Test
  void add() {
    assertEquals(mySZahlThree, SZahl.add(mySZahlOne, mySZahlTwo));
    assertEquals(SZahl.nat2s(1002), SZahl.add(mySZahlThousand, mySZahlTwo));
  }

  @org.junit.jupiter.api.Test
  void sub() {
    assertEquals(mySZahlOne, SZahl.sub(mySZahlTwo, mySZahlOne));
    assertEquals(SZahl.nat2s(998), SZahl.sub(mySZahlThousand, mySZahlTwo));
  }

  @Test
  void mul() {
    assertEquals(mySZahlZero, SZahl.mul(mySZahlZero, mySZahlTwo));
    assertEquals(mySZahlZero, SZahl.mul(mySZahlTwo, mySZahlZero));
    assertEquals(SZahl.nat2s(6), SZahl.mul(mySZahlTwo, mySZahlThree));
    assertEquals(SZahl.nat2s(2000), SZahl.mul(mySZahlTwo, mySZahlThousand));
  }

  @Test
  void power() {
    assertEquals(SZahl.nat2s(8), SZahl.power(mySZahlTwo, mySZahlThree));
    assertEquals(mySZahlOne, SZahl.power(mySZahlThousand, mySZahlZero));
  }

  @Test
  void fac() {
    assertEquals(SZahl.nat2s(2), SZahl.fac(mySZahlTwo));
    assertEquals(SZahl.nat2s(6), SZahl.fac(mySZahlThree));
    assertEquals(mySZahlOne, SZahl.fac(mySZahlZero));
  }

  @Test
  void lt() {
    assertFalse(SZahl.lt(mySZahlZero, mySZahlZero));
    assertTrue(SZahl.lt(mySZahlZero, mySZahlOne));
    assertFalse(SZahl.lt(mySZahlOne, mySZahlZero));
    assertTrue(SZahl.lt(SZahl.nat2s(999), mySZahlThousand));
    assertFalse(SZahl.lt(mySZahlThousand, SZahl.nat2s(999)));
  }

  @Test
  void mods() {
    assertThrows(ArithmeticException.class, () -> SZahl.mods(mySZahlThree, mySZahlZero));
    assertThrows(ArithmeticException.class, () -> SZahl.mods(mySZahlZero, mySZahlZero));
    assertEquals(mySZahlOne, SZahl.mods(mySZahlThree, mySZahlTwo));
    assertEquals(mySZahlTwo, SZahl.mods(mySZahlTwo, mySZahlThree));
    assertEquals(mySZahlOne, SZahl.mods(mySZahlThousand, mySZahlThree));
  }
}