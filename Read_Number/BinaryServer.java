import java.net.*;
import java.io.*;

class BinaryServer {
    public static void main(String[] args) {
        try {
            // Initialize the Server Socket on port 20230
            ServerSocket ss = new ServerSocket(20230);
            System.out.println("Server initialized successfully");
            while (true) { // Serving multiple clients
                try {
                    // Accept incoming connections
                    Socket s = ss.accept();
                    System.out.println("++ A client " + s.getInetAddress() + " on port " + s.getPort() + " connected");
                    // Get input and output streams
                    InputStream is = s.getInputStream();
                    OutputStream os = s.getOutputStream();
                    while (true) {
                        // Receive a request from the client
                        byte b[] = new byte(100);
                        int n = is.read(b);
                        String request = new String(b, 0, n);
                        // Check the condition to exit
                        if (request.equals("EXIT"))
                            break;
                        // Process the request
                        String result = new String();
                        try {
                            int x = Integer.parseInt(request);
                            result = Integer.toBinaryString(x);
                        } catch (NumberFormatException e) {
                            result = "Not an integer";
                        }
                        // Send the result back to the client
                        os.write(result.getBytes());
                    }
                    // Close the connection with the client
                    System.out.println("-- A client " + s.getInetAddress() + " on port " + s.getPort() + " disconnected");
                    s.close();
                } catch (IOException e) {
                    System.out.println("Error while serving a client");
                }
            }
        } catch (IOException e) {
            System.out.println("Error while initializing the Server");
        }
    }
}
