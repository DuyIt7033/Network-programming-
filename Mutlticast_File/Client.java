import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            MulticastSocket socket = new MulticastSocket(23456);
            InetAddress group = InetAddress.getByName("231.2.3.4");
            socket.joinGroup(group);

            boolean receiveFile = false;
            FileOutputStream fileOutputStream = null;
            String fileName = "receive.txt";

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);

                socket.receive(packet);

                if (packet.getLength() == 0) {
                    if (receiveFile) {
                        System.out.println("File oke.");
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                            fileOutputStream = null;
                        }
                    }
                    receiveFile = true;
                } else if (receiveFile) {
                    if (fileOutputStream == null) {
                        fileOutputStream = new FileOutputStream(fileName);
                    }

                    if (fileOutputStream != null) {
                        fileOutputStream.write(packet.getData(), 0, packet.getLength());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
