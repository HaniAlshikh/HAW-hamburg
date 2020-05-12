package de.alshikh.haw.streams.toolbox;

import de.alshikh.haw.streams.interfaces.Better;

import java.util.List;
import java.util.function.Predicate;

public final class Toolbox {

    private Toolbox() {}

    public static <T> T customCompare(T first, T second, Better<T, T> p) {
        if (p.test(first, second)) return first;
        else return second;
    }

    public static <T> Predicate<T> anyPredicate(List<Predicate<T>> predicates) {
        return predicates.stream().reduce(Predicate::or).orElseThrow();
    }

    public static <T> Predicate<T> allPredicate(List<Predicate<T>> predicates) {
        return predicates.stream().reduce(Predicate::and).orElseThrow();
    }

}
