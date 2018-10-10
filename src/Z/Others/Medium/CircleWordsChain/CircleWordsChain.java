package Z.Others.Medium.CircleWordsChain;

/**
 * Given an array of strings, find if all the strings can be chained to form a circle. Two string s1 and s2 can be chained, iff the last letter of s1 is identical to the first letter of s2.
 *  For example, 
 * “abc” and “cd” can be chained, 
 * “abc” and “dz” can not be chained.
 *  Example Input: 
 * arr[] = {"aaa", "bbb", "baa", "aab"}; 
 * Output: True, 
 *
 * The given input strings can be chained to form a circle. The strings can be chained as "aaa", "aab", "bbb" and "baa"
 */

public class CircleWordsChain {
    public boolean canFormCircle(String[] input) {
        if (input == null || input.length == 0) {
            return false;
        }
        return canBeChained(input, 1);
    }

    private boolean canBeChained(String[] input, int index) {
        // Base case: when we have checked all the words in the list
        //            check if the last string connects to the first one
        if (index == input.length) {
            return canConnect(input[index - 1], input[0]);
        }
        // Check all the possible strings can be put at each position
        // See if it can connect to the previous string
        for (int i = index; i < input.length; i++) {
            if (!canConnect(input[index - 1], input[i])) {
                continue;
            }
            // If they does connect, put the word that can connect
            // to the previous string to the current position
            // and move on
            swap(input, i, index);
            if (canBeChained(input, index + 1)) {
                return true;
            }
            swap(input, index, i);
        }
        return false;
    }

    private boolean canConnect(String head, String tail) {
        return head.charAt(head.length() - 1) == tail.charAt(0);
    }

    private void swap(String[] input, int left, int right) {
        String temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }

    public static void main(String[] args) {
        CircleWordsChain instance = new CircleWordsChain();
        String[] input = new String[] {"aaa", "bbb", "baa", "aab"};
        System.out.println(instance.canFormCircle(input));
    }
}
