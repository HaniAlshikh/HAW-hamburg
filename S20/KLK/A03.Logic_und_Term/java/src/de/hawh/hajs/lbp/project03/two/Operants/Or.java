package de.hawh.hajs.lbp.project03.two.Operants;

public class Or extends Term {

  public Or(Term termLinks, Term termRechts) {
    super(termLinks, termRechts);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Or)) {
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
    return TermLinks.auswerten() || TermRechts.auswerten();
  }
  @Override
  public String toString(){
    return "(" + TermLinks.toString() + ") ∨ (" + TermRechts.toString() + ")";
  }

}
