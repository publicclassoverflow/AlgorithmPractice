package N.DynamicProgramming.I.Easy.LongestIncreasingContinuousSubsequence;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/longest-increasing-continuous-subsequence/
 *
 * Give an integer array，find the longest increasing continuous subsequence in
 * this array.
 * An increasing continuous subsequence:
 * Can be from right to left or from left to right.
 * Indices of the integers in the subsequence should be continuous.
 *
 * Notice:
 * O(n) time and O(1) extra space.
 *
 * Example:
 * For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.
 * For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.
 */

public class LongestIncreasingContinuousSubsequence {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] tests = new int[][]{
                {5, 4, 2, 1, 3},    // 4
                {5, 1, 2, 3, 4},    // 4
                {99,55,7,29,80,33,19,23,6,35,40,27,44,74,5,17,52,36,67,32,37,42,
                    18,77,66,62,97,79,60,94,30,2,85,22,26,91,3,16,8,0,48,93,39,
                    31,63,13,71,58,69,50,21,70,61,43,12,88,47,45,72,76},    // 4
                {},     // 0
                null    // 0
        };
        for (int[] A : tests) {
            System.out.format(
                    "In array %s:%nThe length of the longest increasing " +
                            "continuous subsequence is %d%n",
                    Arrays.toString(A),
                    solution.longestIncreasingContinuousSubsequence(A)
            );
        }
    }
}

class Solution {
    /**
     * @param A an array of Integer
     * @return an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        // Write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        // Method #1: O(n) time and O(3) memory
        /*
        int left = 1;
        int right = A.length - 1;
        int leftLength = 1;
        int rightLength = 1;
        int maxLength = 1;
        while (left < A.length || right > 0) {
            // If left is out of bound
            if (left == A.length) {
                if (A[right] < A[right - 1]) {
                    rightLength++;
                    if (rightLength > maxLength) {
                        maxLength = rightLength;
                    }
                } else {
                    rightLength = 1;
                }
                right--;
                continue;
            }
            // If right is out of bound. It is actually right - 1 out of bounds
            if (right == 0) {
                if (A[left] > A[left - 1]) {
                    leftLength++;
                    if (leftLength > maxLength) {
                        maxLength = leftLength;
                    }
                } else {
                    leftLength = 1;
                }
                left++;
                continue;
            }
            // If both pointers are in bound
            if (A[left] > A[left - 1]) {
                leftLength++;
                if (leftLength > maxLength) {
                    maxLength = leftLength;
                }
            } else {
                leftLength = 1;
            }
            if (A[right] < A[right - 1]) {
                rightLength++;
                if (rightLength > maxLength) {
                    maxLength = rightLength;
                }
            } else {
                rightLength = 1;
            }
            left++;
            right--;
        }
        return maxLength;
        */

        // Method #2: O(2n) time and O(2) memory
        int length = 1;
        int maxLength = 1;
        // Left to right
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                length++;
                maxLength = Math.max(maxLength, length);
            } else {
                length = 1;
            }
        }
        // Right to left
        length = 1;
        for (int j = A.length - 1; j > 0; j--) {
            if (A[j] < A[j - 1]) {
                length++;
                maxLength = Math.max(maxLength, length);
            } else {
                length = 1;
            }
        }
        return maxLength;
    }
}
