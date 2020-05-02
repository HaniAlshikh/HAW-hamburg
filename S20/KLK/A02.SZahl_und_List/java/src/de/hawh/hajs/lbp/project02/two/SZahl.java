package de.hawh.hajs.lbp.project02.two;

/**
 * Demonstrates some arithmetic on natural numbers defined as a recursive successor of 0 called a
 * "S-Zahl".
 *
 * @author Hani Alshikh
 * @author Jannik Stuckstätte
 */
public class SZahl {

  public String s_zahl = "0";

  private static String inkrement(String szahl) {
    return "s(" + szahl + ")";
  }

  private static String dekrement(String szahl) {
    if (szahl.equals("0")) {
      return "0";
    }
    return szahl.substring(2, szahl.length() - 1);
  }

  /**
   * Checks whether o is equivalent to this object.
   *
   * We are using this method for convenient testing.
   *
   * @param o Any object to compare.
   * @return Is o equivalent to this?
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SZahl)) {
      return false;
    }
    SZahl sZahl = (SZahl) o;
    return s_zahl.equals(sZahl.s_zahl);
  }

  /**
   * Obligatory override of the toString method.
   *
   * Also very useful for debugging.
   *
   * @return String representation of this object.
   */
  @Override
  public String toString() {
    return "SZahl{" +
        "s_zahl='" + s_zahl + '\'' +
        '}';
  }

  /**
   * Obligatory override of the hashCode method.
   *
   * @return A hash code value for this "S-Zahl".
   */
  @Override
  public int hashCode() {
    return s_zahl.hashCode();
  }

  /**
   * Converts any natural number to the corresponding "S-Zahl".
   *
   * @param naturalNumber The natural number to convert.
   * @return The natural number as "S-ZahL".
   */
  public static SZahl nat2s(int naturalNumber) {
    // Preventing negative numbers
    if (naturalNumber < 0) {
      throw new IllegalArgumentException("Natural number cannot be negative!");
    }
    // Start at 0
    SZahl sZahl = new SZahl();
    // End if counter is at 0
    while (naturalNumber != 0) {
      // Decrement counter
      naturalNumber--;
      // Increment output for every decrement of the counter
      sZahl.s_zahl = inkrement(sZahl.s_zahl);
    }
    return sZahl;
  }

  /**
   * Converts any "S-Zahl" to the corresponding natural number.
   *
   * @param sZahl The "S-Zahl" to convert.
   * @return The "S-Zahl" as natural number.
   */
  public static int s2nat(SZahl sZahl) {
    int output = 0;
    // We need to copy the value of the parameter to avoid destructive changes
    // to the given object attributes
    SZahl inputSZahl = new SZahl();
    inputSZahl.s_zahl = sZahl.s_zahl;
    // Check if input got to 0
    while (!inputSZahl.s_zahl.equals("0")) {
      // Increment output
      output++;
      // Decrement the "S-Zahl" for every increment of the output
      inputSZahl.s_zahl = dekrement(inputSZahl.s_zahl);
    }
    return output;
  }

  /**
   * Adds up s1 and s2.
   *
   * @param s1 First addend.
   * @param s2 Second addend.
   * @return Addition result.
   */
  public static SZahl add(SZahl s1, SZahl s2) {
    // Starting at the value of s1
    SZahl output = new SZahl();
    output.s_zahl = s1.s_zahl;

    // We need to copy the value of the parameter to avoid destructive changes
    // to the attributes of the given object
    SZahl s2Copy = new SZahl();
    s2Copy.s_zahl = s2.s_zahl;

    // Check if s2 got to 0
    while (!s2Copy.s_zahl.equals("0")) {
      // Decrement s2
      s2Copy.s_zahl = dekrement(s2Copy.s_zahl);
      // Increment output for every decrement of s2
      output.s_zahl = inkrement(output.s_zahl);
    }
    return output;
  }

  /**
   * Subtracts s1 and s2.
   *
   * @param s1 The minuend.
   * @param s2 The subtrahend.
   * @return The subtraction result.
   */
  public static SZahl sub(SZahl s1, SZahl s2) {
    // Starting at the value of s1
    SZahl output = new SZahl();
    output.s_zahl = s1.s_zahl;

    // We need to copy the value of the parameter to avoid destructive changes
    // to the attributes of the given object
    SZahl s2Copy = new SZahl();
    s2Copy.s_zahl = s2.s_zahl;

    // Decrement both as long as both (ouput and s2) are greater than 0
    while (!s2Copy.s_zahl.equals("0") && !output.s_zahl.equals("0")) {
      // Decrement both, the output and s2
      s2Copy.s_zahl = SZahl.dekrement(s2Copy.s_zahl);
      output.s_zahl = SZahl.dekrement(output.s_zahl);
    }
    return output;
  }

  /**
   * Multiplies s1 und s2.
   *
   * @param s1 First factor.
   * @param s2 Second factor.
   * @return Multiplication result.
   */
  public static SZahl mul(SZahl s1, SZahl s2) {
    // Starting at 0
    SZahl output = new SZahl();

    // We need to copy the value of the parameter to avoid destructive changes
    // to the attributes of the given object
    SZahl s2Copy = new SZahl();
    s2Copy.s_zahl = s2.s_zahl;

    // We use s2 as a counter so we continue as long as it is greater than 0
    while (!s2Copy.s_zahl.equals("0")) {
      // Decrement counter
      s2Copy.s_zahl = SZahl.dekrement(s2Copy.s_zahl);
      // Add the value of s1 to the output
      output = add(output, s1);
    }
    return output;
  }

  /**
   * Potentiates s1 with s2.
   *
   * @param s1 The basis.
   * @param s2 The exponent.
   * @return The power value.
   */
  public static SZahl power(SZahl s1, SZahl s2) {
    // Starting at 1
    SZahl output = new SZahl();
    output.s_zahl = SZahl.inkrement(output.s_zahl);

    // We need to copy the value of the parameter to avoid destructive changes
    // to the attributes of the given object
    SZahl s2Copy = new SZahl();
    s2Copy.s_zahl = s2.s_zahl;

    // We use s2 as a counter so we continue as long as it is greater than 0
    while (!s2Copy.s_zahl.equals("0")) {
      // Decrement counter
      s2Copy.s_zahl = SZahl.dekrement(s2Copy.s_zahl);
      // Multiply the output and s1
      output = mul(output, s1);
    }
    return output;
  }

  /**
   * Calculates the faculty of the given "S-Zahl"
   *
   * @param sZahl The "S-Zahl" to calculate the faculty of.
   * @return The faculty result.
   */
  public static SZahl fac(SZahl sZahl) {
    // We need to copy the value of the parameter to avoid destructive changes
    // to the attributes of the given object
    SZahl sCopy = new SZahl();
    sCopy.s_zahl = sZahl.s_zahl;
    
    // faculty is the Multiplication of all natural numbers
    // less or equal to the number (without Zero) 
    // 1 (s(0)) is the neutral in multiplication,
    // thus output is initialized with 1 (s(0))
    SZahl output = new SZahl();
    output.s_zahl = SZahl.inkrement(output.s_zahl);
    // we check if the input is equal to zero (1)
    // to detrmine when should the loop stop
    while (!sCopy.s_zahl.equals("0")) { 
      // multiplay the output with the input (2)
      output = mul(output, sCopy);
      // decrement the input to go through all natural numbers
      // below the input, then multiple with the output (3)
      sCopy.s_zahl = SZahl.dekrement(sCopy.s_zahl);
    }
    // return output when the input is or became zero
    // if input is already zero, then output is one.
    return output;
  }

  /**
   * Checks whether s1 is less than s2.
   *
   * @param s1 The "S-Zahl" to compare to s2
   * @param s2 The "S-Zahl" to compare to s1
   * @return The comparison result.
   */
  public static boolean lt(SZahl s1, SZahl s2) {

    // We can exit immediately if both values are equal
    if (s1.equals(s2)) {
      return false;
    }

    // We need to copy the value of the parameter to avoid destructive changes
    // to the attributes of the given object
    SZahl s1Copy = new SZahl();
    s1Copy.s_zahl = s1.s_zahl;

    // We need to copy the value of the parameter to avoid destructive changes
    // to the attributes of the given object
    SZahl s2Copy = new SZahl();
    s2Copy.s_zahl = s2.s_zahl;

    // Decrement both s1 and s2 as long as both are greater than zero. If one gets to zero
    // we return true if s1 == 0 and false if s2 == 0
    while (!(s1Copy.s_zahl.equals("0") && !s2Copy.s_zahl.equals("0"))) {
      if (!s1Copy.s_zahl.equals("0") && s2Copy.s_zahl.equals("0")) {
        return false;
      }

      s1Copy.s_zahl = SZahl.dekrement(s1Copy.s_zahl);
      s2Copy.s_zahl = SZahl.dekrement(s2Copy.s_zahl);
    }

    return true;
  }

  /**
   * Calculates the euclidean division of s1 by s2.
   *
   * @param s1 The dividend.
   * @param s2 The divisor.
   * @return The remainder.
   */
  public static SZahl mods(SZahl s1, SZahl s2) {
    // Preventing division by zero.
    if (s2.s_zahl.equals("0")) {
      throw new ArithmeticException("Division by zero");
    }

    // We need to copy the value of the parameter to avoid destructive changes
    // to the attributes of the given object
    SZahl s1Copy = new SZahl();
    s1Copy.s_zahl = s1.s_zahl;

    // Checking if s1 is still greater than or equal to s2 (and thus can still be divided by two another time)
    while (!lt(s1Copy, s2)) {
      // Decrement s1 by the value of s2
      s1Copy = sub(s1Copy, s2);
    }
    // Returning the remainder.
    return s1Copy;
  }
}
