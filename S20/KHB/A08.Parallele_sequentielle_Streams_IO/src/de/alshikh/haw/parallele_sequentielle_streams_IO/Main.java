package de.alshikh.haw.parallele_sequentielle_streams_IO;

import de.alshikh.haw.parallele_sequentielle_streams_IO.classes.NumericalIntegration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    private static long test(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - start;
    }



    public static void main(String[] args) {

        int numOfRuns = 1_000_000;
        long seqTime = 0;
        long parTime = 0;
        Function<Double, Double> fx1 = x -> x * x * x;
        Function<Double, Double> fx2 = x -> 1/x;
        Function<Double, Double> fx3 = x -> x*x*x + 2*x*x + 1;

        // warming up
        System.out.println("Sequential warme up time:");
        seqTime = test(() -> NumericalIntegration.calculateSequential(0.0, 10, numOfRuns, fx1));
        System.out.print(" fx1: " + seqTime);
        seqTime = test(() -> NumericalIntegration.calculateSequential(0.0, 10, numOfRuns, fx2));
        System.out.print(" fx2: " + seqTime);
        seqTime = test(() -> NumericalIntegration.calculateSequential(0.0, 10, numOfRuns, fx3));
        System.out.print(" fx3: " + seqTime);
        System.out.println();

        System.out.println("Parallel warme up time: ");
        seqTime = test(() -> NumericalIntegration.calculateParallel(0.0, 10, numOfRuns, fx1));
        System.out.print(" fx1: " + seqTime);
        seqTime = test(() -> NumericalIntegration.calculateParallel(0.0, 10, numOfRuns, fx2));
        System.out.print(" fx2: " + seqTime);
        seqTime = test(() -> NumericalIntegration.calculateParallel(0.0, 10, numOfRuns, fx3));
        System.out.print(" fx3: " + seqTime);
        System.out.println();


        // testing
        System.out.println("Sequential time: ");
        seqTime = test(() -> NumericalIntegration.calculateSequential(0.0, 10, numOfRuns, fx1));
        System.out.print(" fx1: " + seqTime);
        seqTime = test(() -> NumericalIntegration.calculateSequential(0.0, 10, numOfRuns, fx2));
        System.out.print(" fx2: " + seqTime);
        seqTime = test(() -> NumericalIntegration.calculateSequential(0.0, 10, numOfRuns, fx3));
        System.out.print(" fx3: " + seqTime);
        System.out.println();

        System.out.println("Parallel time: ");
        seqTime = test(() -> NumericalIntegration.calculateParallel(0.0, 10, numOfRuns, fx1));
        System.out.print(" fx1: " + seqTime);
        seqTime = test(() -> NumericalIntegration.calculateParallel(0.0, 10, numOfRuns, fx2));
        System.out.print(" fx2: " + seqTime);
        seqTime = test(() -> NumericalIntegration.calculateParallel(0.0, 10, numOfRuns, fx3));
        System.out.print(" fx3: " + seqTime);
        System.out.println();


        // ###########################################################################

        //String file = "text.txt";
        //Function<String, Stream<String>> toWordsNoPunct =
        //        line -> Stream.of(line.replaceAll("\\p{Punct}", "").split("\\s+"));
        //
        //try (Stream<String> lines = Files.lines(Paths.get(file))) {
        //
        //    Stream<String> wordStream = lines
        //            .filter(line -> !line.equals(""))
        //            .flatMap(toWordsNoPunct)
        //            .map(String::toLowerCase);
        //
        //    String[] wordArray = wordStream.toArray(String[]::new);
        //    //ArrayList<String> wordArrayList = wordStream.collect(Collectors.toCollection(ArrayList::new));
        //    //LinkedList<String> wordLinkedList = wordStream.collect(Collectors.toCollection(LinkedList::new));
        //
        //    System.out.println("\nWords count:\n");
        //
        //    for (int i = 0; i < numOfRuns; i++) {
        //        seqTime = test(() -> Arrays.stream(wordArray)
        //                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
        //        parTime = test(() -> Arrays.stream(wordArray).parallel()
        //                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
        //        System.out.printf("Array sequential %s  -  parallel %s\n", seqTime/numOfRuns, parTime/numOfRuns);
        //    }
        //
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}


    }
}
