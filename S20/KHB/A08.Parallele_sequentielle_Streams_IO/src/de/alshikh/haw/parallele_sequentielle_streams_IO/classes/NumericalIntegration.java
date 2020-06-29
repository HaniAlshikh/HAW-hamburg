package de.alshikh.haw.parallele_sequentielle_streams_IO.classes;

import java.util.function.Function;
import java.util.stream.IntStream;

/**********************************************************************
 *
 * Utility class to calculate the numerical integration using
 * sequential and/or parallel streams
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public final class NumericalIntegration {

    private static boolean parallel = false;

    private NumericalIntegration() {}

    /**
     * calculate the integration using sequential streams
     *
     * @param a partition start
     * @param b partition end
     * @param n number of slices
     * @param fx the equation
     * @throws IllegalArgumentException if a,b or n have invalid values
     * @return the approximated value of x
     */
    public static double integrateSequential(double a, double b, int n, Function<Double, Double> fx) {
        parallel = false;
        return integrate(a,b,n,fx);
    }

    /**
     * Same as {@link NumericalIntegration#integrateSequential}
     * but using a parallel streams.
     *
     * @see NumericalIntegration#integrateSequential
     */
    public static double integrateParallel(double a, double b, int n, Function<Double, Double> fx) {
        parallel = true;
        return integrate(a,b,n,fx);
    }

    private static double integrate(double a, double b, int n, Function<Double, Double> fx) {
        double range = checkParamsGetRange(a, b, n);
        double delta = range/n;
        double start = a + delta / 2.0;
        IntStream stream = parallel ? IntStream.range(1, n).unordered().parallel() : IntStream.range(1, n);
        return stream
                .mapToDouble(i -> delta * fx.apply(start + delta * i))
                .sum();
    }

    private static double checkParamsGetRange(double a, double b, int n) {
        if (n <= 0) throw new IllegalArgumentException("Invalid value of n");
        double range = b - a;
        if (range <= 0) throw new IllegalArgumentException("Invalid range");
        return range;
    }
}
