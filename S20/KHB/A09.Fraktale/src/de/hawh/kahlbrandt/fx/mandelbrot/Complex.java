/*
 * Copyright (c) 2013, 2014, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.hawh.kahlbrandt.fx.mandelbrot;

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
    public Complex plus(Complex complex) {
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
    public Complex times(Complex complex) {
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

    /**
     * Square of Complex object's length, we're using square of length to
     * eliminate the computation of square root
     * @return square of length
     */
    public double lengthSQ() {
        return real * real + imag * imag;
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
        if (getImag() == 0) return Toolbox.formatNoRound(getReal()) + "";
        if (getReal() == 0) return Toolbox.formatNoRound(getImag()) + "i";
        return "(" + Toolbox.formatNoRound(getReal()) + (getImag() < 0 ? " - " : " + ") +
                Toolbox.formatNoRound(Math.abs(getImag())) + "i)";
    }

    public String toStringPolar() {
        if (getAbs() == 0 && getImag() == 0) return "0";
        if (getImag() == 0) return "" + Toolbox.formatNoRound(getAbs());
        if (getAbs() == 0) return "e^" + Toolbox.formatNoRound(getRad()) + "i";
        return "(" + Toolbox.formatNoRound(getAbs()) + " + " + "e^" + Toolbox.formatNoRound(getRad()) + "i)";
    }

    // endregion
}