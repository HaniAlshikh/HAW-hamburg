package de.alshikh.haw.parallele_sequentielle_streams_IO.tests;

import de.alshikh.haw.parallele_sequentielle_streams_IO.Toolbox.Toolbox;
import de.alshikh.haw.parallele_sequentielle_streams_IO.classes.DequeCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void serializing() {

        // write
        assertDoesNotThrow(() -> Toolbox.serializeObject(strDeque, "DequeCustom.ser"));

        // read
        @SuppressWarnings("unchecked")
        DequeCustom<String> readTest = (DequeCustom<String>)
                assertDoesNotThrow(() -> Toolbox.deSerializeObject("DequeCustom.ser"));

        // verify the object state
        assertEquals(strDeque, readTest);
    }

}

