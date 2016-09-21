package fileSeperator;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by bold on 9/15/16.
 */
public class FileCombiner {
    public static void complete() throws Exception {
        FileOutputStream file = null;
        file = new FileOutputStream("tester");

        FileInputStream[] ins = new FileInputStream[5];
        ins[0] = new FileInputStream("file0");
        ins[1] = new FileInputStream("file1");
        ins[2] = new FileInputStream("file2");
        ins[3] = new FileInputStream("file3");
        ins[4] = new FileInputStream("file4");

        int count = 0;
        byte x = -1;

        while ((x = (byte) ins[count % 5].read()) != -1) {
            System.out.println(x);
            file.write(x);
            count++;
        }
        if (file != null) {
            file.close();
        }
    }
}
