package de.alshikh.haw.streams.interfaces;

/**********************************************************************
 *
 * Basic BiPredicate implementation
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
@FunctionalInterface
public interface Better<T, U> {
    boolean test(T t, U u);
}
