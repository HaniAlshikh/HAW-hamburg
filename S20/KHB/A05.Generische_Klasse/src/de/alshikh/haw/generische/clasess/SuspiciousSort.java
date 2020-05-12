package de.alshikh.haw.generische.clasess;

import java.util.*;

/**********************************************************************
 *
 * Integer Overflow and Underflow
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class SuspiciousSort {

    public static void main(String[] args) {
        Random rnd = new Random();
        Integer[] arr = new Integer[100];
        for (int i = 0; i < arr.length; i++)
            arr[i] = rnd.nextInt();

        // using compareTo
        //Comparator<Integer> cmp = new Comparator<Integer>() {
        //    @Override
        //    public int compare(Integer i1, Integer i2) {
        //        return i2.compareTo(i1); }
        //};

        // make it to lambda
        //Comparator<Integer> cmp = (i1, i2) -> i2.compareTo(i1);

        // to Method reference
        Arrays.sort(arr, Comparator.reverseOrder());

        System.out.println(order(arr));
    }

    enum Order {
        ASCENDING, DESCENDING, CONSTANT, UNORDERED
    }

    static Order order(Integer[] a) {
        boolean ascending = false;
        boolean descending = false;

        for (int i = 1; i < a.length; i++) {
            ascending |= a[i] > a[i - 1];
            descending |= a[i] < a[i - 1];
        }

        if (ascending && !descending) return Order.ASCENDING;
        if (descending && !ascending) return Order.DESCENDING;
        if (!ascending) return Order.CONSTANT; // All elements equal
        return Order.UNORDERED; // Array is not sorted
    }
}
