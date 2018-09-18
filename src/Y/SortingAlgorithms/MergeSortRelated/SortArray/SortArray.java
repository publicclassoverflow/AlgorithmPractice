package Y.SortingAlgorithms.MergeSortRelated.SortArray;

import java.util.Arrays;

/**
 * 1. Selection Sort
 * https://app.laicode.io/app/problem/4
 * Description
 * Given an array of integers, sort the elements in the array in ascending order. The selection sort algorithm should be used to solve this problem.
 *
 * Examples
 *
 * {1} is sorted to {1}
 * {1, 2, 3} is sorted to {1, 2, 3}
 * {3, 2, 1} is sorted to {1, 2, 3}
 * {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
 * Corner Cases
 *
 * What if the given array is null? In this case, we do not need to do anything.
 * What if the given array is of length zero? In this case, we do not need to do anything.
 *
 * 2. Merge Sort
 * https://app.laicode.io/app/problem/9
 * Description
 * Given an array of integers, sort the elements in the array in ascending order. The merge sort algorithm should be used to solve this problem.
 *
 * Examples
 *
 * {1} is sorted to {1}
 * {1, 2, 3} is sorted to {1, 2, 3}
 * {3, 2, 1} is sorted to {1, 2, 3}
 * {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
 * Corner Cases
 *
 * What if the given array is null? In this case, we do not need to do anything.
 * What if the given array is of length zero? In this case, we do not need to do anything.
 *
 * 3. Quick Sort
 * https://app.laicode.io/app/problem/10
 * Description
 * Given an array of integers, sort the elements in the array in ascending order. The quick sort algorithm should be used to solve this problem.
 *
 * Examples
 *
 * {1} is sorted to {1}
 * {1, 2, 3} is sorted to {1, 2, 3}
 * {3, 2, 1} is sorted to {1, 2, 3}
 * {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
 * Corner Cases
 *
 * What if the given array is null? In this case, we do not need to do anything.
 * What if the given array is of length zero? In this case, we do not need to do anything.
 */


public class SortArray {
    static final int SELECTION_SORT = 0;
    static final int MERGE_SORT = 1;
    static final int QUICK_SORT = 2;

    public int[] sort(int[] array, int algorithm) {
        switch (algorithm) {
            case SELECTION_SORT:
                return selectionSort(array);
            case MERGE_SORT:
                return mergeSort(array);
            case QUICK_SORT:
                return quickSort(array);
            default:
                System.out.println("Not a valid sorting algorithm");
                break;
        }
        return null;
    }

    // Selection Sort
    private int[] selectionSort(int[] array) {
        // Write your solution here
        if (array == null || array.length < 2) {
            return array;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        return array;
    }

    // Merge Sort
    private int[] mergeSort(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }
        mergeSortArray(array, new int[array.length], 0, array.length - 1);
        return array;
    }

    private void mergeSortArray(int[] array, int[] temp, int start, int end) {
        // Base case: when start and end meet
        if (start >= end) {
            return;
        }
        // Recursive rule:
        // Divide the array into two halves each time, sort/merge them accordingly
        int mid = start + (end - start) / 2;
        mergeSortArray(array, temp, start, mid);
        mergeSortArray(array, temp, mid + 1, end);
        mergeArray(array, temp, start, mid, end);
    }

    private void mergeArray(int[] array, int[] temp, int start, int mid, int end) {
        // Copy the elements in array to temp such that any comparisons between the elements
        // can be made in temp.
        // Put elements to their designated right place in array
        // After one side of the array has been merged, only the left-over elements in the left
        // half/side of the array should require transfer to the output array.
        // Any left-over elements in the right half/side is already sorted in ascending order
        // and they are already in their right places in the array
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        int left = start;
        int right = mid + 1;
        int index = left;
        while (left <= mid && right <= end) {
            // Move the smaller one
            if (temp[left] < temp[right]) {
                array[index++] = temp[left++];
            } else {
                array[index++] = temp[right++];
            }
        }
        // Transfer the left-over elements in the left hand side/half
        while (left <= mid) {
            array[index++] = temp[left++];
        }
    }

    // Quick Sort
    private int[] quickSort(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) { // or array.length < 2 should also work but it looks ugly
            return array;
        }
        quickSortArray(array, 0, array.length - 1);
        return array;
    }

    private void quickSortArray(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivotIndex = partitionArray(array, start, end);
        // pivot will is in its correct position already
        quickSortArray(array, start, pivotIndex - 1);
        quickSortArray(array, pivotIndex + 1, end);
    }

    private int partitionArray(int[] array, int start, int end) {
        int pivotIndex = (int) Math.random() * (end - start + 1) + start;
        int pivot = array[pivotIndex];
        // Swap the pivot to the end such that we just check array[0, end - 1]
        swap(array, pivotIndex, end);
        // Two pointers: left & right
        // 1. Everything to the left of the left pointer < pivot
        // 2. Everything to the right of the right pointer > pivot
        // 3. Everything in the middle are to be checked
        int left = start;
        int right = end - 1;    // Save the spot for end where the pivot temporarily sits
        // We must guarantee that left and right will meet each other. And after that iteration, left == right + 1
        // Therefore, array[left] is the first element that is greater than the pivot
        // So, we can safely swap left and pivot to comply with the properties
        while (left <= right) {
            if (array[left] < pivot) {
                left++;
            } else if (array[right] > pivot) {
                right--;
            } else {
                swap(array, left++, right--);
            }
        }
        swap(array, left, end);
        return left;    // pivot sits at index left eventually
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
