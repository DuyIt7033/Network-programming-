import java.net.*;
import java.io.*;
import java.util.Scanner;

class TCPFileClient {
    public static void main(String[] args) {
        try {
            // Enter server information
            Scanner kb = new Scanner(System.in);
            System.out.print("Enter the Server address: ");
            String serverAddress = kb.nextLine();
            System.out.print("Enter the Server port: ");
            int serverPort = kb.nextInt();
            kb.nextLine();    // Clear the Enter key
            System.out.print("Enter the file name on the Server: ");
            String serverFileName = kb.nextLine();
            System.out.print("Enter the local file name for saving the result: ");
            String localFileName = kb.nextLine();
            // Connect to the server
            Socket socket = new Socket(serverAddress, serverPort);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            Scanner scanner = new Scanner(inputStream);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            // Send a request to the server
            String request = "READ " + serverFileName;
            printStream.println(request);
            // Receive data from the server
            String response = scanner.nextLine();
            int size = Integer.parseInt(response);
            if (size == -1)
                System.out.println("File " + serverFileName + " does not exist");
            else if (size == 0)
                System.out.println("File " + serverFileName + " is empty");
            else {
                FileOutputStream fileOutputStream = new FileOutputStream(localFileName);
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
                byte data[] = new byte[10000];
                int length = 0;
                while (true) {
                    int bytesRead = dataInputStream.read(data);    // Read n bytes
                    if (bytesRead > 0) {
                        dataOutputStream.write(data, 0, bytesRead);    // Write n bytes to the file
                        length += bytesRead;
                    }
                    System.out.println("Received " + length + " bytes");
                    if (length == size)
                        break;
                }
                fileOutputStream.close();    // Close the result file
                System.out.println("File has been successfully written");
            }
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
