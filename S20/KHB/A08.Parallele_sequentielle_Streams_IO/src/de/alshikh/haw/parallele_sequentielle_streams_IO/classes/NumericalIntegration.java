package de.alshikh.haw.parallele_sequentielle_streams_IO.classes;

import java.util.function.Function;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericalIntegration {

    public static double calculateSequential(double a, double b, int n, Function<Double, Double> fx)
    {
        double range = checkParamsGetRange(a, b, n);
        double sum = IntStream.range(1, n)
                .mapToDouble(i -> fx.apply(a + range * i / n))
                .sum();
        sum += (fx.apply(a) + fx.apply(b)) / 2.0;
        return sum * range / n;
    }

    public static double calculateParallel(double a, double b, int n, Function<Double, Double> fx)
    {
        double range = checkParamsGetRange(a, b, n);
        double sum = IntStream.range(1, n)
                .unordered()
                .parallel()
                .mapToDouble(i -> fx.apply(a + range * i / n))
                .sum();
        sum += (fx.apply(a) + fx.apply(b)) / 2.0;
        return sum * range / n;
    }


    private static double checkParamsGetRange(double a, double b, int n)
    {
        if (n <= 0) throw new IllegalArgumentException("Invalid value of n");
        double range = b - a;
        if (range <= 0) throw new IllegalArgumentException("Invalid range");
        return range;
    }

}
