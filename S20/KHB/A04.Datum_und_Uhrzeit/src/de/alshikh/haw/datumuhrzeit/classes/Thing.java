package de.alshikh.haw.datumuhrzeit.classes;

/**********************************************************************
 *
 * a parent class
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

public class Thing {
    public Thing(int i) {
        // workaround i is never used warning
        System.out.println("class thing is called with i = " + i);
    }
}
