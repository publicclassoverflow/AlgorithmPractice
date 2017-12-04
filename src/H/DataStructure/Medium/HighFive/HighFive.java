package H.DataStructure.Medium.HighFive;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * http://www.lintcode.com/en/problem/high-five/
 *
 * There are two properties in the node student id and scores, to ensure that
 * each student will have at least 5 points, find the average of 5 highest
 * scores for each person.
 *
 * Example:
 * Given results = [[1,91],[1,92],[2,93],[2,99],[2,98],[2,97],[1,60],[1,58],[2,100],[1,61]]
 * Return
 * 1: 72.40
 * 2: 97.40
 */

public class HighFive {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test 1
        Record[] one = new Record[] {
                new Record(1, 91), new Record(1, 92), new Record(2, 93),
                new Record(2, 99), new Record(2, 98), new Record(2, 97),
                new Record(1, 60), new Record(1, 58), new Record(2, 100),
                new Record(1, 61)
        };
        Map<Integer, Double> resultOne = solution.highFive(one);
        // 1: 72.4
        // 2: 97.4
        printResult(resultOne);
        // Test 2
        Record[] two = new Record[] {
                new Record(1, 91), new Record(1, 92), new Record(2, 93),
                new Record(2, 99), new Record(2, 98), new Record(2, 97),
                new Record(1, 60), new Record(1, 58), new Record(2, 100),
                new Record(1, 61), new Record(1, 85), new Record(2, 98)
        };
        Map<Integer, Double> resultTwo = solution.highFive(two);
        // 1: 77.80
        // 2: 98.40
        printResult(resultTwo);
        // Test 3 - Added two low scores that shouldn't have affected the results
        Record[] three = new Record[] {
                new Record(1, 91), new Record(1, 92), new Record(2, 93),
                new Record(2, 99), new Record(2, 98), new Record(2, 97),
                new Record(1, 60), new Record(1, 58), new Record(2, 100),
                new Record(1, 61), new Record(1, 0), new Record(2, 0)
        };
        Map<Integer, Double> resultThree = solution.highFive(three);
        // 1: 72.4
        // 2: 97.4
        printResult(resultThree);
    }

    private static void printResult(Map<Integer, Double> map) {
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

class Record {
    int id, score;
    public Record(int id, int score) {
        this.id = id;
        this.score = score;
    }
}

class Solution {
    /**
     * @param results a list of <student_id, score>
     * @return find the average of 5 highest scores for each person
     * Map<Integer, Double> (student_id, average_score)
     */
    private final int FIVE = 5;
    public Map<Integer, Double> highFive(Record[] results) {
        // Write your code here
        Map<Integer, Double> result = new HashMap<>();
        if (results == null || results.length == 0) {
            return result;
        }
        // Build a HashMap to store the highest 5 scores of each id
        // Use PriorityQueue with a capacity of 5
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (Record record : results) {
            if (!map.containsKey(record.id)) {
                // map.put(record.id, new PriorityQueue<>(FIVE, (a, b) -> b - a));
                // map.put(record.id, new PriorityQueue<>(FIVE, Collections.reverseOrder()));
                map.put(record.id, new PriorityQueue<>());
            }
            PriorityQueue<Integer> minHeap = map.get(record.id);
            minHeap.offer(record.score);
            // Keep the size of the heap/pq to 5 such that it stores the largest
            // 5 elements eventually
            if (minHeap.size() > FIVE) {
                minHeap.poll();
            }
        }
        // Calculate the average of the highest 5 scores
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            PriorityQueue<Integer> minHeap = entry.getValue();
            double avg = 0.0;
            while (!minHeap.isEmpty()) {
                avg += minHeap.poll();
            }
            result.put(entry.getKey(), avg / FIVE);
        }
        return result;
    }
}
