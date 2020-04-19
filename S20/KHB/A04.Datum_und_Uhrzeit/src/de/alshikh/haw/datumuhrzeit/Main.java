package de.alshikh.haw.datumuhrzeit;

import de.alshikh.haw.datumuhrzeit.classes.AnnualInterestRate;
import de.alshikh.haw.datumuhrzeit.classes.MyThing;
import de.alshikh.haw.datumuhrzeit.classes.Payment;

import java.lang.reflect.Array;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        // erste teilAufgabe

        System.out.println(new MyThing().getINCENSE());



        List<Integer> test = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
//        test.forEach(i -> i + 5);



        // working with time
        LocalDateTime start = LocalDateTime.of(1995, Month.AUGUST, 9, 6, 9);
        LocalDateTime end = LocalDateTime.now();
        Period period = Period.between(start.toLocalDate(), end.toLocalDate());
        Duration duration = Duration.between(start, end);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss");

        System.out.println("Starting Date: " + start.format(pattern));
        System.out.println("Ending Date: " + end.format(pattern));
        System.out.println();

        // In welchen Einheiten können Sie die „Länge“ eines Zeitraums ermitteln, wie machen Sie das?
        System.out.println("Possible Units from a period:\n" + period.getUnits());
        System.out.println("Possible Units from a duration:\n" + duration.getUnits());
        System.out.println();
        System.out.println("Period between start and end:");
        System.out.println(period.getYears() + " years, " +
                           period.getMonths() + " months " +
                  "and " + period.getYears() + " Days ");
        System.out.println("Duration between start and end:");
        System.out.println(duration.getSeconds() + " Seconds " +
                  "and " + duration.getNano() + " Nano ");
        System.out.println();

        System.out.println("in total:");
        for (int i = ChronoUnit.values().length - 2; i >= 0 ; i--) {
            System.out.println(ChronoUnit.values()[i] + ": " + ChronoUnit.values()[i].between(start, end) + "\t");
        }
        System.out.println();

//        System.out.println(Duration.ofDays(365).toHours());
//
//        LocalDate date = LocalDate.now().plus(Period.ofDays(365));
//
//        System.out.println(ChronoUnit.DAYS.between(LocalDate.now(), date));


//        Payment payment = new Payment(136.31, LocalDate.of(2020, Month.MAY,1));
//        System.out.println(AnnualInterestRate.rawPayment(
//                payment,
//                LocalDate.of(2020,Month.APRIL,1),
//                0.0399));

        LocalDate today = LocalDate.now().withDayOfMonth(1);
        Payment loan = new Payment(10000, today);
        int term = 84;
        List<Payment> payments = new ArrayList<>();
        for (int i = 0; i < term; i++) {
            payments.add(i, new Payment(136.31, today.plusMonths(i + 1)));
        }
//        payments.forEach(System.out::println);

        System.out.println(AnnualInterestRate.calculate(loan, 0.0388, payments));

//        System.out.println(payments.stream().mapToDouble(payment -> payment.getValue()).sum());

//        loan = new Payment(684.90, today);
//        term = 24;
//        payments = new Payment[term];
//        for (int i = 0; i < term; i++) {
//            payments[i] = new Payment(31.84, today.plusMonths(i + 1));
//        }
//        System.out.println(AnnualInterestRate.calculate(loan, 0.1195, payments));
//        System.out.println(AnnualInterestRate.rawPayment(payments, loan.getDate(), 0.1195));


//        System.out.println(AnnualInterestRate.rawPayment(
//                payments,
//                LocalDate.of(2020,Month.APRIL,1),
//                0.0399));

//        System.out.println(Arrays.toString(AnnualInterestRate.rawPayment(
//                "2020-04-01",new double[]{136.31, 200, 300, 400},10000, "2018-04-14", 0.0399)));
//

//        System.out.println(AnnualInterestRate.algoTest(10000, 0.0399, 136.31));


//        for (int i = 1; i <= 84 ; i++) {
//            double zins = Math.pow((1 + 0.0399), -(1.0 / 12.0 * i));
//            System.out.println(zins + " * " + "136.31 = " + zins * 136.31);
//        }


    }

}
