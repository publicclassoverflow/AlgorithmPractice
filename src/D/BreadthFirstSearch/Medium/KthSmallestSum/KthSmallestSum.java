package D.BreadthFirstSearch.Medium.KthSmallestSum;

import java.util.*;

/**
 * Given two Given two sorted arrays A and B, with their sizes to be m and n,
 * respectively. We can pick one element a from A and the other element b from B,
 * and their sum s is defined to be s = a + b. How to find k-th smallest s from
 * all possible values of s.
 *
 * Assumption: k < m * n.
 *
 * e.g. A = {1, 3, 5}, B = {2, 3},
 *
 * k = 1, the result is A[0] + B[0] = 3
 *
 * k = 2, the result is A[0] + B[1] = 4
 *
 * k = 3, the result is A[1] + B[0] = 5
 **/

public class KthSmallestSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.kthSmallestSum(
                new int[] {1, 3, 5},
                new int[] {2, 3},
                1
        )); // A[0] + B[0] = 3
        System.out.println(solution.kthSmallestSum(
                new int[] {1, 3, 5},
                new int[] {2, 3},
                2
        )); // A[0] + B[1] = 4
        System.out.println(solution.kthSmallestSum(
                new int[] {1, 3, 5},
                new int[] {2, 3},
                3
        )); // A[1] + B[0] = 5
        System.out.println(solution.kthSmallestSum(
                new int[] {1, 3, 5},
                new int[] {2, 3},
                4
        )); // A[1] + B[1] = 6
        System.out.println(solution.kthSmallestSum(
                new int[] {1, 3, 5},
                new int[] {2, 3},
                5
        )); // A[2] + B[0] = 7
        System.out.println(solution.kthSmallestSum(
                new int[] {1, 1, 1},
                new int[] {1, 2},
                3
        ));
    }
}

class Solution {
    public int kthSmallestSum(int[] A, int[] B, int k) {
        if (A == null || B == null) {
            return 0;
        }
        if (A.length == 0 || B.length == 0) {
            return 0;
        }
        if (k == 1) {
            return A[0] + B[0];
        }
        PriorityQueue<PairSum> minHeap = new PriorityQueue<>(
                k,
                new Comparator<>() {
                    @Override
                    public int compare(PairSum one, PairSum two) {
                        return Integer.compare(one.sum, two.sum);
                    }
                }
        );
        PairSum zeroZero = new PairSum(0, 0, A[0] + B[0]);
        minHeap.offer(zeroZero);
        Set<PairSum> picked = new HashSet<>();
        picked.add(zeroZero);
//        minHeap.offer(new PairSum(0, 0, A[0] + B[0]));
//        boolean[][] picked = new boolean[A.length][B.length];
        for (int i = 0; i < k - 1; i++) {
            PairSum current = minHeap.poll();
            // Go to the next element in A
            if (current.indexA < A.length - 1) {
                PairSum next = new PairSum(
                        current.indexA + 1, current.indexB,
                        A[current.indexA + 1] + B[current.indexB]
                );
                if (!picked.contains((next))) {
//                if (!picked[next.indexA][next.indexB]) {
                    minHeap.offer(next);
                    picked.add(next);
//                    picked[next.indexA][next.indexB] = true;
                }
            }
            // Go to the next element in B
            if (current.indexB < B.length - 1) {
                PairSum next = new PairSum(
                        current.indexA, current.indexB + 1,
                        A[current.indexA] + B[current.indexB + 1]
                );
                if (!picked.contains(next)) {
//                if (!picked[next.indexA][next.indexB]) {
                    minHeap.offer(next);
                    picked.add(next);
//                    picked[next.indexA][next.indexB] = true;
                }
            }
        }
        return minHeap.peek().sum;
    }
}

class PairSum {
    int indexA;
    int indexB;
    int sum;

    public PairSum(int indexA, int indexB, int sum) {
        this.indexA = indexA;
        this.indexB = indexB;
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairSum pairSum = (PairSum) o;
        return indexA == pairSum.indexA &&
                indexB == pairSum.indexB &&
                sum == pairSum.sum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexA, indexB, sum);
    }
}
