package E.DepthFirstSearch.Hard.AllPermutationsII;

/**
 * Description
 * Given a string with possible duplicate characters, return a list with all permutations of the characters.
 *
 * Examples
 *
 * Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
 * Set = "aba", all permutations are ["aab", "aba", "baa"]
 * Set = "", all permutations are [""]
 * Set = null, all permutations are []
 **/

import java.util.*;

public class AllPermutationsII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.permutations("abc"));
        solution.printPermutations("abc");
        System.out.println(solution.permutations("aab"));
        solution.printPermutations("aab");
        System.out.println(solution.permutations(""));
        solution.printPermutations("");
        System.out.println(solution.permutations(null));
        solution.printPermutations(null);
    }
}

class Solution {
    public List<String> permutations(String set) {
        // Write your solution here
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        permute(set.toCharArray(), 0, result);
        return result;
    }

    private void permute(char[] input, int index, List<String> result) {
        if (index == input.length) {
            result.add(new String(input));
            return;
        }
        // We need to use a hash table to keep track of any letter that
        // has already been used for this level
        Set<Character> beenUsed = new HashSet<>();
        for (int i = index; i < input.length; i++) {
            if (beenUsed.contains(input[i])) {
                continue;
            }
            beenUsed.add(input[i]);
            swap(input, i, index);
            permute(input, index + 1, result);
            swap(input, index, i);
        }
    }

    private void swap(char[] input, int one, int two) {
        char temp = input[one];
        input[one] = input[two];
        input[two] = temp;
    }
    public void printPermutations(String input) {
        if (input == null) {
            return;
        }
        if (input.length() == 0) {
            System.out.println("");
            return;
        }
        printAllPermutations(input.toCharArray(), 0);
    }

    private void printAllPermutations(char[] input, int index) {
        if (index == input.length) {
            System.out.println(new String(input));
            return;
        }
        Set<Character> beenUsed = new HashSet<>();
        for (int i = index; i < input.length; i++) {
            if (beenUsed.contains(input[i])) {
                continue;
            }
            beenUsed.add(input[i]);
            swap(input, i, index);
            printAllPermutations(input, index + 1);
            swap(input, index, i);
        }
    }
}

