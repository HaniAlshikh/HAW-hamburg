/*#####################################################################
# Assigment sheet A01: Java Basics.
#
# Author:: Hani Alshikh
#
#######################################################################*/

package de.alshikh.haw.grundlagen;

import de.alshikh.haw.grundlagen.clases.AnnualInterestRate;
import de.alshikh.haw.grundlagen.clases.AnnualInterestRateIterate;
import de.alshikh.haw.grundlagen.clases.Unbelievable;

public class Main {
    public static void main(String[] args) {

        // first part
        System.out.print("H" + "a"); // => Ha
        System.out.println('H' + 'a'); // => "169\n" | the sum of there decimal value (72 + 97)
        Unbelievable.main(new String[0]);
        System.out.println();


        // second part
        System.out.printf("%-8s | %-13s | %-13s | %-15s | %-4s | %-13s | %-11s%n",
                "Amount", "Interest Rate", "First Payment", "Monthly Payment", "Term", "Last Payment", "Annual Rate");
        double firstExample = Math.round(AnnualInterestRate.calculate(10000, 0.0388, 136.14, 84) * 10000.0) / 100.0;
        System.out.printf("%-8s | %-13s | %-13s | %-15s | %-4s | %-13s | %-11s%n", 10000, 3.88, 0, 136.14, 84, 0, firstExample);
        double firstExampleIterate = Math.round(AnnualInterestRateIterate.calculate(10000, 0.0388, 136.14, 84) * 10000.0) / 100.0;
        System.out.printf("%-8s | %-13s | %-13s | %-15s | %-4s | %-13s | %-11s%n", 10000, 3.88, 0, 136.14, 84, 0, firstExampleIterate);
        double secondExample = Math.round(AnnualInterestRate.calculate(8815.11, 0.0295, 154, 89, 48, 5478.29) * 10000.0) / 100.0;
        System.out.printf("%-8s | %-13s | %-13s | %-15s | %-4s | %-13s | %-11s%n", 8815.11, 2.95, 154, 89, 48, 5478.29, secondExample);
        double secondExampleIterate = Math.round(AnnualInterestRateIterate.calculate(8815.11, 0.0295, 154, 89, 48, 5478.29) * 10000.0) / 100.0;
        System.out.printf("%-8s | %-13s | %-13s | %-15s | %-4s | %-13s | %-11s%n", 8815.11, 2.95, 154, 89, 48, 5478.29, secondExample);
        double thirdExamole = Math.round(AnnualInterestRate.calculate(30000, 0.04, 410.06, 84) * 10000.0) / 100.0;
        System.out.printf("%-8s | %-13s | %-13s | %-15s | %-4s | %-13s | %-11s%n", 30000, 4, 0, 410.06, 84, 0, thirdExamole);
        double thirdExamoleIterate = Math.round(AnnualInterestRate.calculate(30000, 0.04, 410.06, 84) * 10000.0) / 100.0;
        System.out.printf("%-8s | %-13s | %-13s | %-15s | %-4s | %-13s | %-11s%n", 30000, 4, 0, 410.06, 84, 0, thirdExamoleIterate);

    }
}
