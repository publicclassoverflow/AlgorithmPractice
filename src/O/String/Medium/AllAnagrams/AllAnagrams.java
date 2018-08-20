package O.String.Medium.AllAnagrams;

/**
 * Description
 * Find all occurrence of anagrams of a given string s in a given string l. Return the list of starting indices.
 *
 * Assumptions
 *
 * s is not null or empty.
 * l is not null.
 * Examples
 *
 * l = "abcbac", s = "ab", return [0, 3] since the substring with length 2 starting from index 0/3 are all anagrams of "ab" ("ab", "ba").
 */

import java.util.*;

public class AllAnagrams {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.allAnagrams("ab", "abcbac"));       // [0, 3]
        System.out.println(solution.allAnagrams("aab", "ababacbbaac")); // [0, 2, 7]
    }
}

class Solution {
    public List<Integer> allAnagrams(String sh, String lo) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        if (lo == null || lo.length() == 0) {
            return result;
        }
        char[] array = lo.toCharArray();
        Map<Character, Integer> shFreq = new HashMap<>();
        for (char ch : sh.toCharArray()) {
            shFreq.put(ch, shFreq.getOrDefault(ch, 0) + 1);
        }
        // Iterate over the char array of lo
        // check substrings of length sh.length()
        for (int i = 0; i <= lo.length() - sh.length(); i++) {
            findAnagrams(array, i, sh.length(), shFreq, result);
        }
        return result;
    }

    private void findAnagrams(char[] input, int index, int charCount,
                              Map<Character, Integer> shFreq,
                              List<Integer> result) {
        Map<Character, Integer> loFreq = new HashMap<>();
        for (int i = 0; i < charCount; i++) {
            loFreq.put(input[index + i],
                    loFreq.getOrDefault(input[index + i], 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : shFreq.entrySet()) {
            if (!entry.getValue().equals(loFreq.getOrDefault(entry.getKey(), 0))) {
                // entries in shFreq all have values >= 1
                // So we can use the getOrDefault method to check the keys from
                // shFreq in loFreq safely
                return;
            }
        }
        result.add(index);
    }
}

