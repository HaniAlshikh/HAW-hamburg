package de.doal.haw.smoker;

import java.util.concurrent.ThreadLocalRandom;

// consumer
public class Smoker extends Thread {

    private final String name;
    private final int MAX_SMOKING_TIME;
    private final SmokingRequirements ownedRequirement;
    private final Table<SmokingRequirements> table;

    public Smoker(String name, Table<SmokingRequirements> table, int maxSmokingTime,
                  SmokingRequirements ownedRequirement) {
        super(name);
        this.name = name;
        this.table = table;
        this.MAX_SMOKING_TIME = maxSmokingTime;
        this.ownedRequirement = ownedRequirement;
    }

    public void startSmoking() {
        start();
    }

    public void run() {
        try {
            while (!isInterrupted()) {
                System.err.println(this + " is waiting for " + ownedRequirement.getWhatMissing());
                table.take(ownedRequirement.getWhatMissing());
                smoke();
                table.setSomeoneSmoking(false);
            }
        } catch (InterruptedException ex) {
            System.err.println(this + " is leaving");
            interrupt();
        }
    }

    private void smoke() throws InterruptedException {
        System.err.println(this + " started smoking");
        Thread.sleep(ThreadLocalRandom.current().nextInt(MAX_SMOKING_TIME));
        System.err.println(this + " finished smoking");
    }

    @Override
    public String toString() {
        return name + " {" +
                "Owned Requirement: " + ownedRequirement
                + '}';
    }
}
