import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress group = InetAddress.getByName("231.2.3.4");
            int port = 23456;
            String fileName;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Nhap ten tep tin can gui: ");
            fileName = reader.readLine();
            File file = new File(fileName);

            if (!file.exists()) {
                System.out.println("Tep tin khong ton tai.");
                return;
            }

            byte[] fileData = new byte[(int) file.length()];
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(fileData);
            fileInputStream.close();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        int packetSize = 1024; // Do dai goi du lieu
                        int numPackets = (int) Math.ceil((double) fileData.length / packetSize);
                        int start = 0;
                        int end = packetSize;

                        for (int i = 0; i < numPackets; i++) {
                            if (end > fileData.length) {
                                end = fileData.length;
                            }

                            byte[] packetData = new byte[end - start];
                            System.arraycopy(fileData, start, packetData, 0, end - start);

                            DatagramPacket packet = new DatagramPacket(packetData, packetData.length, group, port);
                            socket.send(packet);

                            start = end;
                            end += packetSize;
                        }

                        //Ket thuc 
                        DatagramPacket endPacket = new DatagramPacket(new byte[0], 0, group, port);
                        socket.send(endPacket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 30000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
