package de.alshikh.haw.grundlagen.fortsetzung.clases;

import java.util.Objects;

public class Complex {

    private Cartesian cartesian;
    private Polar polar;

    public Complex(Cartesian cartesian) {
        this.polar = cartesian.toPolar();
        this.cartesian = cartesian;
    }

    public Complex(Polar polar) {
        this.cartesian = polar.toCartesian();
        this.polar = polar;
    }

    public Complex(double real_or_abs, double imag_or_rad, boolean isPolar) {
        if (isPolar) {
            this.polar = new Polar(real_or_abs, imag_or_rad);
            this.cartesian = ComplexMath.toCartesian(this.polar);
        } else {
            this.cartesian = new Cartesian(real_or_abs, imag_or_rad);
            this.polar = ComplexMath.toPolar(this.cartesian);
        }
    }

    public Complex add(Cartesian cartesian) {
        update(this.cartesian.getReal() + cartesian.getReal(),
              this.cartesian.getImag() + cartesian.getImag());
        return this;
    }

    public Complex add(Polar polar) {
        return add(polar.toCartesian());
    }

    public Complex add(Complex complex) {
        return add(complex.getCartesian());
    }

    public Complex sub(Cartesian cartesian) {
        update(this.cartesian.getReal() - cartesian.getReal(),
                this.cartesian.getImag() - cartesian.getImag());
        return this;
    }

    public Complex sub(Polar polar) {
        return sub(polar.toCartesian());
    }

    public Complex sub(Complex complex) {
        return sub(complex.getCartesian());
    }

    public Complex mult(Cartesian cartesian) {
        update(this.cartesian.getReal() * cartesian.getReal() -
               this.cartesian.getImag() * cartesian.getImag(),
               this.cartesian.getReal() * cartesian.getImag() +
               this.cartesian.getImag() * cartesian.getReal());
        return this;
    }

    public Complex mult(Polar polar) {
        return mult(polar.toCartesian());
    }

    public Complex mult(Complex complex) {
        return mult(complex.getCartesian());
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

    public void setCartesian(Cartesian cartesian) {
        this.cartesian = cartesian;
    }

    public Polar getPolar() {
        return polar;
    }

    public void setPolar(Polar polar) {
        this.polar = polar;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Complex) {
            return cartesian.equals(((Complex) o).cartesian);
        } else if (o instanceof Cartesian) {
            return cartesian.equals(o);
        } else if (o instanceof Polar) {
            return polar.equals(o);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return polar.hashCode();
    }

    @Override
    public String toString() {
        return this.cartesian.toString();
    }

    public String toStringPolar() {
        return this.polar.toString();
    }
}
