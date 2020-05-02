package de.alshikh.haw.datumuhrzeit.classes;

import java.time.LocalDate;
import java.util.Objects;

/**********************************************************************
 *
 * basic class to represent a payment with it's date
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

public class Payment {

    private final double value;
    private final LocalDate date;

    public Payment(double value, LocalDate date) {
        this.value = value;
        this.date = date;
    }

    public double getValue() {
        return this.value;
    }

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * payments are equal if they have the same date and amount
     * this may differ in reality but no prerequisites are given
     * for the implementation
     *
     * @param o other payment
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.value, value) == 0 &&
                date.equals(payment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, date);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "value=" + value +
                ", date=" + date +
                '}';
    }
}
