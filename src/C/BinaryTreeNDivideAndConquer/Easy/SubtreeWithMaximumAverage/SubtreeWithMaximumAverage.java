package C.BinaryTreeNDivideAndConquer.Easy.SubtreeWithMaximumAverage;

/**
 * http://www.lintcode.com/en/problem/subtree-with-maximum-average/
 * Given a binary tree, find the subtree with maximum average.
 * Return the root of the subtree.
 *
 * Notice:
 * LintCode will print the subtree which root is your return node.
 * It's guaranteed that there is only one subtree with maximum average.
 *
 * Example:
 * Given a binary tree:
 *          1
 *        /   \
 *      -5     11
 *      / \   /  \
 *     1   2 4    -2
 * return the node 11.
 */

public class SubtreeWithMaximumAverage {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Build the sample tree
        TreeNode one = new TreeNode(1);
        TreeNode mFive = new TreeNode(-5);
        TreeNode eleven = new TreeNode(11);
        TreeNode newOne = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode four  = new TreeNode(4);
        TreeNode mTwo = new TreeNode(-2);
        one.left = mFive;
        one.right = eleven;
        mFive.left = newOne;
        mFive.right = two;
        eleven.left = four;
        eleven.right = mTwo;
        System.out.println("The expected answer is: 11");
        System.out.format(
                "The actual answer is: %d",
                solution.findSubtree2(one).val
        );
    }
}

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class Solution {
    /**
     * @param root the root of binary tree
     * @return the root of the maximum average of subtree
     */
    // Create a ResultType helper class to store the results
    private class ResultType {
        public int sum, size;
        public ResultType(int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
    }

    private TreeNode subtree = null;
    private ResultType subtreeResult = null;

    public TreeNode findSubtree2(TreeNode root) {
        // Write your code here
        traverse(root);
        return subtree;
    }

    private ResultType traverse(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }
        ResultType left = traverse(root.left);
        ResultType right = traverse(root.right);
        ResultType result = new ResultType(
                left.sum + right.sum + root.val,
                left.size + right.size + 1
        );
        if (subtree == null ||
            // a.sum / a.size < b.sum / b.size is equivalent to
            // a.sum * b.size < b.sum * a.size
            // Since division is expensive, using multiplication is a good trick
            subtreeResult.sum * result.size < result.sum * subtreeResult.size) {
            subtree = root;
            subtreeResult = result;
        }
        return result;
    }
}
