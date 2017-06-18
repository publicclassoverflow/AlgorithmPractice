package L.DataStructure.UnionFindAndTrie.Medium.ConnectedComponentInUndirectedGraph;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/connected-component-in-undirected-graph/
 *
 * Find the number connected component in the undirected graph. Each node in
 * the graph contains a label and a list of its neighbors. (a connected
 * component (or just component) of an undirected graph is a subgraph in which
 * any two vertices are connected to each other by paths, and which is connected
 * to no additional vertices in the supergraph.)
 *
 * Notice:
 * Each connected component should sort by label
 *
 * Clarification:
 * Learn more about representation of graphs
 *
 * Example:
 * Given graph:
 *      A------B  C
 *      \     |  |
 *      \    |  |
 *      \   |  |
 *      \  |  |
 *       D   E
 * Return {A,B,D}, {C,E}. Since there are two connected components which are
 * {A,B,D} and {C,E}.
 */

// I am using BFS instead of Union Find for this one because I am tired of
// writing Union-Find. And for some reason, I feel like BFS is more important

// TODO(MZ): Although... I still need to write a Union-Find solution

public class ConnectedComponentInUndirectedGraph {
    public static void main(String[] args) {
        Solution solution = new Solution();
        UndirectedGraphNode A = new UndirectedGraphNode(1);
        UndirectedGraphNode B = new UndirectedGraphNode(2);
        UndirectedGraphNode C = new UndirectedGraphNode(3);
        UndirectedGraphNode D = new UndirectedGraphNode(4);
        UndirectedGraphNode E = new UndirectedGraphNode(5);
        A.neighbors.addAll(Arrays.asList(B, D));
        B.neighbors.addAll(Arrays.asList(A, D));
        C.neighbors.add(E);
        D.neighbors.addAll(Arrays.asList(A, B));
        E.neighbors.add(C);
        ArrayList<UndirectedGraphNode> nodes =
                new ArrayList<>(Arrays.asList(A, B, C, D, E));
        System.out.println(solution.connectedSet(nodes));
    }
}

class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;
    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
}

class Solution {
    /**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        // Write your code here
        if (nodes == null || nodes.size() == 0) {
            return new ArrayList<>();
        }

        Map<UndirectedGraphNode, Boolean> haveSeen = new HashMap<>();
        for (UndirectedGraphNode node : nodes) {
            haveSeen.put(node, false);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (UndirectedGraphNode node : nodes) {
             if (!haveSeen.get(node)) {
                checkByBFS(node, haveSeen, result);
            }
        }
        return result;
    }

    private void checkByBFS(UndirectedGraphNode root,
                            Map<UndirectedGraphNode, Boolean> map,
                            List<List<Integer>> list) {
        List<Integer> row = new ArrayList<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        map.put(root, true); // This node is seen now
        queue.offer(root);
        while (!queue.isEmpty()) {
            UndirectedGraphNode node = queue.poll();
            row.add(node.label);
            for (UndirectedGraphNode neighbor : node.neighbors) {
                if (map.get(neighbor)) {
                    continue;
                }
                map.put(neighbor, true);
                queue.offer(neighbor);
            }
        }
        Collections.sort(row);
        list.add(row);
    }
}
