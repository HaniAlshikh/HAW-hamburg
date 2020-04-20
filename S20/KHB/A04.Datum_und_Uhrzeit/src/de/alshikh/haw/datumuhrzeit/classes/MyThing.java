package de.alshikh.haw.datumuhrzeit.classes;

/**********************************************************************
 *
 * a child class
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

public class MyThing extends Thing {

    private final int INCENSE;

    public MyThing(int i) {
        super(i);
        this.INCENSE = i;
    }

    public MyThing() {
        this(Something.thing());
    }

    @Override
    public String toString() {
        return "MyThing{" +
                "INCENSE = " + INCENSE +
                '}';
    }
}