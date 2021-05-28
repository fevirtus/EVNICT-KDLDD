/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evnit.util.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;

/**
 *
 * @author khiemvk
 */
public class StreamUtils {

    /**
     * Read the supplied InputStream and return as a byte array.
     *
     * @param stream
     *            The stream to read.
     * @return byte array containing the Stream data.
     * @throws IOException
     *             Exception reading from the stream.
     */
    public static byte[] readStream(InputStream stream) throws IOException {
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        byte[] byteBuf = new byte[1024];
        int readCount = 0;

        while ((readCount = stream.read(byteBuf)) != -1) {
            bytesOut.write(byteBuf, 0, readCount);
        }

        return bytesOut.toByteArray();
    }

    /**
     * Read the supplied InputStream and return as a byte array.
     *
     * @param stream
     *            The stream to read.
     * @return A String containing the Stream data.
     * @throws IOException
     *             Exception reading from the stream.
     */
    public static String readStreamAsString(InputStream stream) throws IOException {


        return new String(readStream(stream));
    }

    public static byte[] readFile(File file) throws IOException {

        InputStream stream = new FileInputStream(file);
        try {
            return readStream(stream);
        } finally {
            stream.close();
        }
    }

    public static void writeFile(File file, byte[] data) throws IOException {

        OutputStream stream = new FileOutputStream(file);
        try {
            stream.write(data);
        } finally {
            try {
                stream.flush();
            } finally {
                stream.close();
            }
        }
    }

    public static String readStream(Reader stream) throws IOException {

        StringBuffer streamString = new StringBuffer();
        char[] readBuffer = new char[256];
        int readCount = 0;

        while ((readCount = stream.read(readBuffer)) != -1) {
            streamString.append(readBuffer, 0, readCount);
        }

        return streamString.toString();
    }

    /**
     * Compares the 2 streams.
     * <p/>
     * Calls {@link #trimLines(InputStream)} on each stream before comparing.
     * @param s1 Stream 1.
     * @param s2 Stream 2.
     * @return True if the streams are equal not including leading and trailing
     * whitespace on each line and blank lines, otherwise returns false.
     */
    public static boolean compareCharStreams(InputStream s1, InputStream s2) {
        StringBuffer s1Buf, s2Buf;

        try {
            s1Buf = trimLines(s1);
            s2Buf = trimLines(s2);

            return s1Buf.toString().equals(s2Buf.toString());
        } catch (IOException e) {
            // fail the comparison
        }

        return false;
    }

    /**
     * Compares the 2 streams.
     * <p/>
     * Calls {@link #trimLines(java.io.Reader)} on each stream before comparing.
     * @param s1 Stream 1.
     * @param s2 Stream 2.
     * @return True if the streams are equal not including leading and trailing
     * whitespace on each line and blank lines, otherwise returns false.
     */
    public static boolean compareCharStreams(Reader s1, Reader s2) {
        StringBuffer s1Buf, s2Buf;

        try {
            s1Buf = trimLines(s1);
            s2Buf = trimLines(s2);

            return s1Buf.toString().equals(s2Buf.toString());
        } catch (IOException e) {
            // fail the comparison
        }

        return false;
    }

    /**
     * Compares the 2 streams.
     * <p/>
     * Calls {@link #trimLines(java.io.Reader)} on each stream before comparing.
     * @param s1 Stream 1.
     * @param s2 Stream 2.
     * @return True if the streams are equal not including leading and trailing
     * whitespace on each line and blank lines, otherwise returns false.
     */
    public static boolean compareCharStreams(String s1, String s2) {
        return compareCharStreams(new StringReader(s1), new StringReader(s2));
    }

    /**
     * Read the lines lines of characters from the stream and trim each line
     * i.e. remove all leading and trailing whitespace.
     * @param charStream Character stream.
     * @return StringBuffer containing the line trimmed stream.
     * @throws IOException
     */
    public static StringBuffer trimLines(Reader charStream) throws IOException {
        StringBuffer stringBuf = new StringBuffer();
        BufferedReader reader = new BufferedReader(charStream);
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuf.append(line.trim());
        }

        return stringBuf;
    }

    /**
     * Read the lines lines of characters from the stream and trim each line
     * i.e. remove all leading and trailing whitespace.
     * @param charStream Character stream.
     * @return StringBuffer containing the line trimmed stream.
     * @throws IOException
     */
    public static StringBuffer trimLines(InputStream charStream) throws IOException {
        return trimLines(new InputStreamReader(charStream, "UTF-8"));
    }

    public static void close(InputStream iStream) {
        try {
            if (iStream != null) {
                iStream.close();
            }
        } catch(IOException ioe) {
        }
    }
}
