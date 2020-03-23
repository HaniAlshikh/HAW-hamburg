/*#####################################################################
#
# calculate annual interest rate according to german PAngV using Bisection method
#
# Author:: Hani Alshikh
#
#######################################################################*/

package de.alshikh.haw.grundlagen.clases;

import java.util.Arrays;

public class AnnualInterestRate {
    private static final double delta = 0.0001;
    private static final double precision = 0.000001;
    private static final double intervalSize = 0.5;

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
        double annualInterestRate = 0.0;
        // calculate starting intervals for Bisection method
        double[] interval =  calcInterval(amount, interestRate, monthlyPayments);
        // the difference between the actual amount and the calculated one
        double difference = 1;
        // if the difference is smaller than the precision value the approximation is achieved
        while (difference > precision) {
            // Calculate c, the midpoint of the intervalls
            annualInterestRate = (interval[0] + interval[1]) / 2;
            difference = calculateDifference(amount, annualInterestRate, monthlyPayments);
            if (difference > 0) {
                interval[0] = annualInterestRate;
            } else {
                interval[1] = annualInterestRate;
            }
        }
        return annualInterestRate;
    }


    /**
     * calculate a good starting point for the intervall to the root
     * @param amount loan amount including all fees
     * @param interestRate interest rate set by the bank
     * @param monthlyPayments double array of evey monthly payment
     * @return double[] array of starting positive and negative value
     */
    private static double[] calcInterval(double amount, double interestRate, double[] monthlyPayments) {
        double[] interval = {interestRate, interestRate + intervalSize};
        while (calculateDifference(amount, interval[0], monthlyPayments) < 0) {
            interval[0] += delta;
        }
        while (calculateDifference(amount, interval[1], monthlyPayments) > 0) {
            interval[1] -= delta;
        }
        return interval;
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
        return amount - sum;
    }
}
