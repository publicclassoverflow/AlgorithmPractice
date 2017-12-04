package G.TwoPointers.Medium.TwoSum.UniquqPairs;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/two-sum-unique-pairs/
 * Given an array of integers, find how many unique pairs in the array such that
 * their sum is equal to a specific target number. Please return the number of
 * pairs.
 *
 * Example:
 * Given nums = [1,1,2,45,46,46], target = 47
 * return 2
 * 1 + 46 = 47
 * 2 + 45 = 47
 */

public class UniquePairs {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 0
        int[] zero = new int[]{1, 1, 2, 45, 46, 46};
        int target0 = 47;
        System.out.println(solution.twoSum6(zero, target0));    // 2
        // Test 1
        // The reason why I test this array is that I made a mistake on line 62
        // where I had num[i] + num[i] and it caused me problems...
        int[] one = new int[]{
                1, 2, 5, 6, 7, 3, 5, 8, -33, -5, -72, 12, -34, 100, 99
        };
        int target1 = -64;
        System.out.println(solution.twoSum6(one, target1));     // 1
    }
}

class Solution {
    /*
     * @param nums: an array of integer
     * @param target: An integer
     * @return: An integer
     */
    public int twoSum6(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        int count = 0;
        while (i < j) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                i++;
                continue;
            }
            if (j < nums.length - 1 && nums[j] == nums[j + 1]) {
                j--;
                continue;
            }
            if (nums[i] + nums[j] < target) {
                i++;
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else {
                i++;
                j--;
                count++;
            }
        }
        return count;
    }
}
