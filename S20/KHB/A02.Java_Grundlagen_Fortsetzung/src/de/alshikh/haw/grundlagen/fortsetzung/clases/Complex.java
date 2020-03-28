package de.alshikh.haw.grundlagen.fortsetzung.clases;

import java.util.Objects;

public class Complex {

    private Cartesian cartesian;
    private Polar polar;
    private int errorMargin;

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

    public Complex(double real_or_abs, double imag_or_rad, boolean isPolar) {
        this(real_or_abs, imag_or_rad, isPolar, 6);
    }

    public Complex(double real_or_abs, double imag_or_rad) {
        this(real_or_abs, imag_or_rad, false, 6);
    }

    public Complex(Cartesian cartesian, int errorMargin) {
        this(cartesian.getReal(), cartesian.getImag(), false, errorMargin);
    }

    public Complex(Cartesian cartesian) {
        this(cartesian, 6);
    }

    public Complex(Polar polar, int errorMargin) {
        this(polar.getAbs(), polar.getRad(), true, errorMargin);
    }

    public Complex(Polar polar) {
        this(polar, 6);
    }



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

    public static boolean isComplex(Object o) {
        return o instanceof Cartesian || o instanceof Polar || o instanceof Complex;
    }



    public Complex add(Cartesian cartesian) {
        update(this.cartesian.getReal() + cartesian.getReal(),
              this.cartesian.getImag() + cartesian.getImag());
        return this;
    }

    public Complex add(Complex complex) {
        return add(complex.getCartesian());
    }

    public Complex add(Polar polar) {
        return add(polar.toCartesian());
    }

    public Complex sub(Cartesian cartesian) {
        update(this.cartesian.getReal() - cartesian.getReal(),
                this.cartesian.getImag() - cartesian.getImag());
        return this;
    }

    public Complex sub(Complex complex) {
        return sub(complex.getCartesian());
    }

    public Complex sub(Polar polar) {
        return sub(polar.toCartesian());
    }

    public Complex mult(Cartesian cartesian) {
        update(this.cartesian.getReal() * cartesian.getReal() -
               this.cartesian.getImag() * cartesian.getImag(),
               this.cartesian.getReal() * cartesian.getImag() +
               this.cartesian.getImag() * cartesian.getReal());
        return this;
    }

    public Complex mult(Complex complex) {
        return mult(complex.getCartesian());
    }

    public Complex mult(Polar polar) {
        return mult(polar.toCartesian());
    }

    public Complex div(Polar polar) {
        this.polar = new Polar(this.polar.getAbs()/polar.getAbs(),
                this.polar.getRad() - polar.getRad());
        this.cartesian = this.polar.toCartesian();
        return this;
    }

    public Complex div(Cartesian cartesian) {
        return div(cartesian.toPolar());
    }

    public Complex div(Complex complex) {
        return div(complex.getPolar());
    }

    private void update(double real, double imag) {
        this.cartesian = new Cartesian(real, imag);
        this.polar = this.cartesian.toPolar();
    }




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

    public void setErrorMargin(int errorMargin) {
        this.errorMargin = errorMargin;
    }

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
}
