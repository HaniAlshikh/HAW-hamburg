package de.alshikh.haw.klausur.SS2019.Classes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class DateAndTimeUtils {

    private DateAndTimeUtils() {}

    public static Map<TemporalUnit, Long> getAge(LocalDate geburtsdatum) {

        Map<TemporalUnit, Long> lebensDauer = new HashMap<>();
        LocalDate now = LocalDate.now();

        lebensDauer.put(ChronoUnit.YEARS, ChronoUnit.YEARS.between(geburtsdatum, now));
        lebensDauer.put(ChronoUnit.MONTHS, ChronoUnit.MONTHS.between(geburtsdatum, now));
        lebensDauer.put(ChronoUnit.DAYS, ChronoUnit.DAYS.between(geburtsdatum, now));

        return lebensDauer;

    }


    public static Stream<String> getBinaryPalindromYears (LocalDate start, LocalDate end) {

        Predicate<LocalDate> wishedDate = date ->
                date.getDayOfMonth() == 25 && date.getMonthValue() == 7;
        Predicate<LocalDate> wishedDay = date -> date.getDayOfWeek() == DayOfWeek.SUNDAY;
        Predicate<LocalDate> isPalindromeYear = date -> {
            String binaryString = Integer.toBinaryString(date.getYear());
            return binaryString.equals(new StringBuilder(binaryString).reverse().toString());
        };

        return Stream.iterate(start, date -> date.getYear() <= end.getYear(), date -> date.plusYears(1))
                .filter(wishedDate.and(wishedDay).and(isPalindromeYear))
                .map(date -> date.getYear()+"");
    }

}
