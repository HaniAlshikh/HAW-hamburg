package de.hawh.JKJSHS.LZW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZW {

    int DICTIONARY_SIZE;

    public LZW(int DICTIONARY_SIZE) {
        this.DICTIONARY_SIZE = DICTIONARY_SIZE + 1; // why +1
    }

    public List<Integer> compress(String text) {
        if (text == null) return null;

        int dictionarySize = DICTIONARY_SIZE;
        List<Integer> neededInts = new ArrayList<>();
        Trie trie = new Trie();

        for (int i = 0; i < dictionarySize; i++) {
            trie.insert("" + (char) i, i);
        }

        String previous = "";
        for(char c : text.toCharArray()) {
            String combined = previous + c;
            if (trie.includes(combined)) {
                previous = combined;
            } else {
                neededInts.add(trie.get(previous).getNumber());
                trie.insert(combined, dictionarySize++);
                previous = "" + c;
            }
        }

        if (!previous.equals("")) {
            neededInts.add(trie.get(previous).getNumber());
        }

        return neededInts;
    }

    public String decompress(List<Integer> compressed) {
        if (compressed == null) return null;

        int dictionarySize = DICTIONARY_SIZE;
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i=0; i<dictionarySize; i++) {
            dictionary.put(i, "" + (char) i);
        }

        String previous = "" + (char)(int) compressed.remove(0);
        StringBuilder result = new StringBuilder(previous);
        for (int j : compressed) {
            String combined;
            if (dictionary.containsKey(j)) {
                combined = dictionary.get(j);
            } else if (j == dictionarySize) {
                combined = previous + previous.charAt(0);
            } else {
                return "-1";
            }

            result.append(combined);
            dictionary.put(dictionarySize++, previous + combined.charAt(0));
            previous = combined;
        }
        return result.toString();
    }
}
