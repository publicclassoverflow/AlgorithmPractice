package S.AdvancedPractice.I.Easy.Move0sToTheEndII;

import java.util.Arrays;

/**
 * Description
 * Given an array of integers, move all the 0s to the right end of the array.
 *
 * The relative order of the elements in the original array need to be maintained.
 *
 * Assumptions:
 *
 * The given array is not null.
 * Examples:
 *
 * {1} --> {1}
 * {1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0}
 */

public class Move0sToTheEndII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.moveZero(null)));
        System.out.println(Arrays.toString(solution.moveZero(null)));
        System.out.println(Arrays.toString(solution.moveZero(new int[]{})));
        System.out.println(Arrays.toString(solution.moveZero(new int[]{})));
        System.out.println(Arrays.toString(solution.moveZero(new int[]{1, 0, 3, 0, 1})));
        System.out.println(Arrays.toString(solution.moveZeroAlt(new int[]{1, 0, 3, 0, 1})));
    }
}

class Solution {
    public int[] moveZero(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }
        int slow = 0;
        for (int fast = 0; fast < array.length; fast++) {
            if (array[fast] != 0) {
                int temp = array[slow];
                array[slow++] = array[fast];
                array[fast] = temp;
            }
        }
        return array;
    }

    public int[] moveZeroAlt(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        int slow = 0;
        for (int fast = 0; fast < array.length; fast++) {
            if (array[fast] != 0) {
                array[slow++] = array[fast];
            }
        }
        // slow points to the first index where all the 0's start at
        for (int i = slow; i < array.length; i++) {
            array[i] = 0;
        }
        return array;
    }
}
