package E.DepthFirstSearch.Medium.CombinationSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/combination-sum/
 *
 * Given a set of candidate numbers (C) and a target number (T), find all unique
 * combinations in C where the candidate numbers sum to T.
 * The same repeated number may be chosen from C unlimited number of times.
 *
 * Notice:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order.
 * (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 *
 * Example:
 * Given candidate set [2,3,6,7] and target 7, a solution set is:
 * [7], [2, 2, 3]
 */

public class CombinationSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test [2, 3, 6, 7] and target 7
        System.out.println(solution.combinationSum(
                new int[] {2, 3, 6, 7}, 7
        ));
        // Test [10, 1, 6, 7, 2, 1, 5] and target 8
        System.out.println(solution.combinationSum(
                new int[] {10, 1, 6, 7, 2, 1, 5}, 8
        ));
    }
}

class Solution {
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null) {
            return result;
        }
        Arrays.sort(candidates);
        findCombination(new ArrayList<Integer>(), candidates, 0, target, result);
        return result;
    }

    private void findCombination(ArrayList<Integer> combination,
                                 int[] candidates,
                                 int startIndex,
                                 int target,
                                 List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            if (i != startIndex && candidates[i] == candidates[i - 1]) {
                // Duplicate result. Ignore the current element and use the
                // previous one
                continue;
            }
            combination.add(candidates[i]);
            findCombination(
                combination, candidates, i, target - candidates[i], result
            );
            combination.remove(combination.size() - 1);
        }
    }
}
