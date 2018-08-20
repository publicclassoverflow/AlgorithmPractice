package O.String.Medium.LongestSubstringWithoutRepeatingCharacters;

import java.util.*;

/**
 * Description
 * Given a string, find the longest substring without any repeating characters and return the length of it. The input string is guaranteed to be not null.
 *
 * For example, the longest substring without repeating letters for "bcdfbd" is "bcdf", we should return 4 in this case.
 **/

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longest("bcdfbd")); // 4
        System.out.println(solution.longest("efhrgsayekasdanfev")); // 9
    }
}

class Solution {
    public int longest(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        int maxLen = 0;
        Set<Character> seen = new HashSet<>();
        while (fast < input.length()) {
            if (!seen.contains(input.charAt(fast))) {
                // If this character has not appeared before
                // 1. add it to the hashSet
                // 2. increment the fast pointer
                // 3. update maxLen
                seen.add(input.charAt(fast));
                fast++;
                if (maxLen < fast - slow) {
                    maxLen = fast - slow;
                }
            } else {
                // If this character has appeared before
                // 1. remove the character that slow points to from the hashSet
                // 2. move the slow pointer down by 1
                seen.remove(input.charAt(slow));
                slow++;
            }
        }
        return maxLen;
    }
}

