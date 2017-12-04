package G.TwoPointers.Medium.TwoSum.InputArrayIsSorted;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/two-sum-input-array-is-sorted/
 *
 * Given an array of integers that is already sorted in ascending order, find
 * two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are not zero-based.
 *
 * Notice:
 * You may assume that each input would have exactly one solution.
 *
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9
 * return [1, 2]
 */

public class InputArrayIsSorted {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 0
        int[] zero = new int[]{2, 7, 11, 15};
        int tZero = 9;
        System.out.println(Arrays.toString(solution.twoSum(zero, tZero)));
    }
}

class Solution {
    /*
     * @param nums: an array of Integer
     * @param target: target = nums[index1] + nums[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] nums, int target) {
        // write your code here
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result;
        }
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            if (nums[i] + nums[j] < target) {
                i++;
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else {
                result[0] = i + 1;
                result[1] = j + 1;
                break;
            }
        }
        return result;
    }
}
