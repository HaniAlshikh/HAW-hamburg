package de.alshikh.haw.grundlagen.fortsetzung.classes;
import de.alshikh.haw.grundlagen.fortsetzung.lib.ToolBox;

import java.util.Objects;

/**********************************************************************
 *
 * basic implementation of the complex numbers
 *
 * @author Hani Alshikh
 *
 ***********************************************************************/
public class Complex {

    private double real;
    private double imag;

    // region **************** object initialization ****************
    
    /**
     * @param real        the real value of the Cartesian coordinates
     * @param imag        the imaginary value of the Cartesian coordinates
     */
    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    /**
     * @param abs        the absolute value of the polar coordinates.
     * @param rad        the angel value of the polar coordinates in radian.
     * @return Complex number created from polar coordinates.
     */
    public static Complex polar(double abs, double rad) {
        double[] convertedValues = ComplexMath.toCartesian(abs, rad);
        return new Complex(convertedValues[0], convertedValues[1]);
    }

    // endregion

    // region **************** arithmetic operations ****************

    /**
     * @param complex      Complex object from which the coordinates are extracted
     *                     and added to this object
     * @return the object itself after addition
     */
    public Complex add(Complex complex) {
        // (re1+re2 + im1+im2 * i)
        this.real += complex.real;
        this.imag += complex.imag;
        return this;
    }

    /**
     * @param complex      Complex object from which the coordinates are extracted
     *                     and subtracted from this object
     * @return the object itself after subtraction
     */
    public Complex sub(Complex complex) {
        // (re1-re2 + im1-im2 * i)
        this.real -= complex.real;
        this.imag -= complex.imag;
        return this;
    }

    /**
     * @param complex      Complex object from which the coordinates are extracted
     *                     and multiplied with this object
     * @return the object itself after multiplication
     */
    public Complex mult(Complex complex) {
        // (re1*re2-im1*im2 + (re1*im2 + im1*re2) * i)
        double newReal = this.real * complex.real - this.imag * complex.imag;
        this.imag = this.real * complex.imag + this.imag * complex.real;
        this.real = newReal;
        return this;
    }

    /**
     * @param complex      Complex object from which the coordinates are extracted
     *                     and divided with this object
     * @return the object itself after division
     */
    public Complex div(Complex complex) {
        // ((re1*re2+im1*im2)/(re2^2+im2^2) + ((im1*re2-re1*im2)/(re2^2+im2^2)) * i)
        double newReal = (this.real * complex.real + this.imag * complex.imag) /
                    (Math.pow(complex.real, 2) + Math.pow(complex.imag, 2));
        this.imag = ((this.imag * complex.real) - (this.real * complex.imag)) /
                    (Math.pow(complex.real, 2) + Math.pow(complex.imag, 2));
        this.real = newReal;
        return this;
    }

    // endregion

    // region **************** getters and setters ****************

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImag() {
        return imag;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    public double getAbs() {
        return ComplexMath.toPolar(real, imag)[0];
    }

    public double getRad() {
        return ComplexMath.toPolar(real,imag)[1];
    }

    // endregion

    // region **************** overrides ****************

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complex complex = (Complex) o;
        return Double.compare(complex.real, real) == 0 &&
                Double.compare(complex.imag, imag) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imag);
    }

    @Override
    public String toString() {
        if (getImag() == 0) return ToolBox.formatNoRound(getReal()) + "";
        if (getReal() == 0) return ToolBox.formatNoRound(getImag()) + "i";
        return "(" + ToolBox.formatNoRound(getReal()) + (getImag() < 0 ? " - " : " + ") +
                ToolBox.formatNoRound(Math.abs(getImag())) + "i)";
    }

    public String toStringPolar() {
        if (getAbs() == 0 && getImag() == 0) return "0";
        if (getImag() == 0) return "" + ToolBox.formatNoRound(getAbs());
        if (getAbs() == 0) return "e^" + ToolBox.formatNoRound(getRad()) + "i";
        return "(" + ToolBox.formatNoRound(getAbs()) + " + " + "e^" + ToolBox.formatNoRound(getRad()) + "i)";
    }

    // endregion
}
