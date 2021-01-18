package de.hawh.JKJSHS.huffman.assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanTest {

    static String[][][] testCases = {
            {
                    {"BCCABBDDAECCBBAEDDCC"},           // testCases[0][0]
                    {"A", "B", "C", "D", "E"},          // testCases[0][1][0..n]
                    {"3", "5", "6", "4", "2"},          // testCases[0][2][0..n]
                    {"011", "10", "11", "00", "010"}    // testCases[0][3][0..n]
            }
    };


    Huffman huffman;
    String binFile;
    String decodedBinFile;

    InputStream data;
    OutputStream encodedDataOutput;

    InputStream encodedData;
    OutputStream decodedDataOutput;

    @BeforeEach
    void setUp() throws IOException {
        huffman = new Huffman();
        binFile = "src/test/java/resources/test.bin";
        decodedBinFile = binFile+"-decoded.txt";

        encodedDataOutput = new FileOutputStream(binFile);
        encodedData = new FileInputStream(binFile);
        decodedDataOutput = new FileOutputStream(binFile+"-decoded.txt");
    }


    @ParameterizedTest
    @MethodSource("provideTestCases")
    void calculateCharacterFrequencies(String dataStr, byte[] byteArr, int[] freqArr) throws IOException {
        data = new ByteArrayInputStream(dataStr.getBytes());
        huffman.setEncodingStreams(data, encodedDataOutput);
        huffman.calculateCharacterFrequencies();

        for (int i = 0; i < byteArr.length; i++) {
            assertEquals(
                    freqArr[i],
                    huffman.freq.get(byteArr[i])
            );
        }
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void calculateCodeFromHuffmanTree(String dataStr, byte[] byteArr, int[] freqArr, String[] codeArr) throws IOException {
        data = new ByteArrayInputStream(dataStr.getBytes());
        huffman.setEncodingStreams(data, encodedDataOutput);
        huffman.calculateCharacterFrequencies();
        huffman.buildHuffmanTree();
        huffman.calculateCodeFromHuffmanTree();

        BinaryTree<HuffNode> current = huffman.root;

        for (int i = 0; i < codeArr.length; i++) {
            for (char c : codeArr[i].toCharArray()) {
                current = c == '0' ? current.left : current.right;
            }

            assertTrue(current.isLeaf());
            assertEquals(current.data.byt, byteArr[i]);
            assertEquals(current.data.freq, freqArr[i]);
            current = huffman.root;
        }
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void encodeDecodeData(String dataStr) throws IOException {
        data = new ByteArrayInputStream(dataStr.getBytes());
        Huffman.encode(data, encodedDataOutput);
        Huffman.decode(encodedData, decodedDataOutput);

        assertEquals(dataStr, Files.readString(Path.of(decodedBinFile)));

    }


    @ParameterizedTest
    @ValueSource(strings = {
            "UTF_8_small.txt", // 100 bytes
            "UTF_8_medium.txt", // 1000 bytes
            "UTF_8_big.txt", // 10000 bytes
            "UTF_8_repeating.txt",
            "UTF_16_small.txt",
            "UTF_16_medium.txt",
            "UTF_16_big.txt",
            "UTF_16_repeating.txt",
            "UTF_16_repeating.txt",
            "21371-101.csv",
            "pic.jpg"
            //"vid.mov" // takes too much time
    })
    public void wasItWorthIt(String fileName) throws IOException {

        File file = new File("Resources/" + fileName);
        File fileCompressed = new File("Resources/" + fileName + ".bin");
        File fileDecompressed = new File("Resources/decompressed_" + fileName);

        System.out.println(fileName);
        long originalSize = Files.size(file.toPath());
        System.out.println("file size before compression: " + originalSize + " bytes");

        Huffman.encode(new FileInputStream(file), new FileOutputStream(fileCompressed));

        long compressedSize = Files.size(fileCompressed.toPath());

        System.out.println("after compression:            " + compressedSize + " bytes");
        System.out.printf(" ->                           %.3f %% reduction\n",
                (((originalSize - compressedSize)/(double)originalSize) * 100));

        Huffman.decode(new FileInputStream(fileCompressed), new FileOutputStream(fileDecompressed));

        assertArrayEquals(Files.readAllBytes(file.toPath()), Files.readAllBytes(fileDecompressed.toPath()));

        Files.delete(fileCompressed.toPath());
        Files.delete(fileDecompressed.toPath());
    }


    // ------------ helper methods ------------

    private static Stream<Arguments> provideTestCases() {
        List<Arguments> arguments= new ArrayList<>();

        for (String[][] testCase : testCases) {
            byte[] bytesArr = Arrays.stream(testCase[1]).reduce(String::concat).orElseThrow().getBytes();
            int[] freqArr = Arrays.stream(testCase[2]).mapToInt(Integer::valueOf).toArray();
            arguments.add(Arguments.of(testCase[0][0], bytesArr, freqArr, testCase[3]));
        }

        return arguments.stream();
    }
}