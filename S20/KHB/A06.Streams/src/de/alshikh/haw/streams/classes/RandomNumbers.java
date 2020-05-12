package de.alshikh.haw.streams.classes;

import java.util.Random;

/**********************************************************************
 *
 * Positive Random Number
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class RandomNumbers {

    static Random rand = new Random();

    static int random(int n) {
        try {
            return rand.nextInt(n);
        } catch (IllegalArgumentException e) {
            System.out.println("n musst be > 0");
            throw new IllegalArgumentException();
        }
    }
    public static void main(String[] args) {
        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 0;
        for (int i = 0; i < 1_000_000; i++) {
            if (random(n) < n / 2) {
                low++;
            }
        }
        System.out.println(low);
    }
}
