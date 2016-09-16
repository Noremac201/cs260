package fileSeperator;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by bold on 9/14/16.
 */
public class FileSeperator {
    private FileInputStream file;
    private int count;
    private byte currentByte;
    private FileOutputStream[] arrOfOuts = new FileOutputStream[5];

    public FileSeperator() {
        int count = 0;
        byte currentByte = -1;
    }

    public void splitFile() {
        try {
            FileInputStream file = new FileInputStream("/home/bold/IdeaProjects/cs260/output.txt");
            arrOfOuts[0] = new FileOutputStream("file0");
            arrOfOuts[1] = new FileOutputStream("file1");
            arrOfOuts[2] = new FileOutputStream("file2");
            arrOfOuts[3] = new FileOutputStream("file3");
            arrOfOuts[4] = new FileOutputStream("file4");


            while ((currentByte = (byte) file.read()) != -1) {
                arrOfOuts[count % 5].write(currentByte);
                count++;
            }
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
