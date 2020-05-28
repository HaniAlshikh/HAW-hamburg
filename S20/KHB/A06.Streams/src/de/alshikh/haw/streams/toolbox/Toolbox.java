package de.alshikh.haw.streams.toolbox;

import de.alshikh.haw.streams.interfaces.Better;

import java.util.List;
import java.util.function.Predicate;

/**********************************************************************
 *
 * Lambdas and Streams helper utility class
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

public final class Toolbox {

    private Toolbox() {}

    /**
     * @param first first object to be compared
     * @param second second object to be compared
     * @param p comparision Better predicate
     * @see Better
     * @return the better object
     */
    public static <T> T customCompare(T first, T second, Better<T, T> p) {
        if (p.test(first, second)) return first;
        else return second;
    }


    /**
     * @param predicates List containing all predicates to concatenated with and
     * @return all predicates concatenated with and
     */
    public static <T> Predicate<T> anyPredicate(List<Predicate<T>> predicates) {
        return predicates.stream().reduce(Predicate::or).orElseThrow();
    }

    /**
     * @param predicates List containing all predicates to concatenated with or
     * @return all predicates concatenated with and
     */
    public static <T> Predicate<T> allPredicate(List<Predicate<T>> predicates) {
        return predicates.stream().reduce(Predicate::and).orElseThrow();
    }

}
