package D.BreadthFirstSearch.Medium.SearchGraphNodes;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/search-graph-nodes/
 * Given a undirected graph, a node and a target, return the nearest node to
 * given node which value of it is target, return NULL if you can't find.
 * here is a mapping store the nodes' values in the given parameters.
 *
 * Notice:
 * It's guaranteed there is only one available solution
 *
 * Example:
 * 2------3  5
 * \     |  |
 * \    |  |
 * \   |  |
 * \  |  |
 *  1 --4
 *  Give a node 1, target is 50
 *  there a hash named values which is [3,4,10,50,50], represent:
 *  Value of node 1 is 3
 *  Value of node 2 is 4
 *  Value of node 3 is 10
 *  Value of node 4 is 50
 *  Value of node 5 is 50
 *  Return node 4
 * */

public class SearchGraphNodes {
    public static void main(String[] args) {
        Solution solution = new Solution();
        UndirectedGraphNode one = new UndirectedGraphNode(1);
        UndirectedGraphNode two = new UndirectedGraphNode(2);
        UndirectedGraphNode three = new UndirectedGraphNode(3);
        UndirectedGraphNode four = new UndirectedGraphNode(4);
        UndirectedGraphNode five = new UndirectedGraphNode(5);
        one.neighbors.add(two);
        one.neighbors.add(three);
        one.neighbors.add(four);
        two.neighbors.add(one);
        two.neighbors.add(three);
        three.neighbors.add(one);
        three.neighbors.add(two);
        four.neighbors.add(one);
        four.neighbors.add(three);
        four.neighbors.add(five);
        five.neighbors.add(four);
        ArrayList<UndirectedGraphNode> graph =
                new ArrayList<>(Arrays.asList(one, two, three, four, five));
        Map<UndirectedGraphNode, Integer> values = new HashMap<>();
        values.put(one, 3);
        values.put(two, 4);
        values.put(three, 10);
        values.put(four, 50);
        values.put(five, 50);
        int target = 50;
        System.out.println(solution.searchNode(graph, values, one, target).label);
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
     * @param graph a list of Undirected graph node
     * @param values a hash mapping, <UndirectedGraphNode, (int)value>
     * @param node an Undirected graph node
     * @param target an integer
     * @return the a node
     */
    public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph,
                                          Map<UndirectedGraphNode, Integer> values,
                                          UndirectedGraphNode node,
                                          int target) {
        // Write your code here
        if (graph == null || graph.size() == 0) {
            return null;
        }
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        // BFS
        while (!queue.isEmpty()) {
            UndirectedGraphNode head = queue.poll();
            if (values.get(head) == target) {
                return head;
            }
            for (UndirectedGraphNode neighbor : head.neighbors) {
                if (!set.contains(neighbor)) {
                    queue.offer(neighbor);
                    set.add(neighbor);
                }
            }
        }
        return null;
    }
}
