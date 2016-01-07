package org.swati.problemSolving;

import java.util.Stack;

/**
 * Evaluate Infix Expression
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class InfixExpressionEval {

    public int evaluate(String infixExpr) {
        Stack<Integer> values = new Stack<Integer>();
        Stack<Character> ops = new Stack<Character>();

        char[] input = infixExpr.replaceAll("\\s", "").toCharArray();
        for (int i = 0; i < input.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            if (isNumber(input[i])) {
                while (i < input.length && isNumber(input[i])) {
                    stringBuilder.append(input[i]);
                    i++;
                }
                i--;
                if (stringBuilder.toString().length() > 0) {
                    values.push(Integer.parseInt(stringBuilder.toString()));
                }
            } else if (input[i] == '(') {
                ops.push(input[i]);
            } else if (input[i] == ')') {
                while (ops.peek() != '(') {
                    values.push(calc(values.pop(), values.pop(), ops.pop()));
                }
                //remove the opening brace
                ops.pop();
            } else if (isOperator(input[i])) {
                while (!ops.isEmpty() && hasPrecedence(ops.peek(), input[i])) {
                    values.push(calc(values.pop(), values.pop(), ops.pop()));
                }
                ops.push(input[i]);
            }
        }

        while (!ops.isEmpty()) {
            values.push(calc(values.pop(), values.pop(), ops.pop()));
        }
        return values.pop();
    }

    private boolean isNumber(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    private Integer calc(Integer later, Integer earlier, Character op) {
        switch(op) {
            case '+': return earlier + later;
            case '-': return earlier - later;
            case '*': return earlier * later;
            case '/': return earlier / later;
        }
        return 0;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * return true is op1 has precedence over op2
     * @param op1 : first operator
     * @param op2 : second operator
     * @return true or false
     */
    private boolean hasPrecedence(char op1, char op2) {
        if (op1 == ')' || op1 == '(') {
            return false;
        }
        if ((op1 == '+' || op1 == '-') && (op2 == '/' || op2 == '*')) {
            return false;
        }
        return true;
    }

    public static void main(String args[]) {
        InfixExpressionEval infixExpressionEval = new InfixExpressionEval();
        String expr = "3 * 4 - 3 - 6 / 6";
        System.out.println("Expr is " + expr);
        System.out.println("Answer is " + infixExpressionEval.evaluate(expr));
    }
}
