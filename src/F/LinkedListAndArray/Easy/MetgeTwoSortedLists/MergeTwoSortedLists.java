package F.LinkedListAndArray.Easy.MetgeTwoSortedLists;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/merge-two-sorted-lists/
 *
 * Merge two sorted (ascending) linked lists and return it as a new sorted list.
 * The new sorted list should be made by splicing together the nodes of the two
 * lists and sorted in ascending order.
 *
 * Example:
 * Given 1->3->8->11->15->null, 2->null ,
 * return 1->2->3->8->11->15->null.
 */

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listOne = buildList(new ArrayList<>(Arrays.asList(
                new ListNode(1), new ListNode(3), new ListNode(8),
                new ListNode(11), new ListNode(15)
        )));
        System.out.println("List 1:");
        printList(listOne);
        ListNode listTwo = buildList(new ArrayList<>(Arrays.asList(
                new ListNode(2)
        )));
        System.out.println("List 2:");
        printList(listTwo);
        ListNode mergedList = solution.mergeTwoLists(listOne, listTwo);
        System.out.println("After merging the two lists:");
        printList(mergedList);
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
    /**
     * @param ListNode l1 is the head of the linked list
     * @param ListNode l2 is the head of the linked list
     * @return: ListNode head of linked list
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write your code here
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // If both lists are null, return either of them will be null, which
        // is correct
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                lastNode.next = l1;
                l1 = l1.next;
            } else {
                lastNode.next = l2;
                l2 = l2.next;
            }
            lastNode = lastNode.next;
        }
        if (l1 != null) {
            lastNode.next = l1;
        } else {
            lastNode.next = l2;
        }
        return dummy.next;
    }
}

