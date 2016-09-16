package fileSeperator;

/**
 * Created by bold on 9/15/16.
 */
public class FileDriver {
    public static void main(String[] args) {
        new FileMaker(1004).makeFile();
        new FileSeperator().splitFile();
        try {
            new FileCombiner().complete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
