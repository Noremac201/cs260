package fileSeperator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by bold on 9/26/16.
 */
public class FileCombiner {
    private String fileName;
    private String baseOutputName;
    private int numberOfFiles;
    private FileOutputStream fileStream;
    private FileInputStream[] arrOfIns = null;
    private int maxByteDifference = 0;

    /**
     * Constructor for the main class FileSeparator.
     *
     * @param baseFileName      The fileName of the file that is going to be partitioned.
     * @param outputName        The base name of the partitoned file, they will be
     *                          appended with
     *                          numbers 0-numFiles.
     * @param maxByteDifference The max number of byte difference allowed/wanted.
     * @param numFiles          number of files to be partitioned into.
     */
    public FileCombiner(String baseFileName, String outputName, int maxByteDifference, int numFiles) {
        this.fileName = baseFileName;
        this.baseOutputName = outputName;
        this.maxByteDifference = maxByteDifference;
        this.numberOfFiles = numFiles;
    }

    /**
     * The "driver" method of the class, this partitions the files based on what the Class
     * was initialized with.
     *
     * @throws IOException
     */
    public void combineFile() throws IOException {
        int partitionCounter = 0;
        DataBuffer buffer = null;

        initializeStreams();
        //assignment statement combined with while loop.
        while ((buffer = readFile(maxByteDifference, partitionCounter++ % numberOfFiles)).hasData()) {
            writeFile(buffer.getBytes());
        }
        closeStreams();
    }

    /**
     * This writes to all the files.
     *
     * @param byteArr The byte array that is going to be written to the file.
     * @throws IOException
     */
    private void writeFile(byte[] byteArr) throws IOException {
        fileStream.write(byteArr);
    }

    /**
     * Reads the file and populates a DataBuffer object, using it's constructor with data from the
     * FileInputStream
     * and based upon the maxByteSizeDifference.
     *
     * @param sizeOfBuffer This is the max byte difference, or how many bytes are to be read in.
     * @return Returns an object that has been populated by calling it's constructor.
     * @throws IOException
     */
    private DataBuffer readFile(int sizeOfBuffer, int partitionNumber) throws IOException {
        return new DataBuffer(arrOfIns[partitionNumber], maxByteDifference);
    }

    /**
     * It is a private helper method, that should not be accessed outside of this class.
     * Initializes the InputStream and all the output streams that it keeps in an array
     * of FileOutputStreams.
     *
     * @throws FileNotFoundException
     */
    private void initializeStreams() throws FileNotFoundException {
        fileStream = new FileOutputStream(fileName);
        arrOfIns = new FileInputStream[numberOfFiles];

        for (int i = 0; i < numberOfFiles; i++) {
            arrOfIns[i] = new FileInputStream(baseOutputName + i);
        }
    }

    /**
     * It is a private helper method, that should not be accessed outside of this class.
     * Closes the input stream and all outputStreams.
     *
     * @throws IOException
     */
    private void closeStreams() throws IOException {
        fileStream.close();
        for (int i = 0; i < numberOfFiles; i++) {
            arrOfIns[i].close();
        }
    }

    /**
     * This class is meant specifically for FileSeparator, that's why it's an inner class.
     * It has two methods, getBytes() and hasData(), and they're both populated by the function
     * FileInputStream.read(byte[], len, off), which populates a byte[] and returns how many bytes
     * were read.
     */
    private class DataBuffer {
        private int returnCode;
        private byte[] readBytes;

        /**
         * @param fileStream     The FileInputStream to read from, or rather the file that is
         *                       going to be partitoned.
         * @param numBytesToRead This is how many bytes will be read into the byte array.
         * @throws IOException
         **/
        public DataBuffer(FileInputStream fileStream, int numBytesToRead) throws IOException {
            this.readBytes = new byte[numBytesToRead];
            this.returnCode = fileStream.read(readBytes, 0, numBytesToRead);
        }

        //returns what bytes were read from the file.
        public byte[] getBytes() {
            //returns a copy of the array, which may be resized to ignore null bytes when the byte[]
            // is not completely filled.
            return Arrays.copyOf(readBytes, returnCode);
        }

        //if returnCode is less than 0 it has reached EOF (-1).
        public boolean hasData() {
            return this.returnCode > 0;
        }
    }

}
