package Z.Strings.Hard.RemoveAdjacentRepeatedCharactersIV;

/**
 * Repeatedly remove all adjacent, repeated characters in a given string from left to right.
 *
 * No adjacent characters should be identified in the final string.
 *
 * Examples
 *
 * "abbbaaccz" → "aaaccz" → "ccz" → "z"
 * "aabccdc" → "bccdc" → "bdc"
 * */

import java.util.*;

public class RemoveAdjacentRepeatedCharactersIV {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.deDup("aaaabbbc"));
        System.out.println(solution.deDup("eabcdddcbba"));
        System.out.println(solution.deDup(""));
        System.out.println(solution.deDup("a"));
        System.out.println(solution.deDup(null));
    }
}

class Solution {
    public String deDup(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }
        char[] letters = input.toCharArray();
        String result = null;
        // Method 1: Stack
        // Iterate over the array, push the character that is different from
        // the top of stack to the stack
        // Ignore the consecutive repeated characters and pop the stack
//        result = deDupWithStack(letters);

        // Method 2: Stack simulated using the left side of the array in-place
        // We can definitely use a linked list or array deque to do this
        // But we can also simulate a stack with the help of a barrier/pointer
        // Variable end is where the top of the stack is
        result = deDupWithSimulatedStack(letters);
        return result;
    }

    private String deDupWithStack(char[] letters) {
        Deque<Character> stack = new ArrayDeque<>();
        stack.offerFirst(letters[0]);
        for (int i = 1; i < letters.length; i++) {
            if (stack.isEmpty() || letters[i] != stack.peekFirst()) {
                stack.offerFirst(letters[i]);
            } else {
                while (i < letters.length - 1 && letters[i] == letters[i + 1]) {
                    i++;
                }
                stack.pollFirst();
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pollFirst());
        }
        return result.toString();
    }

    private String deDupWithSimulatedStack(char[] letters) {
        int end = 0;
        for (int i = 1; i < letters.length; i++) {
            // Case 1: when the stack is empty (end == -1) or the adjacent letters
            // are the same, we move the fast
            // pointer. This simulates the push() operation
            if (end == -1 || letters[i] != letters[end]) {
                end++;
                letters[end] = letters[i];
                // letters[++end] = letters[i];
            } else {
                // Case 2.1: when the stack is not empty and the adjacent letters
                // are not different, we need to pop the stack
                end--;
                // Case 2.2: ignore all the adjacent letters that are the same
                while (i < letters.length - 1 && letters[i] == letters[i + 1]) {
                    i++;
                }
            }
        }
        return new String(letters, 0, end + 1);
    }
}
