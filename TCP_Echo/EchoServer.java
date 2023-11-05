import java.net.*;
import java.io.*;

class EchoServer {
    public static void main(String[] args) {
        try {
            // Create a Server Socket on port 7
            ServerSocket ss = new ServerSocket(7);
            System.out.println("Server initialized on port 7");
            while (true) {
                // Accept incoming connections
                Socket s = ss.accept();
                System.out.println("   ++ A client is connected");
                // Get input and output streams
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                while (true) { // Serve a single client multiple times
                    // Receive a request from the client (1 character)
                    int ch = is.read();
                    System.out.println("Received: " + (char) ch);
                    // Check the condition to exit
                    if (ch == '@')
                        break;
                    // Process the request
                    int ch1 = ch;
                    // Send the response back to the client (1 character ch1)
                    os.write(ch1);
                }
                // Close the connection
                s.close();
            }
            // Close the Server
            // ss.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}