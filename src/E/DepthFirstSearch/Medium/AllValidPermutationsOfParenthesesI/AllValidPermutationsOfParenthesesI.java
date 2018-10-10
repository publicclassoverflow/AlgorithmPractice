package E.DepthFirstSearch.Medium.AllValidPermutationsOfParenthesesI;

import java.util.ArrayList;
import java.util.List;

/**
 * https://app.laicode.io/app/problem/66
 *
 * Description
 * Given N pairs of parentheses “()”, return a list with all the valid permutations.
 *
 * Assumptions
 *
 * N >= 0
 * Examples
 *
 * N = 1, all valid permutations are ["()"]
 * N = 3, all valid permutations are ["((()))", "(()())", "(())()", "()(())", "()()()"]
 * N = 0, all valid permutations are [""]
 **/

public class AllValidPermutationsOfParenthesesI {
    /*
    public List<String> validParentheses(int n) {
        // Write your solution here
        if (n == 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        char[] parentheses = new char[2 * n]; // n pairs of parentheses
        permute(parentheses, n, n, 0, result);
        return result;
    }

    private void permute(char[] parentheses, int left, int right, int index,
                         List<String> result) {
        // Base case: when there are no more parentheses left
        if (left == 0 && right == 0) {
            result.add(new String(parentheses));
            return;
        }
        // When there are still parentheses left
        // We check the left parentheses ( first in order to make it valid
        if (left > 0) {
            parentheses[index] = '(';
            permute(parentheses, left - 1, right, index + 1, result);
        }
        // Right parenthesis can be used only when there are more ( than )
        if (right > left) {
            parentheses[index] = ')';
            permute(parentheses, left, right - 1, index + 1, result);
        }
    }
    */

    // Another method: use StringBuilder
    public List<String> validParentheses(int n) {
        // Write your solution here
        if (n == 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        permute(new StringBuilder(), n, n, result);
        return result;
    }

    private void permute(StringBuilder parentheses, int left, int right, List<String> result) {
        // Base case: when there are no more parentheses left
        if (left == 0 && right == 0) {
            result.add(parentheses.toString());
            return;
        }
        // When there are still any (, it can be added
        if (left > 0) {
            parentheses.append('(');
            // Go to the next level
            permute(parentheses, left - 1, right, result);
            // Backtracking
            parentheses.deleteCharAt(parentheses.length() - 1);
        }
        // When there are more ) than ( left
        if (right > left) {
            parentheses.append(')');
            permute(parentheses, left, right - 1, result);
            parentheses.deleteCharAt(parentheses.length() - 1);
        }
    }

    public static void main(String[] args) {
        AllValidPermutationsOfParenthesesI instance = new AllValidPermutationsOfParenthesesI();
        System.out.println(instance.validParentheses(0));
        System.out.println(instance.validParentheses(1));
        System.out.println(instance.validParentheses(2));
        System.out.println(instance.validParentheses(3));
        System.out.println(instance.validParentheses(6));
    }
}
