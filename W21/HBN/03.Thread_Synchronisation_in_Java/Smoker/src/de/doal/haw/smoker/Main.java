package de.doal.haw.smoker;

import java.util.ArrayList;

public class Main {

    static int SMOKER_NUM = 3;
    static int AGENT_NUM = 2;
    static int RUNNING_TIME = 5000; // in millisecond
    static int MAX_SMOKING_TIME = 500; // in millisecond

    static Table<SmokingRequirements> table = new Table<>();
    static ArrayList<Smoker> smokers = new ArrayList<>();
    static ArrayList<Agent> agents = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        init();

        System.err.println("\n----- Agents and Smokers gathered around the table -----\n");

        agents.forEach(Agent::startDealing);
        smokers.forEach(Smoker::startSmoking);

        Thread.sleep(RUNNING_TIME);

        System.err.println("\n----- Agents and Smokers started leaving -----\n");

        agents.forEach(Thread::interrupt);
        for (Agent agent : agents) agent.join();
        smokers.forEach(Thread::interrupt);
        for (Smoker smoker : smokers) smoker.join();

        System.err.println("\n----- everyone left -----");
    }


    private static void init() {
        for (int i = 0; i < SMOKER_NUM; i++)
            smokers.add(new Smoker("Smoker_"+i, table, MAX_SMOKING_TIME,
                    SmokingRequirements.values()[i]));

        for (int i = 0; i < AGENT_NUM; i++)
            agents.add(new Agent("Agent_"+i, table));
    }
}
