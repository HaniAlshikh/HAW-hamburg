package de.alshikh.haw.klausur;

import de.alshikh.haw.klausur.SS2019.Classes.Gangart;
import de.alshikh.haw.klausur.SS2019.Classes.Pferd;

import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {

        Pferd test = new Pferd(Gangart.STAND);

        test.brr();
        test.hü();
        test.hü();
        test.brr();
    }
}
