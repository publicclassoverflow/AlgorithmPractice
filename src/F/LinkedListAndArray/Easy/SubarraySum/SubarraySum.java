package F.LinkedListAndArray.Easy.SubarraySum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/subarray-sum/
 *
 * Given an integer array, find a subarray where the sum of numbers is zero.
 * Your code should return the index of the first number and the index of the
 * last number.
 *
 * Notice:
 * There is at least one subarray of which sum equals to zero
 *
 * Example:
 * Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].
 */

public class SubarraySum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<Integer> resultOne = solution.subarraySum(new int[]{
                -3, 1, 2, -3, 4
        });
        for (int number : resultOne) {
            System.out.print(number + " ");
        }
        System.out.println();

        ArrayList<Integer> resultTwo = solution.subarraySum(new int[]{
                -5, 10, 5, -3, 1, 1, 1, -2, 3, -4
        });
        for (int number : resultTwo) {
            System.out.print(number + " ");
        }
        System.out.println();

        ArrayList<Integer> resultThree = solution.subarraySum(new int[]{-1, 1});
        for (int number : resultThree) {
            System.out.print(number + " ");
        }
        System.out.println();

        ArrayList<Integer> resultFour = solution.subarraySum(new int[]{});
        for (int number : resultFour) {
            System.out.print(number + " ");
        }
        System.out.println();

        ArrayList<Integer> resultFive = solution.subarraySum(new int[]{0});
        for (int number : resultFive) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}

class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // -1 is needed to deal with the case where sum == 0
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                // When the two sums are equal, it means that the subarray from
                // [previousIndex, currentIndex] has a sum of 0
                result.add(map.get(sum) + 1); // +1 because this is the prefix
                result.add(i);
                return result;
            }
            map.put(sum, i);
        }
        return result;
    }
}
