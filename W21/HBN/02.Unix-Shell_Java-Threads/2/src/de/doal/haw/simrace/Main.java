package de.doal.haw.simrace;

import de.doal.haw.simrace.tracks.MainTrack;

import java.util.List;
import java.util.Scanner;

/**********************************************************************
 *
 * simulate car race using threads
 *
 * @author Michael Dornhof
 * @author Hani Alshikh
 *
 ************************************************************************/

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many rounds? ");
        int rounds = scanner.nextInt();
        System.out.print("How many cars? ");
        int cars = scanner.nextInt();
        System.out.println();

        List<String> result = new MainTrack(rounds, cars).startRace();

        result.forEach(System.out::println);
    }
}
