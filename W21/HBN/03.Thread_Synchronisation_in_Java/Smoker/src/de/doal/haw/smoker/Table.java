package de.doal.haw.smoker;

import java.util.ArrayList;
import java.util.List;

// monitor
public class Table<E> {

    private final ArrayList<E> items;
    public boolean someoneSmoking;

    public Table() {
        this.items = new ArrayList<>();
    }

    public synchronized void put(List<E> items) throws InterruptedException {
        while (!this.items.isEmpty() || isSomeoneSmoking())
            this.wait();
        this.items.addAll(items);
        System.err.println(Thread.currentThread().getName()
                + " added " + items + " to the table");
        this.notifyAll();
    }

    public synchronized void take(ArrayList<SmokingRequirements> itemsToTake) throws InterruptedException {
        while (items.isEmpty() || !items.containsAll(itemsToTake))
            this.wait();
        System.err.println(Thread.currentThread().getName()
                + " took " + items + " from the table");
        items.clear();
        setSomeoneSmoking(true); // take care of notifying
    }

    public synchronized boolean isSomeoneSmoking() {
        return this.someoneSmoking;
    }

    public synchronized void setSomeoneSmoking(boolean someoneSmoking) {
        this.someoneSmoking = someoneSmoking;
        this.notifyAll();
    }
}
