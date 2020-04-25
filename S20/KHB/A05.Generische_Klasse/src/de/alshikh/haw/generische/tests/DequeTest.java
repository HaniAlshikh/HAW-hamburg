package de.alshikh.haw.generische.tests;

import de.alshikh.haw.generische.clasess.Deque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {

    Deque<Integer> intDeque;
    Deque<String> strDeque;

    @BeforeEach
    void setUp() {
        intDeque = new Deque<>();
        strDeque = new Deque<>();

        strDeque.push("middle");
        strDeque.addFirst("first");
        strDeque.addLast("last");

        intDeque.push(2);
        intDeque.addFirst(1);
        intDeque.addLast(3);
    }

    @Test
    void addFirst() {
        Deque<Integer> addFirstTest = new Deque<>();
        assertThrows(IllegalArgumentException.class, ()-> addFirstTest.addFirst(0) );
        assertDoesNotThrow(() -> strDeque.addFirst("new first"));
        assertEquals("new first", strDeque.getFirst());
    }

    @Test
    void addLast() {
    }

    @Test
    void push() {
    }

    @org.junit.jupiter.api.Test
    void offerFirst() {
    }

    @org.junit.jupiter.api.Test
    void offerLast() {
    }

    @org.junit.jupiter.api.Test
    void removeFirst() {
    }

    @org.junit.jupiter.api.Test
    void removeLast() {
    }

    @org.junit.jupiter.api.Test
    void pop() {
    }

    @org.junit.jupiter.api.Test
    void pollFirst() {
    }

    @org.junit.jupiter.api.Test
    void pollLast() {
    }

    @org.junit.jupiter.api.Test
    void getFirst() {
    }

    @org.junit.jupiter.api.Test
    void getLast() {
    }

    @org.junit.jupiter.api.Test
    void peekFirst() {
    }

    @org.junit.jupiter.api.Test
    void peekLast() {
    }

    @org.junit.jupiter.api.Test
    void size() {
    }

    @org.junit.jupiter.api.Test
    void dump() {
    }
}