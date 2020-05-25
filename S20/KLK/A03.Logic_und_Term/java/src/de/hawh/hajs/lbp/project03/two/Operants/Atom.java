package de.hawh.hajs.lbp.project03.two.Operants;

import java.util.Objects;

public class Atom extends Term {
  private final boolean value;

  public Atom(boolean value) {
    super(null, null);
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Atom)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Atom atom = (Atom) o;
    return value == atom.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), value);
  }

  @Override
  public boolean auswerten() {
    return value;
  }

  @Override
  public String toString(){
    return value + "";
  }

}
