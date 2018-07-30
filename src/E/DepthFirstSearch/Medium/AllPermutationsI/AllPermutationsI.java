package E.DepthFirstSearch.Medium.AllPermutationsI;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * Given a string with no duplicate characters, return a list with all permutations of the characters.
 *
 * Examples
 *
 * Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
 * Set = "", all permutations are [""]
 * Set = null, all permutations are []
 **/

public class AllPermutationsI {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.permutations("abc"));
    }
}

class Solution {
    public List<String> permutations(String set) {
        // Write your solution here
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
//         Method 1: permute without preserving order
         permute(set.toCharArray(), 0, result);
//         Method 2: permute with the order preserved
//        permuteWithOrder(set.toCharArray(), new boolean[set.length()],
//                new StringBuilder(), result);
        return result;
    }

    private void permute(char[] input, int index, List<String> result) {
        // Stop and add the chars in this order to the result when we finish
        // the bottom level
        if (index == input.length) {
            result.add(new String(input));
            return;
        }
        // For each level, consider the remaining possible states
        for (int i = index; i < input.length; i++) {
            // Swap the "root" element with the remaining element
            swap(input, index, i);
            // Move on to the next level
            permute(input, index + 1, result);
            // Back-track: swap the two letters back
            swap(input, i, index);
        }
    }

    private void swap(char[] input, int one, int two) {
        char temp = input[one];
        input[one] = input[two];
        input[two] = temp;
    }

    private void permuteWithOrder(char[] input, boolean[] used,
                                  StringBuilder builder, List<String> result) {
        if (builder.length() == input.length) {
            result.add(builder.toString());
            return;
        }
        for (int i = 0; i < input.length; i++) {
            if (!used[i]) {
                used[i] = true;
                builder.append(input[i]);
                permuteWithOrder(input, used, builder, result);
                // Back tracking
                used[i] = false;
                builder.deleteCharAt(builder.length() - 1);
            }
        }
    }
}
