package de.alshikh.haw.datumuhrzeit.classes;

import java.util.Random;

/**********************************************************************
 *
 * a utility class
 * generate input for Thing/myThing class
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

public final class Something {

    private Something() {}

    public static int thing() {
        return new Random().nextInt(100);
    }
}
