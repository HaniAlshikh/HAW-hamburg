package de.hawh.JKJSHS.LZW;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class LZWTest {

    LZW l;

    @BeforeEach
    public void setUp() {
        l = new LZW(255);
    }
    
    @Test
    public void compressTest() {
        // ababcbc -> [97, 98, 256, 99, 98, 99]
        List<Integer> result  = l.compress("ababcbc");
        assertEquals(6, result.size());
        assertEquals(97, result.get(0).intValue());
        assertEquals(98, result.get(1).intValue());
        assertEquals(256, result.get(2).intValue());
        assertEquals(99, result.get(3).intValue());
        assertEquals(98, result.get(4).intValue());
        assertEquals(99, result.get(5).intValue());
        assertNull(l.compress(null));
    }

    @Test
    public void decompressTest() {
        // [97, 98, 256, 99, 98, 99] -> ababcbc
        List<Integer> compressed  = l.compress("ababcbc");
        assertEquals(6, compressed.size());
        assertEquals(97, compressed.get(0).intValue());
        assertEquals(98, compressed.get(1).intValue());
        assertEquals(256, compressed.get(2).intValue());
        assertEquals(99, compressed.get(3).intValue());
        assertEquals(98, compressed.get(4).intValue());
        assertEquals(99, compressed.get(5).intValue());
        assertNull(l.compress(null));

        LZW lzw = new LZW(255);
        String decompressed =  lzw.decompress(compressed);
        assertNotNull(decompressed);
        assertEquals("ababcbc", decompressed);

        String testStr = "This is a test of compression. Just to test if compression works.";
        List<Integer> compressed2 = l.compress(testStr);
        String decompressed2 = lzw.decompress(compressed2);
        assertNotNull(decompressed2);
        assertEquals(testStr, decompressed2);
    }

    @Test
    public void decompressSpecialCaseTest() {
        List<Integer> compressed  = l.compress("abababa");
        assertEquals("abababa", l.decompress(compressed));
    }

    @Test
    public void  decompressInvalidTest() {
        List<Integer> compressed  = l.compress("abababa");
        compressed.add(456);
        assertEquals("-1", l.decompress(compressed));
    }



    int NUM_OF_RUNS = 10, STRING_SIZE_FACTOR = 5;
    int[] DIC_SIZE_IN_BITS = {10, 11, 12};
    double[] LENGTH_FACTOR = {10, 50, 1000}; // array length musst equal NUM_OF_RUNS
    @Test
    public void averageLength() throws IOException {
        for (int bit : DIC_SIZE_IN_BITS) {
            System.out.println("using " + bit + " bits dic");
            int charUpperBound = (int) Math.pow(2, bit) - 1;
            LZW lzw = new LZW(charUpperBound);
            for (double lengthFactor : LENGTH_FACTOR) {
                int strLength = (int)(charUpperBound * lengthFactor);
                int strLengthInBits = strLength * bit;
                System.out.print("average code string length for " + strLengthInBits + " strings is: ");
                int sumCompressedStrBitCount = 0;
                for (int i = 0; i < NUM_OF_RUNS; i++) {
                    String generatedString = generateString(charUpperBound, strLength);
                    sumCompressedStrBitCount += lzw.compress(generatedString).size() * bit+1; // + 1 accounting for the added values to the dic
                    sumCompressedStrBitCount += bit+1; // header
                }
                int average = sumCompressedStrBitCount / NUM_OF_RUNS;
                System.out.print(sumCompressedStrBitCount / NUM_OF_RUNS);
                System.out.printf(" -> %.3f %% reduction\n", (((strLengthInBits - average)/(double)strLengthInBits) * 100));
            }
        }
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "UTF_8_small", // 100 byte
            "UTF_8_medium", // 1000 byte
            "UTF_8_big", // 10000 byte
            "UTF_8_repeating",
            "UTF_16_small",
            "UTF_16_medium",
            "UTF_16_big",
            "UTF_16_repeating",
            "UTF_8_21371-101"
    })
    public void averageLengthNormalTxt(String fileName) throws IOException {

        int bit = Integer.parseInt(fileName.replaceFirst(".*_(.*?)_.*", "$1"));
        //String string = Files.readString(Path.of("Resources/" + fileName + ".txt"));
        InputStream input = new FileInputStream("Resources/" + fileName + ".txt");
        String string = new String(input.readAllBytes());

        System.out.println(fileName + " using " + bit + " bits dic");
        int charUpperBound = (int) Math.pow(2, bit) - 1;
        LZW lzw = new LZW(charUpperBound);
        int strLengthInBits = string.length() * bit;
        System.out.println("text length in bits: " + strLengthInBits);
        int sumCompressedStrBitCount = lzw.compress(string).size() * bit+1; // + 1 accounting for the added values to the dic
        System.out.println("after compression:   " + sumCompressedStrBitCount);
        System.out.printf(" ->                  %.3f %% reduction\n",
                (((strLengthInBits - sumCompressedStrBitCount)/(double)strLengthInBits) * 100));
    }

    private String generateString(int bound, int length) {
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = (int) (random.nextFloat() * (bound + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

}
