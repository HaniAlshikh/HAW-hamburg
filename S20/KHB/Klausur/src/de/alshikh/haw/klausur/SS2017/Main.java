package de.alshikh.haw.klausur.SS2017;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        LocalDateTime beginn = LocalDate.of(2016,9,1).atStartOfDay();
        LocalDateTime end = beginn.plusYears(3);

        System.out.println(ChronoUnit.DAYS.between(beginn, end));

        // ????

        LocalDateTime failEnd = end.plusMonths(18);
        System.out.println(ChronoUnit.MINUTES.between(end, failEnd));


        // ---------------------------

        LocalDate start = LocalDate.ofYearDay(2000,1);
        Predicate<LocalDate> wishedDate = date -> date.getDayOfMonth() == 25
                && date.getMonthValue() == 7 && date.getDayOfWeek() == DayOfWeek.SUNDAY;

        List<Integer> heiligeJahre = Stream
                .iterate(start, date -> date.getYear() <= 2100, date -> date.plusDays(1))
                .filter(wishedDate)
                .map(LocalDate::getYear)
                .collect(Collectors.toList());

        System.out.println(heiligeJahre);

        for (int i = 0; i < heiligeJahre.size() - 1; i++) {
            System.out.print(heiligeJahre.get(i+1) - heiligeJahre.get(i)+", ");
        }
        System.out.println();


        // -----------------------------

        try (Scanner sc = new Scanner(new File("./src/de/alshikh/haw/klausur/SS2017/test/QueueTest.java"))) {
            System.out.println(sc.findAll("import").count());
            System.out.println(sc.findAll("@Test").count());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }



    }
}
