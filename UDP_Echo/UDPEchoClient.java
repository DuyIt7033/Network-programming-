import java.net.*;
import java.io.*;
import java.util.Scanner;

class UDPEchoClient {
    public static void main(String[] args) {
        try {
            // Create a UDP Socket for the Client
            DatagramSocket ds = new DatagramSocket();
            while (true) {
                // Enter a string from the keyboard
                Scanner kb = new Scanner(System.in);
                System.out.print("Enter a string: ");
                String str = kb.nextLine();
                // Check the condition to exit
                if (str.equals("EXIT")) break;
                // Create a UDP packet
                byte b[] = str.getBytes();
                int n = b.length;
                InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
                int serverPort = 7;
                DatagramPacket sendPacket = new DatagramPacket(b, n, serverAddress, serverPort);
                // Send the UDP request packet to the Server
                ds.send(sendPacket);
                // Create a packet for receiving
                byte b1[] = new byte[60000];
                DatagramPacket receivePacket = new DatagramPacket(b1, 60000);
                // Receive the response packet from the Server
                ds.receive(receivePacket);
                // Extract data from the packet
                byte b2[] = receivePacket.getData();
                int n2 = receivePacket.getLength();
                String response = new String(b2, 0, n2);
                // Display the response information
                System.out.println("Received: " + response);
            }
            ds.close();
        } catch (SocketException e) {
            System.out.println("Failed to initialize the UDP Socket");
        } catch (UnknownHostException e) {
            System.out.println("Invalid address");
        } catch (IOException e) {
            System.out.println("Input/output error");
        }
    }
}
