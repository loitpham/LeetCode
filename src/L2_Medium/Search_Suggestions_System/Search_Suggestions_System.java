package L2_Medium.Search_Suggestions_System;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Mon, 31 May 2021, 11:16 PM
 * Description:
 * https://leetcode.com/problems/search-suggestions-system/
 **/
public class Search_Suggestions_System {
    static class TrieNode {
        TrieNode[] data;
        boolean isComplete;
        public TrieNode() {
            data = new TrieNode[26];
            isComplete = false;
        }
    }

    static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }
        public void insert(String word) {
            String lower = word.toLowerCase();
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = lower.charAt(i) - 'a';
                if (curr.data[idx] == null) {
                    curr.data[idx] = new TrieNode();
                }
                curr = curr.data[idx];
            }
            curr.isComplete = true;
        }
        public boolean search(String word, boolean isPrefix) {
            String lower = word.toLowerCase();
            TrieNode curr = root;
            for (int i = 0; i < lower.length(); i++) {
                int idx = lower.charAt(i) - 'a';
                if (curr.data[idx] == null) {
                    return false;
                }
                curr = curr.data[idx];
            }
            return isPrefix || curr.isComplete;
        }
        public boolean search(String word) {
            return search(word, false);
        }
        public boolean startsWith(String prefix) {
            return search(prefix, true);
        }
        public List<String> getWordsStartingWith(String prefix) {
            TrieNode curr = root;
            String lower = prefix.toLowerCase();
            List<String> buffer = new ArrayList<>();
            for (int i = 0; i < lower.length(); i++) {
                int idx = lower.charAt(i) - 'a';
                if (curr.data[idx] == null) {
                    return buffer;
                }
                curr = curr.data[idx];
            }
            dfs(curr, prefix, buffer);
            return buffer;
        }
        public void dfs(TrieNode curr, String prefix,
                        List<String> buffer) {
            if (buffer.size() == 3) {
                return;
            }
            if (curr.isComplete) {
                buffer.add(prefix);
            }

            for (char c = 'a'; c <= 'z'; c++) {
                int idx = c - 'a';
                if (curr.data[idx] != null) {
                    dfs(curr.data[idx], prefix + c, buffer);
                }
            }
        }
    }

    public List<List<String>> suggestedProducts(String[] products,
                                                String searchWord) {
        Trie trie = new Trie();
        List<List<String>> result = new ArrayList<>();
        Arrays.sort(products);
        for (String word : products) {
            trie.insert(word);
        }
        String prefix = "";
        for (char c : searchWord.toCharArray()) {
            prefix += c;
            result.add(trie.getWordsStartingWith(prefix));
        }
        return result;
    }

    public int findFirstIndexWith(String[] products, int start,
                                  String prefix) {
        int i = start;
        int j = products.length;
        int mid;
        while (i < j) {
            mid = (i + j) / 2;
            if (products[mid].compareTo(prefix) >= 0) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    public List<List<String>> suggestedProducts2(String[] products,
                                                String searchWord) {
        Arrays.sort(products);
        List<List<String>> result = new ArrayList<>();
        int start = 0;
        int n = products.length;
        int searchStart = 0;
        String prefix = "";
        for (char c : searchWord.toCharArray()) {
            prefix += c;
            start = findFirstIndexWith(products, searchStart, prefix);
            List<String> list = new ArrayList<>();
            for (int i = start; i < Math.min(start + 3, n); i++) {
                if (products[i].length() >= prefix.length() &&
                        products[i].startsWith(prefix)) {
                    list.add(products[i]);
                }
            }
            result.add(list);
            searchStart = Math.abs(start);
        }
        return result;
    }
}
