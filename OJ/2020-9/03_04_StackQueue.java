import java.util.Stack;


/**
* -*- coding : utf-8 -*-
* @FileName  : 03_04_StackQueue.java
* @Author    : Ruixiang JIANG (Songrise)
* @Time      : 2020-09-16
* @Github    ：https://github.com/songrise
* @Descriptions: None
**/

class MyQueue {

    Stack<Integer> s1 = null;
    Stack<Integer> s2 = null;

    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        // Integer num = new Integer.valueOf(x);
        s1.push(x);

    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        Integer ans = s2.pop();
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return ans;

    }

    /** Get the front element. */
    public int peek() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        Integer ans = s2.peek();
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return ans;

    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such: MyQueue obj =
 * new MyQueue(); obj.push(x); int param_2 = obj.pop(); int param_3 =
 * obj.peek(); boolean param_4 = obj.empty();
 */