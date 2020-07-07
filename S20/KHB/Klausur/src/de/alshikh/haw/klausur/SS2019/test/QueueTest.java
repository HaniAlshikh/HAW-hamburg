package de.alshikh.haw.klausur.SS2019.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.alshikh.haw.klausur.SS2019.Classes.Queue;
import de.alshikh.haw.klausur.SS2019.Exceptions.EmptyQueueException;
import de.alshikh.haw.klausur.SS2019.Interfaces.IQueue;


/**
 * Tests for the class {@link Queue} to be written as a lab task.
 *
 * @author Bernd Kahlbrandt
 *
 */
class QueueTest {
	private List<String> stringTestData = List.of("42", "11", "08", "15");
	private List<Integer> intTestData = List.of(42, 11, 8, 15);
	private IQueue<String> emptyQueue01 = new Queue<>();
	private IQueue<String> emptyQueue02 = new Queue<>();
	private IQueue<String> smallStringQueue01;
	private IQueue<String> smallStringQueue02;
	private IQueue<Integer> smallIntQueue01;
	private IQueue<Integer> smallIntQueue02;
	private IQueue<String> oneElementQueue01;
	private IQueue<String> oneElementQueue02;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Sets testbed for each test.
	 */
	@BeforeEach
	public void setUp() {
		smallStringQueue01 = new Queue<>();
		smallStringQueue02 = new Queue<>();
		smallIntQueue01 = new Queue<>();
		smallIntQueue02 = new Queue<>();
		oneElementQueue01 = new Queue<>();
		oneElementQueue02 = new Queue<>();
		for (String s : stringTestData) {
			smallStringQueue01.enqueue(s);
			smallStringQueue02.enqueue(s);
		}
		for (int i : intTestData) {
			smallIntQueue01.enqueue(i);
			smallIntQueue02.enqueue(i);
		}
		oneElementQueue01.enqueue(stringTestData.get(0));
		oneElementQueue02.enqueue(stringTestData.get(0));
	}

	/**
	 * This test serve the following purposes:
	 * <ul>
	 * <li>Even if the task does not require a default constructor, it's the only
	 * kind of constructor, that makes sense here. So it's tested if there is a
	 * default contractor.</li>
	 * <li>A new {@link Queue} must be empty, so this is tested too.</li>
	 * </ul>
	 */
	@Test
	public void testConstructor() {
		assertTrue(emptyQueue01.isEmpty());
		assertFalse(smallStringQueue01.isEmpty());
		try {
			Queue.class.getConstructor((Class<?> [])null);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("You may have forgotten to provide a default constructor " + "for class 'Queue'");
		}

	}

	/**
	 * Here {@link Queue#enqueue(Object)} is tested. To check the result I rely on
	 * {@link Queue#peek()} and on {@link Queue#isEmpty()}. So I do not need to use
	 * reflectionto find the name of your nested class etc.
	 */
	@Test
	public void testEnqueue() {
		assertFalse(emptyQueue01.enqueue("First").isEmpty());
		try {
			assertEquals(stringTestData.get(0), oneElementQueue01.peek());
			assertEquals(stringTestData.get(0), smallStringQueue01.peek());
			smallStringQueue01.dequeue();
			assertEquals(stringTestData.get(1), smallStringQueue01.peek());
			smallStringQueue01.dequeue();
			assertEquals(stringTestData.get(2), smallStringQueue01.peek());
			smallStringQueue01.dequeue();
			assertEquals(stringTestData.get(3), smallStringQueue01.peek());
		} catch (EmptyQueueException e) {
			System.out.println("'oneElementQueue' should not be empty now!");
			e.printStackTrace();
		}

	}

	/**
	 * Here is tested. To check the result I rely on
	 * {@link Queue#peek()} and on {@link Queue#isEmpty()}. So I do not need to use
	 * reflectionto find the name of your nested class etc.
	 */

	@Test
	public void testDequeue() {
		try {
			assertEquals(stringTestData.get(1), smallStringQueue01.dequeue().peek());
			assertEquals(stringTestData.get(2), smallStringQueue02.dequeue().dequeue().peek());
		} catch (EmptyQueueException e) {
			System.out.println("'smallQueue01' should not be empty at this stage!");
			e.printStackTrace();
		}
	}

	/**
	 * This tests if the required {@link EmptyQueueException} is correctly thrown.
	 */
	@Test
	public void testDequeueException() {
		assertThrows(EmptyQueueException.class, emptyQueue01::dequeue);
		assertDoesNotThrow(smallIntQueue01::dequeue,
				"EmptyQueueException or another Exception is thrown for non-empty Queue!");
	}

	@Test
	public void testPeek() {
		try {
			assertEquals(stringTestData.get(0), smallStringQueue01.peek());
		} catch (EmptyQueueException e1) {
			System.err.println("'smallQueue01' should not be empty now!");
			e1.printStackTrace();
		}
		try {
			smallStringQueue01.dequeue().dequeue();
			assertEquals(stringTestData.get(2), smallStringQueue01.peek());
		} catch (EmptyQueueException e) {
			e.printStackTrace();
			System.err.println("The queue 'smallQueue01' should not be empty at this point!");
		}
		try {
			assertEquals(stringTestData.get(0), smallStringQueue02.peek());
		} catch (EmptyQueueException e) {
			e.printStackTrace();
			System.err.println("The queue 'smallQueue01' should not be empty at this point!");
		}
	}

	/**
	 * Tests if the required exception is thrown or not on {@link Queue#peek}.
	 */
	@Test
	public void testPeekException() {
		assertThrows(EmptyQueueException.class, emptyQueue01::peek);
		assertDoesNotThrow(smallIntQueue01::dequeue,
				"EmptyQueueException or another Exception is thrown for non-empty Queue!");
	}

	@Test
	public void testSize() {
		assertEquals(0, emptyQueue01.size(), "Size of empty Queue not 0.");
		assertEquals(1, oneElementQueue01.size(), "Size of one element queue not 1.");
		assertEquals(4, smallStringQueue01.size(), "Size of three element queue not 3.");
	}

	@Test
	public void testIsEmpty() {
		assertTrue(emptyQueue01.isEmpty(), "Possible error in isEmpty()");
		assertFalse(smallStringQueue01.isEmpty(), "Possible error in isEmpty()");

	}

	@Test
	public void testEquals() {
		assertEquals(emptyQueue01, emptyQueue01);
		assertEquals(emptyQueue01, emptyQueue02);
		assertEquals(oneElementQueue01, oneElementQueue02);
		assertEquals(smallStringQueue01, smallStringQueue02);
	}

	@Test
	public void testEqualsHashcode() {
		assertEquals(smallStringQueue01.hashCode(), smallStringQueue02.hashCode(), "equals may be wrong!");
		if (smallStringQueue01.equals(smallStringQueue02)) {
			assertEquals(smallStringQueue01.hashCode(), smallStringQueue02.hashCode(),
					"equals and hashCode may not be inconsistent!");
		}
		if (smallStringQueue01.equals(smallStringQueue02)) {
			Set<IQueue<String>> set = new HashSet<>();
			set.add(emptyQueue01);
			assertTrue(set.contains(emptyQueue01));
			set.add(smallStringQueue01);
			assertTrue(set.contains(smallStringQueue01));
			Map<IQueue<String>, String> hashMap = new HashMap<>();
			hashMap.put(emptyQueue01, null);
			hashMap.put(smallStringQueue01, stringTestData.get(0));
			hashMap.put(smallStringQueue02, stringTestData.get(1));
			assertTrue(hashMap.containsKey(emptyQueue01));
			assertTrue(hashMap.containsKey(smallStringQueue01));
			assertTrue(hashMap.containsKey(smallStringQueue02));
			assertTrue(hashMap.containsValue(null));
			assertFalse(hashMap.containsValue(stringTestData.get(0)));
			assertTrue(hashMap.containsValue(stringTestData.get(1)));
		}
	}

	@Test
	public void testEqualsDifferentTypes() {
		assertNotEquals(smallIntQueue01, smallStringQueue01);
	}

	@Test
	public void testOverwrites() {
		List<String> missing = new ArrayList<>();
		try {
			Queue.class.getDeclaredMethod("toString", new Class<?>[0]);
		} catch (NoSuchMethodException | SecurityException e) {
			missing.add("Have you overwritten 'toString'?\n");
		}
		try {
			Queue.class.getDeclaredMethod("equals", Object.class);
		} catch (NoSuchMethodException | SecurityException e) {
			missing.add("Have you overwritten 'equals'?\n");
		}
		try {
			Queue.class.getDeclaredMethod("hashCode", new Class<?>[0]);
		} catch (NoSuchMethodException | SecurityException e) {
			missing.add("Have you overwritten 'hashCode'?\n");
		}
		if (!missing.isEmpty()) {
			String msg = "";
			for (String s : missing) {
				msg += s;
			}
			fail(msg);
		}
	}

	@Test
	public void testImplementation() {
		Class<?>[] nestedClasses = Queue.class.getDeclaredClasses();
		assertEquals(1, nestedClasses.length);
		assertTrue(nestedClasses[0].isMemberClass());
		assertTrue(Modifier.isPrivate(nestedClasses[0].getModifiers()));
		assertTrue(Modifier.isStatic(nestedClasses[0].getModifiers()));
	}
}
