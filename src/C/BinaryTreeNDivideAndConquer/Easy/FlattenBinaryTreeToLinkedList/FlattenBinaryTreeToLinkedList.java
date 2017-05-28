package C.BinaryTreeNDivideAndConquer.Easy.FlattenBinaryTreeToLinkedList;

import java.util.Stack;

/**
 * http://www.lintcode.com/en/problem/flatten-binary-tree-to-linked-list/
 * Flatten a binary tree to a fake "linked list" in pre-order traversal.
 * Here we use the right pointer in TreeNode as the next pointer in ListNode.
 *
 * Notice
 * Don't forget to mark the left child of each node to null. Or you will get
 * Time Limit Exceeded or Memory Limit Exceeded.
 *
 * Example
 *               1
 *               \
 *     1          2
 *    / \          \
 *   2   5    =>    3
 *  / \   \          \
 * 3   4   6          4
 *                     \
 *                      5
 *                       \
 *                        6
 */

public class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Build a binary tree {1, 2, 5, 3, 4, #, 6}
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        solution.flatten(root);
        TreeNode node = root;
        while (node != null && node.right != null) {
            System.out.print(node.val + " -> ");
            node = node.right;
        }
        System.out.println((node != null) ? node.val : "");
    }
}

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

/*
// Method 1: System stack
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        // Preorder traversal: add the root to it first
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            // Preorder: try to push right child first so it gets popped first
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            // Remove the left pointer
            node.left = null;
            // Remove the right pointer if this is the last node
            // Otherwise, point it to the current first node on the stack
            if (stack.empty()) {
                node.right = null;
            } else {
                node.right = stack.peek();
            }
        }
    }
}
*/

// Method 2: Recursion with the help of a class variable
class Solution {
    private TreeNode prevNode = null;
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        if (prevNode != null) {
            prevNode.left = null;
            prevNode.right = root;
        }
        prevNode = root;
        TreeNode rightNode = root.right;
        flatten(root.left);
        flatten(rightNode);
    }
}