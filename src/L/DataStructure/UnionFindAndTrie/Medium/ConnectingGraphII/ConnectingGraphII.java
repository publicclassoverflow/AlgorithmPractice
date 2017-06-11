package L.DataStructure.UnionFindAndTrie.Medium.ConnectingGraphII;

/**
 * http://www.lintcode.com/en/problem/connecting-graph-ii/
 * Given n nodes in a graph labeled from 1 to n. There is no edges in the graph
 * at beginning.
 * You need to support the following method:
 * 1. connect(a, b), add an edge to connect node a and node b.
 * 2.query(a, b), returns the number of connected component nodes which include
 * node a.
 *
 * Example
 * 5 // n = 5
 * query(1) return 1
 * connect(1, 2)
 * query(1) return 2
 * connect(2, 4)
 * query(1) return 3
 * connect(1, 4)
 * query(1) return 3
 */

public class ConnectingGraphII {
    public static void main(String[] args) {
        ConnectingGraph2 connection = new ConnectingGraph2(5);
        System.out.println(connection.query(1));
        connection.connect(1, 2);
        System.out.println(connection.query(1));
        connection.connect(2, 4);
        System.out.println(connection.query(1));
        connection.connect(1, 4);
        System.out.println(connection.query(1));
        // Extra test cases
        connection.connect(3, 5);
        System.out.println(connection.query(3));    // Should be 2
        connection.connect(2, 3);
        System.out.println(connection.query(1));    // Should be 5
        connection.connect(3, 10);              // Should do nothing
        System.out.println(connection.query(2));    // Should be 5
    }
}

class ConnectingGraph2 {
    private int[] parent;
    private int[] componentCount;

    public ConnectingGraph2(int n) {
        // initialize your data structure here.
        parent = new int[n + 1];
        componentCount = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
            componentCount[i] = 1;
        }
    }

    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private boolean inBound(int x) {
        return x > 0 && x < parent.length;
    }

    public void connect(int a, int b) {
        // Write your code here
        if (!inBound(a) || !inBound(b)) {
            return;
        }
        int parentA = find(a);
        int parentB = find(b);
        if (parentA != parentB) {
            parent[parentA] = parentB;
            // Update the component counts so far
            componentCount[parentB] += componentCount[parentA];
        }
    }

    public int query(int a) {
        // Write your code here
        if (!inBound(a)) {
            return 0;
        }
        return componentCount[find(a)];
    }
}
