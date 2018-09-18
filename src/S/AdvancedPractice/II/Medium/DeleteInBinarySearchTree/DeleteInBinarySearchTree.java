package S.AdvancedPractice.II.Medium.DeleteInBinarySearchTree;

import S.AdvancedPractice.II.Medium.LargestNumberSmallerInBinarySearchTree.LargestNumberSmallerInBinarySearchTree;

/**
 * Description
 * Delete the target key K in the given binary search tree if the binary search tree contains K. Return the root of the binary search tree.
 *
 * Find your own way to delete the node from the binary search tree, after deletion the binary search tree's property should be maintained.
 *
 * Assumptions
 *
 * There are no duplicate keys in the binary search tree
 *
 * The smallest larger node is first candidate after deletion
 */

public class DeleteInBinarySearchTree {
    public TreeNode delete(TreeNode root, int target) {
        // Write your solution here
        // Base case: root is null or leaf node's child ==> return null
        if (root == null) {
            return null;
        }
        // Basic BST traversal
        // Recursive rule
        if (root.key < target) {
            root.right = delete(root.right, target);
        } else if (root.key > target) {
            root.left = delete(root.left, target);
        } else {
            // Found the node to be deleted
            if (root.left == null && root.right == null) {
                // Case 1: leaf node
                //         Delete the node ==> return null to the parent
                return null;
            } else if (root.left == null || root.right == null) {
                // Case 2: only one of the children is null
                //         Delete the node and return its child to the parent
                //         such that the child replaces the node
                return root.left == null ? root.right : root.left;
            } else {
                // Case 3: both of the children are not null
                //         1. find the smallest node in the right subtree
                //         2. cover the node's value with largestSmaller
                //         3. delete the node from the right subtree
                int smallest = findSmallest(root.right);
                root.key = smallest;
                root.right = delete(root.right, smallest);
            }
        }
        return root;
    }

    private int findSmallest(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.key;
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
