package D.BreadthFirstSearch.Medium.KthSmallestNumberInSortedMatrix;

import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * https://app.laicode.io/app/problem/26
 *
 * Description
 * Given a matrix of size N x M. For each row the elements are sorted in ascending order, and for each column the elements are also sorted in ascending order. Find the Kth smallest number in it.
 *
 * Assumptions
 *
 * the matrix is not null, N > 0 and M > 0
 * K > 0 and K <= N * M
 * Examples
 *
 * { {1,  3,   5,  7},
 *
 *   {2,  4,   8,   9},
 *
 *   {3,  5, 11, 15},
 *
 *   {6,  8, 13, 18} }
 *
 * the 5th smallest number is 4
 * the 8th smallest number is 6
 **/

public class KthSmallestNuberInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        // Write your solution here
        // 1. Assumption: the matrix should not be null or empty
        // 2. Algorithm:
        //    Use a minHeap of size k to store the smallest k elements
        //    add matrix[0][0], which is the smallest elements among all, to the minHeap first
        //    then expand its two neighbors to do a Best-First-Search. For index (i, j),
        //    the two neighbors are matrix[i + 1][j] and matrix[i][j + 1].
        //    When the minHeap is popped for the k-th time, we get the solution
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0 || k <= 0) {
            return -1;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        // Quick check for possible quick exit
        if (k == 1) {
            return matrix[0][0];
        } else if (k == rows * cols) {
            return matrix[rows - 1][cols - 1];
        }
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(k);
        // Build a new Cell with matrix[0][0] and add it to the heap
        Cell first = new Cell(0, 0, matrix[0][0]);
        minHeap.offer(first);
        // Keep track of the cells visited in order to avoid duplications
//        boolean[][] visited = new boolean[rows][cols];
        Set<Cell> visited = new HashSet<>();
        visited.add(first);
        // Because there is already an element in the heap, we need to do another (k - 1) iterations
        // before we can pop the k-th element
        for (int i = 0; i < k - 1; i++) {
            Cell curr = minHeap.poll();
            // Go to the neighbor below
            if (curr.row + 1 < rows) { // inbound
                Cell next = new Cell(curr.row + 1, curr.col, matrix[curr.row + 1][curr.col]);
                if (!visited.contains(next)) {
                    minHeap.offer(next);
                    visited.add(next);
                }
            }
            // Go to the neighbor on the right
            if (curr.col + 1 < cols) { // inbound
                Cell next = new Cell(curr.row, curr.col + 1, matrix[curr.row][curr.col + 1]);
                if (!visited.contains(next)) {
                    minHeap.offer(next);
                    visited.add(next);
                }
            }
        }
        // A total of (k - 1) elements have been popped out of the heap
        // So, the current top of the heap is the k-th smallest element
        return minHeap.peek().val;
    }
}

class Cell implements Comparable<Cell> {
    int row;
    int col;
    int val;
    public Cell(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }

    @Override
    public int compareTo(Cell other) {
        // Because the val field is Integer
        // We can use Integer.compare()
        // return Integer.compare(this.val, another.val);
        // Otherwise we can also implement it manually
        // /*
        if (this.val == other.val) {
            return 0;
        }
        return this.val < other.val ? -1 : 1;
        // */
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Cell other = (Cell) obj;
        return this.row == other.row &&
                this.col == other.col &&
                this.val == other.val;
    }

    @Override
    public int hashCode() {
        // https://docs.oracle.com/javase/8/docs/api/java/util/Objects.html#hash-java.lang.Objects
        return Objects.hash(row, col, val);
    }
}
