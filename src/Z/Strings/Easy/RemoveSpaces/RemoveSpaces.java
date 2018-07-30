package Z.Strings.Easy.RemoveSpaces;

/**
 * Description
 * Given a string, remove all leading/trailing/duplicated empty spaces.
 *
 * Assumptions:
 *
 * The given string is not null.
 * Examples:
 *
 * “  a” --> “a”
 * “   I     love MTV ” --> “I love MTV”
 **/

public class RemoveSpaces {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.removeSpaces(" a"));
        System.out.println(solution.removeSpaces("  I    love MTV "));
        System.out.println(solution.removeSpaces("Yo hey Wal ker!"));
    }
}

class Solution {
    public String removeSpaces(String input) {
        // Write your solution here
        if (input == null || input.isEmpty()) {
            return input;
        }
        char[] inputArray = input.toCharArray();
        int end = 0;
        for (int i = 0; i < input.length(); i++) {
            // Case 1: when we see a space AND we are at the start of
            //         the string
            // Case 2: when the previous character is also a space
            if (inputArray[i] == ' ' &&
                    (i == 0 || inputArray[i - 1] == ' ')) {
                continue;
            }
            // If this is not a space, we move the end pointer forward
            inputArray[end++] = inputArray[i];
        }
        // It is possible that there is still a trailing space at the end
        if (end > 0 && inputArray[end - 1] == ' ') {
            return new String(inputArray, 0, end - 1);
        }
        return new String(inputArray, 0, end);
    }
}

