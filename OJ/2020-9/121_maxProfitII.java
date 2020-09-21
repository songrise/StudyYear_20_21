import java.util.Deque;

class Solution {
    public int maxProfit(int[] prices) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int ans = 0;
        if (prices.length <= 1) {
            return ans;
        }

        for (int i : prices) {
            if (stack.isEmpty() || i > stack.peek()) {
                if (!stack.isEmpty()) {
                    ans = Math.max(i - stack.peekLast(), ans);
                }
                stack.push(i);
            } else {
                while (!stack.isEmpty() && stack.peekFirst() > i) {
                    stack.pop();
                }
                stack.push(i);
            }
        }
        return ans;
    }
}