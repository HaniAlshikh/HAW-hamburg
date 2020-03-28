/**********************************************************************
 *
 * basic implementation for the cartesian from of the complex numbers
 *
 * Author: Hani Alshikh
 *
 ***********************************************************************/

package de.alshikh.haw.grundlagen.fortsetzung.clases;

import de.alshikh.haw.grundlagen.fortsetzung.lib.ToolBox;

public class Cartesian {

    private double real;
    private double imag;

    public Cartesian(double real, double imag) {
        this.real = real;
        this.imag = imag;
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
    public String toString() {
        if (imag == 0) return ToolBox.formatNoRound(real) + "";
        if (real == 0) return ToolBox.formatNoRound(imag) + "i";
        return "(" + ToolBox.formatNoRound(real) + (imag < 0 ? " - " : " + ") +
                            ToolBox.formatNoRound(Math.abs(imag)) + "i)";
    }
}
