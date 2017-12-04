package H.DataStructure.Medium.KClosestPoints;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * A method that doesn't use lambda but a comparator class
 *
 * The idea is the same:
 * Offer each point to a priority queue which gets sorted in descending order
 * and limit the heap size to k.
 */

public class KClosestPointsAlt {
    public static void main(String[] args) {
        AltSolution solution = new AltSolution();
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

class PointComparator implements Comparator<Point> {
    private Point origin;
    PointComparator(Point origin) {
        this.origin = origin;
    }

    @Override
    public int compare(Point a, Point b) {
        // By comparing the distances between b to the origin and a to the
        // origin (b - a), the points will be sorted in descending order (a > b)
        // which gives us a max heap.
        int diff = dist(b, origin) - dist(a, origin);
        if (diff == 0) {
            diff = b.x - a.x;
        }
        if (diff == 0) {
            diff = b.y - a.y;
        }
        return diff;
    }

    private int dist(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
}

class AltSolution {
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
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(
                k, new PointComparator(origin)
        );
        // Fill up the pq
        for (Point point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        Point[] result = new Point[k];
        while (!maxHeap.isEmpty()) {
            result[--k] = maxHeap.poll();
        }
        return result;
    }
}
