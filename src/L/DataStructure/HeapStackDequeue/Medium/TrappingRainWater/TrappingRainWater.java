package L.DataStructure.HeapStackDequeue.Medium.TrappingRainWater;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/trapping-rain-water/
 *
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 *
 * Example:
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */

public class TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] heights = new int[][]{{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1},
                                      {3, 0, 1},
                                      {1, 0, 0},
                                      {0, 0, 0},
                                      null,
                                      {}};
        for (int[] bars : heights) {
            System.out.format(
                    "Rain water trapped in bars %s:%n%d%n",
                    Arrays.toString(bars), solution.trapRainWater(bars)
            );
        }
    }
}

class Solution {
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        // write your code here
        if (heights == null || heights.length < 3) {
            return 0;
        }
        int reservoir = 0;
        // Two-pointers
        int left = 0;
        int right = heights.length - 1;
        int leftHeight = heights[left];
        int rightHeight = heights[right];
        while (left < right) {
            if (leftHeight < rightHeight) { // Move the left pointer
                left++;
                if (leftHeight > heights[left]) {
                    reservoir += leftHeight - heights[left];
                } else {
                    // Update leftHeight
                    leftHeight = heights[left];
                }
            } else {                        // Move the right pointer
                right--;
                if (rightHeight > heights[right]) {
                    reservoir += rightHeight - heights[right];
                } else {
                    rightHeight = heights[right];
                }
            }
        }
        return reservoir;
    }
}
