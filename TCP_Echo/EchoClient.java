import java.util.Scanner;
import java.net.*;
import java.io.*;

class EchoClient {
    public static void main(String[] args) {
        try {
            // Connect to the Echo Server on port 7
            Scanner kb = new Scanner(System.in);
            Socket s = new Socket("127.0.0.1", 7);
            System.out.println("Connected to the Server");
            // Get input and output streams
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            while (true) {
                // Enter a character from the keyboard
                System.out.print("Enter a character: ");
                String str = kb.nextLine();
                int ch = (int) str.charAt(0);
                // Send the character 'ch' to the Server
                os.write(ch);
                // Check the condition to exit
                if (ch == '@')
                    break;
                // Receive the result 'ch1' from the Server
                int ch1 = is.read();
                // Display the result
                System.out.println("Received: " + (char) ch1);
            }
            // Close the connection
            s.close();
        } catch (UnknownHostException e) {
            System.out.println("Wrong Server address");
        } catch (IOException e) {
            System.out.println("Error in sending/receiving");
        }
    }
}
