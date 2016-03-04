package org.swati.clever;

/**
 * finding sum of all prime members from 1 to 2 million
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class PrimeNumberSum {

    private static int printSumPrimeBruteForce(int limit) {
        int sum = 0;
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }
        return sum;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int printPrimeUsingDyPrg(int limit) {
        boolean[] primeArr = new boolean[limit + 1];
        for (int i = 2; i <= limit; i++) {
            primeArr[i] = true;
        }

        for (int i = 2; i*i <= limit; i++) {
            if (primeArr[i]) {
                for (int j = i+i; j <= limit; j+=i) {
                    primeArr[j] = false;
                }
            }
        }

        int sum = 0;
        for (int i = 2; i <= limit; i++) {
            if (primeArr[i]) {
                sum += i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(printSumPrimeBruteForce(2000000));
        System.out.println(printPrimeUsingDyPrg(2000000));
    }
}
