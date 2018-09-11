package S.AdvancedPractice.I.Medium.LargestAndSecondLargest;

import java.util.*;

/**
 * Description
 * Use the least number of comparisons to get the largest and 2nd largest number in the given integer array. Return the largest number and 2nd largest number.
 *
 * Assumptions
 *
 * The given array is not null and has length of at least 2
 * Examples
 *
 * {2, 1, 5, 4, 3}, the largest number is 5 and 2nd largest number is 4.
 */

public class LargestAndSecondLargest {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.largestAndSecond(new int[] {
                2, 1, 5, 4, 3
        })));
        System.out.println(Arrays.toString(solution.largestAndSecond(new int[] {
                1, 2, 3, 3, 3
        })));
        System.out.println(Arrays.toString(solution.largestAndSecond(new int[] {
                5, 4, 3, 2, 1
        })));
    }
}

class Solution {
    public int[] largestAndSecond(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return new int[] {};
        }
        // Step 1: compare the elements in pairs
        // 0 vs end, 1 vs end -1, ...
        // put the larger elements to the right hand side
        // Use a HashMap to record the comparison history for the elements
        // <element, set_of_compared_elements>
        // Or, use <element, list_of_compared_elements> if duplicates are allowed
        int n = array.length;
//        Map<Integer, Set<Integer>> history = new HashMap<>();
        Map<Integer, List<Integer>> history = new HashMap<>();
        for (int i = 0; i < n / 2; i++) {
            if (array[i] < array[n - 1 - i]) {
                // Swap the two element such that the larger one can be moved to the lhs
                swap(array, i, n - 1 - i);
                updateHistoryList(array[n - 1 - i], array[i], history);
            } else {
                updateHistoryList(array[i], array[n - 1 - i], history);
            }
            // Update the comparison history of the larger element
//            updateHistorySet(array[i], array[n - 1 - i], history);
//            updateHistoryList(array[i], array[n - 1 - i], history);
        }
        // Step 2: Find the largest element in the lhs
        int first = array[0];
        for (int i = 1; i <= n / 2; i++) {
            if (array[i] > first) {
//                updateHistorySet(array[i], first, history);
                updateHistoryList(array[i], first, history);
                first = array[i];
            } else {
                updateHistoryList(first, array[i], history);
            }
        }
        // Step 3: Find the largest element in the comparison history of first
        int second = Integer.MIN_VALUE;
//        for (int number : history.getOrDefault(first, new HashSet<>())) {
        for (int number : history.getOrDefault(first, new ArrayList<>())) {
            if (number > second) {
                second = number;
            }
        }
        return new int[] {first, second};
    }

    private void swap(int[] array, int one, int two) {
        int temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    private void updateHistorySet(int one, int two,
                                  Map<Integer, Set<Integer>> history) {
        Set<Integer> compared = history.getOrDefault(
                one, new HashSet<>()
        );
        compared.add(two);
        history.put(one, compared);
    }

    private void updateHistoryList(int one, int two,
                                   Map<Integer, List<Integer>> history) {
        List<Integer> compared = history.getOrDefault(one, new ArrayList<>());
        compared.add(two);
        history.put(one, compared);
    }
}
