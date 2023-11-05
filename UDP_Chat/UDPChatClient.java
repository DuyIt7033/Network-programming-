import java.net.*;
import java.util.Scanner;

public class UDPChatClient {
    public static void main(String[] args) {
        final DatagramSocket clientSocket;
        Scanner scanner = new Scanner(System.in);

        try {
            clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            // Start a thread for receiving and displaying messages from the server
            Thread receiveThread = new Thread(() -> {
                try {
                    byte[] receiveData = new byte[1024];
                    while (true) {
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        clientSocket.receive(receivePacket);
                        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        System.out.println("Server: " + message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            // Start a thread for sending messages to the server
            Thread sendThread = new Thread(() -> {
                try {
                    while (true) {
                        System.out.print("You: ");
                        String message = scanner.nextLine();
                        byte[] sendData = message.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData,
                                sendData.length,
                                serverAddress,
                                serverPort
                        );
                        clientSocket.send(sendPacket);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            sendThread.start();

            // Wait for both threads to finish
            receiveThread.join();
            sendThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

