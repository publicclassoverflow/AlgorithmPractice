package C.BinaryTreeNDivideAndConquer.Easy.MaximumDepthOfBinaryTree;

/**
 * http://www.lintcode.com/en/problem/maximum-depth-of-binary-tree/
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root
 * node down to the farthest leaf node.
 *
 * Example:
 * Given a binary tree as follow:
 *      1
       / \
      2   3
         / \
        4   5
 * The maximum depth is 3.
 */

public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        one.left = two;
        one.right = three;
        three.left = four;
        three.right = five;
        System.out.println(solution.maxDepth(one));
    }
}

class TreeNode {
    int val;
    TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(TreeNode root) {
        // write your code here
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}
