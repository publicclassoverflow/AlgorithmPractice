package Z.Others.Medium.CousinsInBinaryTree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Check if two nodes are cousins in a Binary Tree
 *
 * Given the binary Tree and the two nodes say ‘a’ and ‘b’, determine whether the two nodes are cousins of each other or not.
 *
 * Two nodes are cousins of each other if they are at same level and have different parents.
 *
 * Example:
 *
 *                       6
 *
 *                   /     \
 *
 *                3            5
 *
 *             /     \      /    \
 *
 *          7         8    1       2
 * 7 and 1, result is TRUE
 *
 * 3 and 5, result is FALSE
 *
 * 7 and 5, result is FALSE
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
    }
}

public class CousinsInBinaryTree {
    public boolean isCousin(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // Use a HashSet to store all the children generated in this level
            Set<TreeNode> children = new HashSet<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                // The only situation where two nodes in the same level are not cousins is when they are siblings
                if (curr.left != null && curr.right != null) {
                    if ((curr.left == a && curr.right == b) || (curr.left == b && curr.right == a)) {
                        return false;
                    }
                }
                // Otherwise, the two target nodes have different parents in this level
                if (curr.left != null) {
                    queue.offer(curr.left);
                    children.add(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                    children.add(curr.right);
                }
            }
            // We have excluded the possibility where the two nodes are siblings in this level
            // Therefore, if they are generated in this level and added to the children set, they are cousins
            if (children.contains(a) && children.contains(b)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CousinsInBinaryTree cousinsInBinaryTree = new CousinsInBinaryTree();
        TreeNode six = new TreeNode(6);
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        six.left = three;
        six.right = five;
        three.left = seven;
        three.right = eight;
        five.left = one;
        five.right = two;
        System.out.println(cousinsInBinaryTree.isCousin(six, seven, one));
        System.out.println(cousinsInBinaryTree.isCousin(six, eight, two));
        System.out.println(cousinsInBinaryTree.isCousin(six, eight, one));
        System.out.println(cousinsInBinaryTree.isCousin(six, three, five));
        System.out.println(cousinsInBinaryTree.isCousin(six, seven, five));
    }
}
