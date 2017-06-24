package M.BinarySearchAndSweepLine.Hard.FindPeakElementII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/find-peak-element-ii/
 *
 * Part I at:
 * http://www.lintcode.com/en/problem/find-peak-element/
 * https://github.com/publicclassoverflow/LintCode/tree/Jun2017/src/B/BinarySearch/Medium/FindPeakElement
 *
 * There is an integer matrix which has the following features:
 * The numbers in adjacent positions are different.
 * The matrix has n rows and m columns.
 * For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
 * For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1].
 * We define a position P is a peek if:
 * A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]
 * Find a peak element in this matrix. Return the index of the peak.
 *
 * Example:
 * Given a matrix:
 * [
 *  [1 ,2 ,3 ,6 ,5],
 *  [16,41,23,22,6],
 *  [15,17,24,21,7],
 *  [14,18,19,20,10],
 *  [13,14,11,10,9]
 * ]
 * return index of 41 (which is [1,1]) or index of 24 (which is [2,2])
 *
 * Challenge:
 * Solve it in O(n+m) time.
 * If you come up with an algorithm that you thought it is O(n log m) or
 * O(m log n), can you prove it is actually O(n+m) or propose a similar but
 * O(n+m) algorithm?
 */

public class FindPeakElementII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<int[][]> As = new ArrayList<>();
        As.add(new int[][]{
                {1, 2, 3, 6, 5},
                {16, 41, 23, 22, 6},
                {15, 17, 24, 21, 7},
                {14, 18, 19, 20, 10},
                {13, 14, 11, 10, 9}
        });
        As.add(null);
        As.add(new int[][]{});
        for (int[][] A : As) {
            System.out.format(
                    "The peak element in matrix %s:%n%s%n",
                    Arrays.toString(new String[]{Arrays.deepToString(A)}),
                    Arrays.toString(new List[]{solution.findPeakII(A)})
            );
        }
    }
}

class Solution {
    /**
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        if (A == null || A.length == 0 || A[0].length == 0) {
            return result;
        }
        // Because the 4 edges are guaranteed to be smaller than the elements
        // they surround in. We can safely start from the second and second to
        // last rows
        int startRow = 1;
        int endRow = A.length - 2;
        while (startRow <= endRow) {
            int midRow = startRow + (endRow - startRow) / 2;
            // Find the largest element in the current row
            int col = findLargest(A[midRow]);
            if (A[midRow][col] < A[midRow - 1][col]) {
                endRow = midRow - 1;
            } else if (A[midRow][col] < A[midRow + 1][col]) {
                startRow = midRow + 1;
            } else {
                result.addAll(Arrays.asList(midRow, col));
                break;
            }
        }
        return result;
    }

    private int findLargest(int[] array) {
        // Because the array is not sorted, this can only be done linearly
        int largest = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= array[largest]) {
                continue;
            }
            largest = i;
        }
        return largest;
    }
}

