package de.hawh.jkjs.huffman;

import com.github.jinahya.bit.io.*;

import java.io.*;
import java.nio.channels.WritableByteChannel;
import java.util.*;

public class Huffman {

    private static Map<Byte, Integer> charFrequencies;
    private static Map<Byte, Stack<Boolean>> codeTable;
    private static Queue<BinaryTree<HuffNode>> heap;
    private static Stack<Boolean> path;
    private static BinaryTree<HuffNode> root;
    private static BitInput bitInput = null;

    /*
     * A) ENCODING
     */
    private static void calculateCharacterFrequencies(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[inputStream.available()];
        int bytesRead = inputStream.read(buffer);

        for (byte b : buffer) {
            charFrequencies.put(b, charFrequencies.get(b) + 1);
        }

        buildHeap();
    }

    private static void buildHuffmanTree() {
        while (heap.size() > 1) {
            BinaryTree<HuffNode> firstNode = heap.remove();
            BinaryTree<HuffNode> secondNode = heap.remove();
            heap.add(new BinaryTree<>(new HuffNode(
                firstNode.getData().getFrequency() + secondNode.getData().getFrequency()),
                firstNode, secondNode));
        }
        root = heap.remove();
    }


    private static void calculateCodeFromHuffmanTree(BinaryTree<HuffNode> tree) {
        if (tree.isLeaf()) {
            if (path.empty()) path.add(false);
            Stack<Boolean> stackCopy = new Stack<>();
            stackCopy.addAll(path);
            codeTable.put(tree.getData().getCharacter(), stackCopy);
            return;
        }

        path.push(false);
        calculateCodeFromHuffmanTree(tree.getLeft());
        path.pop();
        path.push(true);
        calculateCodeFromHuffmanTree(tree.getRight());
        path.pop();
    }

    private static void   writeCharacterFrequencies(BitOutput bitOutput) throws IOException {
        for(int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            bitOutput.writeInt32(charFrequencies.get((byte) i));
        }
    }

    private static void resetData() {
        charFrequencies = new HashMap<>();
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            charFrequencies.put((byte) i, 0);
        }
        codeTable = new HashMap<>();
        heap = new PriorityQueue<>(50,
                Comparator.comparing(BinaryTree::getData));
        path = new Stack<>();
    }

    public static void encodeDataFile(String fileName, String outFileName) throws IOException {
        resetData();
        try (InputStream inputStream = new FileInputStream(fileName)) {
            calculateCharacterFrequencies(inputStream);
        }
        buildHuffmanTree();
        calculateCodeFromHuffmanTree(root);

        try (OutputStream outputStream = new FileOutputStream(outFileName); InputStream inputStream = new FileInputStream(fileName)) {
            final BitOutput bitOutput = new DefaultBitOutput(new StreamByteOutput(outputStream));
            writeCharacterFrequencies(bitOutput);

            byte[] buffer = new byte[inputStream.available()];
            int bytesRead = inputStream.read(buffer);

            for (byte b : buffer) {
                Stack<Boolean> currentBits = codeTable.get(b);
                for (boolean currentBit : currentBits) {
                    bitOutput.writeBoolean(currentBit);
                }
            }
            bitOutput.align(1);
        }

    }


    /*
     * B) DECODING
     */
    public static void decodeDataFile(String fileName, String outFileName) throws IOException {
        resetData();
        try (InputStream inputStream = new FileInputStream(fileName); OutputStream outputStream = new FileOutputStream(outFileName)) {
            bitInput = new DefaultBitInput(new StreamByteInput(inputStream));
            int sumOfAllChars = 0;
            for(int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
                int bitValue = bitInput.readInt32();
                charFrequencies.put((byte) i, bitValue);
                sumOfAllChars += bitValue;
            }
            System.out.println(sumOfAllChars);

            buildHeap();

            System.out.println(charFrequencies);
            System.out.println(heap);

            buildHuffmanTree();
            System.out.println(root);
            calculateCodeFromHuffmanTree(root);
            bitInput.align(1);
            for (int x = 0; x < sumOfAllChars; x++) {
                outputStream.write(traverseBits(root));
            }
        }
    }

    private static void buildHeap() {
        for (int currentChar = Byte.MIN_VALUE; currentChar <= Byte.MAX_VALUE; currentChar++) {
            if (charFrequencies.get((byte) currentChar) == 0) {
                continue;
            }

            heap.add(new BinaryTree<>(new HuffNode((byte) currentChar, charFrequencies.get((byte) currentChar))));
        }
    }

    private static byte traverseBits(BinaryTree<HuffNode> tree) throws IOException {
        if (tree.isLeaf())
            return tree.getData().getCharacter();
        if (bitInput.readBoolean()) {
            return traverseBits(tree.getRight());
        } else {
            return traverseBits(tree.getLeft());
        }
    }
}
