package org.swati.problemSolving;

import java.util.Stack;

import org.swati.problemSolving.exceptions.InvalidExpressionException;

/**
 * Reverse Polish notation
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class ReversePolishNotation {
    /** Compute the value of an expression in Reverse Polish Notation. Supported operators are "+", "-", "*" and "/".
     * Reverse Polish is a postfix mathematical notation in which each operator immediately follows its operands.
     * Each operand may be a number or another expression.
     * For example, 3 + 4 in Reverse Polish is 3 4 + and 2 * (4 + 1) would be written as 4 1 + 2 * or 2 4 1 + *
     *
     * @param ops a sequence of numbers and operators, in Reverse Polish Notation
     * @return the result of the computation
     * @throws IllegalArgumentException ops don't represent a well-formed RPN expression
     * @throws ArithmeticException the computation generates an arithmetic error, such as dividing by zero
     *
     * <p>Some sample ops and their results:
     * ["4", "1", "+", "2.5", "*"] -> ((4 + 1) * 2.5) -> 12.5
     * ["5", "80", "40", "/", "+"] -> (5 + (80 / 40)) -> 7
     */

    public double getResult(String[] ops) throws InvalidExpressionException {
        Stack<Double> stack = new Stack<Double>();
        for (String val : ops) {
            try {
                Double operand = Double.parseDouble(val);
                stack.push(operand);
            } catch (NumberFormatException n) {
                //string is not an operand, must be an operator
                //get the first 2 operands from the stack
                if (stack.size() >= 2) {
                    Double operand1 = stack.pop();
                    Double operand2 = stack.pop();
                    Double result = null;
                    switch(val.charAt(0)) {
                        case '+' :
                            result = operand2 + operand1;
                            break;
                        case '-' :
                            result = operand2 - operand1;
                            break;
                        case '*' :
                            result = operand2 * operand1;
                            break;
                        case '/' :
                            result = operand2 / operand1;
                            break;
                        default :
                            throw new InvalidExpressionException("Unknown operand");
                    }
                    stack.push(result);
                } else {
                    throw new InvalidExpressionException("No operands found for the operator to work");
                }
            }
        }
        if (!stack.isEmpty()) {
            return stack.pop();
        } else {
            throw new InvalidExpressionException("invalid expression");
        }
    }

    public static void main(String[] args) {
        String[] ops = {"4", "1", "+", "2.5", "*"};
        String[] otherOps = {"5", "80", "40", "/", "+"};
        ReversePolishNotation rps = new ReversePolishNotation();
        try {
            System.out.println("Result of ops is " + rps.getResult(ops));
            System.out.println("Result otherOps is " + rps.getResult(otherOps));
        } catch (InvalidExpressionException e) {
            e.printStackTrace();
        }
    }
}
