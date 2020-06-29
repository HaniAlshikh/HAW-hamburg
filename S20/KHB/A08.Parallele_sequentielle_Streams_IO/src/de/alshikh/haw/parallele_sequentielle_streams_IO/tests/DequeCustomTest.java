package de.alshikh.haw.parallele_sequentielle_streams_IO.tests;

import de.alshikh.haw.parallele_sequentielle_streams_IO.Toolbox.Toolbox;
import de.alshikh.haw.parallele_sequentielle_streams_IO.classes.DequeCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**********************************************************************
 *
 * basic test cases for the Deque custom Serializable implementation
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
        for (DequeCustom<?> testCase : Arrays.asList(intDeque, strDeque, empty)) {
            // write
            assertDoesNotThrow(() -> Toolbox.serializeObject(testCase, "DequeCustom.ser"));
            // read
            DequeCustom<?> test = assertDoesNotThrow(
                    () -> (DequeCustom<?>) Toolbox.deSerializeObject("DequeCustom.ser"));
            // verify
            assertEquals(testCase, test);
        }
    }
}

