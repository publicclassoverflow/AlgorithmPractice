package S.AdvancedPractice.II.Easy.SearchInBinarySearchTree;

/**
 * Description
 * Find the target key K in the given binary search tree, return the node that contains the key if K is found, otherwise return null.
 *
 * Assumptions
 *
 * There are no duplicate keys in the binary search tree
 */

public class SearchInBinarySearchTree {
    public TreeNode search(TreeNode root, int key) {
        // Write your solution here
        // Recursive method
        // Base case: when the node is (not) found
        if (root == null || root.key == key) {
            return root;
        }
        // Recursive rule
        return root.key > key ? search(root.left, key) : search(root.right, key);

        // Iterative method
        /*
        if (root == null) {
            return null;
        }
        TreeNode curr = root;
        while (curr != null) {
            if (curr.key == key) {
                break;
            } else if (curr.key > key) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return curr;
        */
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
