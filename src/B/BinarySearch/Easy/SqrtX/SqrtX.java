package B.BinarySearch.Easy.SqrtX;

/**
 * http://www.lintcode.com/en/problem/sqrtx/
 * Implement int sqrt(int x)
 * Compute and return the square root of x.
 *
 * Example
 * sqrt(3) = 1
 * sqrt(4) = 2
 * sqrt(5) = 2
 * sqrt(10) = 3
 */

public class SqrtX {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] target = new int[]{-2, 0, 1, 2, 3, 4, 5, 10, 11111};
        for (int i = 0; i < target.length; i++) {
            System.out.println(solution.sqrt(target[i]));
        }
    }
}

class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
        if (x < 0) {
            return -1;
        }
        // if (x == 0 || x == 1) {
        if (x < 2) {
            return x;
        }
        long start = 0;
        long end = x;

        while (start + 1 < end) {
            long mid = (end - start) / 2 + start;
            if (mid * mid <= x) {
                start = mid;
            } else {
                end = mid;
            }
        }

        // If start < end <= target, return end
        if (end * end <= x) {
            return (int) end;
        }
        return (int) start;
    }
}
