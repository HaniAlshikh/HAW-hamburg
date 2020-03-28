package de.alshikh.haw.grundlagen.fortsetzung;

import de.alshikh.haw.grundlagen.fortsetzung.clases.Cartesian;
import de.alshikh.haw.grundlagen.fortsetzung.clases.Complex;
import de.alshikh.haw.grundlagen.fortsetzung.clases.Polar;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Complex num1 = new Complex(new Cartesian(10,5));
        Complex num2 = new Complex(new Polar(11.180339887498949,0.4636476090008061));
        Complex num3 = new Complex(new Cartesian(3,4));
        Complex num4 = new Complex(-3, 4, false);
        Cartesian cartesian = new Cartesian(6,3);
        Polar polar = new Polar(6.708203932499369, 0.4636476090008061);


        Complex num5 = new Complex(-3, 4, false);
        Complex num6 = new Complex(5, 2.214297435588181, true);


        Complex test = new Complex(10, Math.PI, true);
        Complex test1 = new Complex(-10, 0, false);
        System.out.println(test);
        System.out.println(test1);


        System.out.println(Math.floor(10 * 1000000) / 1000000);
        System.out.println(0.000001*1000000);

    }
}
