package de.hawhamburg.hamann.knapsack;

import java.util.*;

public final class Knapsack {

    private final int numOfItems;
    private final int capacity;
    private final int[][] maxTable;
    private final Item[] items;

    public Knapsack(int capacity, Item... items) {
        this.items = items;
        this.numOfItems = items.length;
        this.capacity = capacity;
        this.maxTable = new int[numOfItems+1][capacity+1];
        solve();
    }

    public String toString() {
        return "Max Value: " + getMaxValue()
                + "\n" + getTakenItems();
    }

    public List<Item> getTakenItems() {
        List<Item> result = new ArrayList<>();
        for (int n = numOfItems, w = capacity; n > 0 ; n--) {
            if (maxTable[n][w] != 0 && maxTable[n-1][w] != maxTable[n][w]) { // indexing starts at 1
                result.add(this.items[n-1]); // indexing starts at 0
                w = w - items[n-1].size;
            }
        }
        return result;
    }

    private void solve() {
        for (int i = 1; i < numOfItems+1; i++) { // item
            for (int w = 1; w < capacity+1; w++) { // weight

                int valueWithoutItem = maxTable[i-1][w];
                int addedValue = 0;

                if (items[i-1].size <= w)
                    addedValue = items[i-1].val + maxTable[i-1][w-items[i-1].size];

                maxTable[i][w] = Math.max(valueWithoutItem, addedValue);

            }
        }
    }

    public int getMaxValue() {
        return maxTable[numOfItems][capacity];
    }

    static class Item {
        String name;
        int    size;
        int     val;

        public Item(String name, int size, int value) {
            this.name = name;
            this.size = size;
            this.val = value;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
