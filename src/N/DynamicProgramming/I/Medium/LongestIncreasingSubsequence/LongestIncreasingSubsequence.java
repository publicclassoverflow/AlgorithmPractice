package N.DynamicProgramming.I.Medium.LongestIncreasingSubsequence;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/longest-increasing-subsequence/
 *
 * Given a sequence of integers, find the longest increasing subsequence (LIS).
 * You code should return the length of the LIS.
 *
 * Clarification:
 * What's the definition of longest increasing subsequence?
 * 1. The longest increasing subsequence problem is to find a subsequence of a
 *    given sequence in which the subsequence's elements are in sorted order,
 *    lowest to highest, and in which the subsequence is as long as possible.
 *    This subsequence is not necessarily contiguous, or unique.
 * 2. https://en.wikipedia.org/wiki/Longest_increasing_subsequence
 *
 * Example:
 * For [5, 4, 1, 2, 3], the LIS is [1, 2, 3], return 3
 * For [4, 2, 4, 5, 3, 7], the LIS is [2, 4, 5, 7], return 4
 */

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] test = new int[][]{
                {5, 4, 1, 2, 3},            // [1, 2, 3]: 3
                {4, 2, 4, 5, 3, 7},         // [2, 4, 5, 7]: 4
                {4, 2, 5, 4, 3, 9, 8, 10},  // [2, 4, 8, 10]: 4
                {0},                        // 1
                {},                         // 0
                null                        // 0
        };
        for (int[] nums : test) {
            System.out.format(
                    "In array %s:%n" +
                    "The length of the longest increasing subsequence is: %d%n",
                    Arrays.toString(nums),
                    solution.longestIncreasingSubsequence(nums)
            );
        }
    }
}

class Solution {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] seq = new int[nums.length];
        int longest = 0;

        for (int i = 0; i < nums.length; i++) {
            seq[i] = 1; // Initialization
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (seq[i] > seq[j] + 1) {
                        continue;
                    }
                    seq[i] = seq[j] + 1;
                }
            }
            if (seq[i] > longest) {
                longest = seq[i];
            }
        }
        return longest;
    }
}
