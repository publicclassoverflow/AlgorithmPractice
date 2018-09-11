package S.AdvancedPractice.I.ArrayDeduplicationI;

import java.util.Arrays;

/**
 * Description
 * Given a sorted integer array, remove duplicate elements. For each group of elements with the same value keep only one of them. Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. Return the array after deduplication.
 *
 * Assumptions
 *
 * The array is not null
 * Examples
 *
 * {1, 2, 2, 3, 3, 3} → {1, 2, 3}
 */

public class ArrayDeduplicationI {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.dedup(new int[]{1, 2, 2, 3, 3, 3})));
    }
}

class Solution {
    public int[] dedup(int[] array) {
        // Write your solution here
        if (array == null || array.length < 2) {
            return array;
        }
        // Use fast-slow pointer
        // All the elements to the left of the slow pointer, including the one
        // pointed to by the slow pointer, are processed portion of the input
        // All the elements to the right of the fast pointer are the portion
        // of the input that is to be processed
        // All the elements in the middle of the slow and fast pointer are the
        // ones that have been processed but useless in terms of return value
        int slow = 0;
        for (int fast = 1; fast < array.length; fast++) {
            if (array[slow] == array[fast]) {
                continue;
            }
            // When we see different elements at index slow and fast, we:
            // 1. increment slow such that the original element is kept
            // 2. copy array[fast] to index slow' because everything in between are useless
            array[++slow] = array[fast];
        }
        // array[0, slow] is the part that we want
        int[] result = new int[slow + 1];
        for (int i = 0; i <= slow; i++) {
            result[i] = array[i];
        }
        return result;
    }
}
