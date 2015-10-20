package org.swati.euler.algorithms;

/**
 * Get the largest prime factor of a given number
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class PrimeFactor {

    public Long getLargestPrimeFactor(Long number) {
        Long i;
        Long workingNumber = number;

        for (i = 2L; i <= workingNumber; i++) {
            if (workingNumber % i == 0) {
                workingNumber = workingNumber/i;
                i--;
            }
        }
        return i;
    }

    public static void main(String args[]) {
        PrimeFactor pf = new PrimeFactor();
        Long number = 94L;
        System.out.println("The largest prime factor of " + number + " is " + pf.getLargestPrimeFactor(number));
    }
}
