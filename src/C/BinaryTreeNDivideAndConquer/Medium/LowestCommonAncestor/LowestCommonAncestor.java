package C.BinaryTreeNDivideAndConquer.Medium.LowestCommonAncestor;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/lowest-common-ancestor/
 * Given the root and two nodes in a Binary Tree. Find the lowest common
 * ancestor(LCA) of the two nodes.
 * The lowest common ancestor is the node with largest depth which is the
 * ancestor of both nodes.
 *
 * Notice
 * Assume two nodes exist in tree
 *
 * Example
 * For the following binary tree:
 *      4
 *     / \
 *    3   7
 *       / \
 *      5   6
 */

public class LowestCommonAncestor {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode four = new TreeNode(4);
        TreeNode three = new TreeNode(3);
        TreeNode seven = new TreeNode(7);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        four.left = three;
        four.right = seven;
        seven.left = five;
        seven.right = six;
        TreeNode[][] arg =
                new TreeNode[][]{{three, five}, {five, six}, {six, seven}};
        for (TreeNode[] anArg : arg) {
            TreeNode A = anArg[0];
            TreeNode B = anArg[1];
            TreeNode lca = solution.lowestCommonAncestor(four, A, B);
            System.out.format("The LCA for %d and %d is %d.%n",
                              A.val, B.val, lca != null ? lca.val : "");
        }
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

class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param A     and B: two nodes in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        // Exit condition
        if (root == null || root == A || root == B) {
            return root;
        }
        // Divide
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);
        // Conquer
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }
}
