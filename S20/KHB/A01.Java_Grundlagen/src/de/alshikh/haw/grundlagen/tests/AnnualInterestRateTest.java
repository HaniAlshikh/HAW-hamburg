/**********************************************************************
 *
 * basic test cases for the AnnualInterestRate class
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

package de.alshikh.haw.grundlagen.tests;

import de.alshikh.haw.grundlagen.clases.AnnualInterestRate;

import static org.junit.jupiter.api.Assertions.*;

class AnnualInterestRateTest {
    @org.junit.jupiter.api.Test
    void calculate() {
        assertEquals(3.95, Math.round(AnnualInterestRate.calculate(10000, 0.0388, 136.14, 84) * 10000.0) / 100.0);
        assertEquals(4.07, Math.round(AnnualInterestRate.calculate(30000, 0.04, 410.06, 84) * 10000.0) / 100.0);
        assertEquals(3.04, Math.round(AnnualInterestRate.calculate(100000, 0.03, 965.61, 120) * 10000.0) / 100.0);
        assertEquals(3.24, Math.round(AnnualInterestRate.calculate(8815.11, 0.0295, 154, 89, 48, 5478.29) * 10000.0) / 100.0);
    }
}