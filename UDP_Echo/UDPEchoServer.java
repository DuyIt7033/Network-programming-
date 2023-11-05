import java.net.*;
import java.io.*;

class UDPEchoServer {
    public static void main(String[] args) {
        try {
            // Create a UDP Socket on port 7
            DatagramSocket ds = new DatagramSocket(7);
            // Create a UDP packet for receiving
            byte b[] = new byte[60000];
            DatagramPacket receivePacket = new DatagramPacket(b, 60000);
            while (true) {
                // Receive a request packet
                ds.receive(receivePacket);
                // Process the request
                byte b1[] = receivePacket.getData();
                int n1 = receivePacket.getLength();
                String request = new String(b1, 0, n1);
                String response = request.toUpperCase();
                // Create a packet for the response
                byte b2[] = response.getBytes();
                int n2 = n1;
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket responsePacket = new DatagramPacket(b2, n2, clientAddress, clientPort);
                // Send the response packet to the client
                ds.send(responsePacket);
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
