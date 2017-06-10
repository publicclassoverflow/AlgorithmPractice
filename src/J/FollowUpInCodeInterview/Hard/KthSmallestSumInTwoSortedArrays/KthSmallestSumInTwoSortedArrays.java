package J.FollowUpInCodeInterview.Hard.KthSmallestSumInTwoSortedArrays;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/kth-smallest-sum-in-two-sorted-arrays/
 * Given two integer arrays sorted in ascending order and an integer k. Define
 * sum = a + b, where a is an element from the first array and b is an element
 * from the second one. Find the kth smallest sum out of all possible sums.
 *
 * Example:
 * Given [1, 7, 11] and [2, 4, 6].
 * For k = 3, return 7.
 * For k = 4, return 9.
 * For k = 8, return 15.
 */

/**
 * This problem is pretty much the same as the "Kth Smallest Number in Sorted
 * Matrix"
 * https://github.com/publicclassoverflow/LintCode/tree/DivideAndConquer/src/J/FollowUpInCodeInterview/Medium/KthSmallestNumberInSortedMatrix
 *
 * In this problem, since both arrays are sorted, the sum of the two arrays will
 * form a matrix just like the one in the other problem.
 *
 *    B  2    4    6
 *  A
 *  1    3    5    7
 *  7    9   11   13
 * 11   13   15   17
 *
 * But there is no need to compute the sum matrix first
 */

public class KthSmallestSumInTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = new int[]{1, 7, 11};
        int[] B = new int[]{2, 4, 6};
        int[] K = new int[]{-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int k : K) {
            System.out.format(
                    "%dth smallest sum in the arrays is: %d%n",
                    k, solution.kthSmallestSum(A, B, k)
            );
        }
        int[] C = null;
        int[] D = new int[]{};
        int k = 2;
        System.out.format(
                "%dth smallest sum in the arrays is: %d%n",
                k, solution.kthSmallestSum(C, D, k)
        );
    }
}

class Number {
    int x;
    int y;
    int sum;
    public Number(int x, int y, int sum) {
        this.x = x;
        this.y = y;
        this.sum = sum;
    }
}

class NumberComparator implements Comparator<Number> {
    @Override
    public int compare(Number one, Number two) {
        return one.sum - two.sum;
    }
}

class Solution {
    /**
     * @param A an integer arrays sorted in ascending order
     * @param B an integer arrays sorted in ascending order
     * @param k an integer
     * @return an integer
     */
    public int kthSmallestSum(int[] A, int[] B, int k) {
        // Write your code here
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return -1;
        }
        int n = A.length;
        int m = B.length;
        if (k < 1 || k > n * m) {
            return -1;
        }
        // Keep track of the coordinates that have been visited
        HashSet<List<Integer>> checkedCoordinates = new HashSet<>();
        checkedCoordinates.add(Arrays.asList(0, 0));
        // Same trick: maintain a heap k times to get the kth smallest sum
        PriorityQueue<Number> minHeap =
                new PriorityQueue<>(k, new NumberComparator());
        // Offer the sum of the first numbers
        minHeap.offer(new Number(0, 0, A[0] + B[0]));
        // Direction arrays for traversal
        int[] dx = new int[]{0, 1};
        int[] dy = new int[]{1, 0};
        for (int i = 0; i < k - 1; i++) {
            Number current = minHeap.poll();
            for (int j = 0; j < dx.length; j++) {
                int nextX = current.x + dx[j];
                int nextY = current.y + dy[j];
                Number next = new Number(nextX, nextY, 0);
                List<Integer> coordinates = Arrays.asList(nextX, nextY);
                if (nextX < n && nextY < m
                        && !checkedCoordinates.contains(coordinates)) {
                    checkedCoordinates.add(coordinates);
                    next.sum = A[nextX] + B[nextY];
                    minHeap.offer(next);
                }
            }
        }
        return minHeap.poll().sum;
    }
}

