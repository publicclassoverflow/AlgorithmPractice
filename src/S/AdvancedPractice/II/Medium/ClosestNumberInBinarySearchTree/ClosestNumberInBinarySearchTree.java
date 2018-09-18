package S.AdvancedPractice.II.Medium.ClosestNumberInBinarySearchTree;

/**
 * https://app.laicode.io/app/problem/135
 *
 * Description
 * In a binary search tree, find the node containing the closest number to the given target number.
 *
 * Assumptions:
 *
 * The given root is not null.
 * There are no duplicate keys in the binary search tree.
 * Examples:
 *
 *     5
 *
 *   /    \
 *
 * 2      11
 *
 *      /    \
 *
 *     6     14
 *
 * closest number to 4 is 5
 *
 * closest number to 10 is 11
 *
 * closest number to 6 is 6
 *
 * How is the binary tree represented?
 *
 * We use the level order traversal sequence with a special symbol "#" denoting the null node.
 *
 * For Example:
 *
 * The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
 *
 *     1
 *
 *   /   \
 *
 *  2     3
 *
 *       /
 *
 *     4
 */

public class ClosestNumberInBinarySearchTree {
    public int closest(TreeNode root, int target) {
        // Write your solution here
        // Iterative method
        if (root == null) {
            return -1;
        }
        if (root.key == target) {
            return root.key;    // return target
        }
        TreeNode curr = root;
        // It is fine to use an int to record the result
        // int closest = curr.key
        // But by using a TreeNode and returning its key as the solution,
        // we also have the access to the node in case we need more
        // information in the future
        TreeNode closest = curr;
        int minDiff = Integer.MAX_VALUE;
        // Traverse the tree in classic BST method
        while (curr != null) {
            int diff = Math.abs(curr.key - target);
            if (diff < minDiff) {
                minDiff = diff;
                closest = curr;
            }
            // Traversal
            if (curr.key == target) {
                break;
            } else if (curr.key > target) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return closest.key;
    }
}

class TreeNode {
    int key;
    TreeNode left;
    TreeNode right;
    public TreeNode(int key) {
        this.key = key;
    }
}
