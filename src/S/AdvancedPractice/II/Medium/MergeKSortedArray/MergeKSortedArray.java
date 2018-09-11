package S.AdvancedPractice.II.Medium.MergeKSortedArray;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Description
 * Merge K sorted array into one big sorted array in ascending order.
 *
 * Assumptions
 *
 * The input arrayOfArrays is not null, none of the arrays is null either.
 */

public class MergeKSortedArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test
        System.out.println(Arrays.toString(solution.merge(new int[][]{
                {1, 4, 9, 11},
                {3, 5, 8, 12},
                {2, 6, 7, 10}
        })));
    }
}

class Solution {
    public int[] merge(int[][] arrayOfArrays) {
        // Write your solution here
        if (arrayOfArrays == null) {
            return null;
        }
        if (arrayOfArrays.length == 0) {
            return new int[] {};
        }
        // Use a minHeap of size K to get the smallest number from
        // K arrays each time
        int k = arrayOfArrays.length;
        PriorityQueue<Element> minHeap = new PriorityQueue<>(k);
        // The size of arrayOfArrays is not guaranteed to be K x N
        // So we need to figure out how long each array is
        int totalLength = 0;
        for (int i = 0; i < k; i++) {
            // Check the array in each row
            int[] currentArray = arrayOfArrays[i];
            if (currentArray.length == 0) {
                continue;
            }
            // If there are elements in the current array
            // Update the total length and offer the first
            // number in this row to the heap
            totalLength += currentArray.length;
            minHeap.offer(new Element(i, 0, currentArray[0]));
        }
        // We now have a minHeap with <= k elements in it
        // The k elements are the first element in each row
        // Thus, we can start polling the elements
        int[] result = new int[totalLength];
        int index = 0;
        while (!minHeap.isEmpty()) {
            Element element = minHeap.poll();
            result[index++] = element.value;
            // We need a backup from the same array to fill the space
            // The back up should be the element next to the polled element
            if (element.indexInArray < arrayOfArrays[element.indexOfArray].length - 1) {
                int indexOfArray = element.indexOfArray;
                int indexInArray = element.indexInArray + 1;
                int value = arrayOfArrays[indexOfArray][indexInArray];
                minHeap.offer(new Element(indexOfArray, indexInArray, value));
            }
        }
        return result;
    }
}

class Element implements Comparable<Element>{
    int indexOfArray;
    int indexInArray;
    int value;
    public Element(int indexOfArray, int indexInArray, int value) {
        this.indexOfArray = indexOfArray;
        this.indexInArray = indexInArray;
        this.value = value;
    }

    @Override
    public int compareTo(Element other) {
        return Integer.compare(this.value, other.value);
    }
}
