public class Driver {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{
                            {1, 3, 5, 7},
                            {10, 11, 16, 20},
                            {23, 30, 34, 50}};
        int t = 3, tt = 20;
        int f = 21, ff = 0, fff = 100;
        System.out.println(solution.searchMatrix(matrix, t));
        System.out.println(solution.searchMatrix(matrix, tt));
        System.out.println(solution.searchMatrix(matrix, f));
        System.out.println(solution.searchMatrix(matrix, ff));
        System.out.println(solution.searchMatrix(matrix, fff));

        int [][] empty = new int[][]{{}};
        int ffff = 0;
        System.out.println(solution.searchMatrix(matrix, ffff));
    }
}

class Solution {
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int startRow = 0, endRow = row - 1;
        int startCol = 0, endCol = col - 1;

        // Search for the row to which the targe belongs
        while (startRow + 1 < endRow) {
            int midRow = startRow + (endRow - startRow) / 2;
            if (matrix[midRow][0] == target) {
                return true;
            } else if (matrix[midRow][0] < target) {
                startRow = midRow;
            } else {
                endRow = midRow;
            }
        }
        // If target is greater than the first element of the endRow
        // it is in the endRow
        // If target is greater than the first element of the startRow
        // it is in the startRow
        // Otherwise, it is not absent
        if (matrix[endRow][0] <= target) {
            row = endRow;
        } else if (matrix[startRow][0] <= target) {
            row = startRow;
        } else {
            return false;
        }

        // Search for the exact position of the target
        while (startCol + 1 < endCol) {
            int midCol = startCol + (endCol - startCol) / 2;
            if (matrix[row][midCol] == target) {
                return true;
            } else if (matrix[row][midCol] < target) {
                startCol = midCol;
            } else {
                endCol = midCol;
            }
        }

        // The arrays are sorted in ascending order
        return matrix[row][startCol] == target
            || matrix[row][endCol] == target;
    }
}
