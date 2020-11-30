package de.doal.haw.simrace.tracks;

import de.doal.haw.simrace.vehicles.Car;

import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 *
 * simulate the track (thread) on which the cars ("sub-threads") drives (run)
 *
 * @author Michael Dornhof
 * @author Hani Alshikh
 *
 ************************************************************************/
public class MainTrack extends Thread{

        private final int rounds ;
        private final int numCars;

        public MainTrack(int rounds, int cars){
            this.rounds = rounds;
            this.numCars = cars;
        }


    /**
     * start a race using threads and add cars ("sub-threads") to it
     *
     * @return a sorted list of the participant cars from faster to slower
     */
    public List<String> startRace() throws InterruptedException {
        // start the "main/caller" thread (race)
        this.start();

        List<String> result = new ArrayList<>();
        List<Car> cars = new ArrayList<>();

        // create and start "sub-threads" (cars)
        for (int i = 0; i < numCars; i++) {
            cars.add(new Car("car "+(i+1), rounds));
            cars.get(i).start();
        }

        // Notify Callee-Thread to wait for threads to end.
        for (int i = 0; i < numCars; i++) {
            cars.get(i).join();
        }

        cars.sort(Car::compareTo);

        for(int i = 0; i < cars.size(); i++){
            result.add((i+1)+". Place: " + cars.get(i));
        }
        return result;
    }
}
