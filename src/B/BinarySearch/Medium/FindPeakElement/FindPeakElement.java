package B.BinarySearch.Medium.FindPeakElement;

/**
 * http://www.lintcode.com/en/problem/find-peak-element/
 *
 * There is an integer array which has the following features:
 *      The numbers in adjacent positions are different.
 *      A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
 *      We define a position P is a peek if:
 *      A[P] > A[P-1] && A[P] > A[P+1]
 * Find a peak element in this array. Return the index of the peak.
 * Notice: The array may contains multiple peeks, find any of them.
 *
 * Example
 * Given [1, 2, 1, 3, 4, 5, 7, 6]
 * Return index 1 (which is number 2) or 6 (which is number 7)
 */

public class FindPeakElement {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findPeak(new int[]{1, 2, 1, 3, 4, 5, 7, 6}));
    }
}

class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        // write your code here
        // The problem says there WILL be an array, so no need to check
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < A[mid - 1]) {
                end = mid;
            } else if (A[mid] < A[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return (A[start] < A[end]) ? end : start;
    }
}
