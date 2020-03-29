/**********************************************************************
 *
 * basic implementation for the polar from of the complex numbers
 *
 * @author Mateusz Chylewski
 * @author Hani Alshikh
 *
 ***********************************************************************/

package de.alshikh.haw.grundlagen.fortsetzung.clases;

import de.alshikh.haw.grundlagen.fortsetzung.lib.ToolBox;

public class Polar {

    private double abs;
    private double rad;

    public Polar(double abs, double rad) {
        this.abs = abs;
        this.rad = rad;
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
    public String toString() {
        if (abs == 0) return "e^" + ToolBox.formatNoRound(rad) + "i";
        return "(" + ToolBox.formatNoRound(abs) + " + " + "e^" + ToolBox.formatNoRound(rad) + "i)";
    }
}
