package E.DepthFirstSearch.Medium.PermutationInString;

import java.util.*;

/**
 * https://www.lintcode.com/problem/permutation-in-string/description
 *
 * Description
 * Given two strings s1 and s2, write a function to return true if s2 contains
 * the permutation of s1. In other words, one of the first string's permutations
 * is the substring of the second string.
 *
 * 1.The input strings only contain lower case letters.
 * 2.The length of both given strings is in range [1, 10,000].
 *
 * Have you met this question in a real interview?
 * Example
 * Example 1:
 *
 * Input:s1 = "ab" s2 = "eidbaooo"
 * Output:True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 **/

public class PermutationInString {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.checkInclusion("ab", "eidbaooo"));
        System.out.println(solution.checkInclusion("prosperity", "properties"));
//        String s1 = "qrcczceam";
//        String s2 = "xxyhdqrloraznjmjdutowpdihgojmpkbhytwkplsqolieeinuedffiqrlkgcljktvyppfztcuyoinzfntyjnmjvwwwmatbbfsriwrwonesmcmcdamurgovwlvssposzrjeiexpdcojeqhjxbpoaecqibkybyrjxqibmnybdmizrmrzzwsojfsmyfgvmyscwgaotnjlfwnixdyxlxhtuwwrkcsefubrfxlexixuerldgsuuklrsvrhqndbmslrrtqsudjsgseohbwgojjmepfsawhszluynmfvlfosihatyvupzkxukiedwgmpxsznaqadvalzmigsitmwaunstoqlgskhnsmwvrudgphbnn";
//        System.out.println(solution.checkInclusion(s1, s2));
    }
}

class Solution {
    /**
     * @param s1: a string
     * @param s2: a string
     * @return: if s2 contains the permutation of s1
     */
    public boolean checkInclusion(String s1, String s2) {
        // write your code here
        if (s1.length() > s2.length()) {
            return false;
        }
        Set<String> permutations = new HashSet<>();
        permute(s1.toCharArray(), 0, permutations);
        return existPermutation(permutations, s1, s2);
    }

    private void permute(char[] str, int index, Set<String> permutations) {
        if (index == str.length) {
            permutations.add(new String(str));
            return;
        }
        for (int i = index; i < str.length; i++) {
            swap(str, i, index);
            // Go to the next level, aka index
            permute(str, index + 1, permutations);
            // Back tracking
            swap(str, index, i);
        }
    }

    private void swap(char[] array, int one, int two) {
        char temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    private boolean existPermutation(Set<String> permutations, String s1, String s2) {
        int i = 0;
        int j = s1.length(); //substring(start, end + 1);
        while (j <= s2.length()) {
            if (permutations.contains(s2.substring(i, j))) {
                return true;
            }
            i++;
            j++;
        }
        return false;
    }
}
