package de.alshikh.haw.klausur.SS2019.Classes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public final class A01 {

    private A01 () {}

    public static Comparator<Integer> naturalOrder() {
        return (x, y) -> (x < y) ? -1 : ((x.equals(y)) ? 0 : 1);
    }

    public static long [] generateCoprimes(int number) {
        long[] coprimes = new long[number];
        for (int i = 0; i < number; i++) {
            try {
                coprimes[i] = i == 0 ? 3 : i % 2 == 0 ?
                        (Math.subtractExact(Math.multiplyExact(coprimes[i-1],coprimes[i-1]), 3)) / 2 :
                        Math.addExact(Math.multiplyExact(2, coprimes[i-1]),1);
            } catch (ArithmeticException e) {
                throw new ArithmeticException(""+i);
            }
        }
        return coprimes;
    }

    public static Map<String, Long> wordFrequency(String filename) {

        Map<String, Long> emptyMap = Collections.emptyMap();

        Function<String, Stream<String>> toWordsNoPunc = line -> Stream.of(line
                        .replaceAll("\\p{P}", "").split("\\s+"));
        try (Stream<String> lines = Files.lines(Paths.get(filename), StandardCharsets.UTF_8)) {
            return lines.filter(line -> !line.equals(""))
                    .flatMap(toWordsNoPunc)
                    .map(String::toLowerCase)
                    .collect(groupingBy(Function.identity(), counting()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return emptyMap;
    }
}
