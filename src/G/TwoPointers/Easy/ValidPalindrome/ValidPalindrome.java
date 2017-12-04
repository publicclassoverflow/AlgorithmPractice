package G.TwoPointers.Easy.ValidPalindrome;

/**
 * http://www.lintcode.com/en/problem/valid-palindrome/
 *
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 *
 * Notice:
 * Have you consider that the string might be empty? This is a good question to
 * ask during an interview.
 * For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example:
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 *
 * Challenge:
 * O(n) time without extra memory.
 */

public class ValidPalindrome {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 0
        String zero = "A man, a plan, a canal: Panama";     // true
        System.out.println(solution.isPalindrome(zero));
        // Test 1
        String one = "race a car";                          // false
        System.out.println(solution.isPalindrome(one));
        // Test 2
        String two = "a.";                                  // true
        System.out.println(solution.isPalindrome(two));
        // Test 3
        String three = "Aa";                                // true
        System.out.println(solution.isPalindrome(three));
        // Test 4
        System.out.println(solution.isPalindrome(""));      // true
    }
}

class Solution {
    /*
     * @param s: A string
     * @return: Whether the string is a valid palindrome
     */
    public boolean isPalindrome(String s) {
        // write your code here
        if (s == null) {
            return false;
        }
        s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
