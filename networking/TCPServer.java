import com.sun.corba.se.spi.activation.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public TCPServer() throws Exception {
        FilePartitioner f = new FilePartitioner("input.txt", 5);
        f.createPartitions();
        doYaThang();
    }

    public void doYaThang() throws Exception {
        ServerThread t0 = new ServerThread(3333, "out0.txt");
        ServerThread t1 = new ServerThread(3334, "out1.txt");
        ServerThread t2 = new ServerThread(3335, "out2.txt");
        ServerThread t3 = new ServerThread(3336, "out3.txt");
        ServerThread t4 = new ServerThread(3337, "out4.txt");

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    public class ServerThread extends Thread {
        private final int bufferSize = 512 * 1024;
        private ServerSocket serverSocket = null;
        private Socket socket = null;
        private DataInputStream inStream = null;
        private DataOutputStream outStream = null;
        private int[] ports = new int[5];

        int port;
        String fileName;

        public ServerThread(int port, String fileName) {
            this.port = port;
            this.fileName = fileName;
        }

        public void run() {
            createSocket();
            sendFile();
        }

        public void createSocket() {
            try {
                serverSocket = new ServerSocket(port);
                socket = serverSocket.accept();

                inStream = new DataInputStream(socket.getInputStream());
                outStream = new DataOutputStream(socket.getOutputStream());
                System.out.println("Connected");

            } catch (IOException io) {
                io.printStackTrace();
            }
        }

        public void sendFile() {
            try {
                File file = new File(fileName);
                FileInputStream fileInput = new FileInputStream(file);
                int fileSize = (int) file.length();

                byte[] data = new byte[(int) fileSize];

                fileInput.read(data);

                outStream.writeInt(fileSize);
                outStream.flush();

                outStream.write(data);
                outStream.flush();
                fileInput.close();
                serverSocket.close();
                socket.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
