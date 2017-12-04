package H.DataStructure.Medium.KClosestPoints;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * http://www.lintcode.com/en/problem/k-closest-points/
 *
 * Given some points and a point origin in two dimensional space, find k points
 * out of the some points which are nearest to origin.
 * Return these points sorted by distance, if they are same with distance,
 * sorted by x-axis, otherwise sorted by y-axis.
 *
 * Example:
 * Given points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3
 * return [[1,1],[2,5],[4,4]]
 */

public class KClosestPoints {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 1
        Point[] one = new Point[] {
                new Point(4, 6), new Point(4, 7), new Point(4, 4),
                new Point(2, 5), new Point(1, 1)
        };
        Point[] resultOne = solution.kClosest(one, new Point(0, 0), 3);
        printResult(resultOne);
    }

    private static void printResult(Point[] points) {
        if (points == null || points.length == 0) {
            System.out.println("[[]]");
            return;
        }
        int[][] kPoints = new int[points.length][2];
        for (int i = 0; i < points.length; i++) {
            kPoints[i][0] = points[i].x;
            kPoints[i][1] = points[i].y;
        }
        System.out.println(Arrays.deepToString(kPoints));
    }
}

class Point {
    int x, y;
    public Point() {x = 0; y = 0;}
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    /*
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        // write your code here
        if (points == null) {
            return new Point[] {};
        }
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(k, (a, b) -> {
            // a is gonna be the element being added
            // b is the current head
            // So comparing (b - a) will give us a queue sorted in descending
            // order, aka a max heap.
            int diff = dist(b, origin) - dist(a, origin);
            if (diff == 0) {
                // If they are of the same distance, sort them by x-axis
                diff = b.x - a.x;
            }
            if (diff == 0) {
                // Otherwise, sort them by y-axis
                diff = b.y - a.y;
            }
            return diff;
        });
        for (Point point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                // Get rid of the head/largest element to keep the heap size
                maxHeap.poll();
            }
        }
        Point[] result = new Point[k];
        while (!maxHeap.isEmpty()) {
            // maxHeap: 3, 2, 1
            // result: 1, 2, 3
            result[--k] = maxHeap.poll();
        }
        return result;
    }

    private int dist(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
}
