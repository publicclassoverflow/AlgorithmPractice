package H.DataStructure.Medium.TopKLargestNumbers;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * http://www.lintcode.com/en/problem/top-k-largest-numbers/
 * Given an integer array, find the top k largest numbers in it.
 * Example:
 * Given [3,10,1000,-99,4,100] and k = 3.
 * Return [1000, 100, 10].
 */

// This problem is similar to the K Closest Points problem

public class TopKLargestNumbers {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test
        System.out.println(Arrays.toString(solution.topk(
                new int[]{3, 10, 1000, -99, 4, 100}, 3
        )));
    }
}

class Solution {
    /*
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        // write your code here
        if (nums == null) {
            return new int[] {};
        }
        // Build a min heap of which the size is limited to k
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // Put the elements in the min heap into the result array inversely
        int[] result = new int[k];
        while (!minHeap.isEmpty()) {
            result[--k] = minHeap.poll();
        }
        return result;
    }
}
