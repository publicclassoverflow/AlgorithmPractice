package F.LinkedListAndArray.Medium.SortList;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/sort-list/
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example:
 * Given 1->3->2->null, sort it to 1->2->3->null.
 */

public class SortList {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 0
        ListNode one = buildList(new ArrayList<>(Arrays.asList(
                new ListNode(1), new ListNode(3), new ListNode(2)
        )));
        System.out.println("Test 0");
        System.out.println("Before sorting:");
        printList(one);
        System.out.println("After sorting:");
        printList(solution.sortList(one));
        // Test 1
        ListNode two = buildList(new ArrayList<>(Arrays.asList(
                new ListNode(1), new ListNode(3), new ListNode(5),
                new ListNode(2), new ListNode(4), new ListNode(6)
        )));
        System.out.println("Test 1");
        System.out.println("Before sorting:");
        printList(two);
        System.out.println("After sorting:");
        printList(solution.sortList(two));
        // Test 2
        ListNode three = buildList(new ArrayList<>(Arrays.asList(
                new ListNode(6), new ListNode(5), new ListNode(4),
                new ListNode(3), new ListNode(2), new ListNode(1)
        )));
        System.out.println("Test 2");
        System.out.println("Before sorting:");
        printList(three);
        System.out.println("After sorting:");
        printList(solution.sortList(three));
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

    private static void printList(ListNode node) {
        if (node == null) {
            System.out.println("null");
            return;
        }
        ListNode current = node;
        while (current.next != null) {
            System.out.print(current.val + "->");
            current = current.next;
        }
        System.out.println(current.val);
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
    /*
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list, using constant space complexity.
     */
    public ListNode sortList(ListNode head) {
        // write your code here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMiddle(head);
        // Sort the second half of the list: mid -> tail
        ListNode right = sortList(mid.next);
        // Cut out the second half and sort the first half: head -> mid
        mid.next = null;
        ListNode left = sortList(head);
        // Merge
        ListNode newHead = mergeLists(left, right);
        return newHead;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode mergeLists(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (one != null && two != null) {
            if (one.val < two.val) {
                tail.next = one;
                one = one.next;
            } else {
                tail.next = two;
                two = two.next;
            }
            tail = tail.next;
        }
        if (one != null) {
            tail.next = one;
        }
        if (two != null) {
            tail.next = two;
        }
        return dummy.next;
    }
}
