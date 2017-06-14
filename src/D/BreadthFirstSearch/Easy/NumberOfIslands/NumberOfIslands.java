package D.BreadthFirstSearch.Easy.NumberOfIslands;

import java.util.LinkedList;
import java.util.Queue;

/**
 * http://www.lintcode.com/en/problem/number-of-islands/
 *
 * Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as
 * the island. If two 1's are adjacent, we consider them in the same island.
 * We only consider up/down/left/right adjacency.
 * Find the number of islands.
 *
 * Given graph:
 * [
 *  [1, 1, 0, 0, 0],
 *  [0, 1, 0, 0, 1],
 *  [0, 0, 0, 1, 1],
 *  [0, 0, 0, 0, 0],
 *  [0, 0, 0, 0, 1]
 * ]
 * return 3
 */

public class NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean[][] grid = new boolean[][]{{true, true, false, false, false},
                                           {false, true, false, false, true},
                                           {false, false, false, true, true},
                                           {false, false, false, false, false},
                                           {false, false, false, false, true}};
        System.out.println(solution.numIslands(grid));
    }
}

class Coordinate {
    int x;
    int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public final static int NUMDIR = 4; // Number of directions: u/d/l/r

    public int numIslands(boolean[][] grid) {
        // Write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int islandCount = 0;
        // Search for every coordinate in the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]) {
                    // Do BFS
                    markIsland(grid, i, j);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    private void markIsland(boolean[][] isIsland, int x, int y) {
        // Traversal/Searching direction matrix
        // This is going right/down/up/left respectively
        int[] dx = new int[]{0, 1, -1, 0};
        int[] dy = new int[]{1, 0, 0, -1};
        // BFS template
        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate location = new Coordinate(x, y);
        queue.offer(location);
        // Set the current location to false to simplify the searching job for
        // its neighbors
        isIsland[x][y] = false;
        // BFS
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            for (int k = 0; k < NUMDIR; k++) {
                Coordinate next =
                        new Coordinate(current.x + dx[k], current.y + dy[k]);
                if (!inBound(isIsland, next)) {
                    continue;
                }
                if (isIsland[next.x][next.y]) {
                    // They are considered to be 1 big island
                    isIsland[next.x][next.y] = false;
                    queue.offer(next);
                }
            }
        }
    }

    private boolean inBound(boolean[][] isIsland, Coordinate location) {
        return location.x >= 0 && location.x < isIsland.length
               && location.y >= 0 && location.y < isIsland[0].length;
    }
}
