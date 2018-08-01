package Z.Strings.Medium.ReverseWordsInASentence;

/**
 * Description
 * Reverse the words in a sentence.
 *
 * Assumptions
 *
 * Words are separated by single space
 *
 * There are no heading or tailing white spaces
 *
 * Examples
 *
 * “I love Google” → “Google love I”
 *
 * Corner Cases
 *
 * If the given string is null, we do not need to do anything.
 **/

public class ReverseWordsInASentence {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseWords("I love Google"));
        System.out.println(solution.reverseWords("an Apple"));
    }
}

class Solution {
    public String reverseWords(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }
        char[] chars = input.toCharArray();
        // Reverse the whole sentence by characters
        reverseCharacters(chars, 0, chars.length - 1);
        // Reverse the characters in each word separated by spaces
        int i = 0;
        for (int j = 0; j < chars.length;j++) {
            if (chars[j] == ' ') {
                reverseCharacters(chars, i, j - 1);
                i = j + 1;
            } else if (j == chars.length - 1) {
                reverseCharacters(chars, i, j);
            }
        }
        return new String(chars);
    }

    private void reverseCharacters(char[] array, int left, int right) {
        while (left < right) {
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
}

