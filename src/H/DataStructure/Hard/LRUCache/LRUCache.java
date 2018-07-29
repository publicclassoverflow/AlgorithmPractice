package H.DataStructure.Hard.LRUCache;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/lru-cache/
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.
 *
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 *
 * set(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently
 * used item before inserting a new item.
 *
 * Example:
 * LRUCache(2)
 * set(2, 1)
 * set(1, 1)
 * get(2)
 * set(4, 1)
 * get(1)
 * get(2)
 *
 * output:
 * [1, -1, 1]
 */

public class LRUCache {
    public static void main(String[] args) {
        LRUSolution solution = new LRUSolution(2);
        // Test
        solution.set(2, 1);
        solution.set(1, 1);
        System.out.println(solution.get(2));
        solution.set(4, 1);
        System.out.println(solution.get(1));
        System.out.println(solution.get(2));
        solution.set(2, 0);
        System.out.println(solution.get(2));
    }
}

// class LRUCache {
// Names conflicted. Renamed to LRUSolution
class LRUSolution {
    /*
    * @param capacity: An integer
    */
    // The idea is to use a deque to keep track of the keys in the cache because
    // it supports add/delete at both ends.
    // An additional hash map is needed to store the corresponding value of
    // each key
    private Deque<Integer> cache;
    private Map<Integer, Integer> inCache;
    private int limit;

    public LRUSolution(int capacity) {
        // do initialization if necessary
        cache = new LinkedList<>();
        inCache = new HashMap<>();
        limit = capacity;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
        if (!inCache.containsKey(key)) {
            return -1;
        }
        // If the key is present in the cache, it now becomes the most recently
        // used item and needs to be moved to the head of the deque
        cache.remove(key);
        cache.offerFirst(key);
        return inCache.get(key);
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // write your code here
        // If the key is present in the cache, it needs to be replaced with the
        // new value and moved to the head of the deque
        if (inCache.containsKey(key)) {
            cache.remove(key);
            cache.offerFirst(key);
            inCache.replace(key, value);
            return;
        }
        // If the key isn't present in the cache, we need to check the size of
        // the cache first and remove the least recently used item at the end of
        // the deque if the cache reaches its maximum capacity
        if (cache.size() >= limit) {
            int lruKey = cache.removeLast();
            inCache.remove(lruKey);
        }
        cache.offerFirst(key);
        inCache.put(key, value);
    }
}
