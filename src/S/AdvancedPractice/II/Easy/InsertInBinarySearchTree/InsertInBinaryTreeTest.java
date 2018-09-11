package S.AdvancedPractice.II.Easy.InsertInBinarySearchTree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertInBinaryTreeTest {
    InsertInBinaryTree instance;
//    TreeNode root;

    @BeforeEach
    void setUp() {
        instance = new InsertInBinaryTree();
        // Build the test tree
        /*
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
        */
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNull() {
        TreeNode result = instance.insert(null, 0);
//        assertEquals(new TreeNode(0), result);
        assertAll("null",
                () -> assertEquals(0, result.key),
                () -> assertEquals(null, result.left),
                () -> assertEquals(null, result.right)
        );
    }
}