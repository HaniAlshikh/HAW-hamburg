package de.alshikh.haw.parallele_sequentielle_streams_IO.tests;

import de.alshikh.haw.parallele_sequentielle_streams_IO.Toolbox.Toolbox;
import de.alshikh.haw.parallele_sequentielle_streams_IO.classes.ArrDequeCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**********************************************************************
 *
 * basic test cases for the ArrDeque custom Serializable implementation
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

class ArrDequeCustomTest {

    ArrDequeCustom<Integer> intArrDeque;
    ArrDequeCustom<String> strArrDeque;
    ArrDequeCustom<Object> empty;
    ArrDequeCustom<Object> zero;
    int capacity = 3;

    @BeforeEach
    void setUp() {
        intArrDeque = new ArrDequeCustom<>(capacity);
        strArrDeque = new ArrDequeCustom<>(capacity);
        zero = new ArrDequeCustom<>(0);
        empty = new ArrDequeCustom<>();

        strArrDeque.push("middle");
        strArrDeque.addFirst("first");
        strArrDeque.addLast("last");

        intArrDeque.push(2);
        intArrDeque.addFirst(1);
        intArrDeque.addLast(3);
    }


    @Test
    void serializing() {
        for (ArrDequeCustom<?> testCase : Arrays.asList(intArrDeque, strArrDeque, empty, zero)) {
            // write
            assertDoesNotThrow(() -> Toolbox.serializeObject(testCase, "ArrDequeCustom.ser"));
            // read
            ArrDequeCustom<?> test = assertDoesNotThrow(
                    () -> (ArrDequeCustom<?>) Toolbox.deSerializeObject("ArrDequeCustom.ser"));
            // verify
            assertEquals(testCase, test);
        }
    }
}

