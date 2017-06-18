package L.DataStructure.HeapStackDequeue.Medium.ExpressionExpand;

import java.util.Stack;

/**
 * http://www.lintcode.com/en/problem/expression-expand/
 *
 * Given an expression s includes numbers, letters and brackets. Number
 * represents the number of repetitions inside the brackets(can be a string or
 * another expression)．Please expand expression to be a string.
 *
 * Example:
 * s = abc3[a] return abcaaa
 * s = 3[abc] return abcabcabc
 * s = 4[ac]dy, return acacacacdy
 * s = 3[2[ad]3[pf]]xyz, return adadpfpfpfadadpfpfpfadadpfpfpfxyz
 *
 * Challenge:
 * Can you do it without recursion?
 */

public class ExpressionExpand {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] str = new String[]{
                "abc3[a]", "3[abc]", "4[ac]dy", "3[2[ad]3[pf]]xyz", null, ""
        };
        for (String s : str) {
            System.out.format(
                    "\"%s\": \"%s\"%n",
                    s, solution.expressionExpand(s)
            );
        }
    }
}

class Solution {
    /**
     * @param s  an expression includes numbers, letters and brackets
     * @return a string
     */
    public String expressionExpand(String s) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return "";
        }

        Stack<Object> stack = new Stack<>();
        int num = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            } else if (ch == '[') {
                stack.push(Integer.valueOf(num));
                num = 0;
            } else if (ch == ']') {
                String newStr = popFromStack(stack);
                Integer count = (Integer) stack.pop();
                for (int i = 0; i < count; i++) {
                    stack.push(newStr);
                }
            } else {
                stack.push(String.valueOf(ch));
            }
        }
        return popFromStack(stack);
    }

    private String popFromStack(Stack<Object> stack) {
        Stack<String> buffer = new Stack<>();
        while (!stack.isEmpty() && (stack.peek() instanceof String)) {
            buffer.push((String) stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        while (!buffer.isEmpty()) {
            sb.append(buffer.pop());
        }
        return sb.toString();
    }
}
