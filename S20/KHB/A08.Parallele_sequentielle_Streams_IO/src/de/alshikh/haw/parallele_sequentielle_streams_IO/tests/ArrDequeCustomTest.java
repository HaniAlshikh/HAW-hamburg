package de.alshikh.haw.parallele_sequentielle_streams_IO.tests;

import de.alshikh.haw.parallele_sequentielle_streams_IO.Toolbox.Toolbox;
import de.alshikh.haw.parallele_sequentielle_streams_IO.classes.ArrDequeCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
    }


    @Test
    void serializing() {

        // write
        assertDoesNotThrow(() -> Toolbox.serializeObject(strArrDeque, "ArrDequeCustom.ser"));

        // read
        @SuppressWarnings("unchecked")
        ArrDequeCustom<String> readTest = (ArrDequeCustom<String>)
                assertDoesNotThrow(() -> Toolbox.deSerializeObject("ArrDequeCustom.ser"));

        // verify the object state
        assertEquals(strArrDeque, readTest);
    }
}

