package de.alshikh.haw.klausur.SS2019.Classes;

public enum Gangart {

    STAND("STAND", "SCHRITT"){
        @Override
        public void update() {
            System.out.println("Gangart: Stand");
        }
    },
    SCHRITT("STAND", "TRAB"){
        @Override
        public void update() {
            System.out.println("Gangart: Schritt");
        }
    },
    TRAB("STAND", "GALOPP"){
        @Override
        public void update() {
            System.out.println("Gangart: Trab");
        }
    },
    GALOPP("TRAB", "GALOPP"){
        @Override
        public void update() {
            System.out.println("Gangart: Galopp");
        }
    };

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

    public abstract void update();
}
