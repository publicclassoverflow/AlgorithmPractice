package S.AdvancedPractice.II.Medium.DeleteInBinarySearchTree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class DeleteInBinarySearchTreeTest {
    static DeleteInBinarySearchTree instance;
    TreeNode root;

    @BeforeAll
    static void setInstance() {
        instance = new DeleteInBinarySearchTree();
    }

    @BeforeEach
    void setUp() {
        // Build tree
        root = buildTree(Arrays.asList(7, Integer.MIN_VALUE, 16, 11, 18, Integer.MIN_VALUE, 12, Integer.MIN_VALUE, 20));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNull() {
        TreeNode result = instance.delete(null, 0);
        assertEquals(null, result);
    }

    @Test
    void testTen() {
        TreeNode result = instance.delete(root, 10);
        assertAll("Delete 10 -> no changes",
                () -> assertEquals(root.key, result.key),
                () -> assertEquals(root.left, result.left),
                () -> assertEquals(root.right.key, result.right.key),
                () -> assertEquals(root.right.left.key, result.right.left.key),
                () -> assertEquals(root.right.right.key, result.right.right.key),
                () -> assertEquals(root.right.left.right.key, result.right.left.right.key),
                () -> assertEquals(root.right.right.right.key, result.right.right.right.key)
        );
    }

    @Test
    void testEleven() {
        TreeNode result = instance.delete(root, 11);
        assertEquals(7, result.key);
    }

    @Test
    // This is the test that I got incorrect solution
    void testNew() {
        root = buildTree(Arrays.asList(7, 3, Integer.MIN_VALUE, 2, 5, 1, Integer.MIN_VALUE, 4, 6));
        TreeNode result = instance.delete(root, 5);
        assertAll("Delete 5, replaced with 6",
                () -> assertEquals(7, result.key),
                () -> assertEquals(3, result.left.key),
                () -> assertEquals(null, result.right),
                () -> assertEquals(2, result.left.left.key),
                () -> assertEquals(6, result.left.right.key),
                () -> assertEquals(4, result.left.right.left.key),
                () -> assertEquals(null, result.left.right.right)
        );
    }
    
    private TreeNode buildTree(List<Integer> keys) {
        TreeNode root = new TreeNode(keys.get(0));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        while (!queue.isEmpty() && index < keys.size()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr == null) {
                    continue;
                }
                int left = keys.get(index++);
                curr.left = left == Integer.MIN_VALUE ? null : new TreeNode(left);
                queue.offer(curr.left);
                int right = keys.get(index++);
                curr.right = right == Integer.MIN_VALUE ? null : new TreeNode(right);
                queue.offer(curr.right);
            }
        }
        return root;
    }
}