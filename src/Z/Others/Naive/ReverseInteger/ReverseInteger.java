package Z.Others.Naive.ReverseInteger;

public class ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseInteger(Integer.MAX_VALUE / 10));
        System.out.println(solution.reverseInteger(Integer.MAX_VALUE));
    }
}

class Solution {
    /*
     * @param n: the integer to be reversed
     * @return: the reversed integer
     */
    public int reverseInteger(int n) {
        // write your code here
        int revN = 0;
        while (n != 0) {
            int temp = revN * 10 + n % 10;
            n /= 10;
            if (temp / 10 != revN) {
                revN = 0;
                break;
            }
            revN = temp;
        }
        return revN;
    }
}
