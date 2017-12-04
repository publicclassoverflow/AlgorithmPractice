package C.BinaryTreeNDivideAndConquer.Easy.MinimumSubtree;

import com.sun.org.apache.regexp.internal.RE;
import sun.security.util.Resources_sv;

import java.util.ArrayList;

/**
 * http://www.lintcode.com/en/problem/minimum-subtree/
 * Given a binary tree, find the subtree with minimum sum. Return the root of
 * the subtree.
 *
 * Notice:
 * LintCode will print the subtree which root is your return node.
 * It's guaranteed that there is only one subtree with minimum sum and the given
 * binary tree is not an empty tree.
 *
 * Example:
 * Given a binary tree:
 *      1
 *    /   \
 *  -5     2
 *  / \   /  \
 * 0   2 -4  -5
 */

public class MinimumSubtree {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 0
        TreeNode one0 = new TreeNode(1);
        TreeNode mFive0 = new TreeNode(-5);
        TreeNode two0 = new TreeNode(2);
        TreeNode zero = new TreeNode(0);
        TreeNode two1 = new TreeNode(2);
        TreeNode mFour0 = new TreeNode(-4);
        TreeNode mFive1 = new TreeNode(-5);
        one0.left = mFive0;
        one0.right = two0;
        mFive0.left = zero;
        mFive0.right = two1;
        two0.left = mFour0;
        two0.right = mFive1;
        System.out.println(solution.findSubtree(one0).val);
        // Test 1: Tree {1,-10,6,1,2,-4,-1}
        TreeNode one1 = new TreeNode(1);
        TreeNode mTen0 = new TreeNode(-10);
        TreeNode six0 = new TreeNode(6);
        TreeNode one2 = new TreeNode(1);
        TreeNode two2 = new TreeNode(2);
        TreeNode mFour1 = new TreeNode(-4);
        TreeNode mOne0 = new TreeNode(-1);
        one1.left = mTen0;
        one1.right = six0;
        mTen0.left = one2;
        mTen0.right = two2;
        six0.left = mFour1;
        six0.right = mOne0;
        System.out.println(solution.findSubtree(one1).val);
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

// Method 1: Divide and Conquer
class ResultType {
    TreeNode minSubtree;
    int sum;
    int minSum;
    protected ResultType(TreeNode minSubtree, int sum, int minSum) {
        this.minSubtree = minSubtree;
        this.sum = sum;
        this.minSum = minSum;
    }
}

class Solution {
    public TreeNode findSubtree(TreeNode root) {
        ResultType result = findMinSubtree(root);
        return result.minSubtree;
    }

    private ResultType findMinSubtree(TreeNode node) {
        if (node == null) {
            return new ResultType(null, 0, Integer.MAX_VALUE);
        }
        // Divide
        ResultType left = findMinSubtree(node.left);
        ResultType right = findMinSubtree(node.right);
        // Conquer
        ResultType result = new ResultType(
                node,
                left.sum + right.sum + node.val,
                left.sum + right.sum + node.val
        );
        if (left.minSum <= result.minSum) {
            result.minSum = left.minSum;
            result.minSubtree = left.minSubtree;
        }
        if (right.minSum <= result.minSum) {
            result.minSum = right.minSum;
            result.minSubtree = right.minSubtree;
        }
        return result;
    }
}

// Method 2: Traverse + Divide and Conquer
/*
class Solution {
    private TreeNode subtree = null;
    private int minSum = Integer.MAX_VALUE;

    public TreeNode findSubtree(TreeNode root) {
        // Write your code here
        if (root == null) {
            return null;
        }
        int minimumSum = findMinSum(root);
        return subtree;
    }

    private int findMinSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sum = node.val + findMinSum(node.left) + findMinSum(node.right);
        if (sum <= minSum) {
            minSum = sum;
            subtree = node;
        }
        return sum;
    }
}
*/