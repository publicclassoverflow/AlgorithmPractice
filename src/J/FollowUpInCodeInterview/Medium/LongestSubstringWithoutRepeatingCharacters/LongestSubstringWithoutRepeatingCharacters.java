package J.FollowUpInCodeInterview.Medium.LongestSubstringWithoutRepeatingCharacters;

import java.util.HashSet;

/**
 * http://www.lintcode.com/en/problem/longest-substring-without-repeating-characters/
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example
 * For example, the longest substring without repeating letters for "abcabcbb"
 * is "abc", which the length is 3.
 * For "bbbbb" the longest substring is "b", with the length of 1.
 */

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] arg = new String[]{"abcabcbb", "bbbbb", "", null, "12314"};
        for (String str : arg) {
            System.out.format(
                    "The longest substring without repeating characters for " +
                    "%s is: %s%n", str, solution.lengthOfLongestSubstring(str)
            );
        }
    }
}

class Solution {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        int j = 0;
        int result = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            // while (!set.isEmpty() && j < s.length()) {
            while (j < s.length()) {
                if (set.contains(s.charAt(j))) {
                    break;
                }
                set.add(s.charAt(j));
                result = Math.max(result, j - i + 1);
                j++;
            }
            // When char at j is reached and the while-loop hits the break,
            // the char at i in the set needs to be removed such that this
            // process carries on from the next character
            set.remove(s.charAt(i));
        }
        return result;
    }
}
