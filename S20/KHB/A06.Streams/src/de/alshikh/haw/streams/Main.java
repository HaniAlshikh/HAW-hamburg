package de.alshikh.haw.streams;

import de.alshikh.haw.streams.interfaces.Better;
import de.alshikh.haw.streams.toolbox.Toolbox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

/**********************************************************************
 *
 * Lambdas and Streams basic usage demonstration
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

public class Main {

    public static void main(String[] args) {

        // region ****************** 2.1. Binary palindrome year ******************

        Predicate<String> isPalindrome = s -> {
            int n = s.length();
            for (int i = 0; i < (n/2); i++)
                if (s.charAt(i) != s.charAt(n - i - 1)) return false;
            return true;
        };

        Year startingYear = Year.now();

        Year binaryPalindromeYear = Stream.iterate(startingYear, year -> year.plusYears(1))
            .filter(year -> isPalindrome.test(Integer.toBinaryString(year.getValue())))
            .findFirst()
            .orElse(startingYear);

        System.out.println("\nFirst binary palindrome year: " + binaryPalindromeYear + "\n");

        // endregion

        // region ****************** 2.2. 13th is friday ******************

        Predicate<LocalDate> isFriday = date -> date.getDayOfWeek() == DayOfWeek.FRIDAY;
        Predicate<LocalDate> dayNum13 = date -> date.getDayOfMonth() == 13;

        LocalDate startDate = LocalDate.now();

        // with limit
        System.out.println("Fridays that fall on the 13th of a month:");
        System.out.println("- With limit: ");
        Stream.iterate(startDate, date -> date.plusDays(1))
            .filter(isFriday.and(dayNum13))
            .limit(5)
            .forEach(s -> System.out.print(s + " "));
        System.out.println();

        // with a predicate
        System.out.println("- With a predicate: ");
        Stream.iterate(startDate, date-> date.getYear() < 2024, date -> date.plusDays(1))
                .filter(isFriday.and(dayNum13))
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

        // with take while
        System.out.println("- With take while: ");
        startDate.datesUntil(LocalDate.MAX)
            .takeWhile(date-> date.getYear() < 2024)
            .filter(isFriday.and(dayNum13))
            .forEach(s -> System.out.print(s + " "));
        System.out.println();

        // with findFirst (or any terminal operation with valid syntax)
        System.out.println("- With terminal operation: ");
        LocalDate ignore = Stream.iterate(startDate, date -> date.plusDays(1))
            .peek(date ->{ if(isFriday.and(dayNum13).test(date)) System.out.print(date + " ");})
            .filter(date -> date.getYear() > 2023)
            .findFirst()
            .orElse(startDate);
        System.out.println(ignore.toString().replaceAll(".*", ""));

        // with stream builder
        System.out.println("- With stream builder: ");
        Stream.Builder<LocalDate> datesStream = Stream.builder();
        for (LocalDate date = startDate; date.getYear() != 2024; date = date.plusDays(1))
            datesStream.add(date);
        datesStream.build()
                .filter(isFriday.and(dayNum13))
                .forEach(s -> System.out.print(s + " "));
        System.out.println("\n");

        // endregion

        // region ****************** 2.3. Word frequency ******************

        String file = "text.txt";
        Function<String, Stream<String>> toWordsNoPunct =
                line -> Stream.of(line.replaceAll("\\p{Punct}", "").split("\\s+"));

        try (Stream<String> lines = Files.lines(Paths.get(file))) {

            System.out.println("Words count:");

            // using collect and Collectors
            //Map<String, Long> resultCollect = lines
            //        .filter(line -> !line.equals(""))
            //        .flatMap(toWordsNoPunct)
            //        .map(String::toLowerCase)
            //        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            //System.out.println(resultCollect + "\n");

            // using reduce
            Map<String, Integer> resultReduce = lines
                .filter(line -> !line.equals(""))
                .flatMap(toWordsNoPunct)
                .map(String::toLowerCase)
                .reduce(
                    // identity
                    new HashMap<>(),
                    // Accumulator
                    (accuMap, word) -> { accuMap.merge(word, 1, Integer::sum); return accuMap; },
                    // Combiner
                    (countMap, accuMap) -> countMap = accuMap
                );
            System.out.println(resultReduce + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // endregion

        // region ****************** 2.4. Better compare ******************

        //BiPredicate<String, String> better = (f, s) -> f.length() > s.length();
        Better<String, String> better = (f, s) -> f.length() > s.length();

        System.out.println("Which is better method:");
        System.out.println(Toolbox.customCompare("I am better", "I am not", better) + "\n");

        Predicate<Integer> all = Toolbox.allPredicate(
                Arrays.asList(x -> x > 0, x -> x < 100, x -> x % 10 == 0));
        Predicate<Integer> any = Toolbox.anyPredicate(
                Arrays.asList(x -> x > 0, x -> x < 100, x -> x % 10 == 0));

        System.out.println("61 is > 0 and < 100 and divisible by 10?");
        System.out.println(all.test(61));
        System.out.println("61 is > 0 or < 100 or divisible by 10?");
        System.out.println(any.test(61));
        System.out.println();

        // endregion

        // region ****************** 2.5. Primitive streams ******************

        // 1. primitive streams deals with primitive types directly,
        // and save on the boxing time in the other case

        // 2. primitive streams have more specific methods like sum(), average() etc...
        System.out.println("More specific methods:");
        int[] nums = {1,2,3,4,5};
        System.out.println("Arrays.stream(nums).sum(): "
                + Arrays.stream(nums).sum());
        Integer[] numbers = {1,2,3,4,5};
        System.out.println("Arrays.stream(numbers).reduce(0, Integer::sum): "
                + Arrays.stream(numbers).reduce(0, Integer::sum));
        System.out.println();

        // 3. but it is more strict and map for example musst produce the same preemptive type
        System.out.println("But more restrictions:");
        // wont compile
        //double[] doubles = {1.2,2.3,5.12};
        //Arrays.stream(doubles).map(d -> "d = " + d + " ").forEach(System.out::print); // double unary operator
        //System.out.println();
        // will compile
        Double[] doubles = {1.2,2.3,5.12};
        Arrays.stream(doubles).map(d -> "d = " + d + " ").forEach(System.out::print); // Function

        // endregion
    }
}
