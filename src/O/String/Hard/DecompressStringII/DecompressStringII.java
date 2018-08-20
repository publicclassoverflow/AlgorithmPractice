package O.String.Hard.DecompressStringII;

/**
 * Description
 * Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences.
 *
 * Assumptions
 *
 * The string is not null
 *
 * The characters used in the original string are guaranteed to be ‘a’ - ‘z’
 *
 * There are no adjacent repeated characters with length > 9
 *
 * Examples
 *
 * “a1c0b2c4” → “abbcccc”
 **/

public class DecompressStringII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.decompress("a1c0b2c4"));
    }
}

class Solution {
    public String decompress(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }
        char[] array = input.toCharArray();
        int decompSize = getStringSize(array);
        char[] output = new char[decompSize];
        int outputPointer = 0;
        for (int i = 0; i < array.length - 1; i++) {
            int charCount = Character.digit(array[i + 1], 10);
            // Populate the output array with charCount number of the character
            // move i by one more to skip the count
            // populateOutput(output, outputPointer, array[i++], charCount);
            // outputPointer += charCount;
            for (int j = 0; j < charCount; j++) {
                output[outputPointer++] = array[i];
            }
            i++; // Skip the count number
        }
        return new String(output);
    }

    private int getStringSize(char[] input) {
        int size = 0;
        for (char ch : input) {
            if (Character.isDigit(ch)) {
                size += Character.digit(ch, 10);
            }
        }
        return size;
    }

    private void populateOutput(char[] output, int outputPointer, char ch, int charCount) {
        for (int i = 0; i < charCount; i++) {
            output[outputPointer++] = ch;
        }
    }
}
