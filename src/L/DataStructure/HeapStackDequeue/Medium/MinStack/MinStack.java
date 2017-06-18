package L.DataStructure.HeapStackDequeue.Medium.MinStack;

import java.util.Stack;

/**
 * http://www.lintcode.com/en/problem/min-stack/
 *
 * Implement a stack with min() function, which will return the smallest number
 * in the stack.
 * It should support push, pop and min operation all in O(1) cost.
 *
 * Example:
 * push(1)
 * pop()   // return 1
 * push(2)
 * push(3)
 * min()   // return 2
 * push(1)
 * min()   // return 1
 */

public class MinStack {
    public static void main(String[] args) {
        MinimumStack minStack = new MinimumStack();
        minStack.push(1);
        System.out.println(minStack.pop());     // 1
        minStack.push(2);
        minStack.push(3);
        System.out.println(minStack.min());     // 2
        minStack.push(1);
        System.out.println(minStack.min());     // 1
    }
}

// class MinStack {
class MinimumStack {
    // public MinStack() {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinimumStack() {
        // do initialize if necessary
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int number) {
        // write your code here
        stack.push(number);
        if (minStack.isEmpty()) {
            minStack.push(number);
        } else {
            minStack.push(Math.min(number, minStack.peek()));
        }
    }

    public int pop() {
        // write your code here
        minStack.pop();
        return stack.pop();
    }

    public int min() {
        // write your code here
        return minStack.peek();
    }
}

