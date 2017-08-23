package D.BreadthFirstSearch.Medium.BinaryTreeSerialization;

/**
 * http://www.lintcode.com/en/problem/binary-tree-serialization/
 * Design an algorithm and write code to serialize and deserialize a binary
 * tree. Writing the tree to a file is called 'serialization' and reading back
 * from the file to reconstruct the exact same binary tree is 'deserialization'.
 *
 * Notice:
 * There is no limit of how you deserialize or serialize a binary tree, LintCode
 * will take your output of serialize as the input of deserialize, it won't
 * check the result of serialize.
 *
 * Example:
 * An example of testdata: Binary tree {3,9,20,#,#,15,7}, denote the following
 * structure:
 *      3
 *     / \
 *    9  20
 *      /  \
 *     15   7
 *
 * Our data serialization use bfs traversal. This is just for when you got wrong
 * answer and want to debug the input.
 * You can use other method to do serializaiton and deserialization.
 * */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeSerialization {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Testing {3,9,20,#,#,15,7}
        TreeNode three = new TreeNode(3);
        TreeNode nine = new TreeNode(9);
        TreeNode twenty = new TreeNode(20);
        TreeNode fifteen = new TreeNode(15);
        TreeNode seven = new TreeNode(7);
        three.left = nine;
        three.right = twenty;
        twenty.left = fifteen;
        twenty.right = seven;
        String serializedTree = solution.serialize(three);
        System.out.println(serializedTree);
        TreeNode deserializedRoot = solution.deserialize(serializedTree);
        System.out.println(deserializedRoot.val);
        System.out.println(deserializedRoot.left.val);
        System.out.println(deserializedRoot.right.val);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        // Method 1: Jiuzhang's solution
        /*
        if (root == null) {
            return "{}";
        }
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        for (int i = 0; i < queue.size(); i++) {
            TreeNode node = queue.get(i);
            if (node == null) {
                continue;
            }
            queue.add(node.left);
            queue.add(node.right);
        }
        while (queue.get(queue.size() - 1) == null) {
            queue.remove(queue.size() - 1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(queue.get(0).val);
        for (int i = 1; i < queue.size(); i++) {
            if (queue.get(i) == null) {
                sb.append(",#");
            } else {
                sb.append(",");
                sb.append(queue.get(i).val);
            }
        }
        sb.append("}");
        return sb.toString();
        */
        // My solution
        if (root == null) {
            return "{}";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder result = new StringBuilder("{");
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                if (result.length() > 1) {
                    result.append(",");
                }
                TreeNode node = queue.poll();
                if (node == null) {
                    result.append("#");
                    continue;
                }
                result.append(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        // Get rid of the #'s of the leaf nodes in the last level
        for (int i = result.length() - 1; i >= 0; i--) {
            if (result.charAt(i) != '#') {
                break;
            }
            // Delete the '#' and the ',' before it
            result.delete(i - 1, i + 1);
            i--;
        }
        result.append("}");
        return result.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if (data.equals("{}")) {
            return null;
        }
        String[] vals = data.substring(1, data.length() - 1).split(",");
        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        queue.add(root);
        int index = 0;
        boolean isLeftChild = true;
        for (int i = 1; i < vals.length; i++) {
            if (!vals[i].equals("#")) {
                TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
                if (isLeftChild) {
                    queue.get(index).left = node;
                } else {
                    queue.get(index).right = node;
                }
                queue.add(node);
            }
            if (!isLeftChild) {
                index++;
            }
            isLeftChild = !isLeftChild;
        }
        return root;
    }
}