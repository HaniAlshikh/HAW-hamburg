package de.alshikh.haw.parallele_sequentielle_streams_IO.tests;

import de.alshikh.haw.parallele_sequentielle_streams_IO.Toolbox.Toolbox;
import de.alshikh.haw.parallele_sequentielle_streams_IO.classes.ArrDequeProxy;
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

class ArrDequeProxyTest {

    ArrDequeProxy<Integer> intArrDeque;
    ArrDequeProxy<String> strArrDeque;
    ArrDequeProxy<Object> empty;
    int capacity = 3;

    @BeforeEach
    void setUp() {
        intArrDeque = new ArrDequeProxy<>(capacity);
        strArrDeque = new ArrDequeProxy<>(capacity);
        empty = new ArrDequeProxy<>(0);

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
        assertDoesNotThrow(() -> Toolbox.serializeObject(strArrDeque, "ArrDequeProxy.ser"));

        // read
        @SuppressWarnings("unchecked")
        ArrDequeProxy<String> readTest = (ArrDequeProxy<String>)
                assertDoesNotThrow(() -> Toolbox.deSerializeObject("ArrDequeProxy.ser"));

        // verify the object state
        assertEquals(strArrDeque, readTest);
    }
}

