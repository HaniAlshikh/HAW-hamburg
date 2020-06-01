package de.alshikh.haw.parallele_sequentielle_streams_IO.tests;

import de.alshikh.haw.parallele_sequentielle_streams_IO.Toolbox.Toolbox;
import de.alshikh.haw.parallele_sequentielle_streams_IO.classes.ArrDequeProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**********************************************************************
 *
 * basic test cases for the ArrDeque serialization proxy
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

class ArrDequeProxyTest {

    ArrDequeProxy<Integer> intArrDeque;
    ArrDequeProxy<String> strArrDeque;
    ArrDequeProxy<Object> empty;
    ArrDequeProxy<Object> zero;
    int capacity = 3;

    @BeforeEach
    void setUp() {
        intArrDeque = new ArrDequeProxy<>(capacity);
        strArrDeque = new ArrDequeProxy<>(capacity);
        zero = new ArrDequeProxy<>(0);
        empty = new ArrDequeProxy<>();

        strArrDeque.push("middle");
        strArrDeque.addFirst("first");
        strArrDeque.addLast("last");

        intArrDeque.push(2);
        intArrDeque.addFirst(1);
        intArrDeque.addLast(3);
    }

    @Test
    void serializing() {
        for (ArrDequeProxy<?> testCase : Arrays.asList(intArrDeque, strArrDeque, empty, zero)) {
            // write
            assertDoesNotThrow(() -> Toolbox.serializeObject(testCase, "ArrDequeProxy.ser"));
            // read
            ArrDequeProxy<?> test = assertDoesNotThrow(
                    () -> (ArrDequeProxy<?>) Toolbox.deSerializeObject("ArrDequeProxy.ser"));
            // verify
            assertEquals(testCase, test);
        }
    }
}

