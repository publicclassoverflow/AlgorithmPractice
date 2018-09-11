package S.AdvancedPractice.I.Medium.ArrayDeduplicationIII;

import java.util.Arrays;

/**
 * Description
 * Given a sorted integer array, remove duplicate elements. For each group of elements with the same value do not keep any of them. Do this in-place, using the left side of the original array and and maintain the relative order of the elements of the array. Return the array after deduplication.
 *
 * Assumptions
 *
 * The given array is not null
 * Examples
 *
 * {1, 2, 2, 3, 3, 3} → {1}
 */

public class ArrayDeduplicationIII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.dedup(new int[]{
                1, 2, 2, 3, 3, 3
        })));
        System.out.println(Arrays.toString(solution.dedup(new int[]{
                1, 2, 2, 2, 3, 3, 4
        })));
        System.out.println(Arrays.toString(solution.dedup(new int[]{
                1, 1, 2, 3, 3, 4, 5, 5, 5
        })));
    }
}

class Solution {
    public int[] dedup(int[] array) {
        // Write your solution here
        // Assumption: the array cannot be null or empty
        if (array == null || array.length < 2) {
            return array;
        }
        int slow = 0;
        int fast = 0;
        // For every element, we need to see if its proceeding elements
        // are the same. If there are duplicates, we keep skipping that index
        while (fast < array.length) {
            int start = fast;
            while (fast < array.length && array[fast] == array[start]) {
                fast++;
            }
            if (fast - start > 1) {
                // There are duplicate values
                continue;
            }
            // When there aren't any duplicate values
            // Copy the element at index start because it has only one copy, too
            // and we need to increment slow to keep the processed values
            array[slow++] = array[start];
        }
        return Arrays.copyOf(array, slow);
    }
}
