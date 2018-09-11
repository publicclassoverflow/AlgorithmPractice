package S.AdvancedPractice.II.Easy.InsertInBinarySearchTree;

import java.util.Objects;

/**
 * Description
 * Insert a key in a binary search tree if the binary search tree does not already contain the key. Return the root of the binary search tree.
 *
 * Assumptions
 *
 * There are no duplicate keys in the binary search tree
 *
 * If the key is already existed in the binary search tree, you do not need to do anything
 *
 * Examples
 *
 *         5
 *
 *       /    \
 *
 *     3        8
 *
 *   /   \
 *
 *  1     4
 *
 * insert 11, the tree becomes
 *
 *         5
 *
 *       /    \
 *
 *     3        8
 *
 *   /   \        \
 *
 *  1     4       11
 *
 * insert 6, the tree becomes
 *
 *         5
 *
 *       /    \
 *
 *     3        8
 *
 *   /   \     /  \
 *
 *  1     4   6    11
 */

public class InsertInBinaryTree {
    public TreeNode insert(TreeNode root, int key) {
        // Write your solution here
        if (root == null) {
            return new TreeNode(key);
        }
        TreeNode curr = root;
        while (curr != null) {
            if (curr.key == key) {
                // Case 1: curr node has the same key as the input ==> it is already in the tree
                break;
            } else if (curr.key > key) {
                // Case 2: look left
                if (curr.left == null) {
                    curr.left = new TreeNode(key);
                    break;
                } else {
                    curr = curr.left;
                }
            } else {
                // Case 3: look right
                if (curr.right == null) {
                    curr.right = new TreeNode(key);
                    break;
                } else {
                    curr = curr.right;
                }
            }
        }
        return root;
    }
}

class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return key == treeNode.key &&
                Objects.equals(left, treeNode.left) &&
                Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, left, right);
    }
}
