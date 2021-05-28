/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evnit.util.common;

/**
 *
 * @author khiemvk
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Work with files in file system (delete, get size, etc...).
 *
 * @author   Alexander Feldman (updated by $Author: feldman $)
 * @version  $Revision: 1.11 $ $Date: 2002/05/02 14:41:53 $
 *
 */
public class FileUtils {
    //public FileUtils(){}

    public static void deleteFile(String fileName)
            throws IOException {        
        if (fileName == null) {
            return;
        }
        File theFile = new File(fileName);

        // Check if a file exists.
        if (theFile.exists()) {
            theFile.delete();
        }
    }

    /**
     * Calls writeToFile with createDir flag false.
     *
     * @see writeToFile(String fileName, InputStream iStream, boolean createDir)
     *
     * @created 2002-05-02 by Alexander Feldman
     *
     */
    public static void writeToFile(String fileName, InputStream iStream)
            throws IOException {
        writeToFile(fileName, iStream, false);
    }

    /**
     * Writes InputStream to a given <code>fileName<code>.
     * And, if directory for this file does not exists,
     * if createDir is true, creates it, otherwice throws OMDIOexception.
     *
     * @param fileName - filename save to.
     * @param iStream  - InputStream with data to read from.
     * @param createDir (false by default)
     * @throws IOException in case of any error.
     *
     * @refactored 2002-05-02 by Alexander Feldman
     * - moved from OMDBlob.
     *
     */
    public static void writeToFile(String fileName, InputStream iStream,
            boolean createDir)
            throws IOException {
        String me = "FileUtils.WriteToFile";
        if (fileName == null) {
            throw new IOException(me + ": filename is null");
        }
        if (iStream == null) {
            throw new IOException(me + ": InputStream is null");
        }

        File theFile = new File(fileName);

        // Check if a file exists.
        if (theFile.exists()) {
            String msg =
                    theFile.isDirectory() ? "directory"
                    : (!theFile.canWrite() ? "not writable" : null);
            if (msg != null) {
                theFile.setWritable(true);
                theFile.setExecutable(true);
                //throw new IOException(me + ": file '" + fileName + "' is " + msg);
            }
        }

        // Create directory for the file, if requested.
        if (createDir && theFile.getParentFile() != null) {
            theFile.getParentFile().mkdirs();
        }

        // Save InputStream to the file.
        BufferedOutputStream fOut = null;
        try {
            fOut = new BufferedOutputStream(new FileOutputStream(theFile));
            byte[] buffer = new byte[32 * 1024];
            int bytesRead = 0;
            while ((bytesRead = iStream.read(buffer)) != -1) {
                fOut.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            throw new IOException(me + " failed, got: " + e.toString());
        } finally {
            fOut.close();
        }
    }

    /**
     * Closes InputStream and/or OutputStream.
     * It makes sure that both streams tried to be closed,
     * even first throws an exception.
     *
     * @throw IOException if stream (is not null and) cannot be closed.
     *
     */
    protected static void close(InputStream iStream, OutputStream oStream)
            throws IOException {
        try {
            if (iStream != null) {
                iStream.close();
            }
        } finally {
            if (oStream != null) {
                oStream.close();
            }
        }
    }

    public static void copyFile(String from, String to) throws IOException {
        File fromFile = new File(from);
        File toFile = new File(to);

        writeFile(readFile(fromFile), toFile);
    }

    /**
     * Read the contents of the specified file.
     * @param file The file to read.
     * @return The file contents.
     * @throws IOException Error readiong file.
     */
    public static byte[] readFile(File file) throws IOException {


        if(!file.exists()) {
            throw new IllegalArgumentException("No such file '" + file.getAbsoluteFile() + "'.");
        } else if(file.isDirectory()) {
            throw new IllegalArgumentException("File '" + file.getAbsoluteFile() + "' is a directory.  Cannot read.");
        }

        InputStream stream = new FileInputStream(file);
        try {
            return StreamUtils.readStream(stream);
        } finally {
            stream.close();
        }
    }

    public static void writeFile(byte[] bytes, File file) throws IOException {
        if(file.isDirectory()) {
            throw new IllegalArgumentException("File '" + file.getAbsoluteFile() + "' is an existing directory.  Cannot write.");
        }

        FileOutputStream stream = new FileOutputStream(file);
        try {
            stream.write(bytes);
            stream.flush();
        } finally {
            stream.close();
        }
    }

    public static void writeToFile(String fileName, byte[] bytes,
            boolean createDir)
            throws IOException {
        String me = "FileUtils.WriteToFile";
        if (fileName == null) {
            throw new IOException(me + ": filename is null");
        }

        File theFile = new File(fileName);

        // Check if a file exists.
        if (theFile.exists()) {
            String msg =
                    theFile.isDirectory() ? "directory"
                    : (!theFile.canWrite() ? "not writable" : null);
            if (msg != null) {
                theFile.setWritable(true);
                theFile.setExecutable(true);
            }
        }

        // Create directory for the file, if requested.
        if (createDir && theFile.getParentFile() != null) {
            theFile.getParentFile().mkdirs();
        }

        // Save to the file.
        FileOutputStream stream = new FileOutputStream(theFile);
        try {
            stream.write(bytes);
            stream.flush();
        } finally {
            stream.close();
        }
    }
}
