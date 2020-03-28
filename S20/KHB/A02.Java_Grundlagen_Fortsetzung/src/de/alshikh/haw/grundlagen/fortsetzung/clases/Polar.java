package de.alshikh.haw.grundlagen.fortsetzung.clases;

import de.alshikh.haw.grundlagen.fortsetzung.lib.ToolBox;

import java.util.Objects;

public class Polar {

    private double abs;
    private double rad;
    private int errorMargin;

    public Polar(double abs, double rad, int errorMargin) {
        this.abs = abs;
        this.rad = rad;
        this.errorMargin = errorMargin;
    }

    public Polar(double abs, double rad) {
        this(abs,rad,6);
    }


    public Cartesian toCartesian() {
        return ComplexMath.toCartesian(this);
    }


    public double getAbs() {
        return abs;
    }

    public double getRad() {
        return rad;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Polar || o instanceof Cartesian || o instanceof Complex) {
            Polar num = o instanceof Cartesian ? ((Cartesian) o).toPolar() :
                    o instanceof Complex ? ((Complex) o).getPolar() : (Polar) o;
            return (Math.abs(num.abs) - Math.abs(abs) < Math.pow(1, errorMargin * -1) &&
                    Math.abs(num.rad) - Math.abs(rad) < Math.pow(1, errorMargin * -1));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.floor(abs * Math.pow(1, errorMargin)) / Math.pow(1, errorMargin),
                                    Math.floor(rad * Math.pow(1, errorMargin)) / Math.pow(1, errorMargin));
    }

    @Override
    public String toString() {
        if (abs == 0) return "e^" + ToolBox.formatNoRound(rad) + "i";
        return "(" + ToolBox.formatNoRound(abs) + " + " + "e^" + ToolBox.formatNoRound(rad) + "i)";
    }


}
