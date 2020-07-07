package de.alshikh.haw.klausur.SS2018;

import de.alshikh.haw.klausur.SS2018.classes.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<String> test = new Stack<>(3);

        test.push("test");
        test.push("test1");
        test.push("test2");
        System.out.println(test);
    }
}
