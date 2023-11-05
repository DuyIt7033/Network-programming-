import java.net.*;
import java.io.*;
import java.util.Scanner;

class TCPFileServer {
    public static void main(String[] args) {
        try {
            // Create a ServerSocket
            ServerSocket serverSocket = new ServerSocket(6868);
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                PrintStream printStream = new PrintStream(outputStream);
                Scanner scanner = new Scanner(inputStream);
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                // Receive a command
                String command = scanner.nextLine();
                String fileName = command.substring(5);
                // Check the file
                File file = new File(fileName);
                if (file.isFile() && file.exists()) {
                    int size = (int) file.length();
                    printStream.println(size);    // Send the file size
                    // Send the file content
                    byte data[] = new byte[size];
                    FileInputStream fileInputStream = new FileInputStream(fileName);
                    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                    dataInputStream.readFully(data);    // Read the entire file content
                    dataOutputStream.write(data);        // Send the entire byte array
                    fileInputStream.close();            // Close the file
                } else
                    printStream.println("-1");
                socket.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
