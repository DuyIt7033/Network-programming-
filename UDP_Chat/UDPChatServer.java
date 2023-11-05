import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class UDPChatServer {
    private static List<InetSocketAddress> clients = new ArrayList<>();

    public static void main(String[] args) {
        DatagramSocket serverSocket = null;

        try {
            serverSocket = new DatagramSocket(9876);
            System.out.println("Server is running...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetSocketAddress clientAddress = new InetSocketAddress(receivePacket.getAddress(), receivePacket.getPort());

                if (!clients.contains(clientAddress)) {
                    clients.add(clientAddress);
                }

                System.out.println("Received from " + clientAddress + ": " + message);

                // Broadcast the message to all clients except the sender
                for (InetSocketAddress client : clients) {
                    if (!client.equals(clientAddress)) {
                        DatagramPacket sendPacket = new DatagramPacket(
                                message.getBytes(),
                                message.getBytes().length,
                                client.getAddress(),
                                client.getPort()
                        );
                        serverSocket.send(sendPacket);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        }
    }
}