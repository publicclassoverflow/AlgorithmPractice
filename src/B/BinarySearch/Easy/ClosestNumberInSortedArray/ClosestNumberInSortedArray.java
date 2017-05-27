package B.BinarySearch.Easy.ClosestNumberInSortedArray;

import java.util.HashMap;

/**
 * http://www.lintcode.com/en/problem/closest-number-in-sorted-array/
 *
 * Given a target number and an integer array A sorted in ascending order,
 * find the index i in A such that A[i] is closest to the given target.
 * Return -1 if there is no element in the array.
 *
 * Example
 * Given [1, 2, 3] and target = 2, return 1.
 * Given [1, 4, 6] and target = 3, return 1.
 * Given [1, 4, 6] and target = 5, return 1 or 2.
 * Given [1, 3, 3, 4] and target = 2, return 0 or 1 or 2.
 */

public class ClosestNumberInSortedArray {
    public static void main(String[] args) {
        Solution solution = new Solution();

        HashMap<Integer, int[]> mapOne = new HashMap<>();
        mapOne.put(2, new int[]{1, 2, 3});
        mapOne.put(3, new int[]{1, 4, 6});
        mapOne.put(5, new int[]{1, 4, 6});
        String msg = "The closest neighbor of ";
        mapOne.forEach((k, v) -> System.out.println(
                                        msg + k + " in " + v + ": " +
                                        solution.closestNumber(v, k)));

        HashMap<Integer, int[]> mapTwo = new HashMap<>();
        int target = 2;
        mapTwo.put(target, new int[]{1, 3, 3, 4});
        System.out.println(msg + target + " in " + mapTwo.get(target) + ": " +
                           solution.closestNumber(mapTwo.get(target), target));
    }
}

class Solution {
    /**
     * @param A      an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public int closestNumber(int[] A, int target) {
        // Write your code here
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        // The problem doesn't specify whether to return the smaller or larger
        // neighbor when there is a tie.
        // I choose to return the smaller neighbor.
        // Omit the '=' in to get the larger neighbor.
        return diff(A[start], target) <= diff(A[end], target) ? start : end;
    }

    private int diff(int a, int b) {
        return Math.abs(a - b);
    }
}
