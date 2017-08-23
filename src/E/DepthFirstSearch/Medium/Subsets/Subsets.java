package E.DepthFirstSearch.Medium.Subsets;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/subsets/
 *
 * Given a set of distinct integers, return all possible subsets.
 *
 * Example:
 * If S = [1,2,3], a solution is:
 * [
 *  [3],
 *  [1],
 *  [2],
 *  [1,2,3],
 *  [1,3],
 *  [2,3],
 *  [1,2],
 *  []
 * ]
 * */

// I did it only recursively

public class Subsets {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test nums = [1, 3, 2]
        System.out.println(solution.subsets(new int[]{1, 3, 2}));
    }
}

class Solution {
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        if (nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        Arrays.sort(nums);
        getSubsets(new ArrayList<>(), nums, 0, result);
        return result;
    }

    private void getSubsets(ArrayList<Integer> subset,
                            int[] nums,
                            int startIndex,
                            ArrayList<ArrayList<Integer>> result) {
        result.add(new ArrayList<>(subset));
        for (int i = startIndex; i < nums.length; i++) {
            subset.add(nums[i]);
            getSubsets(subset, nums, i + 1, result);
            subset.remove(subset.size() - 1);
        }
    }
}
