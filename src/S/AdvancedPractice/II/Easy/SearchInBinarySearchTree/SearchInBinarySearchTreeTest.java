package S.AdvancedPractice.II.Easy.SearchInBinarySearchTree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchInBinarySearchTreeTest {
    static SearchInBinarySearchTree instance;
    TreeNode root;

    @BeforeAll
    static void setInstance() {
        instance = new SearchInBinarySearchTree();
    }

    @BeforeEach
    void setUp() {
        // Build the example tree from last question
        TreeNode five = new TreeNode(5);
        TreeNode three = new TreeNode(3);
        TreeNode eight = new TreeNode(8);
        TreeNode one = new TreeNode(1);
        TreeNode four = new TreeNode(4);
        TreeNode eleven = new TreeNode(11);
        five.left = three;
        five.right = eight;
        three.left = one;
        three.right = four;
        eight.right = eleven;
        root = five;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNull() {
        TreeNode result = instance.search(null, 0);
        assertEquals(null, result);
    }

    @Test
    void testZero() {
        TreeNode result = instance.search(root, 0);
        assertEquals(null, result);
    }

    @Test
    void testOne() {
        TreeNode result = instance.search(root, 1);
        assertEquals(1, result.key);
    }

    @Test
    void testEight() {
        TreeNode result = instance.search(root, 8);
        assertAll("Node 8",
                () -> assertEquals(8, result.key),
                () -> assertEquals(null, result.left),
                () -> assertEquals(11, result.right.key)
        );
    }

    @Test
    void testHundred() {
        TreeNode result = instance.search(root, 100);
        assertEquals(null, result);
    }
}