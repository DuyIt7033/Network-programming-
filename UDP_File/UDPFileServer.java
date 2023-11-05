import java.net.*;
import java.io.*;
import java.util.Date;

class UDPFileServer {
    public static void main(String[] args) {
        try {
            // Create a UDP Socket on port 20233
            DatagramSocket ds = new DatagramSocket(20233);
            // Create a UDP packet for receiving
            byte b[] = new byte[60000];
            DatagramPacket receivePacket = new DatagramPacket(b, 60000);
            while (true) {
                // Receive a request packet
                ds.receive(receivePacket);
                // Process the request
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                int n = receivePacket.getLength();
                String request = new String(b, 0, n);
                String fileName = request.substring(8);
                File file = new File(fileName);
                if (file.isFile() && file.exists()) {
                    int length = (int) file.length();    // Get the file size
                    FileInputStream fileInputStream = new FileInputStream(fileName);
                    byte response[] = new byte[length];
                    fileInputStream.read(response);
                    fileInputStream.close();
                    DatagramPacket responsePacket = new DatagramPacket(response, length, clientAddress, clientPort);
                    ds.send(responsePacket);
                } else {
                    byte response[] = new byte[10];
                    DatagramPacket responsePacket = new DatagramPacket(response, 0, clientAddress, clientPort);
                    ds.send(responsePacket);
                }
            }
        } catch (SocketException e) {
            System.out.println("Failed to initialize the UDP Socket");
        } catch (UnknownHostException e) {
            System.out.println("Invalid address");
        } catch (IOException e) {
            System.out.println("Input/output error");
        }
    }
}
