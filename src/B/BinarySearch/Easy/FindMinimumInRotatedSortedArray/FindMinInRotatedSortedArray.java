package B.BinarySearch.Easy.FindMinimumInRotatedSortedArray;

import java.util.HashMap;

/**
 * http://www.lintcode.com/en/problem/find-minimum-in-rotated-sorted-array/
 *
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 *
 * Example
 * Given [4, 5, 6, 7, 0, 1, 2] return 0
 */

public class FindMinInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        HashMap<Integer, int[]> arg = new HashMap<>();
        arg.put(0, new int[]{4, 5, 6, 7, 0, 1, 2});
        arg.put(1, new int[]{1, 2, 3});
        arg.forEach((target, num) -> System.out.format(
                    "The result should be %d. The function returned %d.\n",
                    target, solution.findMin(arg.get(target))
        ));
    }
}

class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        // write your code here
        if (num == null || num.length == 0) {
            return -1;
        }
        int start = 0;
        int pivot = num.length - 1;
        while (start + 1 < pivot) {
            int mid = start + (pivot - start) / 2;
            if (num[mid] < num[pivot]) {
                pivot = mid;
            } else {
                start = mid;
            }
        }
        return (num[start] < num[pivot]) ? num[start] : num[pivot];
    }
}

