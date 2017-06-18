package L.DataStructure.UnionFindAndTrie.Medium.FindTheWeakConnectedComponentInTheDirectedGraph;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/
 *
 * Find the number Weak Connected Component in the directed graph. Each node
 * in the graph contains a label and a list of its neighbors. (a connected set
 * of a directed graph is a subgraph in which any two vertices are connected
 * by direct edge path.)
 *
 * Notice:
 * Sort the element in the set in increasing order
 *
 * Exmaple
 * Given graph:
 *      A  ----->  B  C
 *      \         |  |
 *      \        |  |
 *      \       |  |
 *      \      v  v
 *      ->D  E <- F
 * Return {A, B, D}, {C, E, F}. Since there are two connected components
 * which are {A,B,D} and {C,E,F}
 */

public class WeakConnectedComponentInDirectedGraph {
    public static void main(String[] args) {
        Solution solution = new Solution();
        DirectedGraphNode A = new DirectedGraphNode(1);
        DirectedGraphNode B = new DirectedGraphNode(2);
        DirectedGraphNode C = new DirectedGraphNode(3);
        DirectedGraphNode D = new DirectedGraphNode(4);
        DirectedGraphNode E = new DirectedGraphNode(5);
        DirectedGraphNode F = new DirectedGraphNode(6);
        A.neighbors.addAll(Arrays.asList(B, D));
        B.neighbors.add(D);
        C.neighbors.add(E);
        F.neighbors.add(E);
        ArrayList<DirectedGraphNode> nodes = new ArrayList<>(Arrays.asList(A, B, C, D, E, F));
        System.out.println(solution.connectedSet2(nodes));
    }
}

class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
}

class UnionFind {
    HashMap<Integer, Integer> parent = new HashMap<>();
    public UnionFind(HashSet<Integer> set) {
        for (Integer num : set) {
            parent.put(num, num);
        }
    }

    protected int find(int x) {
        int parentX = parent.get(x);
        while (parentX != parent.get(parentX)) {
            parentX = parent.get(parentX);
        }
        return parentX;
    }

    protected int compressedFind(int x) {
        int parentX = parent.get(x);
        while (parentX != parent.get(parentX)) {
            parentX = parent.get(parentX);
        }
        int update;
        int father = parent.get(x);
        while (father != parent.get(father)) {
            update = parent.get(father);
            parent.put(father, parentX);
            father = update;
        }
        return parentX;
    }

    protected void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA != parentB) {
            parent.put(parentA, parentB);
        }
    }
}

class Solution {
    /**
     * @param nodes a array of Directed graph node
     * @return a connected set of a directed graph
     */
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        // Write your code here
        if (nodes == null || nodes.size() == 0) {
            return new ArrayList<>();
        }
        // Add all the nodes to the set
        HashSet<Integer> set = new HashSet<>();
        for (DirectedGraphNode node : nodes) {
            set.add(node.label);
            for (DirectedGraphNode neighbor : node.neighbors) {
                set.add(neighbor.label);
            }
        }
        // Union-Find
        UnionFind uf = new UnionFind(set);
        for (DirectedGraphNode node : nodes) {
            int nodeParent = uf.find(node.label);
            for (DirectedGraphNode neighbor : node.neighbors) {
                int neighorParent = uf.find(neighbor.label);
                if (nodeParent != neighorParent) {
                    uf.union(node.label, neighbor.label);
                }
            }
        }
        return sortResult(set, uf);
    }

    private List<List<Integer>> sortResult(HashSet<Integer> set, UnionFind uf) {
        List<List<Integer>> result = new ArrayList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i : set) {
            int parent = uf.find(i);
            if (!map.containsKey(parent)) {
                map.put(parent, new ArrayList<>());
            }
            List<Integer> nodes = map.get(parent);
            nodes.add(i);
            map.put(parent, nodes);
        }
        for (List<Integer> node : map.values()) {
            Collections.sort(node);
            result.add(node);
        }
        return result;
    }
}
