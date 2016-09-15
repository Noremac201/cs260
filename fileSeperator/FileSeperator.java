package fileSeperator;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by bold on 9/14/16.
 */
public class FileSeperator {
    public static void complete() throws Exception {
        FileInputStream file = null;
        file = new FileInputStream("fileName.txt");
        FileOutputStream[] outs = new FileOutputStream[5];
        outs[0] = new FileOutputStream("file0");
        outs[1] = new FileOutputStream("file1");
        outs[2] = new FileOutputStream("file2");
        outs[3] = new FileOutputStream("file3");
        outs[4] = new FileOutputStream("file4");
        int count = 0;

        byte x = -1;
        x = (byte) file.read();
        while (x != -1) {
            outs[count % 5].write(x);
            x = (byte) file.read();
            count++;
        }
        if (file != null) {
            file.close();
        }
    }

    public static void main(String[] args) throws Exception {
        complete();

    }
}
