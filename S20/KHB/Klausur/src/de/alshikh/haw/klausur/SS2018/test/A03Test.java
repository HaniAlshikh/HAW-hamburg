package de.alshikh.haw.klausur.SS2018.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.FileSystems;
import java.util.Map;

import de.alshikh.haw.klausur.SS2018.classes.A03;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test cases for part three of the exam B-AI 2 PM 2 in the summer term 2018.
 * @author Bernd Kahlbrandt
 *
 */
class A03Test {
  Map<Integer, Long> collatzBounds = Map.of(1, 0L, 2, 1L, 4, 2L,  27, 111L);
  @BeforeEach
  void setUp() throws Exception {
  }
/**
 * I test, if the correct document line number, starting the count with 0, is returned.
 */
  @Test
  public void testAdorno() {
    assertEquals(4, A03.adornoWinner(FileSystems.getDefault().getPath("./src/de/alshikh/haw/klausur/SS2018/data", "quotes.txt")));
    assertEquals(1,A03.adornoWinner(FileSystems.getDefault().getPath("./src/de/alshikh/haw/klausur/SS2018/data", "quotessich.txt")));
  }
  /**
   * I test, if the correct document line number, starting the count with 0, is returned.
   */
  @Test
  public void testAdornoReturnCode() {
    assertEquals(-1,A03.adornoWinner(FileSystems.getDefault().getPath("./src/de/alshikh/haw/klausur/SS2018/data", "quotesohne.txt")));
  }
  /**
   * I test for the correct length of the sequence.
   */
//  @Test
//  public void testCollatz() {
////    assertEquals(collatzBounds.get(1), A03.collatz(1).count());
//    assertTrue(collatzBounds.get(1)==A03.collatz(1).count());
//    assertTrue(collatzBounds.get(2)==A03.collatz(2).count());
//    assertTrue(collatzBounds.get(4)==A03.collatz(4).count());
//    assertTrue(collatzBounds.get(27)==A03.collatz(27).count());
//    A03.collatz(4).forEach(System.out::println);
//    System.out.println(A03.collatz(4).count());
//  }
//  /**
//   * Tests that a {@link RuntimeException} with a not empty message is thrown, if the
//   * parameter is less than or equal to zero.
//   */
//  @Test
//  public void testCollatzException() {
//    assertThrows(RuntimeException.class, () -> A03.collatz(0));
//    try {
//      A03.collatz(0);
//    } catch (RuntimeException re) {
//      assert(re.getMessage().length()>0);
//    }
//    assertThrows(RuntimeException.class, () -> A03.collatz(-42));
//
//  }

}
