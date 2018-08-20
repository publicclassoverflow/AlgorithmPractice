package H.DataStructure.Medium;

//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.PriorityQueue;

import java.util.*;

/**
 * https://www.lintcode.com/problem/top-k-frequent-words/description
 *
 * Given a list of words and an integer k, return the top k frequent words in the list.
 *
 * You should order the words by the frequency of them in the return list, the
 * most frequent one comes first. If two words has the same frequency, the one
 * with lower alphabetical order come first.
 *
 * Example
 * Given
 *
 * [
 *     "yes", "lint", "code",
 *     "yes", "code", "baby",
 *     "you", "baby", "chrome",
 *     "safari", "lint", "code",
 *     "body", "lint", "code"
 * ]
 * for k = 3, return ["code", "lint", "baby"].
 *
 * for k = 4, return ["code", "lint", "baby", "yes"],
 *
 * Challenge
 * Do it in O(nlogk) time and O(n) extra space.
 *
 **/

public class TopKFrequentWords {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test
        String[] one = {"yes", "lint", "code",
                        "yes", "code", "baby",
                        "you", "baby", "chrome",
                        "safari", "lint", "code",
                        "body", "lint", "code"};
        System.out.println(Arrays.toString(solution.topKFrequentWords(one, 3)));
        System.out.println(Arrays.toString(solution.topKFrequentWords(one, 4)));
    }
}

class Solution {
    /**
     * @param words: an array of string
     * @param k: An integer
     * @return: an array of string
     */

    public String[] topKFrequentWords(String[] words, int k) {
        // write your code here
        if (words == null || words.length == 0) {
            return words;
        }
        // Iterate over the array and count the frequency of each word
        Map<String, Integer> frequency = getWordsFrequency(words);
        // Use a min heap to store the largest k elements
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                k,
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> one,
                                       Map.Entry<String, Integer> two) {
                        if (one.getValue() != two.getValue()) {
                            return one.getValue().compareTo(two.getValue());
                        }
                        return two.getKey().compareTo(one.getKey());
                    }
                }
        );
        // Get the top k frequent words from the map using the min heap
        return getMostFrequentWords(frequency, minHeap, k);
    }

    private Map<String, Integer> getWordsFrequency(String[] words) {
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }
        return frequency;
    }

    private String[] getMostFrequentWords(
            Map<String, Integer> frequency,
            PriorityQueue<Map.Entry<String, Integer>> minHeap, int k) {
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            /*
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
            */
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        String[] result = new String[minHeap.size()];
        for (int i = minHeap.size() - 1; i >= 0; i--) {
            result[i] = minHeap.poll().getKey();
        }
        return result;
    }
}
