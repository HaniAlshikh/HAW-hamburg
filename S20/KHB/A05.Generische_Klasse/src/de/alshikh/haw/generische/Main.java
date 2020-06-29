package de.alshikh.haw.generische;

import de.alshikh.haw.generische.clasess.Deque;

public class Main {

    public static void main(String[] args) {
        Deque<String> test = new Deque<>();


        test.push("dasd");
        test.addFirst("blabla");


        System.out.println(test);
    }
}
