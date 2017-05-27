package B.BinarySearch.Medium.SearchInABigSortedArray;

import java.util.Arrays;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/search-in-a-big-sorted-array/
 * Given a big sorted array with positive integers sorted by ascending order. The array is so big so that you can not
 * get the length of the whole array directly, and you can only access the kth number by ArrayReader.get(k) (or
 * ArrayReader->get(k) for C++). Find the first index of a target number. Your algorithm should be in O(log k),
 * where k is the first index of the target number.
 * Return -1, if the number doesn't exist in the array.
 *
 * Example
 * Given [1, 3, 6, 9, 21, ...], and target = 3, return 1.
 * Given [1, 3, 6, 9, 21, ...], and target = 4, return -1.
 *
 * This problem uses a secret ArrayReader class
 * I faked it by using ArrayList. And the array is not so big here.
 */

public class SearchInABigSortedArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // List<Integer> testList1 = Arrays.asList(1, 3, 6, 9, 21);
        List<Integer> testList1 = Arrays.asList(1, 3, 6, 9, 21, 22, 24);
        int[] trueList1 = new int[]{1, 3, 21};
        // int t = 1, tt = 3, ttt = 21;
        // Because this array is BIG, there is no reason to test a value that is outside of the upper bound
        int[] falseList1 = new int[]{0, 4, 13};
        //int f = 0, ff = 4, fff = 25;
        String trueMsg = "The first index of ";
        String falseMsg = "There is no ";
        for (int i = 0; i < trueList1.length; i++) {
            System.out.println(trueMsg + trueList1[i] + ": " + solution.searchBigSortedArray(testList1, trueList1[i]));
            System.out.println(falseMsg + falseList1[i] + ": " + solution.searchBigSortedArray(testList1, falseList1[i]));
        }

        // Test the situation where there are multiple values present
        // The example is looking for a 4 which is at index 9
        List<Integer> testList2 = Arrays.asList(1, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6,
                                                6, 6, 6, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10,
                                                10, 10, 11, 11, 11, 11, 12, 12, 12, 13, 13, 13, 13, 13, 14, 14, 14, 14,
                                                14, 15, 15, 15, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16, 16, 16, 16, 17,
                                                17, 17, 17, 17, 17, 17, 18, 18, 19, 19, 19, 19, 20, 20, 20, 20, 20, 20,
                                                20, 20, 20);
        int four = 4;
        String msg = "The first index of ";
        System.out.println(msg + four + ": " + solution.searchBigSortedArray(testList2, 4));
    }
}

/**
 * Definition of ArrayReader:
 *
 * class ArrayReader {
 *      // get the number at index, return -1 if index is less than zero.
 *      public int get(int index);
 * }
 */
class Solution {
    /**
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return : An integer which is the index of the target number
     */
    // Below is the original signature
    // public int searchBigSortedArray(ArrayReader reader, int target) {
    public int searchBigSortedArray(List<Integer> reader, int target) {
        // write your code here
        if (reader == null) {
            return -1;
        }
        // Binary search to look for the upper bound
        int end = 1;
        while (reader.get(end) < target) {
            end *= 2;
        }
        // Second binary search to locate the target
        int start = 0;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            // Because it looks for the first index of the value
            if (reader.get(mid) >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (reader.get(start) == target) {
            return start;
        }
        if (reader.get(end) == target) {
            return end;
        }
        return -1;
    }
}
