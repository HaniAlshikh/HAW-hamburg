package de.hawh.JKJSHS.huffman.assignment;

import com.github.jinahya.bit.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * using the char count as header
 *
 *      bytesCount 32bit      bytesFreq 40bit pro byte          data + padding
 * |----------------------|------------------------------|----------------------------|
 */
public class Huffman {

    static int chars = 256;

    Map<Byte, Integer>                  freq;
    Map<Byte, Queue<Boolean>>           codeTable;
    PriorityQueue<BinaryTree<HuffNode>> heap;
    Stack<Boolean>                      path;
    BinaryTree<HuffNode>                root;



    InputStream                         inputStream;
    OutputStream                        outputStream;
    BitInput                            bitInput;
    BitOutput                           bitOutput;
    byte[]                              data;


    public static void encode(InputStream inputStream, OutputStream outputStream) throws IOException {
        Huffman huffman = new Huffman();
        huffman.setEncodingStreams(inputStream, outputStream);
        huffman.encode();
    }

    public static void decode(InputStream inputStream, OutputStream outputStream) throws IOException {
        Huffman huffman = new Huffman();
        huffman.setDecodingStreams(inputStream, outputStream);
        huffman.decode();
    }


    Huffman() {
        freq = new HashMap<>();
        codeTable = new HashMap<>();
        heap = new PriorityQueue<>(Comparator.comparing(e -> e.data.freq));
        path = new Stack<>();
    }

    void encode() throws IOException {
        calculateCharacterFrequencies();
        buildHuffmanTree();
        calculateCodeFromHuffmanTree();
        writeCharacterFrequencies();
        encodeDataFile();
    }

    void decode() throws IOException {
        readCharacterFrequencies();
        buildHuffmanTree();
        decodeDataFile();
    }

    /**
     * A) Encoding
     */
    void calculateCharacterFrequencies() throws IOException {
        data = inputStream.readAllBytes();

        for (byte b : data) {
            freq.put(b, freq.getOrDefault(b, 0) + 1);
        }
    }

    void buildHuffmanTree() {
        createHuffmanNodes();
        connectHuffmanNodes();
    }

    void calculateCodeFromHuffmanTree() {
        traverseHuffmanTree(root);
    }

    void writeCharacterFrequencies() throws IOException {
        bitOutput.writeInt32(freq.size());

        for (Byte b : freq.keySet()) {
            bitOutput.writeByte8(b);
            bitOutput.writeInt32(freq.get(b));
        }
    }

    void encodeDataFile() throws IOException {
        for (byte b : data) {
            Queue<Boolean> code = codeTable.get(b);
            for (Boolean bool : code) {
                bitOutput.writeBoolean(bool);
            }
        }
        bitOutput.align(1);
    }


    /**
     * B) Decoding
     */
    void readCharacterFrequencies() throws IOException {
        int charNum = bitInput.readInt32();

        for (int i = 0; i < charNum; i++) {
            freq.put(bitInput.readByte8(), bitInput.readInt32());
        }
    }

    void decodeDataFile() throws IOException {
        int bytesCount = freq.values().stream().mapToInt(Integer::intValue).sum();
        BinaryTree<HuffNode> currentNode = root;

        while (bytesCount != 0) {
            if (!bitInput.readBoolean()) currentNode = currentNode.left;
            else currentNode = currentNode.right;

            if (currentNode.isLeaf()) {
                outputStream.write(currentNode.data.byt);
                bytesCount--;
                currentNode = root;
            }
        }
    }

    void createHuffmanNodes() {
        freq.forEach((b, f) -> heap.add(new BinaryTree<>(new HuffNode(b, f))));
    }

    void connectHuffmanNodes() {
        while (heap.size() > 1) {
            BinaryTree<HuffNode> left = heap.poll(), right = heap.poll();
            heap.add(new BinaryTree<>(new HuffNode((byte) '-',
                    left.data.freq + right.data.freq), left, right));
        }
        root = heap.poll();
    }

    private void traverseHuffmanTree(BinaryTree<HuffNode> aTree) {
        if (aTree.isLeaf()) {
            codeTable.put(aTree.data.byt, getPath());
            return;
        }
        path.push(false);
        traverseHuffmanTree(aTree.left);
        path.pop();
        path.push(true);
        traverseHuffmanTree(aTree.right);
        path.pop();
    }

    private Queue<Boolean> getPath() {
        return new ArrayDeque<>(path);
    }

    void setEncodingStreams(InputStream inputStream, OutputStream outputStream) throws IOException {
        this.inputStream = inputStream;
        this.bitOutput = new DefaultBitOutput(new StreamByteOutput(outputStream));
    }

    void setDecodingStreams(InputStream inputStream, OutputStream outputStream) {
        this.bitInput = new DefaultBitInput(new StreamByteInput(inputStream));
        this.outputStream = outputStream;
    }
}
