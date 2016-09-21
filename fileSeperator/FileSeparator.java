package fileSeperator;

import java.io.*;
import java.util.Arrays;

/**
 * Created by bold on 9/14/16.
 */
public class FileSeparator {
    private File inputFile = null;
    private FileInputStream fileStream;
    private FileData fileData;

    public FileSeparator(String fileName) {
        try {
            this.inputFile = new File(fileName);
            this.fileStream = new FileInputStream(inputFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public class FileData {
        private int returnCode;
        private byte[] returnBytes;

        public FileData(int sizeOfArr) {
            this.returnBytes = new byte[sizeOfArr];
        }

        public byte[] getReturnBytes() {
            return this.returnBytes;
        }

        public void setReturnBytes(byte[] returnBytes) {
            this.returnBytes = returnBytes;
        }

        public int getReturnCode() {
            return this.returnCode;
        }

        public void setReturnCode(int returnCode) {
            this.returnCode = returnCode;
        }
    }

    public void partitionFile(String basePartitionedFileName, int numberOfPartitions, int maxByteDifference) {
        int timesRead = 0;
        int partitionCounter = 0;
        FileData z = readFile(timesRead, maxByteDifference);

        while (z.getReturnCode() != -1) {
            writeFile(basePartitionedFileName + (partitionCounter % numberOfPartitions), z.getReturnBytes());

            if (z.getReturnCode() < maxByteDifference) {
                writeFile(basePartitionedFileName + (partitionCounter % numberOfPartitions), Arrays.copyOf(z.getReturnBytes(), z.getReturnCode()));
            }

            z = readFile(timesRead, maxByteDifference);
            partitionCounter++;
        }
    }

    public void writeFile(String fileName, byte[] inputBytes) {
        try {
            FileOutputStream fileStreamOut = new FileOutputStream(new File(fileName), true);
            fileStreamOut.write(inputBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileData readFile(int partOfFile, int sizeOfBuffer) {
        byte[] readBytes = new byte[sizeOfBuffer];
        fileData = new FileData(sizeOfBuffer);

        try {
            fileData.setReturnCode(fileStream.read(readBytes, partOfFile * sizeOfBuffer, sizeOfBuffer));
            fileData.setReturnBytes(readBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }
}
