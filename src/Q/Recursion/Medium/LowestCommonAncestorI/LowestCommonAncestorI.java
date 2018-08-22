package Q.Recursion.Medium.LowestCommonAncestorI;

/**
 * Description
 * Given two nodes in a binary tree, find their lowest common ancestor.
 *
 * Assumptions
 *
 * There is no parent pointer for the nodes in the binary tree
 *
 * The given two nodes are guaranteed to be in the binary tree
 *
 * Examples
 *
 *         5
 *
 *       /   \
 *
 *      9     12
 *
 *    /  \      \
 *
 *   2    3      14
 *
 * The lowest common ancestor of 2 and 14 is 5
 *
 * The lowest common ancestor of 2 and 9 is 9
 */

public class LowestCommonAncestorI {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 1
        TreeNode five = new TreeNode(5);
        TreeNode nine = new TreeNode(9);
        TreeNode twelve = new TreeNode(12);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode fourteen = new TreeNode(14);
        five.left = nine;
        five.right = twelve;
        nine.left = two;
        nine.right = three;
        twelve.right = fourteen;
        System.out.println(solution.lowestCommonAncestor(five, two, fourteen).key);
        System.out.println(solution.lowestCommonAncestor(five, two, nine).key);
    }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode one, TreeNode two) {
        // Write your solution here.
        // Base case:
        // 1. root is null ==> leaf
        if (root == null) {
            return root;
        }
        // 2. one/two is the root ==> one/two is the LCA itself
        if (root == one || root == two) {
            return root;
        }
        // Recursive rule:
        // Look for the two nodes in the left/right subtree of the root
        TreeNode left = lowestCommonAncestor(root.left, one, two);
        TreeNode right = lowestCommonAncestor(root.right, one, two);
        // 1. If both left and right are not null
        //    ==> they are children of the root ==> root is the LCA
        if (left != null && right != null) {
            return root;
        }
        // 2. If left/right is null
        //    ==> only left/right is root's child ==> LCA is above root
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        // 3. If left & right are null
        //    ==> root is not their ancestor
        return null;
    }
}

class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int key) {
        this.key = key;
    }
}
