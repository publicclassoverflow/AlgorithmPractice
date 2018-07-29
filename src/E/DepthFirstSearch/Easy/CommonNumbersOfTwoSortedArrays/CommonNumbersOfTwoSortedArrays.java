package E.DepthFirstSearch.Easy.CommonNumbersOfTwoSortedArrays;

import java.util.*;

/**
 * Description
 * Find all numbers that appear in both of two sorted arrays (the two arrays are all sorted in ascending order).
 *
 * Assumptions
 *
 * In each of the two sorted arrays, there could be duplicate numbers.
 * Both two arrays are not null.
 * Examples
 *
 * A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2]
 */

public class CommonNumbersOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> A = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3));
        System.out.println(solution.common(
                new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3)),
                new ArrayList<>(Arrays.asList(1, 1, 2, 5, 6))
        ));
    }
}

class Solution {
    public List<Integer> common(List<Integer> A, List<Integer> B) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        if (A == null || B == null) {
            return result;
        }
        // Use a HashMap to store <element, # of times it appears in A>
        Map<Integer, Integer> existInA = new HashMap<>();
        for (Integer element : A) {
            existInA.put(element, existInA.getOrDefault(element, 0) + 1);
        }
        // For each element in B, look for its appearance in A
        for (Integer number : B) {
            if (existInA.containsKey(number) && existInA.get(number) > 0) {
                result.add(number);
                // Since we are moving forward, the searching window will
                // be moving towards right, as well.
                // The # of times this element appears in A needs to be
                // decremented by 1
                existInA.put(number, existInA.get(number) - 1);
            }
        }
        return result;
    }
}

