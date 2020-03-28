package de.alshikh.haw.grundlagen.fortsetzung.clases;

import de.alshikh.haw.grundlagen.fortsetzung.lib.ToolBox;

import java.util.Objects;

public class Cartesian {

    private double real;
    private double imag;
    private int errorMargin;

    public Cartesian(double real, double imaginary, int errorMargin) {
        this.real = real;
        this.imag = imaginary;
        this.errorMargin = errorMargin;
    }

    public Cartesian(double real, double imaginary) {
        this(real,imaginary,6);
    }

    public Polar toPolar() {
        return ComplexMath.toPolar(this);
    }

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Cartesian || o instanceof Polar || o instanceof Complex) {
            Cartesian num = o instanceof Polar ? ((Polar) o).toCartesian() :
                            o instanceof Complex ? ((Complex) o).getCartesian() : (Cartesian) o;
            return (Math.abs(num.real) - Math.abs(real) < Math.pow(1, errorMargin * -1) &&
                    Math.abs(num.imag) - Math.abs(imag) < Math.pow(1, errorMargin * -1));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.floor(real * Math.pow(1, errorMargin)) / Math.pow(1, errorMargin),
                                    Math.floor(imag * Math.pow(1, errorMargin)) / Math.pow(1, errorMargin));
    }

    @Override
    public String toString() {
        if (imag == 0) return ToolBox.formatNoRound(real) + "";
        if (real == 0) return ToolBox.formatNoRound(imag) + "i";
        return "(" + ToolBox.formatNoRound(real) + (imag < 0 ? " - " : " + ") +
                            ToolBox.formatNoRound(Math.abs(imag)) + "i)";
    }
}
