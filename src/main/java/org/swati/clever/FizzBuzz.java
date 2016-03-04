package org.swati.clever;

/**
 * Write a program that prints the numbers from 1 to 100.
 * But for multiples of three print “Fizz” instead of the number and for the multiples of five print “Buzz”.
 * For numbers which are multiples of both three and five print “FizzBuzz”.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class FizzBuzz {
    public static void printFizzBuzz(int number1, int number2) {
        int multiple = number1 * number2;
        for (int i = 1; i <= 100; i++) {
            if (i % multiple == 0) {
                System.out.println("FizzBuzz");
            } else if (i % number1 == 0) {
                System.out.println("Fizz");
            } else if (i % number2 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }

    public static void main(String args[]) {
        printFizzBuzz(3, 5);
    }
}
