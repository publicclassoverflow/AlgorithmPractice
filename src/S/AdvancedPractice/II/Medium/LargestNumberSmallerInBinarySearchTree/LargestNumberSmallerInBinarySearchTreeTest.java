package S.AdvancedPractice.II.Medium.LargestNumberSmallerInBinarySearchTree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LargestNumberSmallerInBinarySearchTreeTest {
    static LargestNumberSmallerInBinarySearchTree instance;
    TreeNode root;

    @BeforeAll
    static void setInstance() {
        instance = new LargestNumberSmallerInBinarySearchTree();
    }

    @BeforeEach
    void setUp() {
        // Build the example tree
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
        int result = instance.largestSmaller(null, 0);
        assertEquals(Integer.MIN_VALUE, result);
    }

    @Test
    void testZero() {
        int result = instance.largestSmaller(root, 0);
        assertEquals(Integer.MIN_VALUE, result);
    }

    // The next 3 tests are the ones done in the description
    @Test
    void testOne() {
        int result = instance.largestSmaller(root, 1);
        assertEquals(Integer.MIN_VALUE, result);
    }

    @Test
    void testTen() {
        int result = instance.largestSmaller(root, 10);
        assertEquals(6, result);
    }

    @Test
    void testSix() {
        int result = instance.largestSmaller(root, 6);
        assertEquals(5, result);
    }
}