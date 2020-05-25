package de.hawh.hajs.lbp.project03.two.Operants;


public class Xor extends Term {

  public Xor(Term termLinks, Term termRechts) {
    super(termLinks, termRechts);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Xor)) {
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
    return new And(new Or(TermLinks, TermRechts), new Or(new Not(TermLinks), new Not(TermRechts))).auswerten();
  }

  @Override
  public String toString(){
    return "(" + TermLinks.toString() + ") ⊕ (" + TermRechts.toString() + ")";
  }
}
