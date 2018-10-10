package Z.Others.Medium.MinimumPerfectSquareSum;

/**
 * How to cut/split the number into a minimum number of items such that each item is equal to an integer's square value.
 * For example 
 * 4 can be split to 1+1+1+1 (4 items) or 2^2 (1 item, which is the solution)
 * Return 1
 * 10 can be split to two items 3^2 + 1^2 (solution) or four items 2^2 + 2^2 + 1^2 +1^2
 * Return 2
 */

public class MinimumPerfectSquareSum {
    public int minSquareCut(int number) {
        // Corner case
        if (number <= 0) {
            return 0;
        }
        int[] cuts = new int[number + 1];
        cuts[0] = 0;
        for (int i = 1; i <= number; i++) {
            // Initialize cuts[i] to i (cut it to all 1's)
            cuts[i] = i;
            // Check all the possible perfect square cuts on the other part
            for (int j = 1; j * j <= i; j++) {
                cuts[i] = Math.min(cuts[i], cuts[i - j * j] + 1);
            }
        }
        return cuts[number];
    }

    public static void main(String[] args) {
        MinimumPerfectSquareSum instance = new MinimumPerfectSquareSum();
        System.out.println(instance.minSquareCut(2));
        System.out.println(instance.minSquareCut(4));
        System.out.println(instance.minSquareCut(10));
        System.out.println(instance.minSquareCut(18));
    }
}
