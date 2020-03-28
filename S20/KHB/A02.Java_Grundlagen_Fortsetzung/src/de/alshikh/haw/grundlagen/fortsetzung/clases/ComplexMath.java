package de.alshikh.haw.grundlagen.fortsetzung.clases;

public class ComplexMath {

    private ComplexMath(){
        throw new IllegalStateException();
    }

    public static Polar toPolar(Cartesian cartesian) {
        return new Polar(Math.hypot(cartesian.getReal(), cartesian.getImag()),
                Math.atan2(cartesian.getImag() , cartesian.getReal()));
    }

    public static Cartesian toCartesian(Polar polar) {
        return new Cartesian(polar.getAbs() * Math.cos(polar.getRad()),
                    polar.getAbs() * Math.sin(polar.getRad()));
    }

}
