package J.FollowUpInCodeInterview.Medium.LongestSubstringithAtMostKDistinctCharacters;

import java.util.HashMap;

/**
 * http://www.lintcode.com/en/problem/longest-substring-with-at-most-k-distinct-characters/
 * Given a string s, find the length of the longest substring T that contains
 * at most k distinct characters.
 *
 * Example
 * For example, Given s = "eceba", k = 3,
 * T is "eceb" which its length is 4.
 */

public class LongestSubstringWithAtMostKDistinctCharacters {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] test =
                new String[]{"eceba", "eceba", "eceba", "eceba", "eceba",
                             "aabbcd", "aabbcd", "", null};
        int[] k = new int[]{3, 2, 0, -1, 6, 3, 2, 1, 1};
        for (int i = 0; i < k.length; i++) {
            System.out.format(
                    "The length of the longest substring in \"%s\" with at " +
                    "most %d distinct characters is: %d%n",
                    test[i], k[i],
                    solution.lengthOfLongestSubstringKDistinct(test[i], k[i])
            );
        }
    }
}

class Solution {
    /**
     * @param s : A string
     * @return : The length of the longest substring
     * that contains at most k distinct characters.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        if (s == null || s.length() == 0 || k <=0) {
            return 0;
        }
        // Use a HashMap to keep track of the number of times that a character
        // has been seen
        HashMap<Character, Integer> hasSeen = new HashMap<>();
        int j = 0;
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j < s.length()) {
                char jChar = s.charAt(j);
                if (hasSeen.containsKey(jChar)) {
                    // Increment the appearance count of this character
                    hasSeen.replace(jChar, hasSeen.get(jChar) + 1);
                } else {
                    if (hasSeen.size() >= k) {
                        break;
                    }
                    // Create the appearance count entry for this character
                    hasSeen.put(jChar, 1);
                }
                length = Math.max(length, j - i + 1);
                j++;
            }
            // If the first character in the current substring has appeared more
            // than once, decrement the count value by 1.
            // Otherwise, delete the entry.
            char iChar = s.charAt(i);
            if (hasSeen.get(iChar) > 1) {
                hasSeen.replace(iChar, hasSeen.get(iChar) - 1);
            } else {
                hasSeen.remove(iChar);
            }
        }
        return length;
    }
}
