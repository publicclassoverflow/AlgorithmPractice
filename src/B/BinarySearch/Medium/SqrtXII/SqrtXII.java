package B.BinarySearch.Medium.SqrtXII;

/**
 * http://www.lintcode.com/en/problem/sqrtx-ii/
 *
 * Implement double sqrt(double x) and x >= 0.
 * Compute and return the square root of x.
 *
 * Example
 * Given n = 2 return 1.41421356
 *
 * It seems that this problem requires at least 8 significant numbers.
 * I personally prefer the first method because it is classical binary search
 * whereas Newton's method is just too sublime to be a good practice
 */
public class SqrtXII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        double[] x = new double[]{0.0, 1.0, 2.0, 3.0, 8.0, 10.0};
        for (double num : x) {
            System.out.format("Square root of %.1f is %.8f\n",
                              num, solution.sqrt(num));
        }
    }
}

class Solution {
    /**
     * @param x a double
     * @return the square root of x
     */
    public double sqrt(double x) {
        // Write your code here
        // Method #1: Binary Search
        if (x < 0) {
            return -1;
        }
        double start = 0.0;
        // If x is between 0 and 1, end needs to be set to 1
        double end = (x < 1.0) ? 1.0 : x;
        double precision = 1e-10;
        while (start + precision < end) {
            double mid = (end - start) / 2 + start;
            if (mid * mid < x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return start;

        /*
        // Method #2: Newton's method
        double result = 1.0;
        double precision = 1e-10;
        while (Math.abs(result * result - x) > precision) {
            result = (result + x / result) / 2;
        }
        return result;
        */
    }
}
