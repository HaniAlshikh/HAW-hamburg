package de.hawh.JKJSHS.huffman;

import com.github.jinahya.bit.io.*;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitIO {

    @Test
    void writeBits() throws IOException {

        // padding
        //

        final BitOutput output = new DefaultBitOutput(new StreamByteOutput(new FileOutputStream("test")));


        output.writeChar(1, (char) 1); // (char) 1 start of heading
        output.writeBoolean(false);
        output.writeBoolean(false);
        output.writeBoolean(true);           // 1-bit boolean          1    1
        output.writeChar(2, (char) 2); // (char) 2 start of text/encoding
        //output.writeInt(false, 9, -72);       // 9-bit signed int       9   10
        //output.writeBoolean(true);            // 1-bit boolean          1   11
        //output.writeLong(true, 33, 99L);      // 33-bit unsigned long  33   44

        final long padded = output.align(1);  // aligns to (4*8)-bit   20   64
        System.out.println(padded); // 2

        System.out.println((char) 1+"d");
        System.out.println((char) 2+"D");
        System.out.println(Integer.toBinaryString((char) 2));
        //assert padded == 20L;


        System.out.println("--------- input ----------");

        BitInput input = new DefaultBitInput(new StreamByteInput(new FileInputStream("test")));

        System.out.println(input.readChar(1));
        System.out.println(input.readBoolean());
        System.out.println(input.readBoolean());
        System.out.println(input.readBoolean());
        System.out.println(input.readChar(2));
        System.out.println(input.align(1)); //2



        //final boolean b = input.readBoolean();        // 1-bit boolean        1    1
        //final int ui6 = input.readInt(true, 6);       // 6-bit unsigned int   6    7
        //final long sl47 = input.readLong(false, 47);  // 47-bit signed long  47   54
        //final boolean c = input.readBoolean();        // 1-bit boolean        1    1
        //final boolean d = input.readBoolean();        // 1-bit boolean        1    1
        //
        //
        //final long discarded = input.align(1);        // aligns to (1*8)-bit  2   56
        //System.out.println(discarded);

        //assert discarded == 2L;


        //byte[] array = "0".getBytes();
        //System.out.println(array[0]);
        //ByteInput byteInput = new BufferByteInput(() -> ByteBuffer.wrap(array));
        //BitInput bitInput = new BitInputAdapter(() -> byteInput);
        //boolean value = bitInput.readBoolean(); // the left most bit of array[0]
        ////System.out.println(bitInput.readByte8());
        //System.out.println(value);


        //String huffmanCode = "0100"; // lets say its huffman coding output for c
        //
        //BitSet huffmanCodeBit = new BitSet(huffmanCode.length());
        //
        //for (int i = 0; i < huffmanCode.length(); i++) {
        //    if(huffmanCode.charAt(i) == '1')
        //        huffmanCodeBit.set(i);
        //}
        //String path = "test.txt";
        //ObjectOutputStream outputStream = null;
        //try {
        //    outputStream = new ObjectOutputStream(new FileOutputStream(path));
        //    outputStream.writeObject(huffmanCodeBit);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}


        //try (ReadableByteChannel channel = Channels.newChannel(new ByteArrayInputStream("001".getBytes())) ;
        //     ByteInput byteInput = BufferByteInput.from(() -> channel);
        //     BitInput bitInput = new BitInputAdapter(() -> byteInput)) {
        //    int u17 = bitInput.readUnsignedInt(17); // 8 + 8 + 1
        //    // discards 7 bits so that total number of bits aligns to 3 bytes (24 bits)
        //    bitInput.align();
        //    System.out.println(u17);
        //    System.out.println(Integer.toBinaryString(u17));
        //}

    }
}
