package C.BinaryTreeNDivideAndConquer.Easy.BinaryTreePostOrderTraversal;

import java.util.ArrayList;

/**
 * http://www.lintcode.com/en/problem/binary-tree-postorder-traversal/
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * Example
 * Given binary tree {1,#,2,3},
 * return [3,2,1].
 */
public class BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        // Build a testing binary tree {1, #, 2, 3}
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        Solution solution = new Solution();
        System.out.println(solution.postorderTraversal(root));
        // Build a testing binary tree {1, 2, 3}
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        System.out.println(solution.postorderTraversal(node));
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
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // Divide
        ArrayList<Integer> leftChild = postorderTraversal(root.left);
        ArrayList<Integer> rightChild = postorderTraversal(root.right);
        // Conquer
        result.addAll(leftChild);
        result.addAll(rightChild);
        result.add(root.val);
        return result;
    }
}
