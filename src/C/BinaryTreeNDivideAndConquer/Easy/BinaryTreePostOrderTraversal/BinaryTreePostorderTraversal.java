package C.BinaryTreeNDivideAndConquer.Easy.BinaryTreePostOrderTraversal;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/binary-tree-postorder-traversal/
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * Example
 * Given binary tree {1,#,2,3},
 *      1
 *      \
 *      2
 *     /
 *    3
 * return [3,2,1].
 */

public class BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Build a testing binary tree {1, #, 2, 3}
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        System.out.println(
                "Expected output: [3, 2, 1]. " +
                "Actual output: " + solution.postorderTraversal(root)
        );
        // Build a testing binary tree {1, 2, 3}
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        System.out.println(
                "Expected output: [2, 3, 1]. " +
                "Actual output: " + solution.postorderTraversal(node)
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
     * @return: Postorder in ArrayList which contains node values.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // Method #1: Divide and Conquer
        /*
        // Divide
        List<Integer> leftChild = postorderTraversal(root.left);
        List<Integer> rightChild = postorderTraversal(root.right);
        // Conquer
        result.addAll(leftChild);
        result.addAll(rightChild);
        result.add(root.val);
        return result;
        */

        // Method #2: stack
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            // The current node on the top of stack is the root
            // and it needs to be kept in place because we are
            // visiting it again after we finish traversal from
            // its child subtrees
            TreeNode curr = stack.peekFirst();
            if (prev == null || curr == prev.left || curr == prev.right) {
                // When prev is null (root is the "root") or prev is the
                // parent of the current node
                // We should go down (left subtree first)
                if (curr.left != null) {
                    stack.offerFirst(curr.left);
                } else if (curr.right != null) {
                    stack.offerFirst(curr.right);
                } else {
                    // Child traversal has finished
                    // Now we need to pop the stack
                    stack.pollFirst();
                    result.add(curr.val);
                }
            } else if (prev == curr.left) {
                // When we are coming back from the left subtree
                // We should try to go right
                if (curr.right != null) {
                    stack.offerFirst(curr.right);
                } else {
                    stack.pollFirst();
                    result.add(curr.val);
                }
            } else if (prev == curr.right) {
                stack.pollFirst();
                result.add(curr.val);
            }
            prev = curr;
        }
        return result;
    }
}
