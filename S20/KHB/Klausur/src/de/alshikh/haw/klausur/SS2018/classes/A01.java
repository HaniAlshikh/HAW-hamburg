package de.alshikh.haw.klausur.SS2018.classes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

public final class A01 {

    private A01() {}

    public static LocalDate getAnniversary(LocalDate founding, int years) {
        if (years<0) throw new RuntimeException();
        return founding.plusYears(years);
    }

    public static ZonedDateTime switchTimeZone(ZonedDateTime zdt, ZoneId zoneId) {
        return ZonedDateTime.of(zdt.toLocalDateTime(), zoneId);
    }

    public static long factorial(long n) {
        if (n < 0) throw new ArithmeticException("n < 0");
        if (n == 0) return 1;
        long fac = 1;
        for (int i = 1; i <= n ; i++) {
            fac = Math.multiplyExact(fac, i);
        }
        return fac;
    }
}
