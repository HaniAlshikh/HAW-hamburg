package de.hawh.hajs.lbp.project03.two;

import de.hawh.hajs.lbp.project03.two.Operants.*;

public class TermUtility {

  private TermUtility() {}

  public static boolean isAtomic(Term termToCheck) {
    return termToCheck.getClass() == Atom.class;
  }

  public static boolean isLiteral(Term termToCheck) {
    return isAtomic(termToCheck) || (
           termToCheck.getClass() == Not.class &&
           isAtomic(termToCheck.TermLinks)
           );
  }

  public static Term nnf_expr(Term termToSimplify) {

    // ist literal? es gibt nichts zu machen.
    if (TermUtility.isLiteral(termToSimplify)) {
      return termToSimplify;
    }

    // Term hat Form (X → Y), (X ↔ Y) oder die Negation davon?
    termToSimplify = simplyDef(termToSimplify);

    // ist literal? es gibt nichts zu machen.
    if (TermUtility.isLiteral(termToSimplify)) {
      return termToSimplify;
    }

    // simplifiedTerm zunächst mit übergebenen Term initialisieren
    Term simplifiedTerm = termToSimplify;

    // Term hat die Form nor(X,Y)
    if (termToSimplify.getClass() == Nor.class) {
      Term simplified_Nor = new Not(new Or(termToSimplify.TermLinks, termToSimplify.TermRechts));
      simplifiedTerm = nnf_expr(simplified_Nor);
    }
    // Term hat die Form or(X,Y)?
    else if (termToSimplify.getClass() == Or.class) {
      Term simplified_X = nnf_expr(termToSimplify.TermLinks);
      Term simplified_Y = nnf_expr(termToSimplify.TermRechts);
      simplifiedTerm = new Or(simplified_X, simplified_Y);
    }

    // Term hat die Form and(X,Y)?
    else if (termToSimplify.getClass() == And.class) {
      Term simplified_X = nnf_expr(termToSimplify.TermLinks);
      Term simplified_Y = nnf_expr(termToSimplify.TermRechts);
      simplifiedTerm = new And(simplified_X, simplified_Y);
    }

    // Term hat die Form nand(X,Y)?
    else if (termToSimplify.getClass() == Nand.class) {
      Term simplified_Nand = new Not(new And(termToSimplify.TermLinks, termToSimplify.TermRechts));
      simplifiedTerm = nnf_expr(simplified_Nand);
    }

    // Term hat die Form xor(X,Y)?
    else if (termToSimplify.getClass() == Xor.class) {
      Term simplified_Xor = new And(
          new Or(new Not(termToSimplify.TermLinks), new Not(termToSimplify.TermRechts)),
          new Or(termToSimplify.TermLinks, termToSimplify.TermRechts));
      simplifiedTerm = nnf_expr(simplified_Xor);
    }

    // Term hat die Form not(X)?
    else if (termToSimplify.getClass() == Not.class) {

      // Merke notTerm für bessere Lesbarkeit
      Term notTerm = termToSimplify.TermLinks;

      // X hat die Form Or(Y,Z)?
      if (notTerm.getClass() == Or.class) {
        Term Simplified_Not_Or = new And(new Not(notTerm.TermLinks),
            new Not(notTerm.TermRechts));
        simplifiedTerm = nnf_expr(Simplified_Not_Or);
      }
      // X hat die Form Nor(Y,Z)?
      else if (notTerm.getClass() == Nor.class) {
        Term simplified_Not_Nor = new Or(notTerm.TermLinks,
            notTerm.TermRechts);
        simplifiedTerm = nnf_expr(simplified_Not_Nor);
      }

      // X hat die Form Nand(Y,Z)?
      else if (notTerm.getClass() == Nand.class) {
        Term simplified_Not_Nand = new And(notTerm.TermLinks,
            notTerm.TermRechts);
        simplifiedTerm = nnf_expr(simplified_Not_Nand);
      }

      // X hat die Form And(Y,Z)?
      else if (notTerm.getClass() == And.class) {
        Term simplified_Not_And = new Or(new Not(notTerm.TermLinks),
            new Not(notTerm.TermRechts));
        simplifiedTerm = nnf_expr(simplified_Not_And);
      }

      // X hat die Form Not(Y,Z)?
      else if (notTerm.getClass() == Not.class) {
        Term simplified_Not_Not = notTerm.TermLinks;
        simplifiedTerm = nnf_expr(simplified_Not_Not);
      }

      // X hat die Form Xor(Y,Z)?
      else if (notTerm.getClass() == Xor.class) {
        Term simplified_Not_Xor = new Nand(new Or(new Not(notTerm.TermLinks),
            new Not(notTerm.TermRechts)),
            new Or(notTerm.TermLinks, notTerm.TermRechts));
        simplifiedTerm = nnf_expr(simplified_Not_Xor);
      }
    }
    return simplifiedTerm;
  }

  private static Term simplyDef(Term termToSimplify) {
    // Term hat die Form Impl(X,Y)?
    if (Impl.class.equals(termToSimplify.getClass())) {
      termToSimplify = new Or(new Not(termToSimplify.TermLinks), termToSimplify.TermRechts);
    }

    // Term hat die Form Aequiv(X,Y)?
    else if (Aequiv.class.equals(termToSimplify.getClass())) {
      termToSimplify = new Or(new And(termToSimplify.TermLinks, termToSimplify.TermRechts),
          new And(new Not(termToSimplify.TermLinks), new Not(termToSimplify.TermRechts)));
    }

    // Term hat die Form Not(X)?
    else if (Not.class.equals((termToSimplify.getClass()))) {
      // Rufe simplyDef(X) auf und speichere das Ergebnis umschlossen von einem Not
      // Hiermit wird not(impl(X, Y)) und not(aequiv(X, Y)) umgeformt.
      termToSimplify = new Not(simplyDef(termToSimplify.TermLinks));
    }

    return termToSimplify;
  }

}
