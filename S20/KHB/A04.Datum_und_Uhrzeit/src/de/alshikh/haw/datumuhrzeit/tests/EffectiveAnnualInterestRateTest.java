package de.alshikh.haw.datumuhrzeit.tests;

import de.alshikh.haw.datumuhrzeit.classes.EffectiveAnnualInterestRate;
import de.alshikh.haw.datumuhrzeit.classes.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**********************************************************************
 *
 * basic test cases for the AnnualInterestRate class
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

class EffectiveAnnualInterestRateTest {

    private LocalDate payOut =  LocalDate.now().withDayOfMonth(1);
    private Function<Double, Payment> toLoan = v -> new Payment(v, payOut);
    private BiFunction<Double, Integer , List<Payment>> generatePayments = (payment, term) -> {
        List<Payment> payments = new ArrayList<>();
        for (int i = 0; i < term; i++) {
            payments.add(i, new Payment(payment, payOut.plusMonths(i + 1)));
        }
        return payments;
    };


    @Test
    void calculate() {
        assertEquals(3.99, Math.round(EffectiveAnnualInterestRate.calculate(
                toLoan.apply(10000.0), 0.0388, generatePayments.apply(136.31, 84)) * 10000.0) / 100.0);
        assertEquals(4.07, Math.round(EffectiveAnnualInterestRate.calculate(
                toLoan.apply(30000.0), 0.04, generatePayments.apply(410.06, 84)) * 10000.0) / 100.0);
        assertEquals(3.04, Math.round(EffectiveAnnualInterestRate.calculate(
                toLoan.apply(100000.0), 0.03, generatePayments.apply(965.61, 120)) * 10000.0) / 100.0);
    }

    @Test
    void rawPayment() {
        assertEquals(10000, EffectiveAnnualInterestRate.rawPayment(
                generatePayments.apply(136.31, 84), payOut, 0.0399), 5);
        assertEquals(100000, EffectiveAnnualInterestRate.rawPayment(
                generatePayments.apply(965.61, 120), payOut, 0.0304), 5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Payment loan = new Payment(5000, LocalDate.parse("22.08.2016", formatter));
        List<Payment> payments = new ArrayList<>(Arrays.asList(
                new Payment(500, LocalDate.parse("10.10.2016", formatter)),
                new Payment(750, LocalDate.parse("28.11.2016", formatter)),
                new Payment(1000, LocalDate.parse("02.02.2017", formatter)),
                new Payment(2500, LocalDate.parse("29.05.2017", formatter)),
                new Payment(350.16, LocalDate.parse("25.07.2017", formatter))
        ));
        assertEquals(loan.getValue(), EffectiveAnnualInterestRate.rawPayment(
                payments, loan.getDate(), 0.035), 5);
    }
}