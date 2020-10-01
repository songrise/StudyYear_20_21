import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : 20_bracketPair.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-09-15
 * @Github ：https://github.com/songrise
 * @Descriptions: Learn Java Collections
 **/

class Solution {
    public boolean isValid(String s) {
        int len = s.length();
        HashMap<Character, Character> pair = new HashMap<Character, Character>() {
            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }
        };
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (pair.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pair.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }


}
