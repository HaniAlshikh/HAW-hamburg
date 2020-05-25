package de.hawh.hajs.lbp.project03.two.Operants;

public class Nand extends Term{

  public Nand(Term TermLinks, Term TermRechts) {
    super(TermLinks, TermRechts);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Nand)) {
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
    return new Not((new And(TermLinks, TermRechts))).auswerten();
  }

  @Override
  public String toString(){
    return "(" + TermLinks.toString() + ") ⊼ (" + TermRechts.toString() + ")";
  }

}
