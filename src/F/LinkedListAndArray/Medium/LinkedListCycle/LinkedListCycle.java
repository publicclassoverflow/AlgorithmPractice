package F.LinkedListAndArray.Medium.LinkedListCycle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/linked-list-cycle/
 *
 * Given a linked list, determine if it has a cycle in it.
 *
 * Example:
 * Given -21->10->4->5, tail connects to node index 1, return true
 */

public class LinkedListCycle {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listOne = buildList(new ArrayList<>(Arrays.asList(
                new ListNode(-21), new ListNode(10), new ListNode(4),
                new ListNode(5)
        )));
    }

    private static ListNode buildList(ArrayList<ListNode> nodes) {
        if (nodes == null) {
            return null;
        }
        ListNode current = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            current.next = nodes.get(i);
            current = current.next;
        }
        current.next = null;
        return nodes.get(0);
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
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {
        // write your code here
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
