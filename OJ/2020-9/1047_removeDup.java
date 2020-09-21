import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {
    public String removeDuplicates(String S) {
        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        for (int i = 0; i < S.length(); i++) {
            if (stack.isEmpty() || stack.peek() != S.charAt(i)) {
                stack.push(S.charAt(i));
            } else {
                while (!stack.isEmpty() && stack.peek() == S.charAt(i)) {
                    stack.pop();
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.append(stack.removeLast());
        }

        return ans.toString();
    }
}