package de.alshikh.haw.parallele_sequentielle_streams_IO.tests;

import de.alshikh.haw.parallele_sequentielle_streams_IO.classes.DequeCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**********************************************************************
 *
 * basic test cases for the Deque class
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

class DequeCustomTest {

    DequeCustom<Integer> intDeque;
    DequeCustom<String> strDeque;
    DequeCustom<Object> empty;

    @BeforeEach
    void setUp() {
        intDeque = new DequeCustom<>();
        strDeque = new DequeCustom<>();
        empty = new DequeCustom<>();

        strDeque.push("middle");
        strDeque.addFirst("first");
        strDeque.addLast("last");

        intDeque.push(2);
        intDeque.addFirst(1);
        intDeque.addLast(3);
    }

    @Test
    void addFirst() {
        assertThrows(IllegalArgumentException.class, ()-> empty.addFirst("0") );
        assertDoesNotThrow(() -> strDeque.addFirst("new first"));
        assertEquals("new first", strDeque.getFirst());
    }

    @Test
    void addLast() {
        assertThrows(IllegalArgumentException.class, ()-> empty.addLast("0") );
        assertDoesNotThrow(() -> strDeque.addLast("new last"));
        assertEquals("new last", strDeque.getLast());
    }

    @Test
    void push() {
        assertEquals("last", strDeque.getLast());
        assertDoesNotThrow(() -> strDeque.push("new last"));
        assertEquals("new last", strDeque.getLast());
    }

    @Test
    void offerFirst() {
        assertFalse(empty.offerFirst("0"));
        assertTrue(strDeque.offerFirst("new first"));
        assertEquals("new first", strDeque.getFirst());
    }

    @Test
    void offerLast() {
        assertFalse(empty.offerLast("0"));
        assertTrue(strDeque.offerLast("new last"));
        assertEquals("new last", strDeque.getLast());
    }

    @Test
    void removeFirst() {
        assertThrows(NoSuchElementException.class, ()-> empty.removeFirst());
        assertEquals("first", strDeque.getFirst());
        assertDoesNotThrow(() -> strDeque.removeFirst());
        assertEquals("middle", strDeque.removeFirst());
        assertEquals("last", strDeque.removeFirst());
        assertThrows(NoSuchElementException.class, ()-> strDeque.getFirst());
    }

    @Test
    void removeLast() {
        assertThrows(NoSuchElementException.class, ()-> empty.removeLast());
        assertEquals("last", strDeque.getLast());
        assertDoesNotThrow(() -> strDeque.removeLast());
        assertEquals("middle", strDeque.removeLast());
        assertEquals("first", strDeque.removeLast());
        assertThrows(NoSuchElementException.class, ()-> strDeque.getLast());
    }

    @Test
    void pop() {
        removeLast();
    }

    @Test
    void pollFirst() {
        assertNull(empty.pollFirst());
        assertEquals("first", strDeque.getFirst());
        assertNotNull(strDeque.pollFirst());
        assertEquals("middle", strDeque.pollFirst());
        assertEquals("last", strDeque.pollFirst());
        assertNull(strDeque.pollFirst());
    }

    @Test
    void pollLast() {
        assertNull(empty.pollLast());
        assertEquals("last", strDeque.getLast());
        assertNotNull(strDeque.pollLast());
        assertEquals("middle", strDeque.pollLast());
        assertEquals("first", strDeque.pollLast());
        assertNull(strDeque.pollLast());
    }

    @Test
    void getFirst() {
        assertThrows(NoSuchElementException.class, ()-> empty.getFirst());
        assertEquals("first", strDeque.getFirst());
    }

    @Test
    void getLast() {
        assertThrows(NoSuchElementException.class, ()-> empty.getLast());
        assertEquals("last", strDeque.getLast());
    }

    @Test
    void peekFirst() {
        assertNull(empty.peekFirst());
        assertEquals("first", strDeque.peekFirst());
    }

    @Test
    void peekLast() {
        assertNull(empty.peekLast());
        assertEquals("last", strDeque.peekLast());
    }

    @Test
    void size() {
        assertEquals(0, empty.size());
        assertEquals(3, strDeque.size());
    }


    @Test
    void equals() {
        assertEquals(empty, empty);
        assertNotEquals(strDeque, empty);
        empty.push("middle");
        empty.addFirst("first");
        empty.addLast("last");
        assertEquals(strDeque, strDeque);
        assertNotEquals(strDeque, intDeque);
        assertEquals(strDeque, empty);
        empty.pop();
        assertNotEquals(strDeque, empty);
        empty.push("new first");
        assertNotEquals(strDeque, empty);
        assertNotEquals(strDeque, "");
    }

    @Test
    void testHashCode() {
        Set<DequeCustom<?>> hash = new HashSet<>();
        hash.add(strDeque);
        assertEquals(1, hash.size());
        hash.add(empty);
        assertEquals(2, hash.size());
        hash.add(new DequeCustom<>());
        assertEquals(2, hash.size());
        empty.push("middle");
        empty.addFirst("first");
        empty.addLast("last");
        hash.add(empty);
        assertEquals(2, hash.size());
        hash.add(intDeque);
        assertEquals(3, hash.size());
    }
}

