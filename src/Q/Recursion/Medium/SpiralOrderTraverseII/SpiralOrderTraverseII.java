package Q.Recursion.Medium.SpiralOrderTraverseII;

import java.util.*;

/**
 * Description
 * Traverse an M  N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.
 *
 * Assumptions
 *
 * The 2D array is not null and has size of M  N where M, N >= 0
 * Examples
 *
 * { {1,  2,  3,  4},
 *
 *   {5,  6,  7,  8},
 *
 *   {9, 10, 11, 12} }
 *
 * the traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
 */

public class SpiralOrderTraverseII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.spiral(new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        }));
        System.out.println(solution.spiral(new int[][] {
                {1, 2, 3, 4},
                {16, 17, 18, 5},
                {15, 24, 19, 6},
                {14, 23, 20, 7},
                {13, 22, 21, 8},
                {12, 11, 10, 9}
        }));
        System.out.println(solution.spiral(new int[][] {
                {1, 2, 3, 4, 5, 6},
                {16, 17, 18, 19, 20, 7},
                {15, 24, 23, 22, 21, 8},
                {14, 13, 12, 11, 10, 9}
        }));
    }
}

class Solution {
    public List<Integer> spiral(int[][] matrix) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        spiralTraverse(matrix, result, 0, matrix.length, matrix[0].length);
        return result;
    }

    private void spiralTraverse(int[][] matrix, List<Integer> result,
                                int offset, int rowLength, int colLength) {
        // Base case:
        // 1. When there are no more elements left
        if (rowLength == 0 || colLength == 0) {
            return;
        }
        // 2. When there is only one row left
        if (rowLength == 1) {
            for (int i = 0; i < colLength; i++) {
                result.add(matrix[offset][offset + i]);
            }
            return;
        }
        // 3. When there is only one column left
        if (colLength == 1) {
            for (int i = 1; i < rowLength - 1; i++) {
                result.add(matrix[offset + i][offset]);
            }
            return;
        }
        // Recursive rule
        // 1. Add the top row from left to right
        for (int i = 0; i < colLength; i++) {
            result.add(matrix[offset][offset + i]);
        }
        // 2. Add the rightmost column from top to bottom
        for (int i = 1; i < rowLength - 1; i++) {
            result.add(matrix[offset + i][offset + colLength - 1]);
        }
        // 3. Add the bottom row from right to left
        for (int i = colLength - 1; i >= 0; i--) {
            result.add(matrix[offset + rowLength - 1][offset + i]);
        }
        // 4. Add the leftmost column from bottom to top
        for (int i = rowLength - 2; i >= 1; i--) {
            result.add(matrix[offset + i][offset]);
        }
        // Go to the next layer: offset + 1 && rowLength - 2 && colLength - 2
        spiralTraverse(matrix, result, offset + 1, rowLength - 2, colLength - 2);
    }
}


