package Q.Recursion.Medium.SpiralOrderGenerateI;

import java.util.Arrays;

/**
 * Description
 * Generate an M  N 2D array in spiral order clock-wise starting from the top left corner, using the numbers of 1, 2, 3, …, M  N in increasing order.
 *
 * Assumptions
 *
 * M >= 0, N >= 0
 * Examples
 *
 * M = 3, N = 4, the generated matrix is
 *
 * { {1,  2,  3,  4}
 *
 *   {10, 11, 12, 5},
 *
 *   {9,  8,  7,  6} }
 */

public class SpiralOrderGenerateI {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.spiralGenerate(3, 4)));
        System.out.println(Arrays.deepToString(solution.spiralGenerate(4, 4)));
        System.out.println(Arrays.deepToString(solution.spiralGenerate(4, 3)));
        System.out.println(Arrays.deepToString(solution.spiralGenerate(5, 4)));
    }
}

class Solution {
    public int[][] spiralGenerate(int m, int n) {
        // Write your solution here
        int[][] result = new int[m][n];
        if (m < 0 || n < 0) {
            return result;
        }
        spiralFill(result, 0, 1, m, n, m * n);
        return result;
    }

    private void spiralFill(int[][] matrix, int offset, int current,
                            int rowLength, int colLength, int total) {
        // Base case:
        // 1. When we have added all the numbers
        if (current > total) {
            return;
        }
        // 2. When there is only one row left to be filled
        // This happens when the length of the remaining column becomes 1
        // Fill this row from top to bottom
        if (colLength == 1) {
            for (int i = 0; i < rowLength; i++) {
                matrix[offset + i][offset] = current++;
            }
            return;
        }
        // 3. When there is only one column left to be filled
        // This happens when the length of the remaining row becomes 1
        // Fill this column from left to right
        if (rowLength == 1) {
            for (int i = 0; i < colLength; i++) {
                matrix[offset][offset + i] = current++;
            }
            return;
        }
        // Recursive rule:
        // At current layer/level
        // 1. Populate the top row from left to right
        for (int i = 0; i < colLength; i++) {
            matrix[offset][offset + i] = current++;
        }
        // 2. Populate the rightmost column from top to bottom
        for (int i = 1; i < rowLength - 1; i++) {
            matrix[offset + i][offset + colLength - 1] = current++;
        }
        // 3. Populate the bottom row from right to left
        for (int i = colLength - 1; i >= 0; i--) {
            matrix[offset + rowLength - 1][offset + i] = current++;
        }
        // 4. Populate the leftmost column from bottom to top
        for (int i = rowLength - 2; i >= 1; i--) {
            matrix[offset + i][offset] = current++;
        }
        // Go to the next layer/level: offset + 1 && colLength - 2 && rowLength - 2
        spiralFill(matrix, offset + 1, current, rowLength - 2, colLength - 2, total);
    }
}