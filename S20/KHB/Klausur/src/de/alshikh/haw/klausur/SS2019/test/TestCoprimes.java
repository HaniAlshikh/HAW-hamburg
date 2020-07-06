package de.alshikh.haw.klausur.SS2019.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import de.alshikh.haw.klausur.SS2019.Classes.A01;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests the elementary requirements for a lab task that asks for the
 * generation of the OEIS.org sequence A002715.
 * @author Bernd Kahlbrandt
 *
 */
public class TestCoprimes {
	private static List<Long> testdata;

	@BeforeAll
	static void setUpBeforeClass() {
		testdata = List.of(3L, 7L, 23L, 47L, 1103L, 2207L, 2435423L, 4870847L, 11862575248703L, 23725150497407L);
	}
/**
 * Tests if the maximum number of longs is generated.
 */
	@Test
	void test()	 {
		for (int i = 0; i < testdata.size(); i++) {
			assertEquals(testdata.get(i), A01.generateCoprimes(10)[i]);
		}
	}
/**
 * Tests if the required {@link ArithmeticException} is correctly thrown.
 */
	@Test
	void testException() {
		assertThrows(ArithmeticException.class, () -> A01.generateCoprimes(11));
		try {
			A01.generateCoprimes(11);
		} catch (ArithmeticException ae) {
			assertTrue(ae.getMessage().contains("10"));
		}
	}

}
