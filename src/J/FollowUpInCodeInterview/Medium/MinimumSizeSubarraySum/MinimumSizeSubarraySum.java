package J.FollowUpInCodeInterview.Medium.MinimumSizeSubarraySum;


import java.util.HashMap;

/**
 * http://www.lintcode.com/en/problem/minimum-size-subarray-sum/
 * Given an array of n positive integers and a positive integer s, find the
 * minimal length of a subarray of which the sum ≥ s. If there isn't one,
 * return -1 instead.
 *
 * Example
 * Given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal
 * length under the problem constraint.
 */
public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        HashMap<int[], Integer> arg = new HashMap<>();
        arg.put(new int[]{2, 3, 1, 2, 4, 3}, 7);
        arg.put(new int[]{2, 3, 2, 1, 4, 2}, 8);
        arg.put(new int[]{1, 2}, 4);
        arg.put(null, 0);
        arg.forEach((nums, target) -> System.out.format(
                "The minimum size of the subarray that has a sum of %d is: %d%n",
                target, solution.minimumSize(nums, target)
        ));
    }
}

class Solution {
    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int j = 0;
        int sum = 0;
        int size = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length) {
                if ((sum += nums[j]) < s) {
                    j++;
                    continue;
                }
                size = Math.min(size, j - i + 1);
                sum -= nums[i] + nums[j];
                break;
            }
        }
        return size == Integer.MAX_VALUE ? -1 : size;
    }
}
