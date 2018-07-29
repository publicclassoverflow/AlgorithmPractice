package B.BinarySearch.Medium.SearchA2DMatrixIILeetcode;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/description/
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 *      Integers in each row are sorted in ascending from left to right.
 *      Integers in each column are sorted in ascending from top to bottom.
 * For example,
 * Consider the following matrix:
 * [
 *  [1,   4,  7, 11, 15],
 *  [2,   5,  8, 12, 19],
 *  [3,   6,  9, 16, 22],
 *  [10, 13, 14, 17, 24],
 *  [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * Given target = 20, return false.
 */

public class SearchA2DMatrixIILeetcode {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] one = new int[][] {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(solution.searchMatrix(one, 5));
        System.out.println(solution.searchMatrix(one, 20));
    }
}

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int n = matrix[0].length;
        for (int[] row : matrix) {
            if (row[n - 1] < target) {
                continue;
            }
            int start = 0;
            int end = n - 1;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (row[mid] == target) {
                    return true;
                } else if (row[mid] < target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
            if (row[start] == target) {
                return true;
            }
            if (row[end] == target) {
                return true;
            }
        }
        return false;
    }
}
