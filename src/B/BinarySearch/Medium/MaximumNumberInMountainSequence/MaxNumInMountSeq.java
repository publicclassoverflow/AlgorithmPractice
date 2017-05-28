package B.BinarySearch.Medium.MaximumNumberInMountainSequence;

import java.util.HashMap;

/**
 * http://www.lintcode.com/en/problem/maximum-number-in-mountain-sequence/
 * Given a mountain sequence of n integers which increase firstly and then
 * decrease, find the mountain top.
 *
 * Example
 * Given nums = [1, 2, 4, 8, 6, 3] return 8
 * Given nums = [10, 9, 8, 7], return 10
 */

public class MaxNumInMountSeq {
    public static void main(String[] args) {
        Solution solution = new Solution();
        HashMap<Integer, int[]> arg = new HashMap<>();
        arg.put(8, new int[]{1, 2, 4, 8, 6, 3});
        arg.put(10, new int[]{10, 9, 8, 7});
        arg.put(2, new int[]{1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1});
        arg.forEach((target, nums) -> System.out.format(
                    "The result should be %d. The function returned %d\n",
                    target, solution.mountainSequence(arg.get(target))));
    }
}

class Solution {
    /**
     * @param nums a mountain sequence which increase firstly and then decrease
     * @return then mountain top
     */
    public int mountainSequence(int[] nums) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            // If the mountain is still going up
            if (nums[mid] > nums[mid - 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] >= nums[end]) {
            return nums[start];
        }
        return nums[end];
    }
}
