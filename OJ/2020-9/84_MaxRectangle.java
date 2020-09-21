import java.util.ArrayDeque;

class Solution {

    public int largestRectangleArea(int[] heights) {
        var stack = new ArrayDeque<Integer>();

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
                if(i ！= heights.length-1){
                    continue;
                }
            }
                var queue = stack.clone();
                int maxCurArea = 0;
                while (!queue.isEmpty()) {
                    int area = heights[queue.peekLast()] * (queue.peek() - queue.peekLast() + 1);
                    maxArea = Math.max(area, maxArea);
                    queue.pollLast();
                }
                while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                    stack.pop();
                }
                stack.push(i);

            
            System.out.println(stack);
        }
        return maxArea;
    }
}