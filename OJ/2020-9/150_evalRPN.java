import java.util.ArrayDeque;

/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : 150_evalRPN.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-09-18
 * @Github ：https://github.com/songrise
 * @Descriptions: None
 **/

class Solution {

    static boolean isOp(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    public int evalRPN(String[] tokens) {

        Deque<String> stack = new ArrayDeque<String>();
        for (String s : tokens) {
            if (isOp(s)) {
                int operand2 = Integer.parseInt(stack.pop());
                int operand1 = Integer.parseInt(stack.pop());
                switch (s) {
                    case "+":
                        stack.push(String.valueOf(operand1 + operand2));
                        break;
                    case "-":
                        stack.push(String.valueOf(operand1 - operand2));
                        break;
                    case "*":
                        stack.push(String.valueOf(operand1 * operand2));
                        break;
                    case "/":
                        stack.push(String.valueOf(operand1 / operand2));
                        break;
                    default:
                        break;
                }
            } else {
                stack.push(s);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}