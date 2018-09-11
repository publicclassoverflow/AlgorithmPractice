package N.DynamicProgramming.III.Hard.LargestSubMatrixSum;

/**
 * Description
 * Given a matrix that contains integers, find the submatrix with the largest sum.
 *
 * Return the sum of the submatrix.
 *
 * Assumptions
 *
 * The given matrix is not null and has size of M * N, where M >= 1 and N >= 1
 * Examples
 *
 * { {1, -2, -1, 4},
 *
 *   {1, -1,  1, 1},
 *
 *   {0, -1, -1, 1},
 *
 *   {0,  0,  1, 1} }
 *
 * the largest submatrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7.
 */

public class LargestSubMatrixSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largest(new int[][] {
                {1, -2, -1, 4},
                {1, -1, 1, 1},
                {0, -1, -1, 1},
                {0, 0, 1, 1}
        }));
    }
}

class Solution {
    public int largest(int[][] matrix) {
        // Write your solution here
        // Assumption: matrix is not null and of size m x n
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            // For each row, we compute the total sum of all the columns
            int[] currentRow = new int[n];
            for (int j = i; j < m; j++) {
                add(currentRow, matrix[j]);
                result = Math.max(result, max(currentRow));
            }
        }
        return result;
    }

    private void add(int[] currentRow, int[] add) {
        for (int i = 0; i < currentRow.length; i++) {
            currentRow[i] += add[i];
        }
    }

    private int max(int[] currentRow) {
        int currentSum = currentRow[0];
        int maxSum = currentRow[0];
        for (int i = 1; i < currentRow.length; i++) {
            currentSum = Math.max(currentSum + currentRow[i], currentRow[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
