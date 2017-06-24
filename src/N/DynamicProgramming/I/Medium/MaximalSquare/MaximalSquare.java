package N.DynamicProgramming.I.Medium.MaximalSquare;

/**
 * http://www.lintcode.com/en/problem/maximal-square/
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
 * containing all 1's and return its area
 *
 * Example:
 * For example, given the following matrix:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * return 4.
 *
 */

public class MaximalSquare {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test case 1
        int[][] m1 = new int[][]{
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        };
        System.out.println(solution.maxSquare(m1));     // 4

        // Test case 2
        // I got an error when running my code on LintCode and this is the
        // matrix
        int[][] m2 = new int[][]{
                {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}
        };  // There are much more {1}s in that test case
        System.out.println(solution.maxSquare(m2));     // 1

        // Test case 3
        int[][] m3 = new int[][]{
                {0,1,1,1,1,1,1,1,1,1},
                {1,0,1,1,1,1,1,1,1,1},
                {1,1,0,1,1,1,1,1,1,1},
                {1,1,1,0,1,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,1,0,1,1,1,1},
                {1,1,1,1,1,1,0,1,1,1},
                {1,1,1,1,1,1,1,0,1,1},
                {1,1,1,1,1,1,1,1,0,1},
                {1,1,1,1,1,1,1,1,1,0}
        };
        System.out.println(solution.maxSquare(m3));     // 25
    }
}

class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length;          // 4
        int m = matrix[0].length;       // 5
        int[][] square = new int[n][m]; // 4x5
        // Initialize the edges
        for (int i = 0; i < n; i++) {
            square[i][0] = matrix[i][0];
        }
        for (int j = 0; j < m; j++) {
            square[0][j] = matrix[0][j];
        }
        // All three cells (up, left, diagonal) matter when trying to form a
        // square with the current cell
        // And the longest edge the current cell can make up with the previous
        // cells is the minimum length of those three preceding cells
        int maxLen = 0;
        for (int i = 1; i < n; i++) {
            // This solved the error in case of Test case #2
            if (square[i][0] > maxLen) {
                maxLen = square[i][0];
            }
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    square[i][j] = 0;
                    continue;
                }
                square[i][j] = findMin(
                        square[i - 1][j],
                        square[i][j - 1],
                        square[i - 1][j - 1]) + 1;
                if (square[i][j] > maxLen) {
                    maxLen = square[i][j];
                }
            }
        }
        return maxLen * maxLen;
    }

    private int findMin(int a, int b, int c) {
        int min = Math.min(a, b);
        return Math.min(min, c);
    }
}
