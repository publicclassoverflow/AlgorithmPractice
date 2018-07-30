package Z.Strings.Easy.RemoveCertainCharacters;

import java.util.HashSet;
import java.util.Set;

/**
 * Description
 * Remove given characters in input string, the relative order of other
 * characters should be remained. Return the new string after deletion.
 *
 * Assumptions
 *
 * The given input string is not null.
 * The characters to be removed is given by another string, it is guranteed to be not null.
 *
 * Examples
 *
 * input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd".
 **/

public class RemoveCertainCharacters {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.remove("abcd", "ab"));
        System.out.println(solution.remove("acbd", "ab"));
        System.out.println(solution.remove("acd", "ab"));
        System.out.println(solution.remove("acd", ""));
    }
}

class Solution {
    public String remove(String input, String t) {
        // Write your solution here
        if (input == null || input.isEmpty()) {
            return input;
        }
        // Use a set to store the chars in t for quick lookup
        Set<Character> target = new HashSet<>();
        for (int i = 0; i < t.length(); i++) {
            target.add(t.charAt(i));
        }
        // Two pointers/barriers, three sections
        // Section 1: 0 --> slow - 1: chars to be returned
        // Section 2: slow --> fast - 1: chars that are useless
        // Section 3: fast --> end - 1: chars to be checked
        int slow = 0;
        int fast = 0;
        char[] toCheck = input.toCharArray();
        while (fast < toCheck.length) {
            if (!target.contains(toCheck[fast])) {
                // We should keep this char to return
                toCheck[slow] = toCheck[fast];
                slow++;
            }
            // We skip the chars to be removed
            fast++;
        }
        // toCheck[0, slow - 1] is the range that we care about
        return new String(toCheck, 0, slow);
    }
}
