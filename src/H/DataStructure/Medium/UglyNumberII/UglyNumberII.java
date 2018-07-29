package H.DataStructure.Medium.UglyNumberII;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * http://www.lintcode.com/en/problem/ugly-number-ii/
 * An ugly number is a number that only has factors 2, 3 and 5.
 * Design an algorithm to find the nth ugly number. The first 10 ugly numbers
 * are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...
 *
 * Notice:
 * note that 1 is typically treated as an ugly number
 *
 * Exmaple:
 * If n = 9, return 10
 */

public class UglyNumberII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test
        System.out.println(solution.nthUglyNumber(9));
        System.out.println(solution.nthUglyNumber(1));
        System.out.println(solution.nthUglyNumber(41));
    }
}

class Solution {
    /*
     * @param n: An integer
     * @return: the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
        // write your code here
        if (n < 1) {
            return -1;
        }
        PriorityQueue<Long> queue = new PriorityQueue<>();
        HashSet<Long> inQueue = new HashSet<>();
        long[] primes = new long[] {(long) 2, (long) 3, (long) 5};
        for (long prime : primes) {
            queue.offer(prime);
            inQueue.add(prime);
        }
        long num = (long) 1;    // If it asks for the 1st ugly number
        for (int i = 1; i < n; i++) {
            num = queue.poll();
            for (long prime : primes) {
                long current = num * prime;
                if (!inQueue.contains(current)) {
                    queue.offer(current);
                    inQueue.add(current);
                }
            }
        }
        return (int) num;
    }
}
