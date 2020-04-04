package de.alshikh.haw.grundlagen.fortsetzung.classes;

import java.util.HashSet;
import java.util.Set;

/**********************************************************************
 *
 * Assigment sheet A02: Java Basics follow-up.
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class Name {
    private String first, last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Name))
            return false;
        Name n = (Name)o;
        return n.first.equals(first) && n.last.equals(last);
    }

    public int hashCode() {
        return 31 * first.hashCode() + last.hashCode();
    }

    public static void main(String[] args) {
        Set<Name> s = new HashSet<>();
        s.add(new Name("Mickey", "Mouse"));
        System.out.println(s.contains(new Name("Mickey", "Mouse")));

        Set<Name> d = new HashSet<Name>();
        d.add(new Name("Donald", "Duck"));
        System.out.println(d.contains(new Name("Donald", "Duck")));
    }
}
