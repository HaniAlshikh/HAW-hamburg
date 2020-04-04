package de.alshikh.haw.grundlagen.fortsetzung.tests;

import de.alshikh.haw.grundlagen.fortsetzung.classes.ComplexMath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**********************************************************************
 *
 * @author Hani Alshikh
 *
 ***********************************************************************/
class ComplexMathTest {
    @Test
    void toPolar() {
        assertArrayEquals(new double[]{6.708203932499369, 0.4636476090008061}, ComplexMath.toPolar(6,3));
        assertArrayEquals(new double[]{9.899494936611665, -0.7853981633974483}, ComplexMath.toPolar(7,-7));
    }

    @Test
    void toCartesian() {
        assertArrayEquals(new double[]{9.649660284921133, -2.6237485370392877}, ComplexMath.toCartesian(10,50));
        assertArrayEquals(new double[]{-2.2403680806458506, 4.4699833180027895}, ComplexMath.toCartesian(5,90));
    }
}