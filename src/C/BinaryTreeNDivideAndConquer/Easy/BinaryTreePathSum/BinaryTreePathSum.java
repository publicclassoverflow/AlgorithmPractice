package C.BinaryTreeNDivideAndConquer.Easy.BinaryTreePathSum;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/binary-tree-path-sum/
 * Given a binary tree, find all paths that sum of the nodes in the path equals to a given number target.
 * A valid path is from root node to any of the leaf nodes.
 *
 * Example
 * Given a binary tree, and target = 5:
 *         1
 *       / \
 *     2    4
 *   / \
 * 2    3
 * return
 * [
 *  [1, 2, 3],
 *  [1, 4]
 * ]
 */

public class BinaryTreePathSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Build a binary tree {1, 2, 4, 2, 3}
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        int target = 5;
        System.out.println(
                "Expected output: [[1, 2, 2], [1, 4]]. " +
                "Actual output: " + solution.binaryTreePathSum(root, target)
        );
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
     * @param root   the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // Write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        ArrayList<Integer> path = new ArrayList<>();
        path.add(root.val);
        preorderTraversal(root, root.val, target, path, result);
        return result;
    }

    private void preorderTraversal(TreeNode node, int currentSum, int targetSum,
                                   ArrayList<Integer> currentPath,
                                   List<List<Integer>> resultList) {
        // Reach a leaf node; add the path to result if target sum is achieved
        if (node.left == null && node.right == null) {
            if (currentSum == targetSum) {
                resultList.add(new ArrayList<Integer>(currentPath));
            }
            return;
        }
        // Preorder: root -> left -> right
        // Go left
        if (node.left != null) {
            currentPath.add(node.left.val);
            preorderTraversal(node.left, currentSum + node.left.val,
                              targetSum, currentPath, resultList);
            // Need to remove the latest path before going right
            currentPath.remove(currentPath.size() - 1);
        }
        // Go right
        if (node.right != null) {
            currentPath.add(node.right.val);
            preorderTraversal(node.right, currentSum + node.right.val,
                              targetSum, currentPath, resultList);
            currentPath.remove(currentPath.size() - 1);
        }
    }
}
