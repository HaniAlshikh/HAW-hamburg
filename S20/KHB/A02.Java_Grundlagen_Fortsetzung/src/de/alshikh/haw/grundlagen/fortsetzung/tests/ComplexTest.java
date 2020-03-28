package de.alshikh.haw.grundlagen.fortsetzung.tests;

import de.alshikh.haw.grundlagen.fortsetzung.clases.Cartesian;
import de.alshikh.haw.grundlagen.fortsetzung.clases.Complex;
import de.alshikh.haw.grundlagen.fortsetzung.clases.Polar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {

    private Complex num1;
    private Complex num2;
    private Complex num3;
    private Complex num4;
    private Complex num5;
    private Complex num6;
    private Cartesian cartesian;
    private Polar polar;


    @BeforeEach
    void setUp() {
        this.num1 = new Complex(new Cartesian(10,5));
        this.num2 = new Complex(new Polar(11.180339887498949,0.4636476090008061));
        this.num3 = new Complex(-3, 4, false);
        this.num4 = new Complex(5, 2.214297435588181, true);
        this.num5 = new Complex(10, Math.PI, true);
        this.num6 = new Complex(-10, 0, false);
        this.cartesian = new Cartesian(6,3);
        this.polar = new Polar(6.708203932499369, 0.4636476090008061);
    }


    @Test
    void testComplex() {
//        Complex num = new Complex()
    }

    @Test
    void testAdd() {
        assertEquals("(20 + 10i)", num1.add(num2).toString());
        assertEquals("(26 + 13i)", num1.add(cartesian).toString());
        assertEquals("(16 + 8i)", num2.add(polar).toString());
    }

    @Test
    void testSub() {
        assertEquals("0", num1.sub(num2).toString());
        assertEquals("(-6 - 3i)", num1.sub(cartesian).toString());
        assertEquals("(4 + 2i)", num2.sub(polar).toString());
    }

    @Test
    void testMult() {
        assertEquals("(75 + 100i)", num1.mult(num2).toString());
        assertEquals("(150 + 825i)", num1.mult(cartesian).toString());
        assertEquals("(45 + 60i)", num2.mult(polar).toString());
    }

    @Test
    void testDiv() {
        assertEquals("(1 + e^0i)", num1.div(num2).toStringPolar());
        assertEquals("(0.14907119849998599 + e^-0.4636476090008061i)", num1.div(cartesian).toStringPolar());
        assertEquals("(1.6666666666666667 + e^0i)", num2.div(polar).toStringPolar());
    }

    @Test
    void testEquals() {
        assertEquals(num1, num2);
        assertEquals(num3.getCartesian(), num4.getPolar());
        assertEquals(cartesian, polar);
        assertEquals(num5, num6);
    }

    @Test
    void testHashCode() {
        Set<Object> set = new HashSet<>(Arrays.asList(num1,num2,num3,num4,num5,num6));
        assertEquals(3, set.size());
    }

    @Test
    void testToString() {
        assertEquals("(10 + 5i)", num1.toString());
    }
}