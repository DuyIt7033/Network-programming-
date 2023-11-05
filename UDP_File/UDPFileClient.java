import java.net.*;
import java.io.*;
import java.util.Scanner;

class UDPFileClient {
    public static void main(String[] args) {
        try {
            // Create a UDP Socket for the Client
            DatagramSocket ds = new DatagramSocket();
            // Enter the Server address and file names from the keyboard
            Scanner kb = new Scanner(System.in);
            System.out.print("Enter the Server address: ");
            String serverAddress = kb.nextLine();
            System.out.print("Enter the file name on the Server to retrieve: ");
            String serverFileName = kb.nextLine();
            System.out.print("Enter the file name to save: ");
            String localFileName = kb.nextLine();
            // Create a request message
            String request = "READUDP " + serverFileName;
            byte b[] = request.getBytes();
            int n = b.length;
            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);
            int serverPort = 20233;
            DatagramPacket sendPacket = new DatagramPacket(b, n, serverInetAddress, serverPort);
            // Send the UDP request packet to the Server
            ds.send(sendPacket);
            // Create a packet for receiving
            byte b1[] = new byte[60000];
            DatagramPacket receivePacket = new DatagramPacket(b1, 60000);
            // Receive the response packet from the Server
            ds.receive(receivePacket);
            // Extract data from the packet
            byte responseData[] = receivePacket.getData();
            int responseDataLength = receivePacket.getLength();
            if (responseDataLength == 0)
                System.out.println("File is empty or does not exist");
            else {
                FileOutputStream fileOutputStream = new FileOutputStream(localFileName);
                fileOutputStream.write(responseData, 0, responseDataLength);
                fileOutputStream.close();
                System.out.println("File has been successfully written");
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
