package L.DataStructure.UnionFindAndTrie.Medium.ConnectingGraphIII;

/**
 * http://www.lintcode.com/en/problem/connecting-graph-iii/
 * Given n nodes in a graph labeled from 1 to n. There is no edges in the graph
 * at beginning.
 * You need to support the following method:
 * 1. connect(a, b), add an edge to connect node a and node b.
 * 2.query(a, b), returns the number of connected components in the graph
 */

public class ConnectingGraphIII {
    public static void main(String[] args) {
        ConnectingGraph3 connection = new ConnectingGraph3(5);
        System.out.println(connection.query());
        connection.connect(1, 2);
        System.out.println(connection.query());
        connection.connect(2, 4);
        System.out.println(connection.query());
        connection.connect(1, 4);
        System.out.println(connection.query());
        // Extra test cases
        connection.connect(3, 5);
        System.out.println(connection.query());    // Should be 2
        connection.connect(2, 3);
        System.out.println(connection.query());    // Should be 1
        connection.connect(3, 10);          // Should do nothing
        System.out.println(connection.query());    // Should be 1
    }
}

class ConnectingGraph3 {
    private int[] parent;
    private int componentCount;

    public ConnectingGraph3(int n) {
        // initialize your data structure here.
        parent = new int[n + 1];
        componentCount = n;
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }
    }

    private boolean inBound(int x) {
        return x > 0 && x < parent.length;
    }

    private int find(int x) {
        // inBound will always be called before find
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
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
            componentCount--;
        }
    }

    public int query() {
        // Write your code here
        return componentCount;
    }
}
