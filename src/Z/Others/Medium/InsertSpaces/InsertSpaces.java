package Z.Others.Medium.InsertSpaces;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, we can insert at most one empty space between each pair of adjacent letters. 
 * Print all permutations of strings after insertions of empty spaces. 
 *
 * Input: str = "ABC" 
 * Output: 
 * ABC
 * AB_C
 * A_BC
 * A_B_C
 */

public class InsertSpaces {
    public List<String> spacePermutation(String input) {
        List<String> result = new ArrayList<>();
        if (input == null || input.length() == 0) {
            return result;
        }
        // Pass the first character in because there cannot be leading spaces
        StringBuilder sb = new StringBuilder();
        sb.append(input.charAt(0));
        // Because there is no need to check the first character, we only need to check the rest of the input starting from index 1
        permute(input, sb, 1, result);
        return result;
    }

    private void permute(String input, StringBuilder sb, int index, List<String> result) {
        // Base case: when all of the input characters have been added
        if (index == input.length()) {
            result.add(sb.toString());
            return;
        }
        // Option 1: add the character directly
        sb.append(input.charAt(index));
        permute(input, sb, index + 1, result);
        sb.deleteCharAt(sb.length() - 1);
        // Option 2: add a space before adding the character
        sb.append(' ').append(input.charAt(index));
        permute(input, sb, index + 1, result);
        // Delete both of the characters added
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) {
        InsertSpaces insertSpaces = new InsertSpaces();
        System.out.println(insertSpaces.spacePermutation("ABC"));
    }
}
