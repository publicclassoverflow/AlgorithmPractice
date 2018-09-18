package Y.SortingAlgorithms.MergeSortRelated.SortArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortArrayTest {
    static SortArray instance;
    int[] one;
    int[] two;
    int[] three;
    int[] four;
    int[] five;

    @BeforeAll
    static void setInstance() {
        instance = new SortArray();
    }

    @BeforeEach
    void setUp() {
        one = new int[] {1};
        two = new int[] {1, 2, 3};
        three = new int[] {3, 2, 1};
        four = new int[] {1, 1, 1};
        five = new int[] {4, 2, -3, 6, 1};
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNull() {
        int[] selectionSortResult = instance.sort(null, SortArray.SELECTION_SORT);
        assertArrayEquals(null, selectionSortResult);
        
        int[] mergeSortResult = instance.sort(null, SortArray.MERGE_SORT);
        assertArrayEquals(null, mergeSortResult);

        int[] quickSortResult = instance.sort(null, SortArray.QUICK_SORT);
        assertArrayEquals(null, quickSortResult);
    }

    @Test
    void testEmpty() {
        int[] selectionSortResult = instance.sort(new int[] {}, SortArray.SELECTION_SORT);
        assertArrayEquals(new int[] {}, selectionSortResult);

        int[] mergeSortResult = instance.sort(new int[] {}, SortArray.MERGE_SORT);
        assertArrayEquals(new int[] {}, mergeSortResult);

        int[] quickSortResult = instance.sort(new int[] {}, SortArray.QUICK_SORT);
        assertArrayEquals(new int[] {}, quickSortResult);
    }

    @Test
    void testOne() {
        int[] selectionSortResult = instance.sort(one, SortArray.SELECTION_SORT);
        assertArrayEquals(new int[] {1}, selectionSortResult);
        
        int[] mergeSortResult = instance.sort(one, SortArray.MERGE_SORT);
        assertArrayEquals(new int[] {1}, mergeSortResult);

        int[] quickSortResult = instance.sort(one, SortArray.QUICK_SORT);
        assertArrayEquals(new int[] {1}, quickSortResult);
    }

    @Test
    void testTwo() {
        int[] selectionSortResult = instance.sort(two, SortArray.SELECTION_SORT);
        assertArrayEquals(new int[] {1, 2, 3}, selectionSortResult);
        
        int[] mergeSortResult = instance.sort(two, SortArray.MERGE_SORT);
        assertArrayEquals(new int[] {1, 2, 3}, mergeSortResult);

        int[] quickSortResult = instance.sort(two, SortArray.QUICK_SORT);
        assertArrayEquals(new int[] {1, 2, 3}, quickSortResult);
    }

    @Test
    void testThree() {
        int[] selectionSortResult = instance.sort(three, SortArray.SELECTION_SORT);
        assertArrayEquals(new int[] {1, 2, 3}, selectionSortResult);
        
        int[] mergeSortResult = instance.sort(three, SortArray.MERGE_SORT);
        assertArrayEquals(new int[] {1, 2, 3}, mergeSortResult);

        int[] quickSortResult = instance.sort(three, SortArray.QUICK_SORT);
        assertArrayEquals(new int[] {1, 2, 3}, quickSortResult);
    }

    @Test
    void testFour() {
        int[] selectionSortResult = instance.sort(four, SortArray.SELECTION_SORT);
        assertArrayEquals(new int[] {1, 1, 1}, selectionSortResult);
        
        int[] mergeSortResult = instance.sort(four, SortArray.MERGE_SORT);
        assertArrayEquals(new int[] {1, 1, 1}, mergeSortResult);

        int[] quickSortResult = instance.sort(four, SortArray.QUICK_SORT);
        assertArrayEquals(new int[] {1, 1, 1}, quickSortResult);
    }

    @Test
    void testFive() {
        int[] selectionSortResult = instance.sort(five, SortArray.SELECTION_SORT);
        assertArrayEquals(new int[] {-3, 1, 2, 4, 6}, selectionSortResult);
        
        int[] mergeSortResult = instance.sort(five, SortArray.MERGE_SORT);
        assertArrayEquals(new int[] {-3, 1, 2, 4, 6}, selectionSortResult);

        int[] quickSortResult = instance.sort(five, SortArray.QUICK_SORT);
        assertArrayEquals(new int[] {-3, 1, 2, 4, 6}, quickSortResult);
    }
}