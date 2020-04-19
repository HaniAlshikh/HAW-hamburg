package de.alshikh.haw.datumuhrzeit.classes;

public class MyThing extends Thing {

    private final int INCENSE;

    public MyThing(int i) {
        super(i);
        this.INCENSE = i;
    }

    public MyThing() {
        this(Something.thing());
    }

    public int getINCENSE() {
        return INCENSE;
    }
}