package E.DepthFirstSearch.Medium.SubsetsII;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/subsets-ii/
 *
 * Given a list of numbers that may has duplicate numbers, return all possible
 * subsets
 *
 * Notice:
 * Each element in a subset must be in non-descending order.
 * The ordering between two subsets is free.
 * The solution set must not contain duplicate subsets.
 *
 * Example:
 * If S = [1,2,2], a solution is:
 * [
 *   [2],
 *   [1],
 *   [1, 2, 2],
 *   [2, 2],
 *   [1, 2],
 *   []
 * ]
 */

public class SubsetsII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test S = [1,2,2]
        System.out.println(solution.subsetsWithDup(new int[]{1, 2, 2}));
    }
}

class Solution {
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        if (nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        // Sorting the array first is the easiest way to deal with duplicate results
        Arrays.sort(nums);
        findSubsets(new ArrayList<Integer>(), 0, nums, result);
        return result;
    }

    private void findSubsets(ArrayList<Integer> subset,
                             int startIndex,
                             int[] nums,
                             ArrayList<ArrayList<Integer>> result) {
        result.add(new ArrayList<Integer>(subset));
        for (int i = startIndex; i < nums.length; i++) {
            if (i != startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            subset.add(nums[i]);
            findSubsets(subset, i + 1, nums, result);
            subset.remove(subset.size() - 1);
        }
    }
}
