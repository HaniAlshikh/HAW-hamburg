package de.alshikh.haw.datumuhrzeit.classes;

/**********************************************************************
 *
 * a child class
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

public class MyThing extends Thing {

    private final int INSTANCE;

    private MyThing(int i) {
        super(i);
        this.INSTANCE = i;
    }

    public MyThing() {
        this(Something.thing());
    }

    @Override
    public String toString() {
        return "MyThing{" +
                "INCENSE = " + INSTANCE +
                '}';
    }
}