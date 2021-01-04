package de.doal.haw.smoker;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

// producer
public class Agent extends Thread {

    private final String name;
    private final Table<SmokingRequirements> table;

    public Agent(String name, Table<SmokingRequirements> table) {
        super(name);
        this.name = name;
        this.table = table;
    }

    public void startDealing() {
        start();
    }

    public void run() {
        try {
            while (!isInterrupted()) {
                table.put(randomMissingRequirements());
            }
        } catch (InterruptedException ex) {
            System.err.println(this + " is leaving");
            interrupt();
        }
    }


    private ArrayList<SmokingRequirements> randomMissingRequirements() {
        return SmokingRequirements.values()[
                ThreadLocalRandom.current().nextInt(SmokingRequirements.values().length)
                ].getWhatMissing();
    }

    @Override
    public String toString() {
        return name;
    }
}
