package N.DynamicProgramming.I.Medium.ArrayHopperII;

/**
 * Description
 * Given an array A of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the maximum jump distance from index i (you can only jump towards the end of the array). Determine the minimum number of jumps you need to reach the end of array. If you can not reach the end of the array, return -1.
 *
 * Assumptions
 *
 * The given array is not null and has length of at least 1.
 * Examples
 *
 * {3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the end of array)
 *
 * {2, 1, 1, 0, 2}, you are not able to reach the end of array, return -1 in this case.
 */

public class ArrayHopperII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minJump(new int[] {3, 3, 1, 0, 4}));
        System.out.println(solution.minJump(new int[] {2, 1, 1, 0, 2}));
        System.out.println(solution.minJump(new int[] {13,52,42,21,58}));
    }
}

class Solution {
    public int minJump(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return -1;
        }
        int n = array.length;
        int[] jumps = new int[n];
        jumps[n - 1] = 0;
        for (int i = n - 2; i >=0; i--) {
            if (array[i] == 0) {
//                jumps[i] = Integer.MIN_VALUE;
                jumps[i] = -1;
                continue;
            }
            int minJump = Integer.MAX_VALUE;
            for (int j = 1; j <= array[i]; j++) {
                if (i + j > n - 1) {
                    // We are out of bounds
                    break;
                }
//                if (jumps[i + j] == Integer.MIN_VALUE) {
                if (jumps[i + j] == -1) {
                    // We cannot get to the end from current position
                    continue;
                }
                minJump = Math.min(minJump, jumps[i + j]);
            }
            jumps[i] = minJump == Integer.MAX_VALUE ? -1 : minJump + 1;
        }
//        return jumps[0] == Integer.MIN_VALUE ? -1 : jumps[0];
        return jumps[0];
    }
}
