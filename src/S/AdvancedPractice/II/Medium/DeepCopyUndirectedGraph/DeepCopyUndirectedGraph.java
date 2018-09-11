package S.AdvancedPractice.II.Medium.DeepCopyUndirectedGraph;

import java.util.*;

/**
 * Description
 * Make a deep copy of an undirected graph, there could be cycles in the original graph.
 *
 * Assumptions
 *
 * The given graph is not null
 */

public class DeepCopyUndirectedGraph {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test
        GraphNode one = new GraphNode(1);
        GraphNode two = new GraphNode(2);
        GraphNode three = new GraphNode(3);
        one.neighbors.addAll(Arrays.asList(two, three));
        two.neighbors.addAll(Arrays.asList(one, three));
        three.neighbors.addAll(Arrays.asList(one, two));
        List<GraphNode> newGraph = solution.copy(Arrays.asList(one, two, three));
        printGraph(newGraph);
    }

    private static void printGraph(List<GraphNode> graph) {
        for (GraphNode node : graph) {
            System.out.print("Node " + node.key + " connects to nodes");
            for (GraphNode neighbor : node.neighbors) {
                System.out.print(" " + neighbor.key);
            }
            System.out.println();
        }
    }
}

class GraphNode {
    public int key;
    public List<GraphNode> neighbors;
    public GraphNode(int key) {
        this.key = key;
        this.neighbors = new ArrayList<>();
    }
}

class Solution {
    public List<GraphNode> copy(List<GraphNode> graph) {
        // Write your solution here
        if (graph == null) {
            return null;
        }
        // Use a HashMap to keep track of all the new nodes
        // that get generated to avoid duplications
        Map<GraphNode, GraphNode> generated = new HashMap<>();
        List<GraphNode> newGraph = new ArrayList<>();
        for (GraphNode node : graph) {
            if (!generated.containsKey(node)) {
                // Build the 1-to-1 relationship between the
                // original node to the copied new node
                generated.put(node, new GraphNode(node.key));
            }
            GraphNode newNode = generated.get(node);
            // Copy the neighbors
            for (GraphNode neighbor : node.neighbors) {
                // Build the 1-to-1 relationship between
                // the original neighbor and new neighbor
                if (!generated.containsKey(neighbor)) {
                    generated.put(neighbor, new GraphNode(neighbor.key));
                }
                // Copy the new neighbor to the new node's neighbor list
                newNode.neighbors.add(generated.get(neighbor));
            }
            // We have finished copied everything for the new node
            // Add it to the new graph
            newGraph.add(newNode);
        }
        return newGraph;
    }
}
