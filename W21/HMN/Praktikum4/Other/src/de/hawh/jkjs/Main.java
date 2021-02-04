package de.hawh.jkjs;

import de.hawh.jkjs.huffman.Huffman;
import de.hawh.jkjs.lzw.Lzw;

import java.io.IOException;

public class Main {

    private static final String FILE_TO_COMPRESS_TEXT = "./resources/lorem_ipsum.txt";
    private static final String COMPRESSSED_FILE_TEXT = "./resources/lorem_ipsumEncoded";
    private static final String DECOMPRESSSED_FILE_TEXT = "./resources/lorem_ipsumDecoded.txt";

    private static final String FILE_TO_COMPRESS_IMG = "./resources/strangedays.png";
    private static final String COMPRESSSED_FILE_IMG = "./resources/strangedaysEncoded";
    private static final String DECOMPRESSSED_FILE_IMG = "./resources/strangedaysDecoded.png";

    private static final String FILE_TO_COMPRESS_CSV = "./resources/21371-101.csv";
    private static final String COMPRESSSED_FILE_CSV = "./resources/21371-101Encoded";
    private static final String DECOMPRESSSED_FILE_CSV = "./resources/21371-101Decoded.csv";

    private static void testHuffmanCompression() throws IOException {
        Huffman.encodeDataFile(FILE_TO_COMPRESS_TEXT, COMPRESSSED_FILE_TEXT);
        Huffman.decodeDataFile(COMPRESSSED_FILE_TEXT, DECOMPRESSSED_FILE_TEXT);

        Huffman.encodeDataFile(FILE_TO_COMPRESS_IMG, COMPRESSSED_FILE_IMG);
        Huffman.decodeDataFile(COMPRESSSED_FILE_IMG, DECOMPRESSSED_FILE_IMG);
    }

    private static void testLzwCompression() throws IOException {
        //Lzw.encodeDataFile(FILE_TO_COMPRESS_TEXT, COMPRESSSED_FILE_TEXT);
        //Lzw.decodeLzw(COMPRESSSED_FILE_TEXT, DECOMPRESSSED_FILE_TEXT);

        // Lzw.encodeDataFile(FILE_TO_COMPRESS_IMG, COMPRESSSED_FILE_IMG);
        // Lzw.decodeLzw(COMPRESSSED_FILE_IMG, DECOMPRESSSED_FILE_IMG);

        Lzw.encodeDataFile(FILE_TO_COMPRESS_CSV, COMPRESSSED_FILE_CSV);
        Lzw.decodeLzw(COMPRESSSED_FILE_CSV, DECOMPRESSSED_FILE_CSV);
    }

    public static void main(String[] args) throws IOException {
        // testHuffmanCompression();

        testLzwCompression();
    }

}
