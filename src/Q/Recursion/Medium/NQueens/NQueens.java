package Q.Recursion.Medium.NQueens;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * Get all valid ways of putting N Queens on an N * N chessboard so that no two Queens threaten each other.
 *
 * Assumptions
 *
 * N > 0
 * Return
 *
 * A list of ways of putting the N Queens
 * Each way is represented by a list of the Queen's y index for x indices of 0 to (N - 1)
 * Example
 *
 * N = 4, there are two ways of putting 4 queens:
 *
 * [1, 3, 0, 2] --> the Queen on the first row is at y index 1, the Queen on the second row is at y index 3, the Queen on the third row is at y index 0 and the Queen on the fourth row is at y index 2.
 *
 * [2, 0, 3, 1] --> the Queen on the first row is at y index 2, the Queen on the second row is at y index 0, the Queen on the third row is at y index 3 and the Queen on the fourth row is at y index 1.
 *
 *
 *
 *
 *
 * Medium
 * Backtracking
 * Depth First Search
 */

public class NQueens {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.nquees(4));
    }
}

class Solution {
    public List<List<Integer>> nquees(int n) {
        // Write your solution here
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        dfsPlaceQueens(n, new ArrayList<>(), result);
        return result;
    }

    private void dfsPlaceQueens(int n, List<Integer> current, List<List<Integer>> result) {
        // Base case: when we have placed all queens onto the board
        if (n == current.size()) {
            result.add(new ArrayList<>(current));
            return;
        }
        // Try to place one queen (0th to (n-1)th) in one row (0th to (n-1)th) at a time
        // For each row (ith row), there are n columns to try
        // So, we basically just check if placing the queen at the i-th column of current row
        // is legal
        for (int i = 0; i < n; i++) {
            if (canPlaceQueen(current, i)) {
                current.add(i);
                dfsPlaceQueens(n, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean canPlaceQueen(List<Integer> current, int col) {
        int row = current.size();
        for (int i = 0; i < row; i++) {
            if (current.get(i) == col || Math.abs(current.get(i) - col) == row - i) {
                return false;
            }
        }
        return true;
    }
}
