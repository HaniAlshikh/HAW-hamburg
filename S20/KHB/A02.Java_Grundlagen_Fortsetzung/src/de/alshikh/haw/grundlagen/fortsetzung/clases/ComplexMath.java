/**********************************************************************
 *
 * various complex number methods and formel
 *
 * Author: Hani Alshikh
 *
 ***********************************************************************/

package de.alshikh.haw.grundlagen.fortsetzung.clases;

public final class ComplexMath {


    private ComplexMath(){
        throw new UnsupportedOperationException();
    }

    /**
     * @param cartesian    Cartesian object from which the coordinates are extracted
     *                     and converted to polar coordinates
     * @return polar object with the new coordinates
     */
    public static Polar toPolar(Cartesian cartesian) {
        // (sqr(re^2+im^2) + e^atan(im/re)*i)
        return new Polar(Math.hypot(cartesian.getReal(), cartesian.getImag()),
                Math.atan2(cartesian.getImag() , cartesian.getReal()));
    }

    /**
     * @param polar        Polar object from which the coordinates are extracted
     *                     and converted to Cartesian coordinates
     * @return cartesian object with the new coordinates
     */
    public static Cartesian toCartesian(Polar polar) {
        // (abs*cos(rad) + abs*sin(rad)*i)
        return new Cartesian(polar.getAbs() * Math.cos(polar.getRad()),
                    polar.getAbs() * Math.sin(polar.getRad()));
    }

}
