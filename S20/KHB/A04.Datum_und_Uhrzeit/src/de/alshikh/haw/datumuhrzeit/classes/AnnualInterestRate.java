package de.alshikh.haw.datumuhrzeit.classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 *
 * calculate annual interest rate according to german PAngV using Bisection method
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

public final class AnnualInterestRate {
    private static final double DELTA = 10e-6;
    private static final double PRECISION = 10e-6;
    private static final double INTERVAL_SIZE = 0.5;


    // Private constructor to prevent instantiation
    private AnnualInterestRate() {
        throw new UnsupportedOperationException();
    }

    /**
     * calculate annual interest rate for a loan with it's payments.
     *
     * @param loan               amount including all fees.
     * @param interestRate       set by the bank.
     * @param payments           to payback the loan including all fees
     *
     * @return double the calculated annual interest rate
     */
    public static double calculate(Payment loan, double interestRate, List<Payment> payments){
        return calculateAnnualInterestRate(loan, interestRate, payments);
    }

    /**
     * calculate the raw amount of a payment
     * according to the german PAngV
     *
     * @param payment            from which the raw amount is calculated
     * @param payOutDate         the date on which the bank paid out the loan
     * @param annualInterestRate set by the bank
     *
     * @return double difference between the loan amount and the calculated one
     */
    public static double rawPayment(Payment payment, LocalDate payOutDate, double annualInterestRate) {
        long daysUntilPay = ChronoUnit.DAYS.between(payOutDate, payment.getDate());
        return payment.getValue() * Math.pow((1 + annualInterestRate), -(daysUntilPay / 365.0));
    }

    /**
     * calculate the raw amount paid of multiple payments
     * according to the german PAngV
     *
     * @param payments           from which the raw amount is calculated
     * @param payOutDate         annual interest rate to use for the calculation
     * @param annualInterestRate double array of evey monthly payment
     *
     * @return double difference between the loan amount and the calculated one
     */
    public static double rawPayment(List<Payment> payments, LocalDate payOutDate, double annualInterestRate) {
        return payments.stream()
                .mapToDouble(payment -> rawPayment(payment, payOutDate, annualInterestRate))
                .sum();
    }

    /**
     * bisection method
     *
     * @param loan               amount including all fees
     * @param interestRate       interest rate set by the bank
     * @param payments           Payment array of every payment
     *
     * @return double the calculated annual interest rate
     */
    private static double calculateAnnualInterestRate(Payment loan, double interestRate, List<Payment> payments) {
        double annualInterestRate = 0;
        // new list is necessary to avoid side effects on the payments list.
        List<Payment> loanWithPayments = new ArrayList<>(payments);
        loanWithPayments.add(0, new Payment(-loan.getValue(), loan.getDate()));
        // calculate starting intervals for Bisection method
        double[] interval =  calcInterval(loanWithPayments, interestRate);
        // the difference between the actual amount and the calculated one
        double difference = 1;
        // if the difference is smaller than the precision value the approximation is achieved
        while (difference > PRECISION) {
            // Calculate c, the midpoint of the intervalls
            annualInterestRate = (interval[0] + interval[1]) / 2;
            difference = rawPayment(loanWithPayments, loanWithPayments.get(0).getDate(), annualInterestRate);
            if (difference < 0) {
                interval[0] = annualInterestRate;
            } else {
                interval[1] = annualInterestRate;
            }
        }
        return annualInterestRate;
    }


    /**
     * calculate a good starting point for the interval to the root
     *
     * @param loanWithPayments Payment array of loan and all payments
     *                         loan musst be negativ
     * @param interestRate     set by the bank
     *
     * @return double[] array of starting positive and negative value
     */
    private static double[] calcInterval(List<Payment> loanWithPayments, double interestRate) {
        double[] interval = {interestRate, interestRate + INTERVAL_SIZE};
        while (rawPayment(loanWithPayments, loanWithPayments.get(0).getDate(), interval[0]) > 0) {
            interval[0] += DELTA;
        }
        while (rawPayment(loanWithPayments, loanWithPayments.get(0).getDate(), interval[1]) < 0) {
            interval[1] -= DELTA;
        }
        return interval;
    }
}
