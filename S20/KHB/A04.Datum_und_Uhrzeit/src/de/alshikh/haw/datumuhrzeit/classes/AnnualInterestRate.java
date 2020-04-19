package de.alshikh.haw.datumuhrzeit.classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
     * calculate annual interest rate with first and last payments
     * @param loan loan amount including all fees
     * @param interestRate interest rate set by the bank
     * @param payments fixed monthly payment
     * @return double the calculated annual interest rate
     */
    public static double calculate(Payment loan, double interestRate, List<Payment> payments){
        return calculateAnnualInterestRate(loan, interestRate, payments);
    }


    /**
     * bisection method
     * @param loan loan amount including all fees
     * @param interestRate interest rate set by the bank
     * @param payments double array of evey monthly payment
     * @return double the calculated annual interest rate
     */
    private static double calculateAnnualInterestRate(Payment loan, double interestRate, List<Payment> payments) {
        double annualInterestRate = 0;
        payments.add(0, new Payment(-loan.getValue(), loan.getDate()));

        // calculate starting intervals for Bisection method
        double[] interval =  calcInterval(payments, interestRate);
        // the difference between the actual amount and the calculated one
        double difference = 1;
        // if the difference is smaller than the precision value the approximation is achieved
        while (difference > PRECISION) {
            // Calculate c, the midpoint of the intervalls
            annualInterestRate = (interval[0] + interval[1]) / 2;
            difference = rawPayment(payments, payments.get(0).getDate(), annualInterestRate);
            if (difference < 0) {
                interval[0] = annualInterestRate;
            } else {
                interval[1] = annualInterestRate;
            }
        }
        return annualInterestRate;
    }


    /**
     * calculate a good starting point for the intervall to the root
     * @param loanWithPayments loan amount including all fees
     * @param interestRate interest rate set by the bank
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

    /**
     * calculate the difference between the loan amount including all fees and
     * the calculated amount using the PAngV formel
     * @param payment loan amount including all fees
     * @param payOutDate annual interest rate to use for the calculation
     * @param annualInterestRate double array of evey monthly payment
     * @return double difference between the loan amount and the calculated one
     */
    public static double rawPayment(Payment payment, LocalDate payOutDate, double annualInterestRate) {
        long daysUntilPay = ChronoUnit.DAYS.between(payOutDate, payment.getDate());
        return payment.getValue() * Math.pow((1 + annualInterestRate), -(daysUntilPay / 365.0));
    }

    public static double rawPayment(List<Payment> payments, LocalDate payOutDate, double annualInterestRate) {
        return payments.stream()
                .mapToDouble(payment -> rawPayment(payment, payOutDate, annualInterestRate))
                .sum();

    }
}
