import java.util.Scanner;
import java.net.*;
import java.io.*;

class FakeWebServer {
    public static void main(String[] args) {
        try {
            // Create a Server Socket on port 80
            ServerSocket serverSocket = new ServerSocket(80);
            System.out.println("Web Server initialized successfully");
            // Accept incoming client connections
            Socket clientSocket = serverSocket.accept();
            System.out.println("A client connected on port " + clientSocket.getPort());
            // Get input and output streams
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            Scanner scanner = new Scanner(inputStream);
            // Receive a GET request from the web client and display it on the screen
            System.out.println("Received a GET request from the client");
            while (true) {
                String requestLine = scanner.nextLine();
                System.out.println(requestLine);
                if (requestLine.isEmpty())
                    break;
            }
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
