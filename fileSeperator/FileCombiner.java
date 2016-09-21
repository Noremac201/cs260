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
        ins[0] = new FileInputStream("base0");
        ins[1] = new FileInputStream("base1");
        ins[2] = new FileInputStream("base2");
        ins[3] = new FileInputStream("base3");
        ins[4] = new FileInputStream("base4");

        int count = 0;
        byte x = -1;

        file.write(ins[count % 5].read());

        while (x != -1) {
            System.out.println(x);
            file.write(ins[count % 5].read());
            file.write(ins[count % 5].read());
            file.write(ins[count % 5].read());
            file.write(ins[count % 5].read());
            file.write(ins[count % 5].read());

            count++;
        }
        if (file != null) {
            file.close();
        }
    }
}
