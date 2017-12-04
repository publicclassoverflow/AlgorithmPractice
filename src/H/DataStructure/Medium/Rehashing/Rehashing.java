package H.DataStructure.Medium.Rehashing;

/**
 * http://www.lintcode.com/en/problem/rehashing/
 *
 * The size of the hash table is not determined at the very beginning. If the
 * total number of keys is too large (e.g. size >= capacity / 10), we should
 * double the size of the hash table and rehash every key. Say, you have a hash
 * table that looks like below:
 *
 * size = 3, capacity = 4
 * [null, 21, 14, null]
 *        ↓   ↓
 *        9  null
 *        ↓
 *       null
 *
 * The hash function is:
 * int hashcode(int key, int capacity {
 *     return key % capacity;
 * }
 *
 * Here we have three numbers, 9, 14 and 21, where 21 and 9 share the same
 * position as they both have the same hashcode 1 (21 % 4 = 9 % 4 = 1). We store
 * them in the hash table by linked lists.
 *
 * Rehashing this hash table, double the capacity, you will get:
 * size = 3, capacity = 8
 * index:   0    1    2    3     4    5    6   7
 * hash : [null, 9, null, null, null, 21, 14, null]
 *
 * Given the original hash table, return the new hash table after rehashing
 *
 * Notice:
 * For negative integer in hash table, the position can be calculated as follow:
 * C++/Java: if you directly calculate -4 % 3 you will get -1. You can use
 * function: a % b = (a % b + b) % b to make it is a non negative integer.
 * Python: you can directly use -1 % 3, you will get 2 automatically.
 *
 * Example:
 * Given [null, 21->9->null, 14->null, null],
 * return [null, 9->null, null, null, null, 21->null, 14->null, null]
 */

public class Rehashing {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 1
        ListNode[] one = new ListNode[4];
        ListNode twentyone = new ListNode(21);
        twentyone.next = new ListNode(9);
        one[1] = twentyone;
        one[2] = new ListNode(14);
        ListNode[] newOne = solution.rehashing(one);
        printTable(newOne);
    }

    private static void printTable(ListNode[] table) {
        System.out.print("[");
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                if (i == table.length - 1) {
                    System.out.println("null]");
                    return;
                }
                System.out.print("null, ");
                continue;
            }
            ListNode node = table[i];
            while (node != null) {
                System.out.print(node.val + "->");
                node = node.next;
            }
            if (i == table.length - 1) {
                System.out.println("null]");
                return;
            }
            System.out.print("null, ");
        }
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
     * @param hashTable: A list of The first node of linked list
     * @return: A list of The first node of linked list which have twice size
     */
    public ListNode[] rehashing(ListNode[] hashTable) {
        // write your code here
        if (hashTable == null) {
            return null;
        }
        ListNode[] result = new ListNode[hashTable.length * 2];
        for (ListNode node : hashTable) {
            if (node == null) {
                continue;
            }
            while (node != null) {
                hashing(node, result);
                node = node.next;
            }
        }
        return result;
    }

    private void hashing(ListNode node, ListNode[] table) {
        int index = (node.val % table.length + table.length) % table.length;
        ListNode head = table[index];
        if (head == null) {
            head = new ListNode(node.val);
            table[index] = head;
            return;
        }
        while (head.next != null) {
            head = head.next;
        }
        head.next = new ListNode(node.val);
    }
}
