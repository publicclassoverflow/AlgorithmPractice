package E.DepthFirstSearch.Medium.PalindromePartition;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/palindrome-partitioning/
 *
 * Given a string s, partition s such that every substring of the partition is
 * a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * Example:
 * Given s = "aab", return:
 * [
 *  ["aa","b"],
 *  ["a","a","b"]
 * ]
 *
 * */

public class PalindromePartition {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Given s = "aab"
        System.out.println(solution.partition("aab"));
    }
}

class Solution {
    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        // write your code here
        List<List<String>> result = new ArrayList<>();
        if (s == null) {
            return result;
        }
        partitionPalindrome(new ArrayList<>(), 0, s, result);
        return result;
    }

    private void partitionPalindrome(ArrayList<String> partitions,
                                     int startIndex,
                                     String s,
                                     List<List<String>> result) {
        if (startIndex == s.length()) {
            result.add(new ArrayList<>(partitions));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            String sub = s.substring(startIndex, i + 1);
            if (!isPalindrome(sub)) {
                continue;
            }
            partitions.add(sub);
            partitionPalindrome(partitions, i + 1, s, result);
            partitions.remove(partitions.size() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        int n = s.length() - 1;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(n - i)) {
                return false;
            }
        }
        return true;
    }
}
