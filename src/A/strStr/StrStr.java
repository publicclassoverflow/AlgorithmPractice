package A.strStr;

/**
 * http://www.lintcode.com/en/problem/strstr/
 * For a given source string and a target string, you should output the first index(from 0) of target string in source
 * string. If target does not exist in source, just return -1.
 *
 * Clarification:
 * Do I need to implement KMP Algorithm in a real interview?
 * Not necessary. When you meet this problem in a real interview, the interviewer may just want to test your basic
 * implementation ability. But make sure your confirm with the interviewer first.
 *
 * Example
 * If source = "source" and target = "target", return -1.
 * If source = "abcdabcdefg" and target = "bcd", return 1.
 */

public class StrStr {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String source = "source";
        String target = "target";
        System.out.println(solution.strStr(source, target));

        String s = "abcdabcdefg";
        String t = "bcd";
        System.out.println(solution.strStr(s, t));

        String motor = "motorcycle";
        String cycle = "motorcycle";
        System.out.println(solution.strStr(motor, cycle));

        String code = "code";
        String space = "";
        System.out.println(solution.strStr(code, space));
    }
}

class Solution {
    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        //write your code here
        if (source == null || target == null) {
            return -1;
        }
        if (target.length() == 0) {
            return 0;
        }

        // Method 1: compare char
        /*
        int j;
        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            for (j = 0; j < target.length(); j++) {
                if (target.charAt(j) != source.charAt(i + j)) {
                    break;
                }
                if (j == target.length() - 1) {
                    return i;
                }
            }
        }
        */

        // Method 2: compare substring
        int l = target.length() - 1;
        for (int i = 0; i < source.length() - l; i++) {
            if (source.substring(i, (i + l + 1)).equals(target)) {
                // String s = source.substring(i, (i + l + 1));
                // if (s.equals(target)) {
                return i;
            }
        }
        return -1;
    }
}
