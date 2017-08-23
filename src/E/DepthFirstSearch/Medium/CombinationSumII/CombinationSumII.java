package E.DepthFirstSearch.Medium.CombinationSumII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/combination-sum-ii/
 *
 * Given a collection of candidate numbers (C) and a target number (T), find all
 * unique combinations in C where the candidate numbers sums to T.
 * Each number in C may only be used once in the combination.
 *
 * Notice:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order.
 * (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 *
 * Example:
 * Given candidate set [10,1,6,7,2,1,5] and target 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1 ,1, 6]
 * ]
 */

// The difference between Combination Sum II and Combination Sum is the
// "startIndex" in this one gets incremented every time (just like the one in
// the Subset problem) while it stays the same in Combination Sum.

public class CombinationSumII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test [10, 1, 6, 7, 2, 1, 5] and target 8
        System.out.println(solution.combinationSum2(
                new int[]{10, 1, 6, 7, 2, 1, 5}, 8
        ));
    }
}

class Solution {
    /**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (num == null) {
            return result;
        }
        Arrays.sort(num);
        findCombination(new ArrayList<Integer>(), 0, num, target, result);
        return result;
    }

    private void findCombination(ArrayList<Integer> combination,
                                 int startIndex,
                                 int[] num,
                                 int target,
                                 List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(combination));
            return;
        }
        for (int i = startIndex; i < num.length; i++) {
            if (num[i] > target) {
                break;
            }
            if (i != startIndex && num[i] == num[i - 1]) {
                continue;
            }
            combination.add(num[i]);
            findCombination(
                combination, i + 1, num, target - num[i], result
            );
            combination.remove(combination.size() - 1);
        }
    }
}
