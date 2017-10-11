package Y.SortingAlgorithms.MergeSortRelated.MergeSortedArray;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/merge-sorted-array/
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 *
 * Notice:
 * You may assume that A has enough space (size that is greater or equal to
 * m + n) to hold additional elements from B. The number of elements initialized
 * in A and B are m and n respectively.
 *
 * Example:
 * A = [1, 2, 3, empty, empty], B = [4, 5]
 * After merge, A will be filled as [1, 2, 3, 4, 5]
 */

public class MergeSortedArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 0
        int[] largeArray0 = buildArray(new int[]{1, 2, 3});
        int[] smallArray0 = new int[]{4, 5};
        solution.mergeSortedArray(largeArray0, 3, smallArray0, 2);
        printResult(largeArray0);
        // Test 1
        int[] largeArray1 = buildArray(new int[]{1, 5});
        int[] smallArray1 = new int[]{2, 3, 4};
        solution.mergeSortedArray(largeArray1, 2, smallArray1, 3);
        printResult(largeArray1);
        // Test 2
        int[] largeArray2 = buildArray(new int[]{1, 2, 3, 4, 5});
        solution.mergeSortedArray(largeArray2, 5, null, 0);
        printResult(largeArray2);
    }

    public static int[] buildArray(int[] array) {
        // Use a length of 100 to mimic a very large array
        int[] result = new int[100];
        Arrays.fill(result, Integer.MIN_VALUE);
        System.arraycopy(array, 0, result, 0, array.length);
        /*
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        */
        return result;
    }

    public static void printResult(int[] array) {
        int i = 0;
        while (array[i] != Integer.MIN_VALUE) {
            System.out.format("%d ", array[i++]);
        }
        System.out.println();
    }
}

class Solution {
    /*
     * @param A: sorted integer array A which has m elements, but size of A is m+n
     * @param m: An integer
     * @param B: sorted integer array B which has n elements
     * @param n: An integer
     * @return: nothing
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        // if (A == null || m == 0 || B == null || n == 0) {
        if (A == null || B == null) {
            return;
        }
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        // A is big enough
        // Start merging elements towards the end of A so that nothing gets
        // overwritten
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[index--] = A[i--];
            } else {
                A[index--] = B[j--];
            }
        }
        // When one array has finished merging and there are still elements
        // await in the other array
        while (i >= 0) {
            A[index--] = A[i--];
        }
        while (j >= 0) {
            A[index--] = B[j--];
        }
    }
}
