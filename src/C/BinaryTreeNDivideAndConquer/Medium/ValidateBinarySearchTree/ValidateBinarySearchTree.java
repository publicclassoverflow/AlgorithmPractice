package C.BinaryTreeNDivideAndConquer.Medium.ValidateBinarySearchTree;

/**
 * http://www.lintcode.com/en/problem/validate-binary-search-tree/
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * A single node tree is a BST
 *
 * An example:
 * {2,1,4,#,#,3,5}
 */

public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test
        TreeNode two = new TreeNode(2);
        TreeNode one = new TreeNode(1);
        TreeNode four = new TreeNode(4);
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        two.left = one;
        two.right = four;
        four.left = three;
        four.right = five;
        System.out.println(solution.isValidBST(two));   // True
        System.out.println(solution.isValidBST(null));  // True
        System.out.println(solution.isValidBST(new TreeNode(Integer.MAX_VALUE)));
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

class ResultType {
    boolean isBST;
    int minVal;
    int maxVal;
    public ResultType(boolean isBST, int minVal, int maxVal) {
        this.isBST = isBST;
        this.minVal = minVal;
        this.maxVal = maxVal;
    }
}

class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        // write your code here
        ResultType result = validateBSF(root);
        return result.isBST;
    }

    private ResultType validateBSF(TreeNode node) {
        if (node == null) {
            return new ResultType(true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        // Divide
        ResultType left = validateBSF(node.left);
        ResultType right = validateBSF(node.right);
        // Conquer
        if (!left.isBST || !right.isBST) {
            return new ResultType(false, 0, 0);
        }
        // left max and right min violations
        if (node.left != null && left.maxVal >= node.val) {
            return new ResultType(false, 0, 0);
        }
        if (node.right != null && right.minVal <= node.val) {
            return new ResultType(false, 0, 0);
        }
        // Valid
        return new ResultType(
                true,
                Math.min(left.minVal, node.val),
                Math.max(right.maxVal, node.val)
        );
    }
}
