package D.BreadthFirstSearch.Medium.KnightShortestPath;

import java.util.LinkedList;
import java.util.Queue;

public class KnightShortestPath {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 1: 2
        boolean[][] one = new boolean[][] {
                {false, false, false},
                {false, false, false},
                {false, false, false}
        };
        System.out.println(solution.shortestPath(
                one,
                new Point(2, 0),
                new Point(2, 2)
        ));
        // Test 2: 6
        boolean[][] two = new boolean[][] {
                {false, true, false},
                {false, false, false},
                {false, false, false}
        };
        System.out.println(solution.shortestPath(
                two,
                new Point(2, 0),
                new Point(2, 2)
        ));
        // Test 3: -1
        boolean[][] three = new boolean[][] {
                {false, true, false},
                {false, false, true},
                {false, false, false}
        };
        System.out.println(solution.shortestPath(
                three,
                new Point(2, 0),
                new Point(2, 2)
        ));
    }
}

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    /*
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path
     */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        Queue<Point> queue = new LinkedList<>();
        queue.offer(source);
        int[] dx = new int[] {1, 1, 2, 2, -1, -1, -2, -2};
        int[] dy = new int[] {2, -2, 1, -1, 2, -2, 1, -1};
        int step = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Point point = queue.poll();
                /*
                if (point.x == destination.x && point.y == destination.y) {
                    return step;
                }
                */
                for (int dir = 0; dir < dx.length; dir++) {
                    int nextX = point.x + dx[dir];
                    int nextY = point.y + dy[dir];
                    if (!inBound(grid, nextX, nextY)) {
                        continue;
                    }
                    // If the next point is a barrier
                    if (grid[nextX][nextY]) {
                        continue;
                    }
                    // If the next point is the destination
                    if (nextX == destination.x && nextY == destination.y) {
                        return step + 1;
                    }
                    // Offer it to the queue and mark it as a barrier to prevent
                    // any further duplicate steps
                    queue.offer(new Point(nextX, nextY));
                    grid[nextX][nextY] = true;
                }
            }
            // After checking all points for every level
            // one step has been made
            step++;
        }
        return -1;
    }

    private boolean inBound(boolean[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}
