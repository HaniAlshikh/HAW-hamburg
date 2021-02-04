package de.hawh.JKJSHS.huffman.refactored;

import com.github.jinahya.bit.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;


/**
 * using the tree as header + char count
 *
 *      tree        charCount 31bit             data + padding
 * |-------------|------------------|-------------------------------------|
 */
public class MyHuffman {

    Map<Byte, Integer>          freq;
    Map<Byte, Queue<Boolean>>   codeTable;
    PriorityQueue<HuffmanNode>  heap;
    Stack<Boolean>              path;
    HuffmanNode                 root;

    InputStream                 inputStream;
    OutputStream                outputStream;
    BitInput                    bitInput;
    BitOutput                   bitOutput;
    byte[]                      data;


    public static void encode(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        MyHuffman huffman = new MyHuffman();
        huffman.setEncodingStreams(inputStream, outputStream);
        huffman.encode();
    }

    public static void decode(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        MyHuffman huffman = new MyHuffman();
        huffman.setDecodingStreams(inputStream, outputStream);
        huffman.decode();
    }


    MyHuffman() {
        freq = new HashMap<>();
        codeTable = new HashMap<>();
        heap = new PriorityQueue<>();
        path = new Stack<>();
    }

    void encode() throws IOException {
        // compute
        calculateCharacterFrequencies();
        buildHuffmanTree();
        calculateCodeFromHuffmanTree();
        // write header
        writeHuffmanTree();
        writeByteCountToRead();
        // write data
        encodeDataFile();
    }

    void decode() throws IOException {
        // read header
        readHuffmanTree();
        // decode
        decodeDataFile();
    }


    /**
     * A) Encoding
     */
    // compute
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

    // write header
    void writeHuffmanTree() throws IOException {
        writeHuffmanTree(root);
    }

    // TODO: find a better way to determine when to stop reading (skip padding at the end)
    //  this will break if bytes count > 2^31
    void writeByteCountToRead() throws IOException {
        bitOutput.writeInt(true, 31, data.length);
    }

    // write data
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
    void readHuffmanTree() throws IOException {
        root = readHuffmanTreeR();
    }
    HuffmanNode readHuffmanTreeR() throws IOException {
        if (bitInput.readBoolean()) {
            return new HuffmanNode(bitInput.readByte8(), 0, null, null);
        } else {
            HuffmanNode leftChild = readHuffmanTreeR();
            HuffmanNode rightChild = readHuffmanTreeR();
            return new HuffmanNode((byte) '-', 0, leftChild, rightChild);
        }
    }

    void decodeDataFile() throws IOException {
        int bytesCount = bitInput.readInt(true, 31);
        HuffmanNode currentNode = root;

        while (bytesCount != 0) {
            if (!bitInput.readBoolean()) currentNode = currentNode.left;
            else currentNode = currentNode.right;

            if (currentNode.isLeaf()) {
                outputStream.write(currentNode.byt);
                bytesCount--;
                currentNode = root;
            }
        }
    }


    void createHuffmanNodes() {
        freq.forEach((b, f) -> heap.add(new HuffmanNode(b, f)));
    }

    void connectHuffmanNodes() {
        while (heap.size() > 1) {
            HuffmanNode left = heap.poll(), right = heap.poll();
            heap.add(new HuffmanNode((byte) '-', left.freq + right.freq, left, right));
        }
        root = heap.poll();
    }

    private void traverseHuffmanTree(HuffmanNode aTree) {
        if (aTree.isLeaf()) {
            codeTable.put(aTree.byt, getPath());
            return;
        }
        path.push(false);
        traverseHuffmanTree(aTree.left);
        path.pop();
        path.push(true);
        traverseHuffmanTree(aTree.right);
        path.pop();
    }

    void writeHuffmanTree(HuffmanNode node) throws IOException {
        if (node.isLeaf()) {
            bitOutput.writeBoolean(true);
            bitOutput.writeByte8(node.byt);
        } else {
            bitOutput.writeBoolean(false);
            writeHuffmanTree(node.left);
            writeHuffmanTree(node.right);
        }
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
