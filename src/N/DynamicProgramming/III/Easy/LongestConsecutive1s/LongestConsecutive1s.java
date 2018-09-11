package N.DynamicProgramming.III.Easy.LongestConsecutive1s;

/**
 * Description
 * Given an array containing only 0s and 1s, find the length of the longest subarray of consecutive 1s.
 *
 * Assumptions
 *
 * The given array is not null
 * Examples
 *
 * {0, 1, 0, 1, 1, 1, 0}, the longest consecutive 1s is 3.
 */

public class LongestConsecutive1s {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longest(new int[] {0, 1, 0, 1, 1, 1, 0}));
    }
}

class Solution {
    public int longest(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 0;
        }
        int ones = array[0];
        int maxOnes = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 0) {
                ones = 0;
            } else {
                ones++;
                maxOnes = Math.max(maxOnes, ones);
            }
        }
        return maxOnes;
    }
}
