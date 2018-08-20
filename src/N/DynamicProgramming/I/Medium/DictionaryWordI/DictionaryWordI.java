package N.DynamicProgramming.I.Medium.DictionaryWordI;

import java.util.*;

/**
 * Description
 * Given a word and a dictionary, determine if it can be composed by concatenating words from the given dictionary.
 *
 * Assumptions
 *
 * The given word is not null and is not empty
 * The given dictionary is not null and is not empty and all the words in the dictionary are not null or empty
 * Examples
 *
 * Dictionary: {“bob”, “cat”, “rob”}
 *
 * Word: “robob” return false
 *
 * Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"
 */

public class DictionaryWordI {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canBreak("robob", new String[] {"rob", "cat", "bob"}));
        System.out.println(solution.canBreak("bobcatrob", new String[] {"rob", "cat", "bob"}));
        System.out.println(solution.canBreak("abcdddef", new String[] {"abc", "ab", "cd", "de", "def"}));
    }
}

class Solution {
    public boolean canBreak(String input, String[] dict) {
        // Write your solution here
        if (input == null || dict == null || dict.length == 0) {
            return false;
        }
        Set<String> dictionary = new HashSet<>(Arrays.asList(dict));
        boolean[] breakable = new boolean[input.length() + 1];
        // breakable[i] represents index i - 1 in the input
        breakable[0] = true;
        for (int i = 1; i < breakable.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dictionary.contains(input.substring(j, i)) && breakable[j]) {
                    breakable[i] = true;
                    break;
                }
            }
        }
        return breakable[breakable.length - 1];
    }
}
