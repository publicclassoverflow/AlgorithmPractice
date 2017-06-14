package L.DataStructure.UnionFindAndTrie.Easy.NumberOfIslands;

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

class UnionFind {
    private int[] parent;
    private int count;
    public UnionFind(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
        count = 0;
    }

    private int findParent(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }

    private boolean inBound(int x) {
        return x >= 0 && x < parent.length;
    }

    public void connect(int a, int b) {
        if (!inBound(a) || !inBound(b)) {
            return;
        }
        int parentA = findParent(a);
        int parentB = findParent(b);
        if (parentA != parentB) {
            parent[parentA] = parentB;
            count--;
        }
    }

    public void setCount(int num) {
        count = num;
    }

    public int getCount() {
        return count;
    }
}

class Solution {
    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public int numIslands(boolean[][] grid) {
        // Write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;
        UnionFind unionFind = new UnionFind(n * m);
        // Calculate the total number of (1) islands in the grid
        int numIsland = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]) {
                    numIsland++;
                }
            }
        }
        unionFind.setCount(numIsland);

        // Go right/down/left/up
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};

       for (int i = 0; i < n; i++) {
           for (int j = 0; j < m; j++) {
               if (grid[i][j]) {
                   for (int k = 0; k < dx.length; k++) {
                       int nextX = i + dx[k];
                       int nextY = j + dy[k];
                       if (!inBound(grid, nextX, nextY)) {
                           continue;
                       }
                       if (grid[nextX][nextY]) {
                           // Conversion from 2D to 1D index:
                           // 2D: (i, j) -> 1D: m * i + j
                           unionFind.connect(m * i + j, m * nextX + nextY);
                       }
                   }
               }
           }
       }
       return unionFind.getCount();
    }

    private boolean inBound(boolean[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}
