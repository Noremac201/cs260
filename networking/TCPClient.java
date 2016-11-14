import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;

public class TCPClient {
    private int counter = 0;

    public TCPClient() throws Exception {
        doYaThang();
    }

    public void doYaThang() throws Exception {
        ClientThread t0 = new ClientThread(3333, 0);
        ClientThread t1 = new ClientThread(3334, 1);
        ClientThread t2 = new ClientThread(3335, 2);
        ClientThread t3 = new ClientThread(3336, 3);
        ClientThread t4 = new ClientThread(3337, 4);

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    public class ClientThread extends Thread {
        private Socket socket = null;
        private DataInputStream inStream = null;

        private int port;
        private int fileNum;

        public ClientThread(int port, int fileNum) {
            this.port = port;
            this.fileNum = fileNum;
        }

        public void run() {
            createSocket(port);
            receiveFile("part" + fileNum + ".txt");
            try {
                mergePart();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void createSocket(int port) {
            try {
                socket = new Socket("localHost", port);
                System.out.println("Connected");
                inStream = new DataInputStream(socket.getInputStream());

            } catch (UnknownHostException u) {
                u.printStackTrace();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }

        public void receiveFile(String fileName) {
            try {
                int fileSize = inStream.readInt();
                byte data[] = new byte[fileSize];
                FileOutputStream fileOut = new FileOutputStream(fileName);

                int totalBytes = inStream.read(data, 0, fileSize);
                fileOut.write(data);
                fileOut.flush();
                System.out.println("Filesize is " + fileSize + ", bytes read: " + totalBytes);
                socket.close();
                inStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void mergePart() throws IOException {
            FileChannel in = new FileInputStream(new File("part" + fileNum + ".txt")).getChannel();
            FileChannel out = new FileOutputStream("final.txt", true).getChannel();
            while (fileNum != counter) {
                System.out.println("Waiting to write to file...");
            }
            out.transferFrom(in, 0, in.size()); //copies text from one file to another
            counter++;
        }
    }
}
