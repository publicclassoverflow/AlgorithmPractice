package D.BreadthFirstSearch.Medium.ZombieInMatrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * http://www.lintcode.com/en/problem/zombie-in-matrix/
 *
 * Given a 2D grid, each cell is either a wall 2, a zombie 1 or people 0 (the
 * number zero, one, two). Zombies can turn the nearest people (up/down/left/right)
 * into zombies every day, but can not through wall. How long will it take to
 * turn all people into zombies? Return -1 if can not turn all people into zombies.
 *
 * Example:
 * Given a matrix:
 * 0 1 2 0 0
 * 1 0 0 2 1
 * 0 1 0 0 0
 * return 2
 */

public class ZombieInMatrix {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test
        int[][] grid = new int[][] {
                {0, 1, 2, 0, 0},
                {1, 0, 0, 2, 1},
                {0, 1, 0, 0, 0}
        };
        System.out.println(solution.zombie(grid));
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
     * @param grid  a 2D integer grid
     * @return an integer
     */

    private static final int PEOPLE = 0;
    private static final int ZOMBIE = 1;
    // public static final int WALL = 2;

    public int zombie(int[][] grid) {
        // Write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int n = grid.length;
        int m = grid[0].length;
        // Count the number of people and initialize the zombie queue
        int numPeople = 0;
        Queue<Coordinate> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == PEOPLE) {
                    numPeople++;
                } else if (grid[i][j] == ZOMBIE) {
                    queue.offer(new Coordinate(i, j));
                }
            }
        }
        // if (numPeople == 0) {
        // return 0;
        // }
        // BFS looking for zombie infections
        int days = 0;
        int[] dx = new int[] {-1, 0, 0, 1};
        int[] dy = new int[] {0, -1, 1, 0};
        while (!queue.isEmpty()) {
            days++;
            int queueSize = queue.size();
            for (int k = 0; k < queueSize; k++) {
                Coordinate zombie = queue.poll();
                for (int l = 0; l < dx.length; l++) {
                    int nextX = zombie.x + dx[l];
                    int nextY = zombie.y + dy[l];
                    if (!inBound(grid, nextX, nextY)
                            || grid[nextX][nextY] != PEOPLE) {
                        continue;
                    }
                    // Turn the next human into zombie
                    grid[nextX][nextY] = ZOMBIE;
                    if (--numPeople == 0) {
                        return days;
                    }
                    queue.offer(new Coordinate(nextX, nextY));
                }
            }
        }
        return -1;
    }
    private boolean inBound(int[][] grid, int x, int y) {
        int n = grid.length - 1;
        int m = grid[0].length - 1;
        return x >= 0 && x <= n && y >= 0 && y <= m;
    }
}
