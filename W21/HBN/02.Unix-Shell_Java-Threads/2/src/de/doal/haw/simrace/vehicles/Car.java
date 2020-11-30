package de.doal.haw.simrace.vehicles;

import java.util.concurrent.ThreadLocalRandom;


/**********************************************************************
 *
 * abstract implementation of a car, which can participate on tracks
 * and store it's lap time
 *
 * @author Michael Dornhof
 * @author Hani Alshikh
 *
 ************************************************************************/
public class Car extends Thread implements Comparable<Car>{

    private final String name;
    private final int rounds;
    private long raceTimeInMS = 0;
    private int maxRoundTime = 100;

    public Car(String carname, int rounds){
        this.name = carname;
        this.rounds = rounds;
    }

    private void drive(){
        long startTimeInMS = System.currentTimeMillis();
        for(int roundsDone = 1; roundsDone <= rounds && !isInterrupted(); roundsDone++){
            int roundTime = ThreadLocalRandom.current().nextInt(this.maxRoundTime);
            try {
                sleep(roundTime);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
        raceTimeInMS = System.currentTimeMillis() - startTimeInMS;
    }

    public void run(){
        drive();
    }

    public String getCarName(){
        return this.name;
    }

    public long getRaceTimeInMS(){
        return this.raceTimeInMS;
    }

    @Override
    public String toString(){
        return getCarName() + " time: " + getRaceTimeInMS();
    }

    @Override
    public int compareTo(Car o) {
        return Long.compare(this.getRaceTimeInMS(),o.getRaceTimeInMS());
    }

    public void setMaxRoundTime(int maxRoundTime) {
        this.maxRoundTime = maxRoundTime;
    }
}