package fileSeperator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by bold on 9/15/16.
 */
public class FileMaker {
    private long sizeOfFile;
    private Random randomizer = new Random();
    private FileOutputStream fileOut;

    public FileMaker(long sizeOfFile) {
        this.sizeOfFile = sizeOfFile;
    }

    //sizeOfFile in bytes
    public void makeFile() {
        try {
            fileOut = new FileOutputStream("output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < sizeOfFile; i++) {
            char c = (char) (randomizer.nextInt(26) + 'a');
            try {
                fileOut.write(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
