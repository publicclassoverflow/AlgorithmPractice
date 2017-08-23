package E.DepthFirstSearch.Medium.Permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/permutations/
 *
 * Given a list of numbers, return all possible permutations.
 *
 * Notice:
 * You can assume that there is no duplicate numbers in the list.
 *
 * Example:
 * For nums = [1,2,3], the permutations are:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */

public class Permutations {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.permute(new int[]{1, 2, 3}));
    }
}

class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
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
        // Method 1: for-each loop
        // HashSet records the appearance of elements in the array
        for (int element : nums) {
            if (hadSeen.contains(element)) {
                continue;
            }
            hadSeen.add(element);
            permutation.add(element);
            findPermutations(permutation, hadSeen, nums, result);
            hadSeen.remove(element);
            permutation.remove(permutation.size() - 1);
        }
        // Method 2: for loop
        // HashSet records the appearance of the indices
        /*
        for (int i = 0; i < nums.length; i++) {
            if (hadSeen.contains(i)) {
                continue;
            }
            hadSeen.add(i);
            permutation.add(nums[i]);
            findPermutations(permutation, hadSeen, nums, result);
            hadSeen.remove(i);
            permutation.remove(permutation.size() - 1);
        }
        */
    }
}

