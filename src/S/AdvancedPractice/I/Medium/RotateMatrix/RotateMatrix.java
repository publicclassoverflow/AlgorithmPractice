package S.AdvancedPractice.I.Medium.RotateMatrix;

import java.util.Arrays;

/**
 * Description
 * Rotate an N * N matrix clockwise 90 degrees.
 *
 * Assumptions
 *
 * The matrix is not null and N >= 0
 * Examples
 *
 * { {1,  2,  3}
 *
 *   {8,  9,  4},
 *
 *   {7,  6,  5} }
 *
 * after rotation is
 *
 * { {7,  8,  1}
 *
 *   {6,  9,  2},
 *
 *   {5,  4,  3} }
 */

public class RotateMatrix {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test
        int[][] one = new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        solution.rotate(one);
        System.out.println(Arrays.deepToString(one));
        // Test
        int[][] two = new int[][] {
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}
        };
        solution.rotate(two);
        System.out.println(Arrays.deepToString(two));
    }
}

class Solution {
    public void rotate(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0) {
            return;
        }
        if (matrix[0] == null|| matrix[0].length == 0) {
            return;
        }
        if (matrix.length != matrix[0].length){
            return;
        }
        rotateMatrix(matrix, 0, matrix.length);
    }

    private void rotateMatrix(int[][] matrix, int offset, int length) {
        // Base case: when we reach the center
        // there could be one or none element left
        if (length <= 1) {
            return;
        }
        // For rows and columns with length == n
        // rotate each indices:
        // record the value of (0, 0)
        // (n - 1, 0) → (0, 0), (n - 1, n - 1) → (n - 1, 0)
        // (0, n - 1) → (n - 1, n - 1), (0, 0) → (0, n - 1)
        for (int i = 0; i < length - 1; i++) {
            int temp = matrix[offset][offset + i];
            matrix[offset][offset + i] =
                    matrix[offset + length - 1 - i][offset];
            matrix[offset + length - 1 - i][offset] =
                    matrix[offset + length - 1][offset + length - 1 - i];
            matrix[offset + length - 1][offset + length - 1 - i] =
                    matrix[offset + i][offset + length - 1];
            matrix[offset + i][offset + length - 1] = temp;
        }
        rotateMatrix(matrix, offset + 1, length - 2);
    }
}