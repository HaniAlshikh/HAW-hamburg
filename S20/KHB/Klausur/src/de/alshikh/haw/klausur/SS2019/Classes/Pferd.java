package de.alshikh.haw.klausur.SS2019.Classes;

public class Pferd {

    private Gangart gang;

    public Pferd(Gangart gang) {
        this.gang = gang;
    }

    public Gangart getGang() {
        return gang;
    }

    public void hü() {
        gang = gang.getNext();
        gang.update();
    }

    public void brr() {
        gang = gang.getPrev();
        gang.update();
    }
}
