package F.LinkedListAndArray.Medium.SubarraySumClosest;

import java.util.Arrays;
import java.util.Comparator;

/**
 * http://www.lintcode.com/en/problem/subarray-sum-closest/
 * Given an integer array, find a subarray with sum closest to zero. Return the
 * indexes of the first number and last number.
 *
 * Example:
 * Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4].
 */

public class SubarraySumClosest {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.subarraySumClosest(new int[]{
                -3, 1, 1, -3, 5
        });
        System.out.format("%d %d", result[0], result[1]);
    }
}

class Pair {
    int sum;
    int index;
    public Pair(int sum, int index) {
        this.sum = sum;
        this.index = index;
    }
}

class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        // write your code here
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result;
        }
        if (nums.length == 1) {
            result[0] = 0;
            result[1] = 0;
            return result;
        }
        Pair[] prefixSum = new Pair[nums.length + 1];
        int previousSum = 0;
        prefixSum[0] = new Pair(0, 0);
        for (int i = 1; i <= nums.length; i++) {
            int currentSum = previousSum + nums[i - 1];
            prefixSum[i] = new Pair(currentSum, i);
            previousSum = currentSum;
        }
        /*
        Arrays.sort(prefixSum, new Comparator<Pair>() {
            @Override
            public int compare(Pair one, Pair two) {
                return one.sum - two.sum;
            }
        });
        */
        // Using lambda
        Arrays.sort(prefixSum, (one, two) -> one.sum - two.sum);
        // The line below uses the Comparator's comparing method
        // Arrays.sort(prefixSum, Comparator.comparingInt(one -> one.sum));
        int closest = Integer.MAX_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            int diffSum = prefixSum[i].sum - prefixSum[i - 1].sum;
            if (closest > diffSum) {
                closest = diffSum;
                int[] indices = new int[]{
                        prefixSum[i].index - 1, prefixSum[i - 1].index - 1
                };
                Arrays.sort(indices);
                // The lower bound is inclusive because prefix sum literally
                // starts from there
                result[0] = indices[0] + 1;
                result[1] = indices[1];
            }
        }
        return result;
    }
}

