package N.DynamicProgramming.I.Medium.HouseRobber;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/house-robber/
 *
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police.
 *
 * Example:
 * Given [3, 8, 4], return 8.
 *
 * Challenge:
 * O(n) time and O(1) memory.
 */

public class HouseRobber {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] test = new int[][]{
                {3, 8, 4},              // 8
                {1, 1, 1, 1, 1},        // 3
                {1, 2, 3, 4, 5, 6},     // 12
                {1, 2},                 // 2
                {1},                    // 1
                {},                     // 0
                null                    // 0
        };
        for (int[] A : test) {
            System.out.format(
                    "In array %s:%nThe maximum amount of money robbed is %d%n",
                    Arrays.toString(A), solution.houseRobber(A)
            );
        }
    }
}

class Solution {
    /**
     * @param A: An array of non-negative integers.
     *           return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        // O(2) memory
        long[] money = new long[2];
        // Initialization
        money[0] = 0;
        money[1] = A[0];
        // Dynamically look for maximal possible amount of money for current
        // house based on the maximal possible amount of money robbed at
        // house(i - 2) + current house and house(i - 1)
        for (int i = 2; i <= A.length; i++) {
            money[i % 2] = Math.max(money[(i - 1) % 2],
                                    money[(i - 2) % 2] + A[i - 1]);
        }
        return money[A.length % 2];

        /*
        // Regular DP method with O(n) memory
        long[] money = new long[A.length + 1];
        money[0] = 0;
        money[1] = A[0];
        for (int i = 2; i < money.length; i++) {
            money[i] = Math.max(money[i - 1], money[i - 2] + A[i - 1]);
        }
        return money[money.length - 1];
        */
    }
}
