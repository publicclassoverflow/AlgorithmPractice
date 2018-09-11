package S.AdvancedPractice.I.Medium.ZigZagTraversalOfBinaryTree;

import java.util.*;

/**
 * Description
 * Get the list of keys in a given binary tree layer by layer in zig-zag order.
 *
 * Examples
 *
 *         5
 *
 *       /    \
 *
 *     3        8
 *
 *   /   \        \
 *
 *  1     4        11
 *
 * the result is [5, 3, 8, 11, 4, 1]
 *
 * Corner Cases
 *
 * What if the binary tree is null? Return an empty list in this case.
 * How is the binary tree represented?
 *
 * We use the level order traversal sequence with a special symbol "#" denoting the null node.
 *
 * For Example:
 *
 * The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
 *
 *     1
 *
 *   /   \
 *
 *  2     3
 *
 *       /
 *
 *     4
 */

public class ZigZagTraversalOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test the tree:
        /**
         *          1
         *         / \
         *       2    3
         *     /  \  /  \
         *   4    5 6    7
         *  / \         / \
         * 8  9        10  11
         */
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);
        TreeNode nine = new TreeNode(9);
        TreeNode ten = new TreeNode(10);
        TreeNode eleven = new TreeNode(11);
        one.left =two;
        one.right = three;
        two.left = four;
        two.right = five;
        three.left = six;
        three.right = seven;
        four.left = eight;
        four.right = nine;
        seven.left = ten;
        seven.right = eleven;
        System.out.println(solution.zigZag(one));
    }
}

class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int key) {
        this.key = key;
    }
}

class Solution {
    public List<Integer> zigZag(TreeNode root) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offerFirst(root);
        // At a certain level, we need to determine if it is an
        // odd level or even level
        // If the level number is odd, we use deque.pollFirst() to
        // get the designated node
        // Otherwise, we do deque.pollLast()
        // For example, at level 3, the nodes in the deque should maintain the
        // order: 1, 4, 11, (3, 8)
        // 8 and 3 are polled in the previous level
        int level = 1;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = level % 2 == 0 ?
                        deque.pollFirst() : deque.pollLast();
                result.add(node.key);
                if (level % 2 == 0) {
                    // Because we do pollFirst() in even lovels
                    // To maintain the order of the deque, we need to
                    // offer the left (then right) child to the end
                    if (node.left != null) {
                        deque.offerLast(node.left);
                    }
                    if (node.right != null) {
                        deque.offerLast(node.right);
                    }
                } else {
                    // Because we do pollLast() in odd levels
                    // To maintain the order of the deque, we need to
                    // offer the right (then left) child to the head
                    if (node.right != null) {
                        deque.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.offerFirst(node.left);
                    }
                }
            }
            level++;
        }
        return result;
    }
}
