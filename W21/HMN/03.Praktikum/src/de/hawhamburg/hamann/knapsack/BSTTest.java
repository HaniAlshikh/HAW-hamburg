package de.hawhamburg.hamann.knapsack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {

    BST<Integer, String> bst;
    List<Integer> randomKeys;

    @BeforeEach
    void setUp() {
        bst = new BST<>(Integer::compare);
        randomKeys = ThreadLocalRandom.current().ints().boxed().limit(40).collect(Collectors.toList());
    }

    @Test
    void add() {
        assertEquals(0, bst.size());
        bst.add(1, "root");
        assertEquals(1, bst.size());
        assertTrue(bst.contains(1));
    }

    @Test
    void remove_valid() {
        bst.add(1, "root");
        bst.remove(1);
        assertEquals(0, bst.size());
        assertFalse(bst.contains(1));
    }

    @Test
    void remove_notValid() {
        bst.remove(2);
        bst.add(1, "root");
        bst.remove(2);
        assertEquals(1, bst.size());
        assertTrue(bst.contains(1));
    }

    @Test
    void removingParentNode_ChildNodesRemoved() {
        bst.add(5, "root");
        bst.add(6, "right");
        bst.add(7, "right-right");
        bst.add(4, "left");
        bst.add(3, "left-left");
        bst.remove(4);
        assertTrue(bst.contains(5));
        assertTrue(bst.contains(6));
        assertTrue(bst.contains(7));
        assertFalse(bst.contains(4));
        assertFalse(bst.contains(3));
    }

    @Test
    void search_found() {
        bst.add(1, "root");
        assertEquals("root", bst.search(1));
    }

    @Test
    void search_Notfound() {
        assertNull(bst.search(2));
        bst.add(1, "root");
        assertNull(bst.search(2));
    }

    @Test
    void searchAllElementRandom() {
        randomKeys.forEach(k -> bst.add(k, "e" + k));
        randomKeys.forEach(k -> assertEquals("e" + k, bst.search(k)));
    }

    @Test
    void contains() {
        bst.add(1, "root");
        assertTrue(bst.contains(1));
        assertFalse(bst.contains(2));
    }

    @Test
    void containsAllElementRandom() {
        randomKeys.forEach(k -> bst.add(k, "e" + k));
        randomKeys.forEach(k -> assertTrue(bst.contains(k)));
    }
}
