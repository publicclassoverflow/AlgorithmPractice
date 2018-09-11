package S.AdvancedPractice.I.Medium.ArrayDeduplicationII;

import java.util.Arrays;

/**
 * Description
 * Given a sorted integer array, remove duplicate elements. For each group of elements with the same value keep at most two of them. Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. Return the array after deduplication.
 *
 * Assumptions
 *
 * The given array is not null
 * Examples
 *
 * {1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3}
 */

public class ArrayDeduplicationII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.dedup(new int[]{
                1, 2, 2, 3, 3, 3
        })));
        System.out.println(Arrays.toString(solution.dedup(new int[]{
                1, 1, 2, 3, 3, 3, 4, 5, 5, 5
        })));
        System.out.println(Arrays.toString(solution.dedup(new int[]{
                1, 1, 1, 2, 2, 3
        })));
    }
}

class Solution {
    public int[] dedup(int[] array) {
        // Write your solution here
        if (array == null || array.length <= 2) {
            return array;
        }
        // array.length is guaranteed to be at least 2
        int slow = 1;
        for (int fast = 2; fast < array.length; fast++) {
            // Compare array[fast] to array[slow - 1]
            // Case 1: when the two are different
            // All the elements to the left of slow, including slow, are supposed to be kept
            // So, we need to increment slow, entering the processed-but-useless area
            // And copy array[fast] to the first useless position (to make it useful)
            if (array[fast] != array[slow - 1]) {
                array[++slow] = array[fast];
            }
            // Case 2: when the two are the same
            // which means array[slow - 1] == array[slow] == array[fast]
            // We have more than 2 duplicate elements here
            // Only things to the left of slow, array[slow - 1] and array[slow] in this case,
            // are set to be kept for return. So we simply skip array[fast]
        }
        // array[0, slow] is what we want
        int[] result = new int[slow + 1];
        for (int i = 0; i <= slow; i++) {
            result[i] = array[i];
        }
        return result;
    }
}
