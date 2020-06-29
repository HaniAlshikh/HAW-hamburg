package de.alshikh.haw.parallele_sequentielle_streams_IO;

import de.alshikh.haw.parallele_sequentielle_streams_IO.Toolbox.Toolbox;
import de.alshikh.haw.parallele_sequentielle_streams_IO.classes.NumericalIntegration;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**********************************************************************
 *
 * demonstrating the solution of the 08.th assigment
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class Main {

    public static void main(String[] args) {

        // region ****************** 2.1-4 Numerical Integration ******************

        System.out.printf("\n%s %s %s\n\n","=".repeat(10),"Numerical Integration","=".repeat(10));

        int runs = 10;
        long seqTime, parTime;

        Map<Function<Double,Double>, Double[]> fxs = Map.of(
                //   fx                   a  ,   b  ,   n
                x -> x * x, new Double[]{10.0, 100.0, 10e5},
                Math::sin, new Double[]{0.0, Math.PI, 10e5},
                x -> 1/x, new Double[]{50.0, 500.0, 10e5}
        );

        // warming up
        System.out.print("warming up: ");
        fxs.forEach((fx, v) -> {
            // compiler will skip the run if the result is not used -> print
            System.out.print(Toolbox.timeOp(() -> NumericalIntegration
                    .integrateSequential(v[0], v[1], v[2].intValue(), fx)) + " ");
            System.out.print(Toolbox.timeOp(() -> NumericalIntegration
                    .integrateParallel(v[0], v[1], v[2].intValue(), fx)) + " ");
        });
        System.out.println("\n");

        // run-time test
        System.out.printf("Average run time for %s runs\n", runs);
        System.out.printf("%-4s | %-10s | %-10s\n", "Fx", "Sequential", "Parallel");
        int i = 0;
        for (Function<Double, Double> fx : fxs.keySet()) {
            Double[] v = fxs.get(fx);
            seqTime = 0; parTime = 0;
            for (int j = 0; j < runs; j++) {
                seqTime += Toolbox.timeOp(() -> NumericalIntegration
                        .integrateSequential(v[0], v[1], v[2].intValue(), fx));
                parTime += Toolbox.timeOp(() -> NumericalIntegration
                        .integrateParallel(v[0], v[1], v[2].intValue(), fx));
            }
            System.out.printf("%-4d | %-10s | %-10s\n", ++i,seqTime/runs+"ms",parTime/runs+"ms");
        }

        // endregion

        // region ****************** 2.1-4 words count ******************

        System.out.printf("\n%s %s %s\n\n","=".repeat(10),"Words count","=".repeat(10));

        String file = "text.txt";
        Function<Stream<String>, Map<String, Long>> collectWords =
                s -> s.collect(groupingBy(Function.identity(), counting()));

        String[] wordArray = Toolbox.getWordsArr(file);
        ArrayList<String> wordArrayList = new ArrayList<>(Arrays.asList(wordArray));
        LinkedList<String> wordLinkedList = new LinkedList<>(Arrays.asList(wordArray));

        // keep regenerating streams
        Supplier<List<Stream<String>>> streams = () -> Arrays.asList(
                Arrays.stream(wordArray),
                Arrays.stream(wordArray).unordered().parallel(),
                wordArrayList.stream(),
                wordArrayList.parallelStream(),
                wordLinkedList.stream(),
                wordLinkedList.parallelStream()
        );

        System.out.print("warming up: ");
        for (Stream<String> stream : streams.get()) {
            System.out.print(Toolbox.timeOp(() -> collectWords.apply(stream)) + " ");
        }
        System.out.println("\n");

        // run-time test
        System.out.printf("Average run time for %s runs\n", runs);
        System.out.printf("%-10s | %-10s | %-10s\n", "Collection", "Sequential", "Parallel");

        for (i = 0; i < streams.get().size()/2; i++) {
            seqTime = 0; parTime = 0;
            for (int j = 0; j < runs; j++) {
                int finalI = i;
                seqTime += Toolbox.timeOp(() -> collectWords.apply(streams.get().get(finalI)));
                parTime += Toolbox.timeOp(() -> collectWords.apply(streams.get().get(finalI+1)));
            }
            System.out.printf("%-10s | %-10s | %-10s\n",
                    i+1,seqTime/runs+"ms",parTime/runs+"ms");
        }

        // endregion
    }
}
