package de.hawh.JKJSHS.LZW;

import java.util.HashMap;
import java.util.Map;
public class Trie {

    char data;
    int number;
    Map<Character, Trie> subtries;

    public Trie() {
        this.subtries = new HashMap<>();
    }

    public Trie(char data, int number) {
        this();
        this.data = data;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void insert(String aString, int number) {
        char cStart = aString.charAt(0);
        if (!this.subtries.containsKey(cStart)) {
            this.subtries.put(cStart, new Trie());
        }
        this.subtries.get(cStart).insert(aString, 0, number);
    }

    private void insert(String aString, int depth, int number) {
        char aChar;
        Trie newTrie;
        aChar = aString.charAt(depth);

        if (depth >= aString.length() - 1) {
            this.data = aChar;
            this.number = number;
            return;
        }
        if (subtries.containsKey(aChar)) {
            newTrie = subtries.get(aChar);
        } else {
            newTrie = new Trie();
            subtries.put(aChar, newTrie);
        }
        newTrie.insert(aString, ++depth, number);
    }

    public boolean includes(String aString) {
        return this.get(aString) != null;
    }

    public Trie get(String aString) {
        char cStart = aString.charAt(0);
        if (!this.subtries.containsKey(cStart)) {
            return null;
        }
        return this.subtries.get(cStart).get(aString, 0);
    }


    private Trie get(String aString, int depth) {
        char aChar;
        aChar = aString.charAt(depth);
        if (depth == aString.length() - 1) {
            if (aChar == this.data)
                return this;
            else
                return null;
        }
        if (subtries.containsKey(aChar)) {
            return this.subtries.get(aChar).get(aString, ++depth);
        } else {
            return null;
        }
    }
}