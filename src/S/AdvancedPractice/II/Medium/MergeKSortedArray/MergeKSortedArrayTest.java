package S.AdvancedPractice.II.Medium.MergeKSortedArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test")
class MergeKSortedArrayTest {
    Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNull() {
        int[] result = solution.merge(null);
        assertNull(result);
    }

    @Test
    void testEmpty() {
        int[] result = solution.merge(new int[][] {});
        assertArrayEquals(new int[] {}, result);
    }

    @Test
    void testArray() {
        int[][] arrayOfArrays = new int[][] {
                {1, 4, 9, 11},
                {3, 5, 8, 12},
                {2, 6, 7, 10}
        };
        int[] result = solution.merge(arrayOfArrays);
        assertArrayEquals(
                new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12},
                result
        );
    }
}