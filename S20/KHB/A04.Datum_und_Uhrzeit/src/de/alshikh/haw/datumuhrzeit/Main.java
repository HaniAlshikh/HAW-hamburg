package de.alshikh.haw.datumuhrzeit;

import de.alshikh.haw.datumuhrzeit.classes.MyThing;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**********************************************************************
 *
 * demonstrating the solution of the 4.th assigment
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

public class Main {

    public static void main(String[] args) {

        // region ****************** 1.1. MyThing ******************

        System.out.println("******** 1.1. MyThing demonstration ********\n");
        System.out.println(new MyThing());
        System.out.println();

        // endregion


        // region ****************** 2. Date and Time Units ******************

        System.out.println("******** 2. Date and Time units demonstration ********\n");

        LocalDateTime start = LocalDateTime.of(1995, Month.AUGUST, 9, 6, 9);
        LocalDateTime end = LocalDateTime.now();
        Period period = Period.between(start.toLocalDate(), end.toLocalDate());
        Duration duration = Duration.between(start, end);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss");

        System.out.println("Starting Date: " + start.format(pattern));
        System.out.println("Ending Date: " + end.format(pattern));
        System.out.println();

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

        // endregion

        // region ****************** 3. Date and Time Representation ******************

        System.out.println("******** 3. Date and Time Representation ********\n");

        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("1. using DateTimeFormatter:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("\t- basic: " + now.format(formatter));
        formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a z");
        System.out.println("\t- with zone: " + now.format(formatter));
        formatter = DateTimeFormatter.ofPattern("E, MMM dd yyy h:mm a");
        System.out.println("\t- presentable: " + now.format(formatter));
        formatter = DateTimeFormatter.ofPattern("EEEE d MMMM uuuu", new Locale("ar"));
        System.out.println("\t- mielady: " + now.format(formatter));
        System.out.println("\t- Hijrah: " + HijrahDate.now().format(formatter));
        System.out.println();


        Date dateNow = new Date();
        System.out.println("2. using SimpleDateFormat:");
        SimpleDateFormat Sformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("\t- basic: " + Sformatter.format(dateNow));
        Sformatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a z");
        System.out.println("\t- with zone: " + Sformatter.format(dateNow));
        Sformatter = new SimpleDateFormat("E, MMM dd yyy h:mm a");
        System.out.println("\t- presentable: " + Sformatter.format(dateNow));
        Sformatter = new SimpleDateFormat("EEEE d MMMM YYYY", new Locale("ar"));
        // interessant hier, dass eine Variant der indischen Zahlen benutzt wird, die
        // Araber eigentlich auch benutzen. Araber benutzen die arabische Zahlen nicht :).
        System.out.println("\t- mielady: " + Sformatter.format(dateNow));

        // endregion
    }
}
