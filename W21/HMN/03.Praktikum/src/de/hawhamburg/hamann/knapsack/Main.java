package de.hawhamburg.hamann.knapsack;

import java.util.*;

public class Main {

    private static final Knapsack.Item[] items;
    private static long count = 0;
    // 4.1 owen Tree
    private static final BST<Integer, Integer> capMax = new BST<>(Integer::compareTo);
    // 4.2. items to pack
    private static List<Knapsack.Item> itemsToPack = new ArrayList<>();
    private static final Stack<Knapsack.Item> validItems = new Stack<>();

    static {
        items = new Knapsack.Item[] {
                new Knapsack.Item( "A", 3, 4),
                new Knapsack.Item( "B", 4, 5),
                new Knapsack.Item( "C", 7, 10),
                new Knapsack.Item( "D", 8, 11),
                new Knapsack.Item( "E", 9, 13)
        };
    }

    public static void main(String[] args) {
        long start = System.nanoTime();

        int res = knap(17);

        long duration = (System.nanoTime() - start) / 1000000;

        System.out.format("Result: %d Calls: %,d Duration: %,dms\n", res, count, duration);
        System.out.println(itemsToPack);

        // find the packed elements using dynamic programing
        System.out.println(new Knapsack(17, items).getTakenItems());
    }

    private static int knap(int cap) {
        count++;

        if (capMax.contains(cap)) return capMax.search(cap);

        int i, space, max, t;

        for (i = 0, max = 0; i < items.length; i++) {
            space = cap - items[i].size;

            if (space >= 0) {
                // can i get a bigger max with item i?
                validItems.add(items[i]);
                t = knap(space) + items[i].val;

                if (t > max) {
                    max = t;
                    // if yes store it
                    itemsToPack = new Stack<>();
                    itemsToPack.addAll(validItems);
                }
                // otherwise skip it
                validItems.pop();
            }
        }

        capMax.add(cap, max);
        return max;
    }
}
