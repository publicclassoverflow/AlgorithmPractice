package F.LinkedListAndArray.Easy.MaximumSubarray;

/**
 * http://www.lintcode.com/en/problem/maximum-subarray/
 *
 * Given an array of integers, find a contiguous subarray which has the largest
 * sum.
 *
 * Notice:
 * The subarray should contain at least one number.
 *
 * Example:
 * Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1]
 * has the largest sum = 6.
 *
 * Challenge:
 * Can you do it in time complexity O(n)?
 */

public class MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxSubArray(new int[]{
                -2, 2, -3, 4, -1, 2, 1, -5, 3
        }));    // 6
        System.out.println(solution.maxSubArray(new int[]{
                -2, 2, -1
        }));    // 2
        System.out.println(solution.maxSubArray(new int[]{
        }));    // 0
        System.out.println(solution.maxSubArray(new int[]{
                -3, 0
        }));    // 0
        System.out.println(solution.maxSubArray(null));
    }
}

class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // write your code
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int minSum = 0;
        for (int num : nums) {
            sum += num;
            // Find the "real" current max sum by subtracting the minimum prefix
            // sum from the "total" sum
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
        }
        return max;
    }
}
