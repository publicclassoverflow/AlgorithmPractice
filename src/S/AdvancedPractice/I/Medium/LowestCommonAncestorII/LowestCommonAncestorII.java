package S.AdvancedPractice.I.Medium.LowestCommonAncestorII;

/**
 * Description
 * Given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.
 *
 * Assumptions
 *
 * There is parent pointer for the nodes in the binary tree
 *
 * The given two nodes are not guaranteed to be in the binary tree
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
 *
 * The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
 */

public class LowestCommonAncestorII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test the example
        TreeNodeP five = new TreeNodeP(5, null);
        TreeNodeP nine = new TreeNodeP(9, five);
        TreeNodeP twelve = new TreeNodeP(12, five);
        TreeNodeP two = new TreeNodeP(2, nine);
        TreeNodeP three = new TreeNodeP(3, nine);
        TreeNodeP fourteen = new TreeNodeP(14, twelve);
        System.out.println(solution.lowestCommonAncestor(two, fourteen).key);
        System.out.println(solution.lowestCommonAncestor(two, nine).key);
        System.out.println(solution.lowestCommonAncestor(nine, fourteen).key);
        TreeNodeP eight = new TreeNodeP(8, null);
        System.out.println(solution.lowestCommonAncestor(two, eight).key);
    }
}

class TreeNodeP {
    public int key;
    public TreeNodeP left;
    public TreeNodeP right;
    public TreeNodeP parent;
    public TreeNodeP(int key, TreeNodeP parent) {
        this.key = key;
        this.parent = parent;
    }
}

class Solution {
    public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
        // Write your solution here
        if (one == null || two == null) {
            return null;
        }
        int depthOne = getDepth(one);
        int depthTwo = getDepth(two);
        return lca(one, two, depthOne, depthTwo);
    }

    private TreeNodeP lca(TreeNodeP one, TreeNodeP two, int depthOne, int depthTwo) {
        TreeNodeP curr;
        // There are two cases:
        // 1. node one is in a deeper position than two
        // 2. node two is in a deeper position than one
        int diff = depthOne - depthTwo;
        if (diff > 0) {
            // Case 1: because node one sits deeper, we need to traverse from node one
            curr = one;
            while (diff > 0) {
                curr = curr.parent;
                diff--;
            }
            // When node curr and two are in the same level
            // we need to traverse both nodes together until
            // we meet their first common parent
            while (curr != two) {
                curr = curr.parent;
                two = two.parent;
            }
        } else {
            // Case 2: node two sits deeper, we need to traverse upwards from node two
            curr = two;
            while (diff < 0) {
                curr = curr.parent;
                diff++;
            }
            // When node curr and one are in the same level
            // Traverse both nodes until meeting their first common parent
            while (curr != one) {
                curr = curr.parent;
                one = one.parent;
            }
        }
        return curr;
    }

    private int getDepth(TreeNodeP node) {
        if (node == null) {
            return 0;
        }
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.parent;
        }
        return depth;
    }
}
