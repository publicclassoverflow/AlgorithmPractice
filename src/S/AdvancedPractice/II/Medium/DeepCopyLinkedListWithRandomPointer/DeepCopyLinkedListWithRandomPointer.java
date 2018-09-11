package S.AdvancedPractice.II.Medium.DeepCopyLinkedListWithRandomPointer;

import java.util.*;

/**
 * Description
 * Each of the nodes in the linked list has another pointer pointing to a random node in the list or null. Make a deep
 * copy of the original list.
 */

public class DeepCopyLinkedListWithRandomPointer {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test the example
        // N1 -> N2 -> N3 -> N4 -> N5 -> null
        // |     |     ^                  ^
        // |-----|-----|                  |
        //       |-------------------------
        RandomListNode head = buildList(Arrays.asList(1, 2, 3, 4, 5));
        head.random = head.next.next;
        printList(head);
        RandomListNode newHead = solution.copy(head);
        printList(newHead);
    }

    private static RandomListNode buildList(List<Integer> values) {
        RandomListNode head = new RandomListNode(values.get(0));
        RandomListNode curr = head;
        for (int i = 1; i < values.size(); i++) {
            curr.next = new RandomListNode(values.get(i));
            curr = curr.next;
        }
        return head;
    }

    private static void printList(RandomListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.value);
            sb.append(" -> ");
            head = head.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }
}

class RandomListNode {
    public int value;
    public RandomListNode next;
    public RandomListNode random;
    public RandomListNode(int value) {
        this.value = value;
    }
}

class Solution {
    public RandomListNode copy(RandomListNode head) {
        // Write your solution here
        if (head == null) {
            return null;
        }
        // Use a HashMap to keep track of the generated nodes
        // <original node, copied node generated>
        Map<RandomListNode, RandomListNode> copied = new HashMap<>();
        RandomListNode newHead = new RandomListNode(head.value);
        copied.put(head, newHead);
        RandomListNode curr = newHead;
        while (head != null) {
            // Copy the next node
            if (head.next != null) {
                if (!copied.containsKey(head.next)){
                    copied.put(head.next, new RandomListNode(head.next.value));
                }
                curr.next = copied.get(head.next);
            }
            // Copy the random node
            if (head.random != null) {
                if (!copied.containsKey(head.random)) {
                    copied.put(head.random, new RandomListNode(head.random.value));
                }
                curr.random = copied.get(head.random);
            }
            head = head.next;
            curr = curr.next;
        }
        return newHead;
    }
}
