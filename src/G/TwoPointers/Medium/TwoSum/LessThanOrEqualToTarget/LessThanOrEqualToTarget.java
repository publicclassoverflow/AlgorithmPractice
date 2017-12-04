package G.TwoPointers.Medium.TwoSum.LessThanOrEqualToTarget;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/two-sum-less-than-or-equal-to-target/
 *
 * Given an array of integers, find how many pairs in the array such that their
 * sum is less than or equal to a specific target number. Please return the
 * number of pairs.
 *
 * Example:
 * Given nums = [2, 7, 11, 15], target = 24.
 * Return 5.
 * 2 + 7 < 24
 * 2 + 11 < 24
 * 2 + 15 < 24
 * 7 + 11 < 24
 * 7 + 15 < 24
 */

public class LessThanOrEqualToTarget {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.twoSum5(
                new int[]{2, 7, 11, 15}, 24
        ));
    }
}

class Solution {
    /*
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum5(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        int count = 0;
        while (i < j) {
            if (nums[i] + nums[j] > target) {
                j--;
                continue;
            }
            count += j - i;
            i++;
        }
        return count;
    }
}
