package de.doal.haw.mensa;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Cashier extends Thread {

    private final int MAX_PAYING_TIME;

    private final ReentrantLock mutex;
    private final Semaphore customerCounter;
    private final Queue<Student> waitingStudents;

    private final String id;

    public Cashier(String id, int payingTime) {
        this.id=id;
        this.MAX_PAYING_TIME = payingTime;
        this.mutex = new ReentrantLock(true);
        this.customerCounter = new Semaphore(0, true);
        this.waitingStudents = new ArrayDeque<>();
    }

    public int getCustomerCount() {
        return waitingStudents.size();
    }

    public void lineUp(Student student) throws InterruptedException {
        waitingStudents.add(student);
        customerCounter.release();
    }

    public void startWorking() {
        start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                customerCounter.acquire();
                mutex.lockInterruptibly();
                Student student = waitingStudents.poll();
                Thread.sleep(ThreadLocalRandom.current().nextInt(MAX_PAYING_TIME));
                System.err.println(student + " payed at " + this);
                student.setPayed(); // the warning is none sense as we are polling the student after acquiring?

            } catch (InterruptedException e) {
                interrupt();
            } finally {
                mutex.unlock();
            }
        }
    }

    @Override
    public String toString() {
        return id;
    }
}

