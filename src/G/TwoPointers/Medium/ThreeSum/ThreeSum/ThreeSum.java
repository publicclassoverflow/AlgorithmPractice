package G.TwoPointers.Medium.ThreeSum.ThreeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/3sum/
 * Given an array S of n integers, are there elements a, b, c in S such that
 * a + b + c = 0? Find all unique triplets in the array which gives the sum of
 * zero.
 *
 * Example:
 * For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:
 * (-1, 0, 1)
 * (-1, -1, 2)
 */

public class ThreeSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 0
        int[] zero = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(solution.threeSum(zero));
        // Test 1
        int[] one = new int[]{2, 7, 11, 15};
        System.out.println(solution.threeSum(one));
        // Test 2
        int[] two = new int[]{1, 0, -1, -1, -1, -1, 0, 1, 1, 1};
        // This array produced duplicate triplets because I didn't skip the
        // possible duplicate elements after one triplet had been encountered
        System.out.println(solution.threeSum(two));
    }
}

class Solution {
    /*
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] numbers) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (numbers == null || numbers.length == 0) {
            return result;
        }
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 2; i++) {
            // Skip duplicate elements
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            // Do two-sum for every sub array from index left to right with
            // target value of numbers[i]
            int left = i + 1;
            int right = numbers.length - 1;
            while (left < right) {
                if (numbers[left] + numbers[right] + numbers[i] > 0) {
                    right--;
                } else if (numbers[left] + numbers[right] + numbers[i] < 0) {
                    left++;
                } else {
                    result.add(new ArrayList<>(Arrays.asList(
                            numbers[i], numbers[left++], numbers[right--]
                    )));
                    // Skip duplicate elements
                    while (left < right && numbers[left] == numbers[left - 1]) {
                        left++;
                    }
                    while (left < right && numbers[right] == numbers[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
