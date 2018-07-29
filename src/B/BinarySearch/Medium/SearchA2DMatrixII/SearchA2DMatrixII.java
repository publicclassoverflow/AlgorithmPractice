package B.BinarySearch.Medium.SearchA2DMatrixII;

/**
 * http://www.lintcode.com/en/problem/search-a-2d-matrix-ii/
 * There is a similar question on leetcode:
 * https://leetcode.com/problems/search-a-2d-matrix-ii/description/
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix,
 * return the occurrence of it.
 * This matrix has the following properties:
 *      Integers in each row are sorted from left to right.
 *      Integers in each column are sorted from up to bottom.
 *      No duplicate integers in each row or column.
 *
 * Example:
 * Consider the following matrix:
 * [
 *  [1, 3, 5, 7],
 *  [2, 4, 7, 8],
 *  [3, 5, 9, 10]
 * ]
 * Given target = 3, return 2.
 */

public class SearchA2DMatrixII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] one = new int[][] {
                {1, 3, 5, 7},
                {2, 4, 7, 8},
                {3, 5, 9, 10}
        };
        System.out.println(solution.searchMatrix(one, 3));
    }
}

class Solution {
    /*
     * @param matrix: A list of lists of integers
     * @param target: An integer you want to search in matrix
     * @return: An integer indicate the total occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
//        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;
        for (int[] row : matrix) {
            if (row[n - 1] < target) {
                continue;
            }
            int start = 0;
            int end = n - 1;
            while (start + 1 < end) {
                int mid = start + (end - 1) / 2;
                if (row[mid] == target) {
                    count++;
                    break;
                } else if (row[mid] < target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
            if (row[start] == target) {
                count++;
            }
            if (row[end] == target) {
                count++;
            }
        }
        return count;
    }
}
