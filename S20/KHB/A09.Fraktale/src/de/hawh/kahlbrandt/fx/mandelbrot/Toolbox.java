package de.hawh.kahlbrandt.fx.mandelbrot;

/**********************************************************************
 *
 * various practical methods
 *
 * @author Hani Alshikh
 *
 ***********************************************************************/
public final class Toolbox {

    // Private constructor to prevent instantiation
    private Toolbox() {
        throw new UnsupportedOperationException();
    }


    /**
     * removes unnecessary trailing decimal 0 without rounding
     *
     * example:         10,0        => "10"
     *                  100,123400  => 100,1234
     *                  98,7        => 98,7
     *
     * @param d         the double number to be checked.
     * @return double the number with no trailing zeros.
     */
    public static String formatNoRound(double d) {
        return d == (long) d ? String.format("%d",(long)d) : String.format("%s",d);
    }
}
