package de.hawh.kahlbrandt.fx.mandelbrot;

/**********************************************************************
 *
 * various complex number methods and formel
 *
 * @author Hani Alshikh
 *
 ***********************************************************************/
public final class ComplexMath {


    private ComplexMath(){
        throw new UnsupportedOperationException();
    }

    /**
     * @param real    real value to be converted to absolute value
     * @param imag    imaginary value to be converted to radian
     * @return the converted polar coordinates
     */
    public static double[] toPolar(double real, double imag) {
        // (sqr(re^2+im^2) + e^atan(im/re)*i)
        return new double[]{Math.hypot(real, imag), Math.atan2(imag , real)};
    }

    /**
     * @param abs     absolute value to be converted to real value
     * @param rad     radian value to be converted to imaginary value
     * @return the converted cartesian coordinates
     */
    public static double[] toCartesian(double abs, double rad) {
        // (abs*cos(rad) + abs*sin(rad)*i)
        return new double[]{abs * Math.cos(rad), abs * Math.sin(rad)};
    }
}
