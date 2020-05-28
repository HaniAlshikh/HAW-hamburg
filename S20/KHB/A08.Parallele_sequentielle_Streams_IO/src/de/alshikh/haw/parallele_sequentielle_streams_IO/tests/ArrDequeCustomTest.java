package de.alshikh.haw.parallele_sequentielle_streams_IO.tests;

import de.alshikh.haw.parallele_sequentielle_streams_IO.classes.ArrDequeCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**********************************************************************
 *
 * basic test cases for the ArrDeque class
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

class ArrDequeCustomTest {

    ArrDequeCustom<Integer> intArrDeque;
    ArrDequeCustom<String> strArrDeque;
    ArrDequeCustom<Object> empty;
    int capacity = 3;

    FileOutputStream fileOut;
    ObjectOutputStream out;
    FileInputStream fileIn;
    ObjectInputStream in;

    @BeforeEach
    void setUp() {
        intArrDeque = new ArrDequeCustom<>(capacity);
        strArrDeque = new ArrDequeCustom<>(capacity);
        empty = new ArrDequeCustom<>(0);

        strArrDeque.push("middle");
        strArrDeque.addFirst("first");
        strArrDeque.addLast("last");

        intArrDeque.push(2);
        intArrDeque.addFirst(1);
        intArrDeque.addLast(3);

        try {
            fileOut = new FileOutputStream("ArrDequeCustom.ser");
            out = new ObjectOutputStream(fileOut);
            fileIn = new FileInputStream("ArrDequeCustom.ser");
            in = new ObjectInputStream(fileIn);
        }
        catch (IOException i) {
                i.printStackTrace();
        }
    }


    @Test
    void serializing() {

        // write
        assertDoesNotThrow(() -> out.writeObject(strArrDeque));
        assertDoesNotThrow(() -> out.close());
        assertDoesNotThrow(() -> fileOut.close());

        // read
        @SuppressWarnings("unchecked")
        ArrDequeCustom<String> readTest = (ArrDequeCustom<String>)
                assertDoesNotThrow(() -> in.readObject());
        assertDoesNotThrow(() -> in.close());
        assertDoesNotThrow(() -> fileIn.close());

        // verify the object state
        assertEquals(strArrDeque, readTest);
    }


    @Test
    void addFirst() {
        assertThrows(IllegalArgumentException.class, ()-> empty.addFirst("0") );
        assertDoesNotThrow(() -> strArrDeque.addFirst("new first"));
        assertEquals("new first", strArrDeque.getFirst());
    }

    @Test
    void unshift() {
        assertEquals("first", strArrDeque.getFirst());
        assertDoesNotThrow(() -> strArrDeque.unshift("new first"));
        assertEquals("new first", strArrDeque.getFirst());
    }

    @Test
    void addLast() {
        assertThrows(IllegalArgumentException.class, ()-> empty.addLast("0") );
        assertDoesNotThrow(() -> strArrDeque.addLast("new last"));
        assertEquals("new last", strArrDeque.getLast());
    }

    @Test
    void push() {
        assertEquals("last", strArrDeque.getLast());
        assertDoesNotThrow(() -> strArrDeque.push("new last"));
        assertEquals("new last", strArrDeque.getLast());
    }

    @Test
    void offerFirst() {
        assertFalse(empty.offerFirst("0"));
        assertTrue(strArrDeque.offerFirst("new first"));
        assertEquals("new first", strArrDeque.getFirst());
    }

    @Test
    void offerLast() {
        assertFalse(empty.offerLast("0"));
        assertTrue(strArrDeque.offerLast("new last"));
        assertEquals("new last", strArrDeque.getLast());
    }

    @Test
    void removeFirst() {
        assertThrows(NoSuchElementException.class, ()-> empty.removeFirst());
        assertEquals("first", strArrDeque.getFirst());
        assertDoesNotThrow(() -> strArrDeque.removeFirst());
        assertEquals("middle", strArrDeque.removeFirst());
        assertEquals("last", strArrDeque.removeFirst());
        assertThrows(NoSuchElementException.class, ()-> strArrDeque.getFirst());
    }

    @Test
    void removeLast() {
        assertThrows(NoSuchElementException.class, ()-> empty.removeLast());
        assertEquals("last", strArrDeque.getLast());
        assertDoesNotThrow(() -> strArrDeque.removeLast());
        assertEquals("middle", strArrDeque.removeLast());
        assertEquals("first", strArrDeque.removeLast());
        assertThrows(NoSuchElementException.class, ()-> strArrDeque.getLast());
    }

    @Test
    void pop() {
        removeLast();
    }

    @Test
    void pollFirst() {
        assertNull(empty.pollFirst());
        assertEquals("first", strArrDeque.getFirst());
        assertNotNull(strArrDeque.pollFirst());
        assertEquals("middle", strArrDeque.pollFirst());
        assertEquals("last", strArrDeque.pollFirst());
        assertNull(strArrDeque.pollFirst());
    }

    @Test
    void pollLast() {
        assertNull(empty.pollLast());
        assertEquals("last", strArrDeque.getLast());
        assertNotNull(strArrDeque.pollLast());
        assertEquals("middle", strArrDeque.pollLast());
        assertEquals("first", strArrDeque.pollLast());
        assertNull(strArrDeque.pollLast());
    }

    @Test
    void getFirst() {
        assertThrows(NoSuchElementException.class, ()-> empty.getFirst());
        assertEquals("first", strArrDeque.getFirst());
    }

    @Test
    void getLast() {
        assertThrows(NoSuchElementException.class, ()-> empty.getLast());
        assertEquals("last", strArrDeque.getLast());
    }

    @Test
    void peekFirst() {
        assertNull(empty.peekFirst());
        assertEquals("first", strArrDeque.peekFirst());
    }

    @Test
    void peekLast() {
        assertNull(empty.peekLast());
        assertEquals("last", strArrDeque.peekLast());
    }

    @Test
    void size() {
        assertEquals(0, empty.size());
        assertEquals(3, strArrDeque.size());
    }

    @Test
    void stackStream() {
        assertEquals("321",intArrDeque.stackStream()
                .map(Objects::toString).reduce("", String::concat));
    }

    @Test
    void queueStream() {
        assertEquals("123",intArrDeque.queueStream()
                .map(Objects::toString).reduce("", String::concat));
    }

    @Test
    void grow() {
        assertEquals(capacity, intArrDeque.size());
        assertDoesNotThrow(()-> intArrDeque.addLast(4));
        assertDoesNotThrow(()-> intArrDeque.addLast(5));
        assertEquals(5, intArrDeque.size());
    }

    @Test
    void equals() {
        assertEquals(empty, empty);
        assertNotEquals(strArrDeque, empty);
        empty.push("middle");
        empty.addFirst("first");
        empty.addLast("last");
        assertEquals(strArrDeque, strArrDeque);
        assertNotEquals(strArrDeque, intArrDeque);
        assertEquals(strArrDeque, empty);
        empty.pop();
        assertNotEquals(strArrDeque, empty);
        empty.push("new first");
        assertNotEquals(strArrDeque, empty);
        assertNotEquals(strArrDeque, "");
    }

    @Test
    void testHashCode() {
        Set<ArrDequeCustom<?>> hash = new HashSet<>();
        hash.add(strArrDeque);
        assertEquals(1, hash.size());
        hash.add(empty);
        assertEquals(2, hash.size());
        hash.add(new ArrDequeCustom<>());
        assertEquals(2, hash.size());
        empty.push("middle");
        empty.addFirst("first");
        empty.addLast("last");
        hash.add(empty);
        assertEquals(2, hash.size());
        hash.add(intArrDeque);
        assertEquals(3, hash.size());
    }
}

