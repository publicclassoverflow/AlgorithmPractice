package G.TwoPointers.Medium.TwoSum.ClosestToTarget;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/two-sum-closest-to-target/
 * Given an array nums of n integers, find two integers in nums such that the
 * sum is closest to a given number, target.
 * Return the difference between the sum of the two integers and the target.
 *
 * Example:
 * Given array nums = [-1, 2, 1, -4], and target = 4.
 * The minimum difference is 1. (4 - (2 + 1) = 1).
 *
 * Challenge:
 * Do it in O(nlogn) time complexity.
 */

public class ClosestToTarget {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 0
        int[] zero = new int[]{-1, 2, 1, -4};
        System.out.println(solution.twoSumClosest(zero, 4));    // 1
        // Test 1
        int[] one = new int[]{0, 2, 1};
        System.out.println(solution.twoSumClosest(one, 5));     // 2
    }
}

class Solution {
    /*
     * @param nums: an integer array
     * @param target: An integer
     * @return: the difference between the sum and the target
     */
    public int twoSumClosest(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Arrays.sort(nums);  // O(nlogn)
        int i = 0;
        int j = nums.length - 1;
        int minDiff = Integer.MAX_VALUE;
        while (i < j) {     // O(n)
            if (nums[i] + nums[j] > target) {
                minDiff = Math.min(minDiff, nums[i] + nums[j] - target);
                j--;
            } else {
                minDiff = Math.min(minDiff, target - (nums[i] + nums[j]));
                i++;
            }
        }
        return minDiff;
    }
}
