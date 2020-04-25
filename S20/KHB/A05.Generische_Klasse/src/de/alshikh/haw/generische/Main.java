package de.alshikh.haw.generische;

import de.alshikh.haw.generische.clasess.Deque;
import de.alshikh.haw.generische.clasess.LinkedList;

public class Main {

    public static void main(String[] args) {

        Deque<String> test = new Deque<>();

        test.push("1");
        test.push("2");
        test.push("3");
        test.addLast("last");
        test.addFirst("first");

        test.dump();

        Deque<String> str = new Deque<>();
        str.push("test");

        Deque<String> str1 = new Deque<>();
        str1.push("test");

        System.out.println(str.equals(str1));


    }
}
