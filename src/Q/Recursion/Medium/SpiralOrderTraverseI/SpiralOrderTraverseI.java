package Q.Recursion.Medium.SpiralOrderTraverseI;

import java.util.*;

/**
 *
 Description
 Traverse an N N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.

 Assumptions

 The 2D array is not null and has size of N N where N >= 0
 Examples

 { {1,  2,  3},

 {4,  5,  6},

 {7,  8,  9} }

 the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]


 */

public class SpiralOrderTraverseI {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.spiral(new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }));
        System.out.println(solution.spiral(new int[][] {
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9}
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
        spiralTraverse(matrix, result, 0, matrix.length);
        return result;
    }

    private void spiralTraverse(int[][] matrix,
                                 List<Integer> result,
                                 int offset, int length) {
        // Base case: when we reach the last element (in the center)
        if (length == 0) {
            return;
        }
        if (length == 1) {
            result.add(matrix[offset][offset]);
            return;
        }
        // Add the top row, right column, bottom row, and left column respectively
        for (int i = 0; i < length; i++) {
            result.add(matrix[offset][offset + i]);
        }
        for (int i = 1; i < length - 1; i++) {
            result.add(matrix[offset + i][length - 1 + offset]);
        }
        for (int i = length - 1; i >= 0; i--) {
            result.add(matrix[length - 1 + offset][offset + i]);
        }
        for (int i = length - 2; i >= 1; i--) {
            result.add(matrix[offset + i][offset]);
        }
        // Recursively go to one level inside ==> offset + 1, length - 2 (both ends)
        spiralTraverse(matrix, result, offset + 1, length - 2);
    }
}
