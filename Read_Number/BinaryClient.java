import java.util.Scanner;
import java.net.*;
import java.io.*;

class BinaryClient {
    public static void main(String[] args) {
        try {
            // Enter the Server's address
            Scanner kb = new Scanner(System.in);
            System.out.print("Enter the Server's address: ");
            String serverAddress = kb.nextLine();
            // Connect to the Server
            Socket s = new Socket(serverAddress, 20230);
            // Get input and output streams
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            while (true) {
                // Enter an integer (a string)
                System.out.print("Enter an integer: ");
                String str = kb.nextLine();
                // Send the request string to the Server
                os.write(str.getBytes());
                // Check the condition to exit
                if (str.equals("EXIT"))
                    break;
                // Receive the result from the Server
                byte b[] = new byte[1000];
                int n = is.read(b);    // n is the number of bytes received
                // Display the result
                String result = new String(b, 0, n);
                System.out.println("Received: " + result);
            }
            // Close the connection
            s.close();
        } catch (UnknownHostException e) {
            System.out.println("Wrong Server address");
        } catch (IOException e) {
            System.println("Input/output error");
        }
    }
}
