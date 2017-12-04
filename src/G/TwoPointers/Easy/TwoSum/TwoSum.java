package G.TwoPointers.Easy.TwoSum;

import java.util.Arrays;
import java.util.HashMap;

/**
 * http://www.lintcode.com/en/problem/two-sum/
 * Given an array of integers, find two numbers such that they add up to a
 * specific target number.
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are NOT zero-based.
 *
 * Notice:
 * You may assume that each input would have exactly one solution
 *
 * Example:
 * numbers=[2, 7, 11, 15], target=9
 * return [1, 2]
 *
 * Challenge:
 * Either of the following solutions are acceptable:
 * O(n) Space, O(nlogn) Time
 * O(n) Space, O(n) Time
 */

public class TwoSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 0
        int[] zero = new int[]{2, 7, 11, 15};
        System.out.println(Arrays.toString(solution.twoSum(zero, 9)));
    }
}

class Solution {
    /*
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        int[] result = new int[2];
        if (numbers == null || numbers.length == 0) {
            return result;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                result[0] = map.get(numbers[i]) + 1;
                result[1] = i + 1;
                break;
            }
            map.put(target - numbers[i], i);
        }
        return result;
    }
}
