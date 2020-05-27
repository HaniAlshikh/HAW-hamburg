package de.alshikh.haw.parallele_sequentielle_streams_IO;

import java.math.BigInteger;
import java.util.stream.Stream;

import static java.math.BigInteger.*;

/**
 * Parallel stream-based program to generate the first 20 Mersenne
 * primes - HANGS!!! (Page 222)
 * @author Joshua Bloch
 */

public class ParallelMersennePrimes {

    public static void main(String[] args) {

         primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                 //.sequential()
                .unordered()
                .parallel()
                .filter(mersenne -> mersenne.isProbablePrime(50))
                 // limiting and skipping means are stateful and doesn't work efficiently with parallel streams
                .limit(14)
                .count();
    }

    // call unordered on Arraylist to set the ordered bit to 0

    static Stream<BigInteger> primes() {
        //return Stream.generate(s -> TWO.nextProbablePrime())
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }
}