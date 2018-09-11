package N.DynamicProgramming.III.Medium.LargestSubarraySum;

/**
 * Description
 * Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum.
 *
 * Assumptions
 *
 * The given array is not null and has length of at least 1.
 * Examples
 *
 * {2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5
 *
 * {-2, -1, -3}, the largest subarray sum is -1
 * Medium
 * Array
 */

public class LargestSubarraySum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largestSum(new int[] {2, -1, 4, -2, 1}));
        System.out.println(solution.largestSum(new int[] {-2, -1, -3}));
    }
}

class Solution {
    public int largestSum(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        // Use linear scan & look back at the previous record
        // Only use an integer variable to keep track of the
        // current sum
        int currentSum = array[0];
        int maxSum = array[0];
        for (int i = 1; i < array.length; i++) {
            currentSum = Math.max(currentSum + array[i], array[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
