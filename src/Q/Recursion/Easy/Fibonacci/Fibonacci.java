package Q.Recursion.Easy.Fibonacci;

/**
 * https://app.laicode.io/app/problem/12
 * Description
 * Get the Kth number in the Fibonacci Sequence. (K is 0-indexed, the 0th Fibonacci number is 0 and the 1st Fibonacci number is 1).
 *
 * Examples
 *
 * 0th fibonacci number is 0
 * 1st fibonacci number is 1
 * 2nd fibonacci number is 1
 * 3rd fibonacci number is 2
 * 6th fibonacci number is 8
 * Corner Cases
 *
 * What if K < 0? in this case, we should always return 0.
 * Is it possible the result fibonacci number is overflowed? We can assume it will not be overflowed when we solve this
 * problem on this online judge, but we should also know that it is possible to get an overflowed number, and sometimes
 * we will need to use something like BigInteger.
 **/

public class Fibonacci {
    public long fibonacci(int K) {
        // Write your solution here
        // Sanity check
        if (K < 0) {
            return Integer.MIN_VALUE;
        }
        /*
        // Method 1: Recursion
        // Base case: fibo(0) == 0 & fibo(1) == 1
        if (K < 2) {
            return K;
        }
        // Recursive rule: fibo(n) = fibo(n - 1) + fibo(n - 2)
        return fibonacci(K - 1) + fibonacci(K - 2);
        */
        // Method 2: Dynamic Programming
        // Linear scan and look back at the previous two elements
        if (K < 2) {
            return K;
        }
        long[] fibo = new long[K + 1];
        fibo[0] = 0;
        fibo[1] = 1;
        for (int i = 2; i <= K; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        }
        return fibo[K];
    }
}
