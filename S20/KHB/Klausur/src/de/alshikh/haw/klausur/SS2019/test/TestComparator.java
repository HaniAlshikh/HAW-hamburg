package de.alshikh.haw.klausur.SS2019.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.alshikh.haw.klausur.SS2019.Classes.A01;
import org.junit.jupiter.api.Test;


/**
 * Tests your implementation of a Comparator for the natural oder of int's.
 * @author Bernd Kahlbrandt
 *
 */
public class TestComparator {
	/**
	 * Test for a typical error if wrapper classes and primitives are not 
	 * handled correctly.
	 */
	@Test
	public void test01() {
		assertEquals(1, A01.naturalOrder().compare(Integer.valueOf(142),
				Integer.valueOf(-142)), 
				"Should be greater!");
		assertEquals(0, A01.naturalOrder().compare(Integer.valueOf(127), 
				Integer.valueOf(127)), 
				"Should be equals!");
	}
	/**
	 * Test for a typical error if wrapper classes and primitives are not 
	 * handled correctly.
	 */
	@Test
	public void test02() {
		assertEquals(0, A01.naturalOrder().compare(Integer.valueOf(142), 
				Integer.valueOf(142)), 
				"Should be equals!");
	}
	/**
	 * Test for a typical error if wrapper classes and primitives are not 
	 * handled correctly and for some upper and lower boundary cases.
	 */
	@Test
	public void test03() {
		assertEquals(-1, A01.naturalOrder().compare(Integer.valueOf(Integer.MIN_VALUE), 
				Integer.valueOf(142)), 
				"Should be less!");
		assertEquals(0, A01.naturalOrder().compare(Integer.valueOf(Integer.MIN_VALUE), 
				Integer.valueOf(Integer.MIN_VALUE)), 
				"Should be equal!");
		assertEquals(0, A01.naturalOrder().compare(Integer.valueOf(Integer.MAX_VALUE), 
				Integer.valueOf(Integer.MAX_VALUE)), 
				"Should be equal!");
		assertEquals(1, A01.naturalOrder().compare(Integer.valueOf(Integer.MAX_VALUE), 
				Integer.valueOf(142)), 
				"Should be greater!");
	}
}
