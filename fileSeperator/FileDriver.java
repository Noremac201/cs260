package fileSeperator;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by bold on 9/15/16.
 */
public class FileDriver {
    public static void main(String[] args) {
        new FileMaker().makeFile(349, "output.txt");
        FileSeparator s = new FileSeparator("output.txt");
        s.partitionFile("base", 5, 5);
        //s.writeFile("testout.txt", s.readFile(0,5).);
        //System.out.println(Arrays.toString(s.readFile(0, 5)));
    }
}
