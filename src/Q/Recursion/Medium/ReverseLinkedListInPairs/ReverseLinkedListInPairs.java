package Q.Recursion.Medium.ReverseLinkedListInPairs;

/**
 * Description
 * Reverse pairs of elements in a singly-linked list.
 *
 * Examples
 *
 * L = null, after reverse is null
 * L = 1 -> null, after reverse is 1 -> null
 * L = 1 -> 2 -> null, after reverse is 2 -> 1 -> null
 * L = 1 -> 2 -> 3 -> null, after reverse is 2 -> 1 -> 3 -> null
 */

public class ReverseLinkedListInPairs {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 1
        ListNode one = buildList(new int[] {1, 2, 3});
        System.out.println("List:");
        printList(one);
        System.out.println("Reversed List:");
        printList(solution.reverseInPairs(one));
        // Test 2
        ListNode two = buildList(new int[] {1, 2, 3, 4, 5});
        System.out.println("List:");
        printList(two);
        System.out.println("Reversed List:");
        printList(solution.reverseInPairs(two));
    }

    private static ListNode buildList(int[] values) {
        ListNode head = new ListNode(values[0]);
        ListNode curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new ListNode(values[i]);
            curr = curr.next;
        }
        return head;
    }

    private static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            sb.append(curr.value);
            sb.append("->");
            curr = curr.next;
        }
        System.out.println(sb.append(curr.value).toString());
    }
}

class Solution {
    public ListNode reverseInPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curr = head;
        ListNode reversed = reverseInPairs(curr.next.next);
        ListNode next = curr.next;
        curr.next = reversed;
        next.next = curr;
        return next;
    }
}

class ListNode {
    public int value;
    public ListNode next;
    public ListNode(int value) {
        this.value = value;
        next = null;
    }
}