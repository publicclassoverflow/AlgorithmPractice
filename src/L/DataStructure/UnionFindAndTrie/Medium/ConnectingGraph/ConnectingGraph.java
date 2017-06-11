package L.DataStructure.UnionFindAndTrie.Medium.ConnectingGraph;

/**
 * http://www.lintcode.com/en/problem/connecting-graph/
 * Given n nodes in a graph labeled from 1 to n. There is no edges in the graph
 * at beginning.
 * You need to support the following method:
 * 1. connect(a, b), add an edge to connect node a and node b.
 * 2.query(a, b), check if two nodes are connected
 *
 * Example
 * 5 // n = 5
 * query(1, 2) return false
 * connect(1, 2)
 * query(1, 3) return false
 * connect(2, 4)
 * query(1, 4) return true
 */

/**
 * The name of the class on lintcode is ConnectingGraph
 * which conflicts with my naming. So I renamed it to Connection
 */

public class ConnectingGraph {
    public static void main(String[] args) {
        Connection connection = new Connection(5);
        System.out.println(connection.query(1, 2));
        connection.connect(1, 2);
        System.out.println(connection.query(1, 3));
        connection.connect(2,4);
        System.out.println(connection.query(1, 4));
        // Extra test cases;
        connection.connect(1, 5);
        System.out.println(connection.query(4, 5));  // Should be true
        connection.connect(3, 10);                   // Should do nothing
        System.out.println(connection.query(3, 10)); // Should be false
        connection.connect(0, 4);                    // Should do nothing
        System.out.println(connection.query(0, 4));  // Should be false
    }
}

class Connection {
    private int[] parent = null;

    public Connection(int n) {
        // initialize your data structure here.
        // Since the parent array ranges from 1 to n
        parent = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
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
        // This is the "union" function in a typical union-find structure
        if (!inBound(a) || !inBound(b)) {
            return;
        }
        int parentA = find(a);
        int parentB = find(b);
        if (parentA != parentB) {
            parent[parentA] = parentB;
        }
    }

    public boolean query(int a, int b) {
        // Write your code here
        if (!inBound(a) || !inBound(b)) {
            return false;
        }
        return find(a) == find(b);
    }
}
