import java.net.*;
import java.io.*;
class MulticastTimeClient {
	public static void main(String[] args) {
		try {
			// Khoi tao Multicast Socket cong 6789
			MulticastSocket ms = new MulticastSocket(6789);
			// Tham gia vao nhom dia chi 230.0.0.1
			InetAddress dc = InetAddress.getByName("230.0.0.1");
			ms.joinGroup(dc);
			// Nhan goi phuc vu
			byte b[] = new byte[1000];
			DatagramPacket goinhan = new DatagramPacket(b,1000);
			ms.receive(goinhan);
			// Hien thi ket qua
			byte b1[] = goinhan.getData();
			int len1 = goinhan.getLength();
			String ketqua = new String(b1,0,len1);
			System.out.println("Bay gio la: " + ketqua);
			// Roi khoi nhom
			ms.leaveGroup(dc);
			// Dong Socket
			ms.close();
		}
		catch(UnknownHostException e) {
			System.out.println("Sai dia chi");
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}		
}