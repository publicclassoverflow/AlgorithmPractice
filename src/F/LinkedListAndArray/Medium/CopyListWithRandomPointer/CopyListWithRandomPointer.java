package F.LinkedListAndArray.Medium.CopyListWithRandomPointer;

import java.util.HashMap;

/**
 * http://www.lintcode.com/en/problem/copy-list-with-random-pointer/
 *
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 *
 * Challenge:
 * Could you solve it with O(1) space?
 **/

public class CopyListWithRandomPointer {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) {
        this.label = x;
    }
}

class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        // write your code here
        if (head == null) {
            return null;
        }
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode previous = dummy;
        RandomListNode newNode = dummy;
        while (head != null) {
            if (map.containsKey(head)) {
                newNode = map.get(head);
            } else {
                newNode = new RandomListNode(head.label);
                map.put(head, newNode);
            }
            previous.next = newNode;
            if (head.random != null) {
                if (map.containsKey(head.random)) {
                    newNode.random = map.get(head.random);
                } else {
                    newNode.random = new RandomListNode(head.random.label);
                    map.put(head.random, newNode.random);
                }
            }
            previous = newNode;
            head = head.next;
        }
        return dummy.next;
    }
}
