package G.TwoPointers.Easy.MoveZeros;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/move-zeroes/
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 *
 * Notice:
 * 1. You must do this in-place without making a copy of the array.
 * 2. Minimize the total number of operations.
 *
 * Example:
 * Given nums = [0, 1, 0, 3, 12], after calling your function,
 * nums should be [1, 3, 12, 0, 0].
 */

public class MoveZeros {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 1
        int[] one = new int[]{0, 1, 0, 3, 12};
        System.out.println(Arrays.toString(one));
        solution.moveZeroes(one);
        System.out.println(Arrays.toString(one));
        // Test 2
        int[] two = new int[]{};
        System.out.println(Arrays.toString(two));
        solution.moveZeroes(two);
        System.out.println(Arrays.toString(two));
    }
}

class Solution {
    /**
     * @param nums an integer array
     * @return nothing, do this in-place
     */
    public void moveZeroes(int[] nums) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return;
        }
        int ptr = 0;
        for (int n : nums) {
            if (n != 0) {
                nums[ptr++] = n;
            }
        }
        while (ptr < nums.length) {
            nums[ptr++] = 0;
        }
    }
}
