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


        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);
        System.out.println(num4);
        System.out.println(cartesian);
        System.out.println(polar);
        System.out.println(num5);
        System.out.println(num6);

        System.out.println(num3.equals(num4));

    }
}
