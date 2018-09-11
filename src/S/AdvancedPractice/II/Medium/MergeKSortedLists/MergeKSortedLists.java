package S.AdvancedPractice.II.Medium.MergeKSortedLists;

import java.util.*;

/**
 * Description
 * Merge K sorted lists into one big sorted list in ascending order.
 *
 * Assumptions
 *
 * ListOfLists is not null, and none of the lists is null.
 */

public class MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test
        List<ListNode> listOfLists = new ArrayList<>();
        listOfLists.addAll(Arrays.asList(
                buildList(Arrays.asList(1, 4, 7, 8, 14)),
                buildList(Arrays.asList(5, 6, 10, 12)),
                buildList(Arrays.asList(2, 3, 9, 11, 13))
        ));
        ListNode result = solution.merge(listOfLists);
        printList(result);
    }

    private static ListNode buildList(List<Integer> values) {
        ListNode head = new ListNode(values.get(0));
        ListNode curr = head;
        for (int i = 1; i < values.size(); i++) {
            curr.next = new ListNode(values.get(i));
            curr = curr.next;
        }
        return head;
    }

    private static void printList(ListNode head) {
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

class ListNode {
    int value;
    ListNode next;
    public ListNode(int value) {
        this.value = value;
        next = null;
    }
}

class Solution {
    public ListNode merge(List<ListNode> listOfLists) {
        // Write your solution here
        if (listOfLists == null || listOfLists.size() == 0) {
            return null;
        }
        // Create a minHeap of size k
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
                listOfLists.size(),
                (one, two) -> {
                    if (one.value == two.value) {
                        return 0;
                    }
                    return one.value < two.value ? -1 : 1;
                }
        );
        // Offer all of the head nodes to the heap first
        for (ListNode head : listOfLists) {
            if (head == null) {
                continue;
            }
            minHeap.offer(head);
        }
        // Dummy node is used to keep track of the result head node
        ListNode result = new ListNode(0);
        ListNode curr = result;
        // Merge happens here
        while (!minHeap.isEmpty()) {
            ListNode min = minHeap.poll();
            // Connext the current min node to the result
            curr.next = min;
            curr = curr.next;
            // Fill the empty space with the next node if it exists
            if (min.next != null) {
                minHeap.offer(min.next);
            }
        }
        return result.next;
    }
}
