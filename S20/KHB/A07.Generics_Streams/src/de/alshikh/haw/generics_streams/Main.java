package de.alshikh.haw.generics_streams;

import de.alshikh.haw.generics_streams.clasess.Deque;

import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) {


        ArrayDeque<Integer> ints = new ArrayDeque<>(3);

        ints.addLast(3);
        ints.addFirst(2);
        ints.addFirst(1); // head
        ints.removeFirst();
        ints.addLast(1); // tail
        ints.addFirst(0); // ?


        System.out.println(-1 % 16);


        Deque<Integer> test = new Deque<>(4);

        test.enqueue(2);
        test.enqueue(1);
        test.push(3);
        test.push(4);
        test.push(5);

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


        System.out.println(test);
        System.out.println(test.size());
        //System.out.println(test.removeFirst());
        //System.out.println(test.removeLast());
        //System.out.println(test);
        //System.out.println(test.size());


    }
}
