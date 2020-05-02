package de.alshikh.haw.generische.clasess;

import java.util.Arrays;
import java.util.List;

/**********************************************************************
 *
 * Type Parameter Hiding & static nested classes
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class Pair<T> {

    private final T first;
    private final T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }
    public T first() {
        return first;
    }
    public T second() {
        return second;
    }
    public List<String> stringList() {
        return Arrays.asList(String.valueOf(first), String.valueOf(second));
    }

    public static void main(String[] args) {
        Pair<Object> p = new Pair<>(23, "skidoo");
        for (Object s : p.stringList()) {
            System.out.print(s + " ");
        }
        System.out.printf(Integer.MAX_VALUE + "");
        System.out.printf(Integer.MIN_VALUE + "");
    }
}