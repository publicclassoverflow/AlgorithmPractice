package S.AdvancedPractice.I.Medium.LargestAndSmallest;

import java.util.Arrays;

/**
 * Description
 * Use the least number of comparisons to get the largest and smallest number in the given integer array. Return the largest number and the smallest number.
 *
 * Assumptions
 *
 * The given array is not null and has length of at least 1
 * Examples
 *
 * {2, 1, 5, 4, 3}, the largest number is 5 and smallest number is 1. return [5, 1].
 *
 * Medium
 * Array
 */

public class LargestAndSmallest {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.largestAndSmallest(new int[] {
                2, 1, 5, 4, 3
        })));
        System.out.println(Arrays.toString(solution.largestAndSmallest(new int[] {
                2, 5, 1, 4, 3
        })));
        System.out.println(Arrays.toString(solution.largestAndSmallest(new int[] {
                1
        })));
        System.out.println(Arrays.toString(solution.largestAndSmallest(new int[] {
                1, 2
        })));
        System.out.println(Arrays.toString(solution.largestAndSmallest(new int[] {
                2, 1
        })));
    }
}

class Solution {
    public int[] largestAndSmallest(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return new int[] {};
        }
        int n = array.length;
        // Step 1: compare index pairs
        // Compare array[i] with array[n - 1 - i]
        // 0 vs end, 1 vs end - 1, 2 vs end - 2...
        // put all the smaller values to the left hand side
        // put all the larger values to the right hand side
        // n / 2 comparisons
        for (int i = 0; i < n / 2; i++) {
            if (array[i] > array[n - 1 - i]) {
                int temp = array[i];
                array[i] = array[n - 1 - i];
                array[n - 1 - i] = temp;
            }
        }
        // Step 2:
        // Find the smallest element in the left hand side
        // n / 2 comparisons
        int min = Integer.MAX_VALUE;
        for (int j = 0; j <= n / 2; j++) {
            if (array[j] < min) {
                min = array[j];
            }
        }
        // Step 3:
        // Find the largest element in the right hand side
        int max = Integer.MIN_VALUE;
        for (int k = n / 2; k < n; k++) {
            if (array[k] > max) {
                max = array[k];
            }
        }
        return new int[] {max, min};
    }
}
