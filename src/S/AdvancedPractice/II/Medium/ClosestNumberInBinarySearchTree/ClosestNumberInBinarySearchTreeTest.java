package S.AdvancedPractice.II.Medium.ClosestNumberInBinarySearchTree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosestNumberInBinarySearchTreeTest {
    static ClosestNumberInBinarySearchTree instance;
    TreeNode root;

    @BeforeAll
    static void setInstance() {
        instance = new ClosestNumberInBinarySearchTree();
    }

    @BeforeEach
    void setUp() {
        // Build tree
        TreeNode five = new TreeNode(5);
        TreeNode two = new TreeNode(2);
        TreeNode eleven = new TreeNode(11);
        TreeNode six = new TreeNode(6);
        TreeNode fourteen = new TreeNode(14);
        five.left = two;
        five.right = eleven;
        eleven.left = six;
        eleven.right = fourteen;
        root = five;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNull() {
        int result = instance.closest(null, 0);
        assertEquals(-1, result);
    }

    @Test
    void testZero() {
        int result = instance.closest(root, 0);
        assertEquals(2, result);
    }

    @Test
    void testFour() {
        int result = instance.closest(root, 4);
        assertEquals(5, result);
    }

    @Test
    void testTen() {
        int result = instance.closest(root, 10);
        assertEquals(11, result);
    }

    @Test
    void testSix() {
        int result = instance.closest(root, 6);
        assertEquals(6, result);
    }
}