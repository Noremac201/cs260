package fileSeperator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FilePartitioner {
    private String fileName;
    private String baseOutputName;
    private int numberOfFiles;
    private FileInputStream fileStream;
    private FileOutputStream[] arrOfOuts = null;
    private int maxByteDifference = 0;

    /**
     * Constructor for the main class FileSeparator.
     *
     * @param fileName          The fileName of the file that is going to be partitioned.
     * @param baseOutputName    The base name of the partitoned file, they will be appended with numbers 0-numFiles.
     * @param maxByteDifference The max number of byte difference allowed/wanted.
     * @param numFiles          number of files to be partitioned into.
     */
    public FilePartitioner(String fileName, String baseOutputName, int maxByteDifference, int numFiles) {
        this.fileName = fileName;
        this.baseOutputName = baseOutputName;
        this.maxByteDifference = maxByteDifference;
        this.numberOfFiles = numFiles;
    }

    /**
     * This class is meant specifically for FileSeparator, that's why it's an inner class. It has two methods,
     * getBytes() and hasData(), and they're both populated by the function FileInputStream.read(byte[], len, off),
     * which populates a byte[] and returns how many bytes were read.
     */
    private class DataBuffer {
        private int returnCode;
        private byte[] readBytes;

        /**
         * @param fileStream     The FileInputStream to read from, or rather the file that is going to be partitoned.
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

    /**
     * The "driver" method of the class, this partitions the files based on what the Class was initialized with.
     *
     * @throws IOException
     */
    public void partitionFile() throws IOException {
        int partitionCounter = 0;
        DataBuffer buffer = null;

        initializeStreams();
        //assignment statement combined with while loop.
        while ((buffer = readFile(maxByteDifference)).hasData()) {
            writeFile(partitionCounter % numberOfFiles, buffer.getBytes());
            partitionCounter++;
        }
        closeStreams();
    }

    /**
     * This writes to all the files.
     *
     * @param partitionNum Which number file to write to.
     * @param byteArr      The byte array that is going to be written to the file.
     * @throws IOException
     */
    private void writeFile(int partitionNum, byte[] byteArr) throws IOException {
        arrOfOuts[partitionNum].write(byteArr);
    }

    /**
     * Reads the file and populates a DataBuffer object, using it's constructor with data from the FileInputStream
     * and based upon the maxByteSizeDifference.
     *
     * @param sizeOfBuffer This is the max byte difference, or how many bytes are to be read in.
     * @return Returns an object that has been populated by calling it's constructor.
     * @throws IOException
     */
    private DataBuffer readFile(int sizeOfBuffer) throws IOException {
        return new DataBuffer(fileStream, maxByteDifference);
    }

    /**
     * It is a private helper method, that should not be accessed outside of this class.
     * Initializes the InputStream and all the output streams that it keeps in an array of FileOutputStreams.
     *
     * @throws FileNotFoundException
     */
    private void initializeStreams() throws FileNotFoundException {
        fileStream = new FileInputStream(fileName);
        arrOfOuts = new FileOutputStream[numberOfFiles];

        for (int i = 0; i < numberOfFiles; i++) {
            arrOfOuts[i] = new FileOutputStream(baseOutputName + i);
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
            arrOfOuts[i].close();
        }
    }

}
