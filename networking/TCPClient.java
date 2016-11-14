package inClass.inClassFri;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
    private Socket socket = null;
    private DataInputStream inStream = null;
    private DataOutputStream outStream = null;

    public TCPClient() {

    }

    public static void main(String[] args) throws Exception {
        TCPClient client = new TCPClient();
        client.createSocket();
        client.recieveFile();
        /*myChatClient.createReadThread();
        myChatClient.createWriteThread();*/
    }

    public void createSocket() {
        try {
            socket = new Socket("localHost", 4444);
            System.out.println("Connected");
            inStream = new DataInputStream(socket.getInputStream());
            outStream = new DataOutputStream(socket.getOutputStream());

        } catch (UnknownHostException u) {
            u.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void recieveFile() {
        try {
            int fileSize = inStream.readInt();
            byte data[] = new byte[fileSize];
            FileOutputStream fileOut = new FileOutputStream("download.zip", true);

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
}