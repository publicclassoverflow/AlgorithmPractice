package D.BreadthFirstSearch.Medium.GraphValidTree;

/**
 * http://www.lintcode.com/en/problem/graph-valid-tree/
 *
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to check whether these edges make
 * up a valid tree.
 *
 * Notice:
 * You can assume that no duplicate edges will appear in edges. Since all edges
 * are undirected, [0, 1] is the same as [1, 0] and thus will not appear
 * together in edges.
 *
 * Example:
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 */

import java.util.*;

/**
 * Note:
 * The point of this problem is to see if the number of edges is valid (n - 1)
 * based on graph theory
 */

public class GraphValidTree {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test: true
        System.out.println(solution.validTree(
                5,
                new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}}
        ));
        // Test: more than n - 1 edges
        System.out.println(solution.validTree(
                5,
                new int[][] {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}
        ));
        // Test: less than n nodes in the graph
        System.out.println(solution.validTree(
                5,
                new int[][] {{0, 1}, {1, 2}, {2, 3}, {1, 3}}
        ));
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
        // Build up a graph using the edges array
        Map<Integer, Set<Integer>> graph = buildGraph(n, edges);
        // BFS
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> haveSeen = new HashSet<>();
        queue.offer(0);
        haveSeen.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer neighbor : graph.get(node)) {
                if (haveSeen.contains(neighbor)) {
                    continue;
                }
                haveSeen.add(neighbor);
                queue.offer(neighbor);
            }
        }
        // If the graph has exactly n nodes, it is valid
        return haveSeen.size() == n;
    }

    private Map<Integer,Set<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
}
