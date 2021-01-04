package de.doal.haw.mensa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Student extends Thread {

    private static final ReentrantLock mutex = new ReentrantLock(true);

    private final int MAX_EATING_TIME;
    private final ArrayList<Cashier> availableCashiers;
    private final Semaphore paymentStatus;

    private final String name;

    public Student(String name, int maxEatingTime, ArrayList<Cashier> availableCashiers) {
        this.name = name;
        this.MAX_EATING_TIME = maxEatingTime;
        this.availableCashiers = availableCashiers;
        this.paymentStatus = new Semaphore(0, true); // binary
    }

    public void pay() {
        start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                mutex.lock();
                Cashier chosenCashier = getQuickestCashier();
                chosenCashier.lineUp(this);
                mutex.unlock();
                System.err.println(this + " lined up at " + chosenCashier);
                paymentStatus.acquire(); // wait to pay
                System.err.println(this + " is now eating");
                goEat();
            } catch (InterruptedException e) {
                interrupt();
            }
        }
        System.err.println(this + " is leaving");
    }

    public void setPayed() {
        paymentStatus.release();
    }

    private Cashier getQuickestCashier() {
        return availableCashiers.stream()
                .min(Comparator.comparingInt(Cashier::getCustomerCount))
                .orElseThrow();
    }

    private void goEat() throws InterruptedException {
        sleep(ThreadLocalRandom.current().nextInt(MAX_EATING_TIME));
    }

    @Override
    public String toString() {
        return name;
    }
}

