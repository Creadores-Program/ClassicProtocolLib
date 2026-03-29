package com.github.steveice10.mc.classic.protocol.packet;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import java.io.IOException;
import java.util.Arrays;

/**
 * Utility class for reading and writing special protocol structures.
 */
public class ClassicPacketUtil {
    private ClassicPacketUtil() {
    }

    /**
     * Reads a string from packet input.
     *
     * @param in Input to read from.
     * @return The resulting string.
     * @throws IOException If an I/O error occurs.
     */
    public static String readString(NetInput in) throws IOException {
        return new String(in.readBytes(64), "UTF-8").trim();
    }

    /**
     * Writes a string to packet output.
     *
     * @param out Output to write to.
     * @param str String to write.
     * @throws IOException If an I/O error occurs.
     */
    public static void writeString(NetOutput out, String str) throws IOException {
        byte[] data = new byte[64];
        Arrays.fill(data, (byte) ' ');
        System.arraycopy(str.getBytes("UTF-8"), 0, data, 0, Math.min(str.length(), data.length));
        out.writeBytes(data);
    }

    /**
     * Reads a byte array from packet input.
     *
     * @param in Input to read from.
     * @return The resulting byte array.
     * @throws IOException If an I/O error occurs.
     */
    public static byte[] readBytes(NetInput in) throws IOException {
        short length = in.readShort();
        byte[] bytes = in.readBytes(1024);
        return Arrays.copyOfRange(bytes, 0, length);
    }

    /**
     * Writes a byte array to packet output.
     *
     * @param out Output to write to.
     * @param b   Byte array to write.
     * @throws IOException If an I/O error occurs.
     */
    public static void writeBytes(NetOutput out, byte[] b) throws IOException {
        out.writeShort(b.length);
        byte[] data = new byte[1024];
        System.arraycopy(b, 0, data, 0, Math.min(b.length, data.length));
        out.writeBytes(data);
    }

    public static int[] readBulkIndices(NetInput in, int len) throws IOException {
        int[] data = in.readInts(256);
        return Arrays.copyOfRange(data, 0, len);
    }

    public static void writeBulkIndices(NetOutput out, int[] i) throws IOException {
        int[] data = new int[256];
        System.arraycopy(i, 0, data, 0, Math.min(i.length, data.length));
    }

    public static byte[] readBulkBlocks(NetInput in, int len) throws IOException {
        byte[] data = in.readBytes(256);
        return Arrays.copyOfRange(data, 0, len);
    }

    public static void writeBulkBlocks(NetOutput out, byte[] b) throws IOException {
        byte[] data = new byte[256];
        System.arraycopy(b, 0, data, 0, Math.min(b.length, data.length));
        out.writeBytes(data);
    }
}
