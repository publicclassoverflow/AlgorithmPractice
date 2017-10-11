package Y.SortingAlgorithms.MergeSortRelated.MergeTwoSortedArrays;

/**
 * http://www.lintcode.com/en/problem/merge-two-sorted-arrays/
 * Merge two given sorted integer array A and B into a new sorted integer array.
 *
 * Example
 * A=[1,2,3,4]
 * B=[2,4,5,6]
 * return [1,2,2,3,4,4,5,6]
 *
 * Challenge
 * How can you optimize your algorithm if one array is very large and the other
 * is very small?
 */

public class MergeTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 0
        int[] zero = solution.mergeSortedArray(
                new int[]{1, 2, 3, 4},
                new int[]{2, 4, 5, 6}
        );
        printResult(zero);
        // Test 1
        int[] one = solution.mergeSortedArray(
                new int[]{1, 1, 1, 2},
                new int[]{1, 1, 1, 3}
        );
        printResult(one);
        // Test 2
        int[] two = solution.mergeSortedArray(
                new int[]{0},
                null
        );
        printResult(two);
    }

    public static void printResult(int[] array) {
        for (int num : array) {
            System.out.format("%d ", num);
        }
        System.out.println();
    }
}

class Solution {
    /*
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        if (A == null) {
            return B;
        }
        if (B == null) {
            return A;
        }
        int m = A.length;
        int n = B.length;
        int[] result = new int[m + n];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < m && j < n) {
            if (A[i] < B[j]) {
                result[index++] = A[i++];
            } else {
                result[index++] = B[j++];
            }
        }
        // After one of the arrays has finished merging
        while (i < m) {
            result[index++] = A[i++];
        }
        while (j < n) {
            result[index++] = B[j++];
        }
        return result;
    }
}
