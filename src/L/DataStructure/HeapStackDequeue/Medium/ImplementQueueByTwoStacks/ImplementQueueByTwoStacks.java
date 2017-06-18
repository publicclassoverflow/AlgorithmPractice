package L.DataStructure.HeapStackDequeue.Medium.ImplementQueueByTwoStacks;

import java.util.Stack;

/**
 * http://www.lintcode.com/en/problem/implement-queue-by-two-stacks/
 *
 * As the title described, you should only use two stacks to implement a
 * queue's actions.
 * The queue should support push(element), pop() and top() where pop is pop
 * the first(a.k.a front) element in the queue.
 * Both pop and top methods should return the value of first element.
 *
 * Example:
 * push(1)
 * pop()     // return 1
 * push(2)
 * push(3)
 * top()     // return 2
 * pop()     // return 2
 */

public class ImplementQueueByTwoStacks {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        System.out.println(queue.pop());    // 1
        queue.push(2);
        queue.push(3);
        System.out.println(queue.top());    // 2
        System.out.println(queue.pop());    // 2
    }
}

class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public MyQueue() {
        // do initialization if necessary
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    private void transfer() {
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    public void push(int element) {
        // write your code here
        stack2.push(element);
    }

    public int pop() {
        // write your code here
        if (stack1.isEmpty()) {
            transfer();
        }
        return stack1.pop();
    }

    public int top() {
        // write your code here
        if (stack1.isEmpty()) {
            transfer();
        }
        return stack1.peek();
    }
}

