package de.hawh.hajs.lbp.project03.two.Operants;

public class Aequiv extends Term {

  public Aequiv(Term TermLinks, Term TermRechts) {
    super(TermLinks, TermRechts);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Aequiv)) {
      return false;
    }
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean auswerten() {
    return new Or(new And(TermLinks, TermRechts), new And(new Not(TermLinks), new Not(TermRechts))).auswerten();
  }

  @Override
  public String toString(){
    return "(" + TermLinks.toString() + ") <-> (" + TermRechts.toString() + ")";
  }

}
