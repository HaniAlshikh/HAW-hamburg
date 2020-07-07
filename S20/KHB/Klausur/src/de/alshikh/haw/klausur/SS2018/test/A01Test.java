package de.alshikh.haw.klausur.SS2018.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

import de.alshikh.haw.klausur.SS2018.classes.A01;
import org.junit.jupiter.api.Test;


class A01Test {

/**
 * Tests for a valid anniversary.
 */
  @Test
  void testGetAnniversary() {
    assertEquals(LocalDate.of(2020, 4, 1), A01.getAnniversary(LocalDate.of(1970, 4, 1), 50));
    assertEquals(LocalDate.of(2019, 2, 23), A01.getAnniversary(LocalDate.of(1979, 2, 23), 40));    
    assertEquals(LocalDate.of(2034, 7, 7), A01.getAnniversary(LocalDate.of(1984, 7, 7), 50));    
    assertEquals(LocalDate.of(2062, 11, 8), A01.getAnniversary(LocalDate.of(2012, 11, 8), 50));    
    assertEquals(LocalDate.of(2068, 1, 27), A01.getAnniversary(LocalDate.of(2018, 1, 27), 50));    
  }
  /**
   * Tests for invalid parameter.
   */
  @Test
  void testGetAnniversaryWrong() {
    assertThrows(RuntimeException.class,()->A01.getAnniversary(LocalDate.of(1970, 4, 1), -50));    
    assertThrows(RuntimeException.class,()->A01.getAnniversary(LocalDate.of(1990, 2, 29), 5));    
  }
  @Test
  void testSwitchTimeZone() {
    ZonedDateTime today = ZonedDateTime.now(ZoneId.systemDefault());
    ZonedDateTime todaySwitchedtoShanghai1 = A01.switchTimeZone(today, ZoneId.of("Asia/Shanghai"));
    ZonedDateTime todaySwitchedtoLondon = A01.switchTimeZone(today, ZoneId.of("Europe/London"));
    assertEquals(today.getOffset().get(ChronoField.OFFSET_SECONDS)-3600, todaySwitchedtoLondon.getOffset().get(ChronoField.OFFSET_SECONDS));
    assertEquals(today.getOffset().get(ChronoField.OFFSET_SECONDS)+3600*6, todaySwitchedtoShanghai1.getOffset().get(ChronoField.OFFSET_SECONDS));
  }
  @Test
  void testFactorial() {
    assertEquals(1, A01.factorial(0));
    assertEquals(1, A01.factorial(1));
    assertEquals(2, A01.factorial(2));
    assertEquals(2_432_902_008_176_640_000L, A01.factorial(20));
  }
  
  @Test
  void testFactorialWrongParm() {
    assertThrows(ArithmeticException.class, ()->A01.factorial(-42));
  }
  @Test
  void testFactorialTooBig() {
    assertThrows(ArithmeticException.class, ()->A01.factorial(21));   
  }

  /**
   * Test if there is a private default constructor and that all 
   * methods are static.
   */
  @Test
  public void testUtilityProperty() {
    Method [] methods = A01.class.getDeclaredMethods();
    for(Method m : methods) {
      int mod = m.getModifiers();
      assertEquals(Modifier.STATIC|Modifier.PUBLIC, mod);
    }
    Constructor<?> [] constructors = A01.class.getDeclaredConstructors();
    assertEquals(1,constructors.length);
    if(constructors.length == 1) {
      assertEquals(Modifier.PRIVATE, constructors[0].getModifiers());
    }
  }

}
