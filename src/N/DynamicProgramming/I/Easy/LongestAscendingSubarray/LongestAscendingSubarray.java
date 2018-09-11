package N.DynamicProgramming.I.Easy.LongestAscendingSubarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description
 * Given an unsorted array, find the length of the longest subarray in which the numbers are in ascending order.
 *
 * Assumptions
 *
 * The given array is not null
 * Examples
 *
 * {7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.
 *
 * {1, 2, 3, 3, 4, 4, 5}, longest ascending subarray is {1, 2, 3}, length is 3.
 */

public class LongestAscendingSubarray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test
        System.out.println(solution.longest(new int[] {7, 2, 3, 1, 5, 8, 9, 6}));
        System.out.println(Arrays.toString(solution.longestSubarray(new int[]{7, 2, 3, 1, 5, 8, 9, 6})));
        // {1, 5, 8, 9} ==> 4
        System.out.println(solution.longest(new int[] {1, 2, 3, 3, 4, 4, 5}));
        System.out.println(Arrays.toString(solution.longestSubarray(new int[]{1, 2, 3, 3, 4, 4, 5})));
        // {1, 2, 3} ==> 3
        System.out.println(solution.longest(new int[] {1, 2, 2, 3, 3, 4, 5, 6, 6, 7}));
        System.out.println(Arrays.toString(solution.longestSubarray(new int[]{1, 2, 2, 3, 3, 4, 5, 6, 6, 7})));
        // {3, 4, 5, 6} ==> 4
    }
}

class Solution {
    public int longest(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 0;
        }
        int currentLength = 1;
        int maxLength = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                maxLength = Math.max(maxLength, ++currentLength);
            } else {
                currentLength = 1;
            }
        }
        return maxLength;
    }

    public int[] longestSubarray(int[] array) {
        if (array == null || array.length == 0) {
            return new int[] {};
        }
        int currentLength = 1;
        int maxLength = 1;
        int currentStart = 0;
        int maxStart = 0;
        int maxEnd = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                currentLength++;
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    maxStart = currentStart;
                    maxEnd = i;
                }
            } else {
                currentLength = 1;
                currentStart = i;
            }
        }
        int[] result = new int[maxEnd - maxStart + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = array[maxStart + i];
        }
        return result;
    }
}
