package Z.Strings.Easy.RemoveAdjacentRepeatedCharactersI;

/*
 * Description
 * Remove adjacent, repeated characters in a given string, leaving only one
 * character for each group of such characters.
 *
 * Assumptions
 * Try to do it in place.
 *
 * Examples
 * “aaaabbbc” is transferred to “abc”
 *
 * Corner Cases
 * If the given string is null, we do not need to do anything.
 **/

import java.util.*;

public class RemoveAdjacentRepeatedCharactersI {
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
        // Method 1: slow and fast pointers
        // After the iteration, slow points to the first element that should
        // not be included in the result.
        // Everything to the left of slow should be returned
        // Use the String class constructor:
        // public String(char[] value, int offset, int count)
        int slow = 0;
        int fast = 1;
        while (fast < letters.length) {
            if (letters[slow] == letters[fast]) {
                fast++;
            } else {
                slow++;
                letters[slow] = letters[fast];
                fast++;
                // letters[++slow] = letters[fast++];
            }
        }
        return new String(letters, 0, slow + 1);
    }
}

