package de.hawh.hajs.lbp.project03.two.Operants;

import java.util.Objects;

public abstract class Term {

  public Term TermLinks;
  public Term TermRechts;

  public Term(Term TermLinks, Term TermRechts) {
    this.TermLinks = TermLinks;
    this.TermRechts = TermRechts;
  }

  public Term(Term TermLinks) {
    this.TermLinks = TermLinks;
    this.TermRechts = null;
  }

  public Term() {
    this.TermLinks = null;
    this.TermRechts = null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Term)) {
      return false;
    }
    Term term = (Term) o;
    return Objects.equals(TermLinks, term.TermLinks) &&
        Objects.equals(TermRechts, term.TermRechts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(TermLinks, TermRechts);
  }

  public abstract boolean auswerten();
}
