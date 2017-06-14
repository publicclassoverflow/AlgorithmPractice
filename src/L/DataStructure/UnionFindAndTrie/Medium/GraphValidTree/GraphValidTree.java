package L.DataStructure.UnionFindAndTrie.Medium.GraphValidTree;

import java.util.HashMap;

/**
 * http://www.lintcode.com/en/problem/graph-valid-tree/
 *
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes). Write a function to check whether these edges make
 * up a valid tree.
 *
 * Example
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 */

/* This problem should also be solved using BFS */

public class GraphValidTree {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] edgesOne = new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        int[][] edgesTwo = new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}};
        int n = 5;
        System.out.println(solution.validTree(n, edgesOne));
        System.out.println(solution.validTree(n, edgesTwo));
    }
}

class UnionFind {
    private HashMap<Integer, Integer> parent = new HashMap<>();
    public UnionFind(int size) {
        for (int i = 0; i < size; i++) {
            parent.put(i, i);
        }
    }

    public int findParent(int x) {
        int parentX = parent.get(x);
        while (parentX != parent.get(parentX)) {
            parentX = parent.get(parentX);
        }
        // Update the parent of X
        int update = 0;
        int fatherX = parent.get(x);
        while (fatherX != parent.get(fatherX)) {
            update = parent.get(fatherX);
            parent.put(fatherX, parentX);
            fatherX = update;
        }
        return parentX;
    }

    public void union(int a, int b) {
        int parentA = findParent(a);
        int parentB = findParent(b);
        if (parentA != parentB) {
            parent.put(parentA, parentB);
        }
    }
}

class Solution {
    /**
     * @param n     an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        // Write your code here
        if (edges == null || edges.length != n - 1) {
            return false;
        }

        UnionFind unionFind = new UnionFind(n);
        // Looking for cycles in the graph
        // Two nodes sharing the same parent == cycle
        for (int i = 0; i < edges.length; i++) {
            if (unionFind.findParent(edges[i][0]) ==
                    unionFind.findParent(edges[i][1])) {
                return false;
            }
            unionFind.union(edges[i][0], edges[i][1]);
        }
        return true;
    }
}
