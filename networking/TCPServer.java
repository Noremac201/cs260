package inClass.inClassFri;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private final int bufferSize = 512 * 1024;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private DataInputStream inStream = null;
    private DataOutputStream outStream = null;

    public TCPServer() {

    }

    public static void main(String[] args) throws IOException {
        TCPServer server = new TCPServer();
        server.createSocket();
        server.sendFile();
    }

    public void createSocket() {
        try {
            serverSocket = new ServerSocket(4444);
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
            File file = new File("Big_Data.zip");
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
