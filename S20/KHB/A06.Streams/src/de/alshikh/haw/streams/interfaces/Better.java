package de.alshikh.haw.streams.interfaces;

@FunctionalInterface
public interface Better<T, U> {
    boolean test(T t, U u);
}
