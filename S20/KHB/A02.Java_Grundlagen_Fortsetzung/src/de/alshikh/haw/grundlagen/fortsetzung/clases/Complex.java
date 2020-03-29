/**********************************************************************
 *
 * basic implementation of the complex numbers
 *
 * @author Mateusz Chylewski
 * @author Hani Alshikh
 *
 ***********************************************************************/

package de.alshikh.haw.grundlagen.fortsetzung.clases;

import java.util.Objects;

public class Complex {

    private Cartesian cartesian;
    private Polar polar;
    private int errorMargin;

    // region **************** object initialization ****************
    
    /**
     * @param real_or_abs the real value of the Cartesian coordinates
     *                    or the absolute value of the polar coordinates.
     * @param imag_or_rad the imaginary value of the Cartesian coordinates
     *                    or the angel value of the polar coordinates in radian.
     * @param isPolar     musst set to true if the polar coordinates are used
     *                    by default it is false.
     * @param errorMargin the number of decimal places to be considered when
     *                    calculating.
     */
    public Complex(double real_or_abs, double imag_or_rad, boolean isPolar, int errorMargin) {
        if (isPolar) {
            this.polar = new Polar(real_or_abs, imag_or_rad);
            this.cartesian = ComplexMath.toCartesian(this.polar);
        } else {
            this.cartesian = new Cartesian(real_or_abs, imag_or_rad);
            this.polar = ComplexMath.toPolar(this.cartesian);
        }
        this.errorMargin = errorMargin;
    }

    /**
     * @param real_or_abs the real value of the Cartesian coordinates
     *                    or the absolute value of the polar coordinates.
     * @param imag_or_rad the imaginary value of the Cartesian coordinates
     *                    or the angel value of the polar coordinates in radian.
     * @param isPolar     musst set to true if the polar coordinates are used
     *                    by default it is false.
     */
    public Complex(double real_or_abs, double imag_or_rad, boolean isPolar) {
        this(real_or_abs, imag_or_rad, isPolar, 6);
    }

    /**
     * @param real_or_abs the real value of the Cartesian coordinates
     *                    or the absolute value of the polar coordinates.
     * @param imag_or_rad the imaginary value of the Cartesian coordinates
     *                    or the angel value of the polar coordinates in radian.
     */
    public Complex(double real_or_abs, double imag_or_rad) {
        this(real_or_abs, imag_or_rad, false, 6);
    }

    /**
     * @param cartesian   Cartesian object from which the coordinates are
     *                    used to generate the complex number.
     * @param errorMargin the number of decimal places to be considered when
     *                    calculating.
     */
    public Complex(Cartesian cartesian, int errorMargin) {
        this(cartesian.getReal(), cartesian.getImag(), false, errorMargin);
    }

    /**
     * @param cartesian   Cartesian object from which the coordinates are
     *                    used to generate the complex number.
     */
    public Complex(Cartesian cartesian) {
        this(cartesian, 6);
    }

    /**
     * @param polar       Polar object from which the coordinates are
     *                    used to generate the complex number.
     * @param errorMargin the number of decimal places to be considered when
     *                    calculating
     */
    public Complex(Polar polar, int errorMargin) {
        this(polar.getAbs(), polar.getRad(), true, errorMargin);
    }

    /**
     * @param polar       Polar object from which the coordinates are
     *                    used to generate the complex number.
     */
    public Complex(Polar polar) {
        this(polar, 6);
    }
    
    // endregion
    
    // region **************** static methods ****************

    /**
     * @param complex     the first Complex number to be compared
     *                    can be Cartesian or Polar object too
     * @param other       the first Complex number to be compared
     *                    can be Cartesian or Polar object too
     * @param errorMargin the number of decimal places to be considered when
     *                    calculating.
     * @return true if the coordinates are equal (regardless of there type polar or cartesian)
     */
    public static boolean equals(Object complex, Object other, int errorMargin) {
        if (isComplex (complex) && isComplex(other)) {
            Cartesian c = complex instanceof Polar ? ((Polar) complex).toCartesian() :
                    complex instanceof Complex ? ((Complex) complex).getCartesian() : (Cartesian) complex;
            Cartesian o = other instanceof Polar ? ((Polar) other).toCartesian() :
                    other instanceof Complex ? ((Complex) other).getCartesian() : (Cartesian) other;
            return (Math.abs(c.getReal()) - Math.abs(o.getReal()) < Math.pow(1, errorMargin * -1) &&
                    Math.abs(c.getImag()) - Math.abs(o.getImag()) < Math.pow(1, errorMargin * -1));
        } else {
            return false;
        }
    }

    /**
     * @param o           the object to be checked
     *                    Cartesian and Polar are considered Complex too
     * @return true if Complex, Cartesian or Polar
     */
    public static boolean isComplex(Object o) {
        return o instanceof Cartesian || o instanceof Polar || o instanceof Complex;
    }
    
    // endregion

    // region **************** arithmetic operations ****************

    /**
     * @param cartesian    Cartesian object from which the coordinates are extracted
     *                     and added to this object
     * @return the object itself after addition
     */
    public Complex add(Cartesian cartesian) {
        // (re1+re2 + im1+im2 * i)
        update(this.cartesian.getReal() + cartesian.getReal(),
              this.cartesian.getImag() + cartesian.getImag());
        return this;
    }

    /**
     * @param complex      Complex object from which the coordinates are extracted
     *                     and added to this object
     * @return the object itself after addition
     */
    public Complex add(Complex complex) {
        return add(complex.getCartesian());
    }

    /**
     * @param polar        Polar object from which the coordinates are extracted
     *                     and added to this object
     * @return the object itself after addition
     */
    public Complex add(Polar polar) {
        return add(polar.toCartesian());
    }

    /**
     * @param cartesian    Cartesian object from which the coordinates are extracted
     *                     and subtracted from this object
     * @return the object itself after subtraction
     */
    public Complex sub(Cartesian cartesian) {
        // (re1-re2 + im1-im2 * i)
        update(this.cartesian.getReal() - cartesian.getReal(),
                this.cartesian.getImag() - cartesian.getImag());
        return this;
    }

    /**
     * @param complex      Complex object from which the coordinates are extracted
     *                     and subtracted from this object
     * @return the object itself after subtraction
     */
    public Complex sub(Complex complex) {
        return sub(complex.getCartesian());
    }

    /**
     * @param polar        Polar object from which the coordinates are extracted
     *                     and subtracted from this object
     * @return the object itself after subtraction
     */
    public Complex sub(Polar polar) {
        return sub(polar.toCartesian());
    }

    /**
     * @param cartesian    Cartesian object from which the coordinates are extracted
     *                     and multiplied with this object
     * @return the object itself after multiplication
     */
    public Complex mult(Cartesian cartesian) {
        // (re1*re2-img1*im2 + (re1*im2 + im1*re2) * i)
        update(this.cartesian.getReal() * cartesian.getReal() -
               this.cartesian.getImag() * cartesian.getImag(),
               this.cartesian.getReal() * cartesian.getImag() +
               this.cartesian.getImag() * cartesian.getReal());
        return this;
    }

    /**
     * @param complex      Complex object from which the coordinates are extracted
     *                     and multiplied with this object
     * @return the object itself after multiplication
     */
    public Complex mult(Complex complex) {
        return mult(complex.getCartesian());
    }

    /**
     * @param polar        Polar object from which the coordinates are extracted
     *                     and multiplied with this object
     * @return the object itself after multiplication
     */
    public Complex mult(Polar polar) {
        return mult(polar.toCartesian());
    }

    /**
     * @param polar        Polar object from which the coordinates are extracted
     *                     and divided with this object
     * @return the object itself after division
     */
    public Complex div(Polar polar) {
        // (abs1/abs2 + e^ rad1-rad2 * i)
        this.polar = new Polar(this.polar.getAbs()/polar.getAbs(),
                this.polar.getRad() - polar.getRad());
        this.cartesian = this.polar.toCartesian();
        return this;
    }

    /**
     * @param cartesian    Cartesian object from which the coordinates are extracted
     *                     and divided with this object
     * @return the object itself after division
     */
    public Complex div(Cartesian cartesian) {
        return div(cartesian.toPolar());
    }

    /**
     * @param complex      Complex object from which the coordinates are extracted
     *                     and divided with this object
     * @return the object itself after division
     */
    public Complex div(Complex complex) {
        return div(complex.getPolar());
    }


    /**
     * updat values for both the cartesian and polar form
     * @param real the real coordinate from the cartesian form
     * @param imag the imag coordinate from the cartesian form
     */
    private void update(double real, double imag) {
        this.cartesian = new Cartesian(real, imag);
        this.polar = this.cartesian.toPolar();
    }

    // endregion

    // region **************** getters and setters ****************

    public Cartesian getCartesian() {
        return cartesian;
    }

    public Complex setCartesian(Cartesian cartesian) {
        this.polar = cartesian.toPolar();
        this.cartesian = cartesian;
        return this;
    }

    public Polar getPolar() {
        return polar;
    }

    public Complex setPolar(Polar polar) {
        this.cartesian = polar.toCartesian();
        this.polar = polar;
        return this;
    }

    public int getErrorMargin() {
        return errorMargin;
    }

    public Complex setErrorMargin(int errorMargin) {
        this.errorMargin = errorMargin;
        return this;
    }

    // endregion

    // region **************** overrides ****************

    @Override
    public boolean equals(Object o) {
        return equals(this, o, errorMargin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.floor(polar.getAbs() * Math.pow(1, errorMargin)) / Math.pow(1, errorMargin),
                                    Math.floor(polar.getRad() * Math.pow(1, errorMargin)) / Math.pow(1, errorMargin));
    }

    @Override
    public String toString() {
        return this.cartesian.toString();
    }

    public String toStringPolar() {
        return this.polar.toString();
    }

    // endregion
}
