package C.BinaryTreeNDivideAndConquer.Easy.BinaryTreePaths;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/binary-tree-paths/
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Example:
 * Given the following binary tree:
 *    1
    /   \
   2     3
    \
     5
 * All root-to-leaf paths are:
 * [
 * "1->2->5",
 * "1->3"
 * ]
 */

public class BinaryTreePaths {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        one.left = two;
        one.right = three;
        two.right = five;
        System.out.println(solution.binaryTreePaths(one));
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
    /*
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // write your code here
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // Divide
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        for (String path : left) {
            result.add(root.val + "->" + path);
        }
        for (String path : right) {
            result.add(root.val + "->" + path);
        }
        // Conquer
        if (result.size() == 0) {
            result.add(String.valueOf(root.val));
        }
        return result;
    }
}
