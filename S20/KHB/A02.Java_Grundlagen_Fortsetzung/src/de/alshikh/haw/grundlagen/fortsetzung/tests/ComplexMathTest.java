/**********************************************************************
 *
 * @author Mateusz Chylewski
 * @author Hani Alshikh
 *
 ***********************************************************************/

package de.alshikh.haw.grundlagen.fortsetzung.tests;

import de.alshikh.haw.grundlagen.fortsetzung.clases.Cartesian;
import de.alshikh.haw.grundlagen.fortsetzung.clases.Complex;
import de.alshikh.haw.grundlagen.fortsetzung.clases.Polar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexMathTest {

    private Cartesian cartesian;
    private Polar polar;
    private Cartesian cart;
    private Polar pol;

    @BeforeEach
    void setUp() {
        this.cartesian = new Cartesian(6,3);
        this.polar = new Polar(6.708203932499369, 0.4636476090008061);
        this.pol = new Polar(0, Math.PI * 2);
        this.cart = new Cartesian(0,0);

    }

    @Test
    void toPolar() {
        assertTrue(Complex.equals(polar, cartesian.toPolar(), 6));
        assertTrue(Complex.equals(pol, cart.toPolar(), 6));
    }

    @Test
    void toCartesian() {
        assertTrue(Complex.equals(cartesian, polar.toCartesian(), 6));
        assertTrue(Complex.equals(cart, pol.toCartesian(), 6));
    }
}