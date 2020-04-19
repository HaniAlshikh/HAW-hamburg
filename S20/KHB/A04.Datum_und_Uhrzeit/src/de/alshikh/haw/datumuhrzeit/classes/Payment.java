package de.alshikh.haw.datumuhrzeit.classes;

import java.time.LocalDate;

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

    @Override
    public String toString() {
        return "Payment{" +
                "value=" + value +
                ", date=" + date +
                '}';
    }
}
