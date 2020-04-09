package de.alshikh.haw.grundlagen.fortsetzung.tests;

import de.alshikh.haw.grundlagen.fortsetzung.classes.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**********************************************************************
 *
 * @author Hani Alshikh
 *
 ***********************************************************************/
class ComplexTest {

    private Complex num1;
    private Complex num2;
    private Complex num3;
    private Complex num4;
    private Complex num5;
    private Complex num6;


    @BeforeEach
    void setUp() {
        this.num1 = new Complex(10,5);
        this.num2 = Complex.polar(11.180339887498949,0.4636476090008061);
        this.num3 = new Complex(6,3);
        this.num4 = Complex.polar(5, 2.214297435588181);
        this.num5 = Complex.polar(6.708203932499369, 0.4636476090008061);
        this.num6 = new Complex(-10, 0);
    }


    @Test
    void Add() {
        assertEquals("(20 + 10i)", num1.add(num2).toString());
        assertEquals("(26 + 13i)", num1.add(num3).toString());
        assertEquals("(16 + 8i)", num2.add(num5).toString());
    }

    @Test
    void Sub() {
        assertEquals("0", num1.sub(num2).toString());
        assertEquals("(-6 - 3i)", num1.sub(num3).toString());
        assertEquals("(4 + 2i)", num2.sub(num5).toString());
    }

    @Test
    void Mult() {
        assertEquals("(75 + 100i)", num1.mult(num2).toString());
        assertEquals("(150 + 825i)", num1.mult(num3).toString());
        assertEquals("(45 + 60i)", num2.mult(num5).toString());
    }

    @Test
    void Div() {
        assertEquals("1", num1.div(num2).toStringPolar());
        assertEquals("(0.14907119849998596 + e^-0.4636476090008061i)", num1.div(num3).toStringPolar());
        assertEquals("1.6666666666666667", num2.div(num5).toStringPolar());
    }

    @Test
    void Equals() {
        assertEquals(num1, new Complex(10,5));
        assertEquals(num4, Complex.polar(5, 2.214297435588181));
        assertEquals(num6, new Complex(-10, 0));
    }

    @Test
    void HashCode() {
        Set<Object> set = new HashSet<>(Arrays.asList(num1,new Complex(10,5),
                num4, Complex.polar(5, 2.214297435588181),
                num5, Complex.polar(6.708203932499369, 0.4636476090008061)));
        assertEquals(3, set.size());
    }

    @Test
    void Mutability() {
        num1.setReal(6);
        num1.setImag(3);
        assertEquals(num1, num3);
    }

    @Test
    void ToString() {
        assertEquals("(10 + 5i)", num1.toString());
        assertEquals("(11.180339887498949 + e^0.4636476090008061i)", num2.toStringPolar());
    }
}