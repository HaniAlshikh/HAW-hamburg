/*#####################################################################
#
# calculate annual interest rate according to german PAngV by simply
# guessing the value using addition. This exploits the fact, that
# the annual interest rate is a bit bigger then the interest rate.
#
# Author:: Hani Alshikh
#
#######################################################################*/

package de.alshikh.haw.grundlagen.clases;

import java.util.Arrays;

public class AnnualInterestRateIterate {
    private static final double delta = 0.0001;
    private static final double precision = 0.0000001;

    /**
     * calculate annual interest rate
     * @param amount loan amount including all fees
     * @param interestRate interest rate set by the bank
     * @param monthlyPayment fixed monthly payment
     * @param term total number of payments
     * @return double the calculated annual interest rate
     */
    public static double calculate(double amount, double interestRate, double monthlyPayment, int term) {
        double[] monthlyPayments = new double[term];
        Arrays.fill(monthlyPayments, monthlyPayment);
        return calculateAnnualInterestRate(amount, interestRate, monthlyPayments);
    }

    /**
     * calculate annual interest rate with first and last payments
     * @param amount loan amount including all fees
     * @param interestRate interest rate set by the bank
     * @param firstPayment custom first payment
     * @param monthlyPayment fixed monthly payment
     * @param term total number of payments
     * @param lastPayment custom last payment
     * @return double the calculated annual interest rate
     */
    public static double calculate(double amount, double interestRate, double firstPayment, double monthlyPayment,
                                   int term, double lastPayment) {
        double[] monthlyPayments = new double[term];
        Arrays.fill(monthlyPayments, monthlyPayment);
        monthlyPayments[0] = firstPayment;
        monthlyPayments[monthlyPayments.length - 1] = lastPayment;
        return calculateAnnualInterestRate(amount, interestRate, monthlyPayments);
    }

    /**
     * bisection method
     * @param amount loan amount including all fees
     * @param interestRate interest rate set by the bank
     * @param monthlyPayments double array of evey monthly payment
     * @return double the calculated annual interest rate
     */
    private static double calculateAnnualInterestRate(double amount, double interestRate, double[] monthlyPayments) {
        while (calculateDifference(amount, interestRate, monthlyPayments) >= precision) {
            interestRate += delta;
        }
        return interestRate;
    }

    /**
     * calculate the difference between the loan amount including all fees and
     * the calculated amount using the PAngV formel
     * @param amount loan amount including all fees
     * @param annualInterestRate annual interest rate to use for the calculation
     * @param monthlyPayments double array of evey monthly payment
     * @return double difference between the loan amount and the calculated one
     */
    private static double calculateDifference(double amount, double annualInterestRate, double[] monthlyPayments) {
        double sum = 0;
        for (int i = 1; i <= monthlyPayments.length; i++) {
            sum += monthlyPayments[i - 1] * Math.pow((1 + annualInterestRate), -(1.0 / 12.0 * i));
        }
        return (sum / amount) - 1;
    }
}
