import java.util.Date;
import java.net.*;
import java.io.*;
class MulticastTimeServer {
	public static void main(String[] args) {
		try {
			// Tao 1 UDP Socket
			DatagramSocket ds = new DatagramSocket();
			// Tao nhom dia chi nhan
			InetAddress dc = InetAddress.getByName("230.0.0.1");
			int p = 6789;
			int stt = 0;
			while(true) {
				// Lay thoi gian hien tai
				Date d = new Date();
				String str = d.toString();
				byte b[] = str.getBytes();
				// Gui cho nhom can phuc vu
				DatagramPacket goigui = new DatagramPacket(b,b.length,dc,p);
				ds.send(goigui);
				System.out.println("Da gui goi thu " + stt++);
				Thread.sleep(500);
			}
		}
		catch(SocketException e) {
			System.out.println("Khong khoi tao duoc UDP Socket");
		}
		catch(UnknownHostException e) {
			System.out.println("Sai dia chi");
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
		catch(InterruptedException e) {
			System.out.println("Loi khi tam dung");
		}
	}
}