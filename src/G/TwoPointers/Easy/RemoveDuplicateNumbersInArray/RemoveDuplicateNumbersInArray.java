package G.TwoPointers.Easy.RemoveDuplicateNumbersInArray;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * http://www.lintcode.com/en/problem/remove-duplicate-numbers-in-array/
 *
 * Given an array of integers, remove the duplicate numbers in it.
 * You should:
 * 1. Do it in place in the array.
 * 2. Move the unique numbers to the front of the array.
 * 3. Return the total number of the unique numbers.
 *
 * Notice:
 * You don't need to keep the original order of the integers.
 *
 * Example:
 * Given nums = [1,3,1,4,4,2], you should:
 * 1. Move duplicate integers to the tail of nums => nums = [1,3,4,2,?,?].
 * 2. Return the number of unique integers in nums => 4.
 * Actually we don't care about what you place in ?, we only care about the part
 * which has no duplicate integers.
 *
 * Challenge:
 * 1. Do it in O(n) time complexity
 * 2. Do it in O(nlogn) time without extra space
 */

public class RemoveDuplicateNumbersInArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 0
        int[] zero = new int[]{1, 3, 1, 4, 4, 2};
        runTest(zero, 0, solution);
        // Test 1
        int[] one = new int[]{1, 1, 1, 1, 1};
        runTest(one, 1, solution);
        // Test 2
        runTest(new int[]{0}, 2, solution);
        // Test 3
        runTest(new int[]{}, 3, solution);
        // Test 4
        runTest(null, 4, solution);
    }

    private static void runTest(int[] nums, int testNum, Solution solution) {
        System.out.format("Test %d%n", testNum);
        System.out.format("The original array is: %s%n", Arrays.toString(nums));
        System.out.format(
                "The number of unique integer is: %d%n",
                solution.deduplication(nums)
        );
        System.out.format("The array afterwards is: %s%n", Arrays.toString(nums));
    }
}

class Solution {
    /*
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int deduplication(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        /* O(n) time and O(n) space */
        Set<Integer> set = new HashSet<>();
        int count = nums.length;
        int i = 0;
        int j = nums.length - 1;
        while (i < nums.length && j >= 0 && i <= j) {
            int num = nums[i];
            if (!set.contains(num)) {
                set.add(num);
                i++;
                continue;
            }
            // Swap: move the duplicate element towards the end of the array
            nums[i] = nums[j];
            nums[j--] = num;
            count--;
        }
        return count;
    }
}
