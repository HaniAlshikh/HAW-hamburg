package de.alshikh.haw.generics_streams;

import de.alshikh.haw.generics_streams.clasess.ArrDeque;

import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) {


        Integer x = 128;
        Integer y = 128;

        System.out.println(x == y);
        System.out.println(new Integer(128) == x);

        //System.out.println((Integer.MAX_VALUE - 8) + ((Integer.MAX_VALUE - 8) >> 1));

        //Deque<Integer> grenze = new Deque<>(Integer.MAX_VALUE - 1000);
        //
        //for (int i = 0; i < ; i++) {
        //
        //}
        //
        ArrayDeque<Integer> ints = new ArrayDeque<>(0);
        //
        //for (int i = 0; i < Integer.MAX_VALUE - 8; i++) {
        //    System.out.println(i);
        //    ints.addFirst(i);
        //}
        //
        //System.out.println(ints);

        //
        ints.addLast(3);
        ints.addFirst(2);

        //ints.addFirst(1); // head
        //ints.removeFirst();
        //ints.addLast(1); // tail
        //ints.addFirst(0); // ?
        //
        //
        //System.out.println(ints);
        //
        //System.out.println(-1 % 16);
        //
        //
        ArrDeque<Integer> test = new ArrDeque<>(0);
        //
        test.unshift(2);
        //test.unshift(1);
        //test.push(3);
        //test.push(4);
        //test.push(5);

        //int head = 3;
        //int tail = 2;
        //
        ////for (int i = 10, y = 0; i != 15; i++, y++)
        //for (int i = head, y = 0; i != tail; i = (i + 1) % 4, y++) {
        //    System.out.println(i);
        //    System.out.println(y);
        //}

        //ArrayDeque<Integer> ints = new ArrayDeque<>(Integer.MAX_VALUE);
        //Deque<Integer> ints = new Deque<>(-1);


        //System.out.println("\n");
        //test.stackStream().forEach(System.out::print);
        //System.out.println();
        //test.queueStream().forEach(System.out::print);
        //System.out.println("\n");
        //
        System.out.println(test);
        System.out.println(test.size());
        //System.out.println();
        //System.out.println(test.removeFirst());
        //System.out.println(test.removeLast());
        //System.out.println();
        //System.out.println(test);
        //System.out.println(test.size());


    }
}
