package L2_Medium.Evaluate_Reverse_Polish_Notation;

import java.util.Stack;

/**
 * Tue, 25 May 2021, 12:42 AM
 * Description:
 **/
public class Evaluate_Reverse_Polish_Notation {
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            int first, second;
            switch (s) {
                case "+" -> {
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first + second);
                }
                case "-" -> {
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first - second);
                }
                case "*" -> {
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first * second);
                }
                case "/" -> {
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first / second);
                }
                default -> stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String[] tokens = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(evalRPN(tokens));
    }
}
