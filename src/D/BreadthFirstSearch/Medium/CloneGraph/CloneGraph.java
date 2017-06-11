package D.BreadthFirstSearch.Medium.CloneGraph;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/clone-graph/
 *
 * Clone an undirected graph. Each node in the graph contains a label and a list
 * of its neighbors. How we serialize an undirected graph:
 * Nodes are labeled uniquely.
 * We use # as a separator for each node, and , as a separator for node label
 * and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * The graph has a total of three nodes, and therefore contains three parts as
 * separated by #.
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming
 * a self-cycle.
 * Visually, the graph looks like the following:
 *      1
 *     / \
 *    /   \
 *   0 --- 2
 *        / \
 *        \_/
 */

public class CloneGraph {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Build a testing graph
        UndirectedGraphNode zero = new UndirectedGraphNode(0);
        UndirectedGraphNode one = new UndirectedGraphNode(1);
        UndirectedGraphNode two = new UndirectedGraphNode(2);
        zero.neighbors.addAll(Arrays.asList(zero, one, two));
        one.neighbors.addAll(Arrays.asList(one, two));
        two.neighbors.addAll(Arrays.asList(two, two));
        // Testing
        UndirectedGraphNode node = solution.cloneGraph(zero);
        printGraph(node);
    }

    private static void printGraph(UndirectedGraphNode node) {
        System.out.format("Node %d:%n", node.label);
        for (UndirectedGraphNode neighbor : node.neighbors) {
//            if (neighbor.label == node.label) {
//                continue;
//            }
            System.out.format("%d%n", neighbor.label);
        }
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
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
        if (node == null) {
            return null;
        }
        ArrayList<UndirectedGraphNode> allNodes = findAllNodes(node);
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = copyNodes(allNodes);
        connectNeighbors(allNodes, map);
        return map.get(node);
    }

    private void connectNeighbors(
            ArrayList<UndirectedGraphNode> nodes,
            HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
        for (UndirectedGraphNode node : nodes) {
            for (UndirectedGraphNode neighbor : node.neighbors) {
                UndirectedGraphNode newNode = map.get(node);
                UndirectedGraphNode newNeighbor = map.get(neighbor);
                newNode.neighbors.add(newNeighbor);
            }
        }
    }

    private HashMap<UndirectedGraphNode,UndirectedGraphNode> copyNodes(ArrayList<UndirectedGraphNode> nodes) {
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        for (UndirectedGraphNode node : nodes) {
            map.put(node, new UndirectedGraphNode(node.label));
        }
        return map;
    }

    private ArrayList<UndirectedGraphNode> findAllNodes(UndirectedGraphNode node) {
        // BFS
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        ArrayList<UndirectedGraphNode> nodes = new ArrayList<>();
        // Unlike a tree, there may be cycles in the graph.
        // So we need a hash table to make sure the nodes we see during the
        // iteration is unique. Otherwise there will be an infinite loop
        HashSet<UndirectedGraphNode> inQueue = new HashSet<>();
        queue.offer(node);
        nodes.add(node);
        inQueue.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode head = queue.poll();
            for (UndirectedGraphNode neighbor : head.neighbors) {
                if (inQueue.contains(neighbor)) {
                    continue;
                }
                queue.offer(neighbor);
                nodes.add(neighbor);
                inQueue.add(neighbor);
            }
        }
        return nodes;
    }
}
