package fileSeperator;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by bold on 9/15/16.
 */
public class FileDriver {

    public static void main(String[] args) {
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.FILES_ONLY);
        Scanner console = new Scanner(System.in);
        int returnVal = j.showOpenDialog(new JFrame());

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You are partitioning this file: " +
                    j.getSelectedFile().getName());
        }
        System.out.println("Please enter base name for partitioned files.");
        String baseName = console.next();
        FileSeparatorTest t = new FileSeparatorTest(j.getSelectedFile().getAbsolutePath(), baseName, 5, 5);
        try {
            t.partitionFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        console.close();
    }
}
