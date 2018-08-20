package O.String.Hard.StringReplace;

/**
 * Description
 * Given an original string input, and two strings S and T, replace all occurrences of S in input with T.
 *
 * Assumptions
 *
 * input, S and T are not null, S is not empty string
 * Examples
 *
 * input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
 * input = "dodododo", S = "dod", T = "a", input becomes "aoao"
 **/

public class StringReplace {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.replace("student", "den", "xx"));
        System.out.println(solution.replace("student", "de", "xxx"));
    }
}

class Solution {
    public String replace(String input, String source, String target) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }
        char[] inputArray = input.toCharArray();
        // If the length of the source is longer than that of the target,
        // we simply replace every occurrence of source with target
        if (source.length() >= target.length()) {
            return replaceDirectly(inputArray, source, target);
        }
        // When the target is longer than the source, we need to determine
        // how many extra spaces needed to replace the source with target
        return replaceWithExtraSpaces(inputArray, source, target);
    }

    private String replaceDirectly(char[] input, String source, String target) {
        // Since the original input has a length that is sufficient enough to
        // hold the result, we can simply move the pointers from left to right
        int slow = 0;
        int fast = 0;
        while (fast < input.length) {
            // When we see a character that matches the first character of the source
            // we need to check if the source is there.
            // If so, we replace the source with the target and move the slow and
            // fast pointers respectively
            if (fast <= input.length - source.length() && isSubstring(input, fast, source)) {
                replaceSource(input, slow, target);
                slow += target.length();
                fast += source.length();
            } else {
                // If we do not see the first character of the source, just cover
                // input[slow] with input[fast]
                input[slow++] = input[fast++];
            }
        }
        return new String(input, 0, slow);
    }

    private String replaceWithExtraSpaces(char[] input, String source, String target) {
        // Because we need a bigger sized array to hold the result,
        // we need to know how many times the source has appearred in the input.
        int sourceCount = 0;
        for (int i = 0; i <= input.length - source.length(); i++) {
            if (isSubstring(input, i, source)) {
                sourceCount++;
                i += source.length();
            }
        }
        // Allocate enough space for the result array
        char[] output = new char[input.length + sourceCount * target.length()];
        // Two pointers
        // inputPointer: everything to the right of it is to be processed
        // outputPointer: everything to the left of it is to be returned
        int inputPointer = 0;
        int outputPointer = 0;
        while (inputPointer < input.length) {
            if (!isSubstring(input, inputPointer, source)) {
                output[outputPointer++] = input[inputPointer++];
                continue;
            }
            replaceSource(output, outputPointer, target);
            inputPointer += source.length();
            outputPointer += target.length();
        }
        return new String(output, 0, outputPointer); // Not in-place
    }

    private boolean isSubstring(char[] input, int start, String source) {
        for (int i = 0; i < source.length(); i++) {
            if (start + i >= input.length) {
                return false;
            }
            if (input[start + i] != source.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private void replaceSource(char[] input, int start, String target) {
        for (int i = 0; i < target.length(); i++) {
            input[start + i] = target.charAt(i);
        }
    }
}
