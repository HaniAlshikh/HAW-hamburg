package de.alshikh.haw.klausur.SS2018.classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class A03 {

    public static int adornoWinner(Path file) {
        int lineNum = -1;
        int position = 0;

        try(Stream<String> lines = Files.lines(file)) {
            String[] linesArr =  lines.toArray(String[]::new);
            for (int j = 0; j < linesArr.length; j++) {
                String[] words = linesArr[j].split( "\\s+");
                for (int i = 0; i < words.length; i++) {
                    if (words[i].toLowerCase().equals("sich") && i >= position) {
                        position = i;
                        lineNum = j;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineNum;
    }
}
