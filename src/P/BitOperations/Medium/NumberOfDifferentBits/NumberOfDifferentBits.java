package P.BitOperations.Medium.NumberOfDifferentBits;

/**
 * Description
 * Determine the number of bits that are different for two given integers.
 *
 * Examples
 *
 * 5(“0101”) and 8(“1000”) has 3 different bits
 */

public class NumberOfDifferentBits {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.diffBits(5, 6));
        System.out.println(solution.diffBits(-1, Integer.MAX_VALUE));
    }
}

class Solution {
    public int diffBits(int a, int b) {
        // Write your solution here
        // After XOR a and b, only the bits that are different will result in
        // a 1
        // We shift the result to the right one bit at a time and count the number
        // of 1's
        // We use logical shift >>> to avoid sign bit preservation
        // For example, when we compare -1 and 2147483647, if we use arithmetic
        // shift instead of logical shift, there wiil be 1's getting extended
        // when we shift c. But the point of our algorithm is to count the number
        // of 1's. So, these extra 1's would mess up our result
        int count = 0;
        int c = a ^ b;
        while (c != 0) {
            count += c & 1;
            c = c >>> 1;
        }
        return count;
    }
}
