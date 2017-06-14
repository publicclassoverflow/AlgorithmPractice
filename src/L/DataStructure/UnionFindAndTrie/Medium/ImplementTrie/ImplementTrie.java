package L.DataStructure.UnionFindAndTrie.Medium.ImplementTrie;

import java.util.HashMap;

/**
 * http://www.lintcode.com/en/problem/implement-trie/
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Notice:
 * You may assume that all inputs are consist of lowercase letters a-z.
 *
 * Example:
 * insert("lintcode")
 * search("code")
 * >>> false
 * startsWith("lint")
 * >>> true
 * startsWith("linterror")
 * >>> false
 * insert("linterror")
 * search("lintcode)
 * >>> true
 * startsWith("linterror")
 * >>> true
 */

public class ImplementTrie {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("lintcode");
        System.out.println(trie.search("code"));            // false
        System.out.println(trie.startsWith("lint"));        // true
        System.out.println(trie.startsWith("linterror"));   // false
        trie.insert("linterror");
        System.out.println(trie.search("lintcode"));        // true
        System.out.println(trie.startsWith("linterror"));   // true
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
class TrieNode {
    // Initialize your data structure here.
    private char ch;
    protected HashMap<Character, TrieNode> children = new HashMap<>();
    protected boolean isEnd;

    public TrieNode() {}

    public TrieNode(char ch) {
        this.ch = ch;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode current = root;
        HashMap<Character, TrieNode> currentChild = root.children;
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            char c = wordArray[i];
            if (currentChild.containsKey(c)) {
                current = currentChild.get(c);
            } else {
                TrieNode node = new TrieNode(c);
                currentChild.put(c, node);
                current = node;
            }
            currentChild = current.children;
            if (i == wordArray.length - 1) {
                current.isEnd = true;
            }
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        // The first method will search for the word twice if it is not null
        /*
        return searchForNode(word) != null
                && searchForNode(word).isEnd;
        */
        // The method below only searches for the word once
        TrieNode result = searchForNode(word);
        return result != null && result.isEnd;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return searchForNode(prefix) != null;
    }

    private TrieNode searchForNode(String str) {
        HashMap<Character, TrieNode> children = root.children;
        TrieNode current = null;
        char[] strArray = str.toCharArray();
        for (int i = 0; i < strArray.length; i++) {
            char c = strArray[i];
            if (!children.containsKey(c)) {
                return null;
            }
            current = children.get(c);
            children = current.children;
        }
        return current;
    }
}
