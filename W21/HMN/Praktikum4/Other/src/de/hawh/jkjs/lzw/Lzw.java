package de.hawh.jkjs.lzw;

import com.github.jinahya.bit.io.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Lzw {

    private static final int DICTIONARY_LENGTH = 12;

    private static Trie rootTrie;

    public static void encodeDataFile(String fileName, String outFileName) throws IOException {
        rootTrie = new Trie();
        try (InputStream inputStream = new FileInputStream(fileName); OutputStream outputStream = new FileOutputStream(outFileName)) {
            final BitOutput bitOutput = new DefaultBitOutput(new StreamByteOutput(outputStream));
            byte[] buffer = new byte[inputStream.available()];
            int charsRead = inputStream.read(buffer);

            int dicCounter = 255;

            String lastWord;
            String currentWord = "";
            String combinedWord;
            for (byte b : buffer) {
                lastWord = currentWord;
                currentWord = String.valueOf((char) (b & 0xFF));

                combinedWord = lastWord + currentWord;
                if (!rootTrie.includes(combinedWord)) {
                    if (combinedWord.length() > 1) rootTrie.insert(combinedWord, ++dicCounter);
                    if (lastWord.length() > 0) {
                        writeAsciiOrFromTrie(bitOutput, lastWord);
                    }
                } else {
                    // We want to set the lastWord, but lastWord always gets set to the last currentWord. So we set it
                    // to currentWord.
                    currentWord = combinedWord;
                }
            }
            // To write the last byte
            writeAsciiOrFromTrie(bitOutput, currentWord);
            bitOutput.align(1);
        }

    }

    private static void writeAsciiOrFromTrie(BitOutput bitOutput, String lastWord) throws IOException {
        if (lastWord.length() == 1) {
            bitOutput.writeInt(false, DICTIONARY_LENGTH, lastWord.charAt(0));
            //System.out.println(currentWord.charAt(0));
        } else {
            bitOutput.writeInt(false, DICTIONARY_LENGTH, rootTrie.get(lastWord).getNumber());
            //System.out.println(rootTrie.get(currentWord).getNumber());
        }
    }

    public static void decodeLzw(String fileName, String outFileName) throws IOException {
        rootTrie = new Trie();

        Map<Integer, String> codeTable = new HashMap<>();

        try (InputStream inputStream = new FileInputStream(fileName); FileWriter fileWriter = new FileWriter(outFileName)) {
            BitInput bitInput = new DefaultBitInput(new StreamByteInput(inputStream));
            int dicCounter = 255;

            long elementsInFile = new File(fileName).length() * 8 / DICTIONARY_LENGTH;

            int numericalInput;
            String input = "";
            String output;

            for (int i = 0; i < elementsInFile; i++) {
                output = input;
                fileWriter.write(output);

                numericalInput = bitInput.readInt(false, DICTIONARY_LENGTH);
                if (numericalInput < 256) {
                    input = String.valueOf((char) numericalInput);
                } else {
                    if (!codeTable.containsKey(numericalInput)) {
                        input = output + output.charAt(0);
                    } else {
                        input = codeTable.get(numericalInput);
                    }
                }
                if (output.length() > 0) codeTable.put(++dicCounter, output + input.charAt(0));
            }
            fileWriter.write(input);
        }
    }

}
