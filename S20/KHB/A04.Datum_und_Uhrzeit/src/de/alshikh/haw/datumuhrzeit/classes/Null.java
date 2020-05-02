package de.alshikh.haw.datumuhrzeit.classes;

/**********************************************************************
 *
 * calling static methods through an instance.
 *
 * @author Hani Alshikh
 *
 ************************************************************************/

public class Null {

    public static void greet() {
        System.out.println("Hello world!");
    }

    // Static members belongs to the class rather than instance.
    // So at compile time, <instance>.greet() is converted to Null.greet()
    @SuppressWarnings({"AccessStaticViaInstance"})
    public static void main(String[] args) {
        ((Null) null).greet();
    }
}
