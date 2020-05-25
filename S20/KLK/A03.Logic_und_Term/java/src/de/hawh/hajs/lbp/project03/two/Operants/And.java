package de.hawh.hajs.lbp.project03.two.Operants;

public class And extends Term {
  public And(Term TermLinks, Term TermRechts) {
    super(TermLinks, TermRechts);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof And)) {
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
    return TermLinks.auswerten() && TermRechts.auswerten();
  }

  @Override
  public String toString(){
    return "(" + TermLinks.toString() + ") ∧ (" + TermRechts.toString() + ")";
  }
}
