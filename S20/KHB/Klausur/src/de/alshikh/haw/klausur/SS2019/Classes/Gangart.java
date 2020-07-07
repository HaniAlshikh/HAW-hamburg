package de.alshikh.haw.klausur.SS2019.Classes;

public enum Gangart {

    STAND("STAND", "SCHRITT"),
    SCHRITT("STAND", "TRAB"),
    TRAB("STAND", "GALOPP"),
    GALOPP("TRAB", "GALOPP");

    private String prev;
    private String next;

    Gangart(String prev, String next) {
        this.prev = prev;
        this.next = next;
    }

    public Gangart getPrev() {
        return valueOf(prev);
    }

    public Gangart getNext() {
        return valueOf(next);
    }

    public void update() {
        System.out.println("Gangart: " + this.toString());
    }
}
