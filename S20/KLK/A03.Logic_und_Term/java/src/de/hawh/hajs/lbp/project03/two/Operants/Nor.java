package de.hawh.hajs.lbp.project03.two.Operants;

public class Nor extends Term{
  public Nor(Term TermLinks, Term TermRechts){
    super(TermLinks, TermRechts);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Nor)) {
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
    return new Not(new Or(TermLinks, TermRechts)).auswerten();
  }

  @Override
  public String toString(){
    return "(" + TermLinks.toString() + ") ⊽ (" + TermRechts.toString() + ")";
  }
}
