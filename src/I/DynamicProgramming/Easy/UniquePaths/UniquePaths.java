package I.DynamicProgramming.Easy.UniquePaths;

/**
 * http://www.lintcode.com/en/problem/unique-paths/
 *
 * A robot is located at the top-left corner of a m x n grid.
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid.
 * How many possible unique paths are there?
 *
 * Notice:
 * m and n will be at most 100
 *
 * Example:
 * Given m = 3 and n = 3, return 6.
 * Given m = 4 and n = 5, return 35.
 */

public class UniquePaths {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test
        System.out.println(solution.uniquePaths(3, 3));
        System.out.println(solution.uniquePaths(4, 5));
    }
}

class Solution {
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        // m and n are already guaranteed to be in a certain range
        int[][] paths = new int[m][n];
        // Initialization
        for (int i = 0; i < m; i++) {
            paths[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            paths[0][j] = 1;
        }
        // DP
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
            }
        }
        return paths[m - 1][n - 1];
    }
}