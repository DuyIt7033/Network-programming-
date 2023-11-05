import java.util.Scanner;
import java.net.*;
import java.io.*;

class ListClient {
    public static void main(String[] args) {
        try {
            // Enter the Server's address
            Scanner kb = new Scanner(System.in);
            System.out.print("Enter the Server's address: ");
            String serverAddress = kb.nextLine();
            // Connect to the Server
            Socket s = new Socket(serverAddress, 20239);
            // Get input and output streams
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            // Convert to specialized objects (PrintStream - Scanner)
            Scanner sc = new Scanner(is);
            PrintStream ps = new PrintStream(os);
            // Enter the directory name (on the Server) to list
            System.out.print("Enter the directory name to list: ");
            String directory = kb.nextLine();
            // Send the "LIST" command
            String command = "LIST " + directory;
            ps.println(command);
            // Receive 'n' as the number of elements
            String str = sc.nextLine();
            int n = Integer.parseInt(str);
            if (n == -1)
                System.out.println("The directory " + directory + " does not exist");
            else if (n == 0)
                System.out.println("The directory " + directory + " is empty");
            else {
                // Receive the next 'n' lines
                for (int i = 0; i < n; i++) {
                    String result = sc.nextLine();
                    System.out.println(result);
                }
            }
            // Close the connection
            s.close();
        } catch (UnknownHostException e) {
            System.out.println("Wrong server address");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
