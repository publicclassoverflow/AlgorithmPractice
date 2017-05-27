package B.BinarySearch.Easy.LastPositionOfTarget;

/**
 * http://www.lintcode.com/en/problem/last-position-of-target/
 *
 * Find the last position of a target number in a sorted array. Return -1 if target does not exist.
 *
 * Example
 * Given [1, 2, 2, 4, 5, 5].
 * For target = 2, return 2.
 * For target = 5, return 5.
 * For target = 6, return -1.
 */
public class LastPositionOfTarget {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2, 2, 4, 5, 5};
        int[] targets = new int[]{0, 2, 5, 6};
        for (int target : targets) {
            System.out.format("For target = %d, return %d\n",
                              target, solution.lastPosition(nums, target));
        }
    }
}

class Solution {
    /**
     * @param nums:   An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int lastPosition(int[] nums, int target) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        // Check the "last" position first
        if (nums[end] == target) {
            return end;
        }
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }
}
