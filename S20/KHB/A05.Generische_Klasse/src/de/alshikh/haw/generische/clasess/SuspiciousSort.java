package de.alshikh.haw.generische.clasess;

import java.util.*;

/**********************************************************************
 *
 * Compere implementation
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
        //        return i1.compareTo(i2); }
        //};

        // make it to lambda
        //Comparator<Integer> cmp = (i1, i2) -> i1.compareTo(i2);

        // to Method reference
        //Arrays.sort(arr, Integer::compareTo);

        // but for basic Integere sort it's not needed
        Arrays.sort(arr);

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
