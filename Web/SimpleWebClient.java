import java.util.Scanner;
import java.net.*;
import java.io.*;

class SimpleWebClient {
    public static void main(String[] args) {
        try {
            // Enter the Web Server address
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter the Web Server address: ");
            String serverAddress = keyboard.nextLine();
            // Enter the resource name (http)
            System.out.print("Enter the resource name to retrieve: ");
            String resource = keyboard.nextLine();
            // Connect to the Web Server
            Socket socket = new Socket(serverAddress, 80);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            // Send a GET request to the Web Server
            printStream.println("GET /" + resource + " HTTP/1.1");
            printStream.println("Host: " + serverAddress);
            printStream.println("User-Agent: Mozilla/5.0 (Windows NT 10.0; rv:109.0) Gecko/20100101 Firefox/117.0");
            printStream.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            printStream.println("Accept-Language: en-US,en;q=0.5");
            printStream.println("Accept-Encoding: gzip, deflate, br");
            printStream.println("Connection: keep-alive");
            printStream.println("Upgrade-Insecure-Requests: 1");
            printStream.println("Sec-Fetch-Dest: document");
            printStream.println("Sec-Fetch-Mode: navigate");
            printStream.println("Sec-Fetch-Site: cross-site");
            printStream.println();
            // Receive and display the returned result
            while (true) {
                int character = inputStream.read();
                if (character == -1)
                    break;
                System.out.print((char) character);
            }
            // Close the connection
            socket.close();
        } catch (UnknownHostException e) {
            System.out.println("Invalid address");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
