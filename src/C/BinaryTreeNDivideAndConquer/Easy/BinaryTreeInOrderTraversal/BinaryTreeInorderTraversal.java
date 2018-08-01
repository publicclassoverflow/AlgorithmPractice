package C.BinaryTreeNDivideAndConquer.Easy.BinaryTreeInorderTraversal;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/binary-tree-inorder-traversal/
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example
 * Given binary tree {1,#,2,3},
 *      1
 *      \
 *      2
 *     /
 *    3
 * return [1, 3, 2].
 */

public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Build a testing binary tree {1, #, 2, 3}
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        System.out.println(
                "Expected output: [1, 3, 2]. " +
                "Actual output: " + solution.inorderTraversal(root)
        );
        // Build a testing binary tree {1, 2, 3}
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        System.out.println(
                "Expected output: [2, 1, 3]. " +
                "Actual output: " + solution.inorderTraversal(node)
        );
    }
}

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Inorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        /*
        // Method #1: Divide and Conquer
        // Divide
        ArrayList<Integer> leftChild = inorderTraversal(root.left);
        ArrayList<Integer> rightChild = inorderTraversal(root.right);
        // Conquer
        result.addAll(leftChild);
        result.add(root.val);
        result.addAll(rightChild);
        return result;
        */

        // Method #2: System stack
        Deque<TreeNode> stack = new LinkedList<>();
        // Inorder: left -> root -> right
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            /*
            while (node != null) {
                stack.offerFirst(node);
                node = node.left;
            }
            node = stack.pollFirst();
            result.add(node.val);
            node = node.right;
            */
            if (node != null) {
                stack.offerFirst(node);
                node = node.left;
            } else {
                node = stack.pollFirst();
                result.add(node.val);
                node = node.right;
            }
        }
        return result;
    }
}



