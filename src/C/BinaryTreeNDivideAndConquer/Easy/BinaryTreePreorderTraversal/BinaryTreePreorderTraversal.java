package C.BinaryTreeNDivideAndConquer.Easy.BinaryTreePreorderTraversal;

import java.util.ArrayList;
import java.util.Stack;

/**
 * http://www.lintcode.com/en/problem/binary-tree-preorder-traversal/
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * Example
 * Given binary tree {1, 2, 3, 4, 5}
 *      1
 *     /\
 *    2 3
 *   /\
 *  4 5
 *  return [1, 2, 4, 5, 3].
 */

public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Build a testing binary tree {1, 2, 3, 4, 5};
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(
                "Expected output: [1, 2, 4, 5, 3]. " +
                "Actual output: " + solution.preorderTraversal(root)
        );
        // Build a testing binary tree {1, 2, 3}
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        System.out.println(
                "Expected output: [1, 2, 3]. " +
                "Actual output: " + solution.preorderTraversal(node)
        );
    }
}

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

/*
// Method #1: Divide and Conquer
class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // Divide
        ArrayList<Integer> leftChild = preorderTraversal(root.left);
        ArrayList<Integer> rightChild = preorderTraversal(root.right);
        // Conquer
        result.add(root.val);
        result.addAll(leftChild);
        result.addAll(rightChild);
        return result;
    }
}
*/

/*
// Method #2: Recursive traversal
class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<>();
        preorderTraverse(root, result);
        return result;
    }

    private void preorderTraverse(TreeNode node, ArrayList<Integer> list) {
        // Exit: leaf node reached
        if (node == null) {
            return;
        }
        // Breakdown: Preorder - root -> left -> right
        list.add(node.val);
        preorderTraverse(node.left, list);
        preorderTraverse(node.right, list);
    }
}
*/

// Method #3: Non-recursive iteration using the system stack
class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            // Preorder: root -> left -> right
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }
}
