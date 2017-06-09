package J.FollowUpInCodeInterview.Medium.KthSmallestNumberInSortedMatrix;

import java.util.*;

/**
 * Documentation of Java Priority Queue:
 * https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html
 */

/**
 * http://www.lintcode.com/en/problem/kth-smallest-number-in-sorted-matrix/
 * Find the kth smallest number in at row and column sorted matrix.
 *
 * Example
 * Given k = 4 and a matrix:
 * [
 *   [1 ,5 ,7],
 *   [3 ,7 ,8],
 *   [4 ,8 ,9],
 * ]
 * return 5
 */

public class KthSmallestNumberInSortedMatrix {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{{1, 5, 7},
                                     {3, 7, 8},
                                     {4, 8, 9}};
        int targets[] = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int target : targets) {
            System.out.format(
                    "The %dth smallest number is: %d%n",
                    target, solution.kthSmallest(matrix, target)
            );
        }
    }
}

class Number {
    public int x, y;
    public int val;
    public Number(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

class NumberComparator implements Comparator<Number> {
    @Override
    public int compare(Number numOne, Number numTwo) {
        return numOne.val - numTwo.val;
    }
}

class Solution {
    /*
     * @param matrix: a matrix of integers
     * @param k:      an integer
     * @return: the kth smallest number in the matrix
     */
    public int kthSmallest(int[][] matrix, int k) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            // return -1;
            // LintCode asks for 0, however I think -1 should be a better option
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        if (k < 1 || k > n * m) {
            // return -1;
            return 0;
        }
        // Trick for going horizontally and vertically
        int[] dx = new int[]{0, 1}; // Previous coordinate + (0, 1)
        int[] dy = new int[]{1, 0}; // Previous coordinate + (1, 0)
        // Use the comparator constructor to create the heap
        PriorityQueue<Number> minHeap =
                new PriorityQueue<>(k, new NumberComparator());
        // Add the first element in the matrix (0, 0) to the heap
        minHeap.offer(new Number(0, 0, matrix[0][0]));
        // Use a HashSet to keep track of the coordinates that have been checked
        HashSet<List<Integer>> checkedCoordinates = new HashSet<>();
        checkedCoordinates.add(Arrays.asList(0, 0));
        /*
        // An easier way of using a matrix as a fake hash table:
        // boolean[][] hash = new boolean[n][m];
        // hash[0][0] = true;
        */
        // Maintain the heap for (k - 1) times (the first number is maintained
        // already) to get the kth smallest number
        for (int i = 0; i < k - 1; i++) {
            Number current = minHeap.poll();
            // Matrix traversal: horizontal first, then vertical
            for (int j = 0; j < dx.length; j++) {
                int nextX = current.x + dx[j];
                int nextY = current.y + dy[j];
                // nextX and nextY may be out of bounds
                // So the "next" Number object needs to be instantiated with a
                // value of 0 which can be updated later
                Number next = new Number(nextX, nextY, 0);
                List<Integer> coordinate = Arrays.asList(nextX, nextY);
                if (nextX < n && nextY < m
                        && !checkedCoordinates.contains(coordinate)) {
                        // && !hash[nextX][nextY]) { // Fake hash table
                    checkedCoordinates.add(coordinate);
                    // hash[nextX][nextY] = true;
                    // Update the value of next since it is in bound
                    next.val = matrix[nextX][nextY];
                    minHeap.offer(next);
                }
            }
        }
        // return minHeap.peek().val;
        return minHeap.poll().val;
    }
}
