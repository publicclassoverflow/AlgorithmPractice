package F.LinkedListAndArray.Hard.LinkedListCycleII;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/linked-list-cycle-ii/
 *
 * Given a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 *
 * Example:
 * Given -21->10->4->5, tail connects to node index 1，return 10
 */

public class LinkedListCycleII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode mTwentyOne = new ListNode(-21);
        ListNode ten = new ListNode(10);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        mTwentyOne.next = ten;
        ten.next = four;
        four.next = five;
        five.next = ten;
        System.out.println(solution.detectCycle(mTwentyOne).val);
    }
}

class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins.
     *           if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {
        // write your code here
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        // The cycle may start before slow == fast
        while (head != slow.next) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}

