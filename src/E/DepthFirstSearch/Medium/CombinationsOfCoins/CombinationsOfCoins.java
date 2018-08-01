package E.DepthFirstSearch.Medium.CombinationsOfCoins;

/**
 * Description
 * Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents), get all the possible ways to pay a target number of cents.
 *
 * Arguments
 *
 * coins - an array of positive integers representing the different denominations of coins, there are no duplicate numbers and the numbers are sorted by descending order, eg. {25, 10, 5, 2, 1}
 * target - a non-negative integer representing the target number of cents, eg. 99
 * Assumptions
 *
 * coins is not null and is not empty, all the numbers in coins are positive
 * target >= 0
 * You have infinite number of coins for each of the denominations, you can pick any number of the coins.
 * Return
 *
 * a list of ways of combinations of coins to sum up to be target.
 * each way of combinations is represented by list of integer, the number at each index means the number of coins used for the denomination at corresponding index.
 * Examples
 *
 * coins = {2, 1}, target = 4, the return should be
 *
 * [
 *
 *   [0, 4],   (4 cents can be conducted by 0 2 cents + 4 1 cents)
 *
 *   [1, 2],   (4 cents can be conducted by 1 2 cents + 2 1 cents)
 *
 *   [2, 0]    (4 cents can be conducted by 2 2 cents + 0 1 cents)
 *
 * ]
 **/

import java.util.*;

public class CombinationsOfCoins {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.combinations(99, new int[]{1, 5, 10, 25}));
    }
}

class Solution {
    public List<List<Integer>> combinations(int target, int[] coins) {
        // Write your solution here
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        combineCoins(target, coins, 0, combination, result);
        return result;
    }

    private void combineCoins(int target, int[] coins, int index,
                              List<Integer> combination,
                              List<List<Integer>> result) {
        // Terminate when we are about to finish picking the coins
        /*
        if (index == coins.length - 1) {
            if (target % coins[coins.length - 1] == 0) {
                combination.add(target / coins[coins.length - 1]);
                result.add(new ArrayList<>(combination));
                combination.remove(combination.size() - 1);
            }
            return;
        }
        */
        if (index == coins.length) {
            if (target == 0) {
                result.add(new ArrayList<>(combination));
            }
            return;
        }
        // For each coins (coins[index]), we may choose 0, 1, 2, ...,
        // target / coins[index] number of coins
        int max = target / coins[index];
        for (int i = 0; i <= max; i++) {
            combination.add(i);
            // Update the remaining number of cents and move on
            combineCoins(target - i * coins[index], coins, index + 1,
                    combination, result);
            // Back tracking
            combination.remove(combination.size() - 1);
        }
    }
}

