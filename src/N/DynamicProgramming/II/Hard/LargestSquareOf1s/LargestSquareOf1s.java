package N.DynamicProgramming.II.Hard.LargestSquareOf1s;

/**
 * Description
 * Determine the largest square of 1s in a binary matrix (a binary matrix only contains 0 and 1), return the length of the largest square.
 *
 * Assumptions
 *
 * The given matrix is not null and guaranteed to be of size N * N, N >= 0
 * Examples
 *
 * { {0, 0, 0, 0},
 *
 *   {1, 1, 1, 1},
 *
 *   {0, 1, 1, 1},
 *
 *   {1, 0, 1, 1}}
 *
 * the largest square of 1s has length of 2
 */

public class LargestSquareOf1s {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largest(new int[][] {
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 0, 0},
                {1, 1, 1, 0, 0},
        }));    // return 3 ==> max at (3, 2) or (4, 2)
        System.out.println(solution.largestAlt(new int[][] {
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 0, 0},
                {1, 1, 1, 0, 0},
        }));    // return 3 ==> max at (3, 2) or (4, 2)
        System.out.println(solution.largest(new int[][] {{1}}));
        System.out.println(solution.largestAlt(new int[][] {{1}}));
    }
}

class Solution {
    public int largest(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;
        int[][] length = new int[n][n];
        // The matrix is guaranteed to be size N x N
        // at index (i, j), length[i][j] represents the maximum length
        // of the side of the square formed whose bottom right corner
        // is at (i, j)
        // Initialize the first row and column
        for (int i = 0; i < n; i++) {
            length[i][0] = matrix[i][0];
            length[0][i] = matrix[0][i];
        }
        // For every point (i, j) in matrix, treat (i, j) as the
        // bottom right corner of a square, count the length of its side
        int maxLength = length[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    length[i][j] = 0;
                } else {
                    length[i][j] = Math.min(length[i - 1][j - 1],
                                            Math.min(length[i - 1][j],
                                                     length[i][j - 1])) + 1;
                    maxLength = Math.max(maxLength, length[i][j]);
                }
            }
        }
        return maxLength;
    }

    public int largestAlt(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        // Size of the matrix is guaranteed to be N x N
        int N = matrix.length;
        int[][] length = new int[N][N];
        // length[i][j] represents the length of the side of the largest
        // square whose bottom right corner is at (i, j)
        int largestLength = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 || j == 0) {
                    // Initialization step
                    length[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0) {
                    // No squares can be formed at this corner
                    length[i][j] = 0;
                } else if (matrix[i][j] == 1) {
                    length[i][j] = Math.min(length[i - 1][j],
                            length[i][j - 1]);
                    length[i][j] = Math.min(length[i - 1][j - 1],
                            length[i][j]) + 1;
                }
                // Update the global largest length
                largestLength = Math.max(largestLength, length[i][j]);
            }
        }
        return largestLength;
    }
}
