package de.hawh.hajs.lbp.project03.two.Operants;

public class Not extends Term {

  public Not(Term term) {
    super(term);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Not)) {
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
    return !TermLinks.auswerten();
  }

  @Override
  public String toString(){
    return "¬(" + TermLinks.toString() + ")";
  }
}
