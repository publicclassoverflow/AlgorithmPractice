package L.DataStructure.UnionFindAndTrie.Medium.AddAndSearchWord;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/add-and-search-word/
 *
 * Design a data structure that supports the following two operations:
 * addWord(word) and search(word)
 * search(word) can search a literal word or a regular expression string
 * containing only letters a-z or ..
 * A . means it can represent any one letter.
 *
 * Notice:
 * You may assume that all words are consist of lowercase letters a-z.
 *
 * Example:
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad")  // return false
 * search("bad")  // return true
 * search(".ad")  // return true
 * search("b..")  // return true
 */

public class AddAndSearchWord {
    public static void main(String[] args) {
        // Test 1
        WordDictionary dictionaryOne = new WordDictionary();
        String[] addOne = new String[]{"bad", "dad", "mad"};
        String[] searchOne = new String[] {"pad", "bad", ".ad", "b..", "."};
        doTest(dictionaryOne, addOne, searchOne, 1);
        // Test 2
        WordDictionary dictionaryTwo = new WordDictionary();
        doTest(dictionaryTwo, new String[]{}, new String[]{"."}, 2);
        // Test 3
        WordDictionary dictionaryThree = new WordDictionary();
        String[] addThree = new String[]{
                "ran", "rune", "runner", "runs", "add", "adds", "adder", "addee"
        };
        String[] searchThree = new String[]{
                "r.n", "run.ne", "add", "add.", "adde.", ".an.", "...s",
                "....e.", ".......", "..n.r"
        };
        doTest(dictionaryThree, addThree, searchThree, 3);
    }

    private static void doTest(WordDictionary wordDictionary,
                               String[] wordToAdd, String[] wordToSearch,
                               int testNumber) {
        for (String word : wordToAdd) {
            wordDictionary.addWord(word);
        }
        System.out.format("Test %d: in array %s%n",
                testNumber, Arrays.toString(wordToAdd));
        for (String word : wordToSearch) {
            System.out.format(
                    "Searching for \"%s\" -> %s%n",
                    word, wordDictionary.search(word)
            );
        }
    }
}

class TrieNode {
    protected HashMap<Character, TrieNode> children;
    protected boolean isEnd;

    public TrieNode() {
        children = new HashMap<>();
        isEnd = false;
    }
}

class WordDictionary {

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    // Adds a word into the data structure.
    public void addWord(String word) {
        // Write your code here
        if (word == null || word.length() == 0) {
            return;
        }
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                // Add current character to the children map
                node.children.put(ch, new TrieNode());
            }
            // Keep moving down the trie
            node = node.children.get(ch);
        }
        // Set the isEnd flag to true after finishing adding the whole word
        node.isEnd = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        // Write your code here
        return searchForWord(word, 0, root);
    }
    
    private boolean searchForWord(String word, int index, TrieNode node) {
        if (index == word.length()) {
            return node.isEnd;
            // return node.children.size() == 0;
        }
        char ch = word.charAt(index);
        if (ch == '.') {
            boolean result = false;
            for (Map.Entry<Character, TrieNode> child
                    : node.children.entrySet()) {
                if (index == word.length() - 1 && child.getValue().isEnd) {
                    return true;
                    // Notice: If it is not end yet, it should not return false
                }
                if (searchForWord(word, index + 1, child.getValue())) {
                    result = true;
                }
            }
            return result;
        }
        if (node.children.containsKey(ch)) {
            if (index == word.length() - 1) {
                return node.children.get(ch).isEnd;
            }
            return searchForWord(word, index + 1, node.children.get(ch));
        }
        return false;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
