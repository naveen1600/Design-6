// Time Complexity: O(1)
// Space Complexity: O(n)

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class AutocompleteSystem {
    class TrieNode {
        HashMap<Character, TrieNode> children;
        List<String> top3;

        public TrieNode() {
            this.children = new HashMap<>();
            this.top3 = new ArrayList<>();
        }
    }

    private HashMap<String, Integer> map;
    private StringBuilder input;
    private TrieNode root;

    private void insert(TrieNode root, String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c))
                curr.children.put(c, new TrieNode());
            curr = curr.children.get(c);

            List<String> li = curr.top3;
            if (!li.contains(word))
                li.add(word);

            Collections.sort(li, (a, b) -> {
                int count1 = map.get(a);
                int count2 = map.get(b);
                if (count1 == count2)
                    return a.compareTo(b);
                return count2 - count1;
            });
            if (li.size() > 3)
                li.remove(li.size() - 1);
        }
    }

    private List<String> startsWith(TrieNode root, String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c))
                return new ArrayList<>();
            curr = curr.children.get(c);
        }
        return curr.top3;
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.input = new StringBuilder();
        this.root = new TrieNode();
        for (int i = 0; i < times.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(root, sentences[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            String in = input.toString();
            map.put(in, map.getOrDefault(in, 0) + 1);
            insert(root, in);
            input = new StringBuilder();
            return new ArrayList<>();
        }

        input.append(c);
        String searchTerm = input.toString();
        return startsWith(root, searchTerm);

    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */