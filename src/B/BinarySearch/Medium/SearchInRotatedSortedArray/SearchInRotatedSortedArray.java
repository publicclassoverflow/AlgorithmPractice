package B.BinarySearch.Medium.SearchInRotatedSortedArray;

import java.util.HashMap;

/**
 * http://www.lintcode.com/en/problem/search-in-rotated-sorted-array/
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 *
 * Example
 * For [4, 5, 1, 2, 3] and target=1, return 2.
 * For [4, 5, 1, 2, 3] and target=0, return -1.
 */

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        HashMap<Integer, int[]> arg = new HashMap<>();
        arg.put(1, new int[]{4, 5, 1, 2, 3});
        arg.put(0, new int[]{4, 5, 1, 2, 3});
        arg.put(2, new int[]{4, 5, 1, 2, 3});
        arg.put(4, new int[]{4, 5, 1, 2, 3});
        arg.put(6, new int[]{4, 5, 1, 2, 3});
        arg.put(3, new int[]{3, 4, 5, 6, 7});
        arg.put(5, new int[0]);
        arg.forEach((target, A) -> System.out.format(
                    "Target %d is at index %d%n",
                    target, solution.search(arg.get(target), target)
        ));
    }
}

class Solution {
    /**
     * @param A      : an integer rotated sorted array
     * @param target :  an integer to be searched
     *               return : an integer
     */
    public int search(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;
        // Because the array is rotated, the idea is to not only make
        // comparisons between target and mid value, but also start/end values
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            }
            if (A[start] < A[mid]) {
                // The target could be at start, so it needs to be inclusive
                if (A[start] <= target && target < A[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (A[mid] < target && target <= A[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (A[start] == target) {
            return start;
        }
        if (A[end] == target) {
            return end;
        }
        return -1;
    }
}
