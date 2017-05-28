package Z.Others.Naive.Fibonacci;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/fibonacci/
 * I've made some changes to the naive LintCode problem to have some more fun
 * The solution to the original problem can be found at the end of this program
 * The Fibonacci numbers array starts from 0 and 1.
 */

public class Fibonacci {
    public static void main(String[] args) {
        int n = 10;
        // Begin: Method 1
        System.out.println("Result from recursive method:");
        List<Integer> fib = new ArrayList<>();
        fib.add(0);
        fib.add(1);
        getFibNumbers(fib, n);
        System.out.println(fib);
        int sum = 0;
        for (int num : fib) {
            sum += num;
        }
        System.out.format("The sum of the first %d Fibonacci numbers is %d%n",
                n, sum);
        // End: Method 1

        // Begin: Method 2
         System.out.println("Result from non-recursive method:");
         List<Integer> fibo = new ArrayList<>();
         fibo.add(0);
         fibo.add(1);
         int fiboSum = 1;
         while (fibo.size() < n) {
             // fibo.add(fibo.get(fibo.size() - 2) + fibo.get(fibo.size() - 1));
             int next = fibo.get(fibo.size() - 2) + fibo.get(fibo.size() - 1);
             fiboSum += next;
             fibo.add(next);
         }
         System.out.println(fibo);
         System.out.format("The sum of the first %d Fibonacci numbers is %d%n",
                           n, fiboSum);
        // End: Method 2
    }

    private static void getFibNumbers(List<Integer> currentList, int size) {
        int currentSize = currentList.size();
        if (currentSize >= size) {
            return;
        }
        int next = currentList.get(currentSize - 2) +
                currentList.get(currentSize - 1);
        currentList.add(next);
        getFibNumbers(currentList, size);
    }
}

// Original LintCode solution
/*
class Solution {
    public int fibonacci(int n) {
        // write your code here
        List<Integer> fib = new ArrayList<>();
        fib.add(0);
        fib.add(1);

        // Method 1: Non-recursion
        while (fib.size() < n) {
            int next = fib.get(fib.size() - 2) + fib.get(fib.size() - 1);
            fib.add(next);
        }
        return fib.get(n - 1);

        // Method 2: Recursion
        getFibNumbers(fib, n);
        return fib.get(n - 1);
    }

    private void getFibNumbers(List<Integer> currentList, int size) {
        int currentSize = currentList.size();
        if (currentSize >= size) {
            return;
        }
        int next = currentList.get(currentSize - 2) +
                    currentList.get(currentSize - 1);
        currentList.add(next);
        getFibNumbers(currentList, size);
    }
}
*/
