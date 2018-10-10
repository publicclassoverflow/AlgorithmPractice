package D.BreadthFirstSearch.Medium.KthSmallestNumberInSortedMatrix;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KthSmallestNuberInSortedMatrixTest {
    static KthSmallestNuberInSortedMatrix instance;
    int[][] matrix;

    @BeforeAll
    static void setInstance() {
        instance = new KthSmallestNuberInSortedMatrix();
    }

    @BeforeEach
    void setUp() {
        matrix = new int[][] {
                {1, 3, 5, 7},
                {2, 4, 8, 9},
                {3, 5, 11, 15},
                {6, 8, 13, 18},
        };
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNull() {
        int result = instance.kthSmallest(null, 5);
        assertEquals(result, -1);
    }

    @Test
    void testZero() {
        int result = instance.kthSmallest(matrix, 0);
        assertEquals(result, -1);
    }

    @Test
    void testFifth() {
        int result = instance.kthSmallest(matrix, 5);
        assertEquals(result, 4);
    }

    @Test
    void testEighth() {
        int result = instance.kthSmallest(matrix,8);
        assertEquals(result, 6);
    }
}