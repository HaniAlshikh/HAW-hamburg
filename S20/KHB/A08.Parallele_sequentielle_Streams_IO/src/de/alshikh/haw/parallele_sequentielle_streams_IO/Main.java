package de.alshikh.haw.parallele_sequentielle_streams_IO;

import de.alshikh.haw.parallele_sequentielle_streams_IO.classes.NumericalIntegration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class Main {

    private static long test(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - start;
    }

    private static String[] getWordsArr(String file) {
        Function<String, Stream<String>> toWordsNoPunct =
                line -> Stream.of(line.replaceAll("\\p{Punct}", "").split("\\s+"));
        try (Stream<String> lines = Files.lines(Paths.get(file))) {
            return lines
                    .filter(line -> !line.equals(""))
                    .flatMap(toWordsNoPunct)
                    .map(String::toLowerCase)
                    .toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    private static Map<String, Long> collectWords(Stream<String> stream) {
        return stream.collect(groupingBy(Function.identity(), counting()));
    }

    public static void main(String[] args) {

        double a = 0.0, b = 10;
        int n = 1_000_000, runs = 10;
        long seqTime = 0, parTime = 0;
        List<Function<Double, Double>> functions = Arrays.asList(
            x -> x * x * x,
            x -> 1/x,
            x -> x*x*x + 2*x*x + 1
        );

        // warming up
        System.out.print("warming up: ");
        for (Function<Double, Double> fx : functions) {
            // comelier will skip the run if the result is not used -> print
            System.out.print(test(() -> NumericalIntegration
                    .calculateSequential(a, b, n, fx)));
            System.out.print(test(() -> NumericalIntegration
                    .calculateParallel(a, b, n, fx)));
        }
        System.out.println("\n");

        // run time test
        System.out.printf("Average run time for %s runs\n", runs);
        System.out.printf("%-4s | %-10s | %-10s\n", "Fx", "Sequential", "Parallel");
        int i = 0;
        for (Function<Double, Double> fx : functions) {
            seqTime = 0; parTime = 0;
            for (int j = 0; j < runs; j++) {
                seqTime += test(() -> NumericalIntegration.calculateSequential(a, b, n, fx));
                parTime += test(() -> NumericalIntegration.calculateParallel(a, b, n, fx));
            }
            System.out.printf("%-4d | %-10s | %-10s\n", ++i,seqTime/runs+"ms",parTime/runs+"ms");
        }


        // ###########################################################################

        System.out.printf("\n%s %s %s\n\n","=".repeat(10),"Words count:","=".repeat(10));

        String file = "text.txt";

        String[] wordArray = getWordsArr(file);
        ArrayList<String> wordArrayList = new ArrayList<>(Arrays.asList(wordArray));
        LinkedList<String> wordLinkedList = new LinkedList<>(Arrays.asList(wordArray));

        Supplier<List<Stream<String>>> streams = () -> Arrays.asList(
                Arrays.stream(wordArray),
                Arrays.stream(wordArray).parallel(),
                wordArrayList.stream(),
                wordArrayList.parallelStream(),
                wordLinkedList.stream(),
                wordLinkedList.parallelStream()
        );


        List<Stream<String>> warmeUpStreams = streams.get();
        System.out.print("warming up: ");
        for (Stream<String> stream : warmeUpStreams) {
            System.out.print(test(() -> collectWords(stream)));
        }
        System.out.println("\n");

        // run time test
        System.out.printf("Average run time for %s runs\n", runs);
        System.out.printf("%-10s | %-10s | %-10s\n", "Collection", "Sequential", "Parallel");

        seqTime = 0; parTime = 0;
        for (i = 0; i < runs; i++) {
            seqTime += test(() -> collectWords(Arrays.stream(wordArray)));
            parTime += test(() -> collectWords(Arrays.stream(wordArray).parallel()));
        }
        System.out.printf("%-10s | %-10s | %-10s\n", "Array",seqTime/runs+"ms",parTime/runs+"ms");
        seqTime = 0; parTime = 0;
        for (i = 0; i < runs; i++) {
            seqTime += test(() -> collectWords(wordArrayList.stream()));
            parTime += test(() -> collectWords(wordArrayList.parallelStream()));
        }
        System.out.printf("%-10s | %-10s | %-10s\n", "ArrayList",seqTime/runs+"ms",parTime/runs+"ms");
        seqTime = 0; parTime = 0;
        for (i = 0; i < runs; i++) {
            seqTime += test(() -> collectWords(wordLinkedList.stream()));
            parTime += test(() -> collectWords(wordLinkedList.parallelStream()));
        }
        System.out.printf("%-10s | %-10s | %-10s\n", "LinkedList",seqTime/runs+"ms",parTime/runs+"ms");

        //List<Stream<String>> testStreams = streams.get();
        ////for (i = 0; i < testStreams.size(); i+=2) {
        ////    seqTime = 0; parTime = 0;
        ////    Stream<String> seq = testStreams.get(i), par = testStreams.get(i+1);
        ////    for (int j = 0; j < runs; j++) {
        ////        seqTime += test(() -> collectWords(seq));
        ////        parTime += test(() -> collectWords(par));
        ////        seq = streams.get().get(i); par = streams.get().get(i+1);
        ////    }
        ////    System.out.printf("%-10s | %-10s | %-10s\n", seq.getClass(),seqTime/runs+"ms",parTime/runs+"ms");
        ////}
    }
}
