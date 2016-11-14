package fileSeperator;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by bold on 9/15/16.
 */
public class FileDriver {
    //these refer to bytes in a mB/kB.
    private static final int mB = 1000000;
    private static final int kB = 1000;

    public static void main(String[] args) {
        //this just makes a file.
        new FileMaker().makeFile(123560, "input");

        FileCombiner comb = new FileCombiner("combined", "output", 5, 5);

        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "/IdeaProjects/cs260");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        Scanner console = new Scanner(System.in);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            if (fileChooser.getSelectedFile().length() > 100 * mB ||
                    fileChooser.getSelectedFile().length() < kB) {
                System.err.println("File is out of range (1kB - 100mB).");
                System.exit(10);
            }

            System.out.println("You are partitioning this file: " +
                    fileChooser.getSelectedFile().getName());
        }
        System.out.print("Please enter base name for partitioned files: ");
        String baseName = console.next();

        FilePartitioner filePartitioner = new FilePartitioner(fileChooser
                .getSelectedFile().getAbsolutePath(), baseName, 5, 5);
        try {
            filePartitioner.partitionFile();
            comb.combineFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        console.close();
    }
}
