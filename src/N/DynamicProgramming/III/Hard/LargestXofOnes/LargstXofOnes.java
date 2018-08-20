package N.DynamicProgramming.III.Hard.LargestXofOnes;

import java.util.Arrays;

/**
 * Description
 * Given a matrix that contains only 1s and 0s, find the largest X shape which contains only 1s, with the same arm lengths and the four arms joining at the central point.
 *
 * Return the arm length of the largest X shape.
 *
 * Assumptions
 *
 * The given matrix is not null, has size of N * M, N >= 0 and M >= 0.
 * Examples
 *
 * { {0, 0, 0, 0},
 *
 *   {1, 1, 1, 1},
 *
 *   {0, 1, 1, 1},
 *
 *   {1, 0, 1, 1} }
 *
 * the largest X of 1s has arm length 2.
 */

public class LargstXofOnes {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largest(new int[][] {
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        }));
        System.out.println(solution.largest(new int[][] {
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 0}
        }));
    }
}

class Solution {
    public int largest(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        // Create four matrices recording the length of crosses formed in 4 directions:
        // 1. top-left --> bottom-right ==> tl2br
        // 2. bottom-right --> top-left ==> br2tl
        // 3. top-right --> bottome-left ==> tr2bl
        // 4. bottom-left --> top-right ==> bl2tr
        int[][] topLeft = tl2br(matrix, m, n);
        int[][] bottomRight = br2tl(matrix, m, n);
        int[][] topRight = tr2bl(matrix, m, n);
        int[][] bottomLeft = bl2tr(matrix, m, n);
        // Create a matrix to record the arm length of crosses in matrix
        // and initialize the 4 edges of the new matrix
//        int[][] cross = initCross(matrix, m, n);
        int[][] cross = new int[m][n];
        // For each cell (i, j) in cross matrix, if matrix[i][j] == 1
        // take the min from its:
        // top-left (i - 1, j - 1), top-right (i - 1, j + 1),
        // bottom-left (i + 1, j - 1), bottom-right (i + 1, j + 1)
        // and plus 1
        // Keep track of the global maximum
        int maxCross = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                cross[i][j] = Math.min(Math.min(topLeft[i][j], bottomRight[i][j]),
                                       Math.min(topRight[i][j], bottomLeft[i][j]));
                maxCross = Math.max(maxCross, cross[i][j]);
            }
        }
//        System.out.println(Arrays.deepToString(cross));
        return maxCross;
    }

    private int[][] tl2br(int[][] matrix, int row, int col) {
        int[][] topLeft = new int[row][col];
        // Initialize the leftmost column
        for (int i = 0; i < row; i++) {
            topLeft[i][0] = matrix[i][0];
        }
        // Initialize the top row
        for (int j = 0; j < col; j++) {
            topLeft[0][j] = matrix[0][j];
        }
        // Compare each cell (i, j) with the cell at its top-left position (i - 1, j - 1)
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                topLeft[i][j] = topLeft[i - 1][j - 1] + 1;
            }
        }
        return topLeft;
    }

    private int[][] br2tl(int[][] matrix, int row, int col) {
        int[][] bottomRight = new int[row][col];
        // Initialize the rightmost column
        for (int i = 0; i < row; i++) {
            bottomRight[i][col - 1] = matrix[i][col - 1];
        }
        // Initialize the bottom row
        for (int j = 0; j < col; j++) {
            bottomRight[row - 1][j] = matrix[row - 1][j];
        }
        // Compare each cell (i, j) with the cell at its bottom-right position (i + 1, j + 1)
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                bottomRight[i][j] = bottomRight[i + 1][j + 1] + 1;
            }
        }
        return bottomRight;
    }

    private int[][] tr2bl(int[][] matrix, int row, int col) {
        int[][] topRight = new int[row][col];
        // Initialize the rightmost column
        for (int i = 0; i < row; i++) {
            topRight[i][col - 1] = matrix[i][col - 1];
        }
        // Initialize the top row
        for (int j = 0; j < col; j++) {
            topRight[0][j] = matrix[0][j];
        }
        // Compare each cell (i, j) with the cell at its top-right position (i - 1, j + 1)
        for (int i = 1; i < row; i++) {
            for (int j = col - 2; j >= 0; j--) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                topRight[i][j] = topRight[i - 1][j + 1] + 1;
            }
        }
        return topRight;
    }

    private int[][] bl2tr(int[][] matrix, int row, int col) {
        int[][] bottomLeft = new int[row][col];
        // Initialize the leftmost column
        for (int i = 0; i < row; i++) {
            bottomLeft[i][0] = matrix[i][0];
        }
        // Initialize the bottom row
        for (int j = 0; j < col; j++) {
            bottomLeft[row - 1][j] = matrix[row - 1][j];
        }
        // Compare each cell (i, j) with the cell at its bottom-left position (i + 1, j - 1)
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                bottomLeft[i][j] = bottomLeft[i + 1][j - 1] + 1;
            }
        }
        return bottomLeft;
    }

    private int[][] initCross(int[][] matrix, int row, int col) {
        int[][] cross = new int[row][col];
        for (int i = 0; i < row; i++) {
            cross[i][0] = matrix[i][0];
            cross[i][col - 1] = matrix[i][col - 1];
        }
        for (int j = 0; j < col; j++) {
            cross[0][j] = matrix[0][j];
            cross[row - 1][j] = matrix[row - 1][j];
        }
        return cross;
    }
}
