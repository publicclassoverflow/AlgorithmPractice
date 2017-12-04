package D.BreadthFirstSearch.Medium.RemoveSubstrings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * http://www.lintcode.com/en/problem/remove-substrings/
 * Given a string s and a set of n substrings. You are supposed to remove every
 * instance of those n substrings from s so that s is of the minimum length and
 * output this minimum length.
 *
 * Example:
 * Given s = ccdaabcdbb, substrs = ["ab", "cd"]
 * Return 2
 * Explanation:
 * ccdaabcdbb -> ccdacdbb -> cabb -> cb (length = 2)
 */

public class RemoveSubstrings {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minLength(
                "ccdaabcdbb",
                new HashSet<String>(Arrays.asList("ab", "cd"))
        ));
    }
}

class Solution {
    /*
     * @param s: a string
     * @param dict: a set of n substrings
     * @return: the minimum length
     */
    public int minLength(String s, Set<String> dict) {
        // write your code here
        if (s == null) {
            return -1;
        }
        if (dict == null || dict.size() == 0) {
            return s.length();
        }
        // int strLen = s.length();
        for (String sub : dict) {
//            int strLen = s.length();
            int subLen = sub.length();
            int i = 0;
            while (i < s.length() - subLen + 1) {
                String current = s.substring(i, i + subLen);
//                if (!s.substring(i, i + subLen).equals(sub)) {
                if (!current.equals(sub)) {
                    i++;
                    continue;
                }
                if (i + subLen >= s.length()) {
                    String after = s.substring(0, i);
//                    s = s.substring(0, i);
                    s = after;
                    i--;
                    continue;
                }
                String first = s.substring(0, i);
                String second = s.substring(i + subLen, s.length());
//                s = s.substring(0, i) + s.substring(i + subLen, strLen);
                s = first + second;
                i--;
            }
        }
        return s.length();
    }
}
