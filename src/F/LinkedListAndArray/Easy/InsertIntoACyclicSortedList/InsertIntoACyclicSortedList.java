package F.LinkedListAndArray.Easy.InsertIntoACyclicSortedList;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/insert-into-a-cyclic-sorted-list/
 *
 * Given a node from a cyclic linked list which has been sorted, write a
 * function to insert a value into the list such that it remains a cyclic sorted
 * list. The given node can be any single node in the list. Return the inserted
 * new node.
 *
 * Notice:
 * 3->5->1 is a cyclic list, so 3 is next node of 1.
 * 3->5->1 is same with 5->1->3
 *
 * Example:
 * Given a list, and insert a value 4:
 * 3->5->1
 * Return 5->1->3->4
 *
 */

public class InsertIntoACyclicSortedList {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 3->5->1, and insert a value 4
        ListNode listOne = buildList(new ArrayList<>(Arrays.asList(
                new ListNode(3), new ListNode(5), new ListNode(1)
        )));
        System.out.println("List 1:");
        printList(listOne);
        int insertOne = 4;
        ListNode nodeOne = solution.insert(listOne, insertOne);
        System.out.println("After inserting a " + insertOne);
        printList(nodeOne);
        // Test 2->2->2, and insert a value 3
        ListNode listTwo = buildList(new ArrayList<>(Arrays.asList(
                new ListNode(2), new ListNode(2), new ListNode(2)
        )));
        System.out.println("List 2:");
        printList(listTwo);
        int insertTwo = 3;
        ListNode nodeTwo = solution.insert(listTwo, insertTwo);
        System.out.println("After inserting a " + insertTwo);
        printList(nodeTwo);
        // Test 30->50->2->2->3->5->7->9->11->20, and insert 2
        ListNode listThree = buildList(new ArrayList<>(Arrays.asList(
                new ListNode(30), new ListNode(50), new ListNode(2),
                new ListNode(2), new ListNode(2), new ListNode(3),
                new ListNode(5), new ListNode(7), new ListNode(9),
                new ListNode(11), new ListNode(20)
        )));
        System.out.println("List 3:");
        printList(listThree);
        int insertThree = 2;
        ListNode nodeThree = solution.insert(listThree, insertThree);
        System.out.println("After inserting a " + insertThree);
        printList(nodeThree);
    }

    private static ListNode buildList(ArrayList<ListNode> nodes) {
        if (nodes == null) {
            return null;
        }
        for (int i = 0; i < nodes.size() - 1; i++) {
            ListNode current = nodes.get(i);
            current.next = nodes.get(i + 1);
        }
        ListNode last = nodes.get(nodes.size() - 1);
        ListNode first = nodes.get(0);
        last.next = first;
        return first;
    }

    private static void printList(ListNode node) {
        if (node == null) {
            System.out.println("null");
            return;
        }
        ListNode current = node;
        while (current.next != null && current.next != node) {
            System.out.print(current.val + "->");
            current = current.next;
        }
        System.out.println(current.val);
    }
}

class ListNode {
    int val;
    ListNode next;
    public ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    /**
     * @param node a list node in the list
     * @param x an integer
     * @return the inserted new list node
     */
    public ListNode insert(ListNode node, int x) {
        // Write your code here
        if (node == null) {
            ListNode newNode = new ListNode(x);
            newNode.next = newNode;
            return newNode;
        }
        ListNode current = node;
        ListNode previous;
        do {
            previous = current;
            current = current.next;
            // X is in between previous and current
            if (x <= current.val && x >= previous.val) {
                break;
            }
            // X is outside of the range of previous and current
            if ((previous.val > current.val)
                    && (x < current.val || x > previous.val)) {
                break;
            }
        } while (current != node);
        ListNode newNode = new ListNode(x);
        newNode.next = current;
        previous.next = newNode;
        return newNode;
    }
}
