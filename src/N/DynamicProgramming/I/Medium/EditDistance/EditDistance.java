package N.DynamicProgramming.I.Medium.EditDistance;

/**
 * Description
 * Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insert operations needed to transform one string into the other.
 *
 * Assumptions
 *
 * Both strings are not null
 * Examples
 *
 * string one: “sigh”, string two : “asith”
 *
 * the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”).
 */

public class EditDistance {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.editDistance("sign", "asith")); // 2
        System.out.println(solution.editDistance("abcde", "fghij")); // 5
    }
}

class Solution {
    public int editDistance(String one, String two) {
        // Write your solution here
        if (one == null || two == null) {
            return -1;
        }
        /*
        int m = one.length();
        int n = two.length();
        int[][] edit = new int[m + 1][n + 1];
        // Initialization
        for (int i = 0; i <= m; i++) {
            edit[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            edit[0][j] = j;
        }
        // Compare all letters in the two strings and calculate the number of replace/delete/insert needed
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    edit[i][j] = edit[i - 1][j - 1];
                } else {
                    edit[i][j] = Math.min(edit[i - 1][j - 1],
                                        Math.min(edit[i - 1][j], edit[i][j - 1])) + 1;
                }
            }
        }
        printMatrix(edit);
        return edit[m - 1][n - 1];
        */

        int[][] distance = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                if (i == 0) {
                    distance[i][j] = j;
                } else if (j == 0) {
                    distance[i][j] = i;
                } else if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    distance[i][j] = Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1);
                    distance[i][j] = Math.min(distance[i - 1][j - 1] + 1, distance[i][j]);
                }
            }
        }
        printMatrix(distance);
        return distance[one.length()][two.length()];
    }

    private void printMatrix(int[][] edit) {
        for (int i = 0; i < edit.length; i++) {
            for (int j = 0; j < edit[0].length; j++) {
                System.out.print(edit[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

