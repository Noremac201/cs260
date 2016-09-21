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

    //sizeOfFile in bytes
    public void makeFile(long sizeOfFile, String nameOfFile) {
        try {
            fileOut = new FileOutputStream(nameOfFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //randomly generates a-z characters and writes them to file.
            for (int i = 0; i < sizeOfFile; i++) {
                char c = (char) (randomizer.nextInt(26) + 'a');
                fileOut.write(c);
            }
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
