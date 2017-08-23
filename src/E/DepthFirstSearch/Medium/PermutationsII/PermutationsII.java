package E.DepthFirstSearch.Medium.PermutationsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/permutations-ii/
 *
 * Given a list of numbers with duplicate number in it. Find all unique
 * permutations.
 *
 * Example:
 * For numbers [1,2,2] the unique permutations are:
 * [
 *   [1,2,2],
 *   [2,1,2],
 *   [2,2,1]
 * ]
 */

public class PermutationsII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.permuteUnique(new int[]{1, 2, 2}));
    }
}

class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // Write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        Arrays.sort(nums); // To avoid duplicate result
        findPermutations(
            new ArrayList<Integer>(), new HashSet<Integer>(), nums, result
        );
        return result;
    }

    private void findPermutations(ArrayList<Integer> permutation,
                                  HashSet<Integer> hadSeen,
                                  int[] nums,
                                  List<List<Integer>> result) {
        if (permutation.size() == nums.length) {
            result.add(new ArrayList<Integer>(permutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (hadSeen.contains(i)) {
                continue;
            }
            if (i > 0 && !hadSeen.contains(i - 1) && nums[i] == nums[i - 1]) {
                continue;
            }
            hadSeen.add(i);
            permutation.add(nums[i]);
            findPermutations(permutation, hadSeen, nums, result);
            hadSeen.remove(i);
            permutation.remove(permutation.size() - 1);
        }
    }
}
