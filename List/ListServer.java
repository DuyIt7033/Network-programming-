import java.util.Scanner;
import java.net.*;
import java.io.*;
class ListServer {
    public static void main(String[] args) {
        try {
            // Initialize the Server Socket on port 20239
            ServerSocket ss = new ServerSocket(20239);
            while (true) {    // Serve multiple clients
                // Accept incoming client connection
                Socket s = ss.accept();
                // Get input and output streams
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                // Convert to specialized objects (PrintStream - Scanner)
                Scanner sc = new Scanner(is);
                PrintStream ps = new PrintStream(os);
                // Receive the LIST command
                String command = sc.nextLine();
                // Get the directory to list
                String directory = command.substring(5);
                // Send the result to the client
                File f = new File(directory);
                if (f.isDirectory() && f.exists()) {
                    String[] result = f.list();
                    int n = result.length;
                    ps.println(n);    // Send n as the number of elements
                    for (int i = 0; i < n; i++) {
                        File f2 = new File(directory + "/" + result[i]);
                        if (f2.isFile())
                            ps.println(result[i]);    // Send each name
                        else
                            ps.println("[" + result[i] + "]");
                    }
                } else
                    ps.println("-1");
                // Close the connection
                s.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
