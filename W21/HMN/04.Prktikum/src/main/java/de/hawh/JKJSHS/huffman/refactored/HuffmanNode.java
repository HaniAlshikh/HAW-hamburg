package de.hawh.JKJSHS.huffman.refactored;

public class HuffmanNode implements Comparable<HuffmanNode> {
    byte byt;
    long freq;

    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(byte byt, long freq) {
        this.byt = byt;
        this.freq = freq;
    }

    public HuffmanNode(byte byt, long freq, HuffmanNode left, HuffmanNode right) {
        this.byt = byt;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return Long.compare(freq, o.freq);
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "item=" + freq +
                ", chr=" + byt +
                '}';
    }
}
