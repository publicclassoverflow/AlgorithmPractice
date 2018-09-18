package S.AdvancedPractice.II.Medium.LargestNumberSmallerInBinarySearchTree;

/**
 * Description
 * In a binary search tree, find the node containing the largest number smaller than the given target number.
 *
 * If there is no such number, return INT_MIN.
 *
 * Assumptions:
 *
 * The given root is not null.
 * There are no duplicate keys in the binary search tree.
 * Examples
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
 * largest number smaller than 1 is Integer.MIN_VALUE(Java) or INT_MIN(c++)
 *
 * largest number smaller than 10 is 6
 *
 * largest number smaller than 6 is 5
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

public class LargestNumberSmallerInBinarySearchTree {
    public int largestSmaller(TreeNode root, int target) {
        // Write your solution here
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        // Better method imo
        /*
        TreeNode curr = root;
        TreeNode best = null;   // largestSmaller is weird. Call it the best.
        int minDiff = Integer.MAX_VALUE;
        while (curr != null) {
            if (curr.key < target) {
                // Case 1: current node is smaller than the target
                //         update minDiff and the result then go right
                int diff = target - curr.key;
                if (diff < minDiff) {
                    minDiff = diff;
                    best = curr;
                }
                curr = curr.right;
            } else {
                // Case 2: current node is larger than the target
                //         go left and do nothing
                curr = curr.left;
            }
        }
        return best == null ? Integer.MIN_VALUE : best.key;
        */
        // An easier solution
        int result = Integer.MIN_VALUE;
        while (root != null) {
            if (root.key < target) {
                // When we see a node that is smaller than the target, we need to go right
                // And since everything on the right is larger than the current node,
                // we can update the result directly
                result = root.key;
                root = root.right;
            } else {
                // When we see a node >= the target, do nothing but go left
                root = root.left;
            }
        }
        return result;
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
