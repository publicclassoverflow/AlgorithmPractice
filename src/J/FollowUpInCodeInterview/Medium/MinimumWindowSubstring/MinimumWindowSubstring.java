package J.FollowUpInCodeInterview.Medium.MinimumWindowSubstring;

import java.util.HashMap;
import java.util.Set;

/**
 * http://www.lintcode.com/en/problem/minimum-window-substring/
 * Given a string source and a string target, find the minimum window in source
 * which will contain all the characters in target.
 *
 * Notice:
 * If there is no such window in source that covers all characters in target,
 * return the emtpy string "".
 * If there are multiple such windows, you are guaranteed that there will always
 * be only one unique minimum window in source.
 *
 * Clarification:
 * Should the characters in the minimum window have the same order in target?
 * Not necessary
 *
 * Example:
 * For source = "ADOBECODEBANC", target = "ABC", the minimum window is "BANC"
 */

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] source = new String[]{"ADOBECODEBANC", "ADOBECODEBANC", "",
                                       null, "A", "A", "A"};
        String[] target = new String[]{"ABC", "ODO", "A", "A", "", null, "B"};
        for (int i = 0; i < source.length; i++) {
            System.out.format(
                    "The minimum window substring for target \"%s\" in " +
                            "source \"%s\" is: \"%s\"%n",
                    source[i], target[i],
                    solution.minWindow(source[i], target[i])
            );
        }
    }
}

class Solution {
    /**
     * @param source: A string
     * @param target: A string
     * @return: A string denote the minimum window
     *          Return "" if there is no such a string
     */
    public String minWindow(String source, String target) {
        // write your code
        if (source == null || source.length() == 0 ||
                target == null || target.length() == 0) {
            return "";
        }
        HashMap<Character, Integer> sourceMap = new HashMap<>();
        // Initialize targetMap: character -> appearances
        HashMap<Character, Integer> targetMap = new HashMap<>();
        /*
        for (char ch : target.toCharArray()) {
            // targetMap.put(ch, targetMap.getOrDefault(ch, 0) + 1); // Java 8
            if (!targetMap.containsKey(ch)) {
                targetMap.put(ch, 1);
            } else {
                targetMap.put(ch, targetMap.get(ch) + 1);
            }
        }
        */
        for (int i = 0; i < target.length(); i++) {
            char ch = target.charAt(i);
            if (!targetMap.containsKey(ch)) {
                targetMap.put(ch, 1);
            } else {
                targetMap.put(ch, targetMap.get(ch) + 1);
            }
        }
        int j = 0;
        String result = "";
        int length = Integer.MAX_VALUE;
        for (int i = 0; i < source.length(); i++) {
            while (j < source.length() && !contains(sourceMap, targetMap)) {
                char jChar = source.charAt(j);
                if (!sourceMap.containsKey(jChar)) {
                    sourceMap.put(jChar, 1);
                } else {
                    sourceMap.put(jChar, sourceMap.get(jChar) + 1);
                }
                j++;
            }
            if (contains(sourceMap, targetMap)) {
                length = Math.min(length, j - i);
                result = length < j - i ? result : source.substring(i, j);
            }
            char iChar = source.charAt(i);
            int iCount = sourceMap.get(iChar);
            if (iCount > 1) {
                sourceMap.put(iChar, iCount - 1);
            } else {
                sourceMap.remove(iChar);
            }
        }
        return result;
    }

    private boolean contains(HashMap<Character, Integer> source,
                             HashMap<Character, Integer> target) {
        Set<Character> keySet = target.keySet();
        for (char ch : keySet) {
            if (!source.containsKey(ch) || source.get(ch) < target.get(ch)) {
                return false;
            }
        }
        return true;
    }
}
