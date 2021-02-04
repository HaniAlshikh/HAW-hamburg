package de.hawh.jkjs.huffman;

/**
 * This class represents a node in a binary tree used for huffman compression.
 *
 * @author Johan Kemper
 * @author Jannik Stuckstätte
 *
 */
public class HuffNode implements Comparable<HuffNode> {
    private byte character;
    private int frequency;

    public HuffNode(byte character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public HuffNode(int frequency) {
        this.frequency = frequency;
    }

    public byte getCharacter() {
        return character;
    }

    public void setCharacter(byte character) {
        this.character = character;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HuffNode o) {
        if (o == null) {
            throw new NullPointerException();
        }

        return Long.compare(frequency, o.frequency);
    }

    public String toString() {
        if (character > 0) {
            return "char: " + (char) character + " freq: " + frequency;
        } else {
            return "char: " + character + " freq: " + frequency;
        }
    }
}
