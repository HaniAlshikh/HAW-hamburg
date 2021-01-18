package de.hawh.JKJSHS.huffman.assignment;

public class HuffNode {
    byte byt;
    int freq;

    public HuffNode(byte byt, int freq) {
        this.byt = byt;
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "HuffNode{" +
                "chr=" + Character.toString(byt) +
                ", frequency=" + freq +
                '}';
    }
}
