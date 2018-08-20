package N.DynamicProgramming.I.Medium.MaxProductOfCuttingRope;

/**
 * Description
 * Given a rope with positive integer-length n, how to cut the rope into m integer-length parts with length p[0], p[1], ...,p[m-1], in order to get the maximal product of p[0]p[1] ... p[m-1]? m is determined by you and must be greater than 0 (at least one cut must be made). Return the max product you can have.
 *
 * Assumptions
 *
 * n >= 2
 * Examples
 *
 * n = 12, the max product is 3 3 3 3 = 81(cut the rope into 4 pieces with length of each is 3).
 */

public class MaxProductOfCuttingRope {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxProduct(12)); // 81
        System.out.println(solution.maxProduct(25)); // 8748
        System.out.println(solution.maxProduct(4));  // 4
        System.out.println(solution.maxProduct(5));  // 6
    }
}

class Solution {
    public int maxProduct(int length) {
        // Write your solution here
        if (length < 2) {
            return length;
        }
        int[] product = new int[length + 1];
        product[1] = 0;
        product[2] = 1;
        /*
        for (int i = 3; i <= length; i++) {
            for (int j = 0; j < i; j++) {
                product[i] = Math.max(product[i], j * Math.max(i - j, product[i - j]));
            }
        }
        */
        // Optimized method
        for (int i = 3; i < product.length; i++) {
            // At least one of the partitions after getting cut is <= i / 2
            // So we can just pick that partition and find the max product
            for (int j = 1; j <= i / 2; j++) {
                // For the other partition, we can choose either to cut or not to cut the rope
                // So the max number we can get is either i - j or product[i - j]
                product[i] = Math.max(
                        product[i], j * Math.max(i - j, product[i - j]));
            }
        }
        return product[length];
    }
}
