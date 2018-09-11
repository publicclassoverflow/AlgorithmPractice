package N.DynamicProgramming.I.Medium.LongestAscendingSubsequence;

/**
 * Description
 * Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence.
 *
 * Assumptions
 *
 * A is not null
 * Examples
 * Input: A = {5, 2, 6, 3, 4, 7, 5}
 * Output: 4
 * Because [2, 3, 4, 5] is the longest ascending subsequence.
 */

public class LongestAscendingSubsequence {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longest(new int[] {5, 2, 6, 3, 4, 7, 5}));
        // {2, 3, 4, 7} or {2, 3, 4, 5} ==> 4
        System.out.println(solution.longest(new int[] {7, 2, 3, 1, 5, 8, 9, 6}));
        // {2, 3, 5, 8, 9} ==> 5
        System.out.println(solution.longest(new int[] {10, 11, 1, 2, 12 ,3, 4}));
        // {1, 2, 3, 4} ==> 4
        System.out.println(solution.longest(new int[] {1,5,3,2,6,4,3,2,8,4,9,5,5,3,1,6,4,2,7,6,3,8}));
        // TODO: this test case is failed. Expected 8, but was 5
    }
}

class Solution {
    public int longest(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] length = new int[array.length];
        length[0] = 1;
        int maxLength = 1;
        for (int i = 1; i < array.length; i++) {
            length[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (array[i] > array[j]) {
//                    maxLength = Math.max(maxLength, length[j] + 1);
//                    length[i] = maxLength;
                    if (maxLength < length[j] + 1) {
                        length[i] = length[j] + 1;
                        maxLength = length[i];
                        break;
                    } else {
                        length[i] = length[j] + 1;
                    }
                }
            }
        }
        return maxLength;
    }
}
