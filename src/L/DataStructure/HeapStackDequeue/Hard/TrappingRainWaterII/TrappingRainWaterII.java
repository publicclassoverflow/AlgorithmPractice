package L.DataStructure.HeapStackDequeue.Hard.TrappingRainWaterII;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * http://www.lintcode.com/en/problem/trapping-rain-water-ii/
 *
 * Given n x m non-negative integers representing an elevation map 2d where the
 * area of each cell is 1 x 1, compute how much water it is able to trap after raining.
 *
 * Example:
 * Given 5*4 matrix:
 * [12,13,0,12]
 * [13,4,13,12]
 * [13,8,10,12]
 * [12,13,12,12]
 * [13,13,13,13]
 * return 14.
 */

public class TrappingRainWaterII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] heights = new int[][]{
                {12, 13, 0, 12},
                {13, 4, 13, 12},
                {13, 8, 10, 12},
                {12, 13, 12, 12},
                {13, 13, 13, 13}
        };
        System.out.println(solution.trapRainWater(heights));
    }
}

class Cell {
    int x, y, h;
    public Cell() {}
    public Cell(int x, int y, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }
}

class CellComparator implements Comparator<Cell> {
    @Override
    public int compare(Cell a, Cell b) {
//        if (a.h > b.h) {
//            return 1;
//        } else if (a.h < b.h) {
//            return -1;
//        } else {
//            return 0;
//        }
        return a.h - b.h;
    }
}

class Solution {
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    public int trapRainWater(int[][] heights) {
        // write your code here
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return 0;
        }

        PriorityQueue<Cell> queue = new PriorityQueue<>(1, new CellComparator());
        int n = heights.length;
        int m = heights[0].length;
        boolean[][] haveVisited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            queue.offer(new Cell(i, 0, heights[i][0]));
            queue.offer(new Cell(i, m - 1, heights[i][m - 1]));
            haveVisited[i][0] = true;
            haveVisited[i][m - 1] = true;
        }
        for (int j = 0; j < m; j++) {
            queue.offer(new Cell(0, j, heights[0][j]));
            queue.offer(new Cell(n - 1, j, heights[n - 1][j]));
            haveVisited[0][j] = true;
            haveVisited[n - 1][j] = true;
        }

        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int result = 0;

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            for (int i = 0; i < dx.length; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];
                if (!inBound(heights, nextX, nextY)
                        || haveVisited[nextX][nextY]) {
                    continue;
                }
                queue.offer(new Cell(nextX, nextY, Math.max(current.h, heights[nextX][nextY])));
                haveVisited[nextX][nextY] = true;
                result += Math.max(0, current.h - heights[nextX][nextY]);
            }
        }
        return result;
    }

    private boolean inBound(int[][] heights, int x, int y) {
        return x >= 0 && x < heights.length && y >= 0 && y < heights[0].length;
    }
}
