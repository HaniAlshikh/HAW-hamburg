package de.alshikh.haw.parallele_sequentielle_streams_IO.tests;

import de.alshikh.haw.parallele_sequentielle_streams_IO.Toolbox.Toolbox;
import de.alshikh.haw.parallele_sequentielle_streams_IO.classes.DequeProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**********************************************************************
 *
 * basic test cases for the Deque class
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

class DequeProxyTest {

    DequeProxy<Integer> intDeque;
    DequeProxy<String> strDeque;
    DequeProxy<Object> empty;

    @BeforeEach
    void setUp() {
        intDeque = new DequeProxy<>();
        strDeque = new DequeProxy<>();
        empty = new DequeProxy<>();

        strDeque.push("middle");
        strDeque.addFirst("first");
        strDeque.addLast("last");

        intDeque.push(2);
        intDeque.addFirst(1);
        intDeque.addLast(3);
    }


    @Test
    void serializing() {

        for (DequeProxy<?> testCase : Arrays.asList(intDeque, strDeque, empty)) {
            // write
            assertDoesNotThrow(() -> Toolbox.serializeObject(testCase, "DequeProxy.ser"));
            // read
            DequeProxy<?> test = assertDoesNotThrow(
                    () -> (DequeProxy<?>) Toolbox.deSerializeObject("DequeProxy.ser"));
            // verify
            assertEquals(testCase, test);
        }
    }
}

