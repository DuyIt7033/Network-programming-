import java.util.Scanner;
import java.net.*;
import java.io.*;
class ClientNhiPhan {
	public static void main(String[] args) {
		try {
			// Nhap dia chi Server
			Scanner kb = new Scanner(System.in);
			System.out.print("Nhap dia chi Server: ");
			String dcServer = kb.nextLine();
			// Noi ket
			Socket s = new Socket(dcServer,20230);
			// Lay ra 2 stream in-out
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			while(true) {
				// Nhap 1 so nguyen (1 chuoi)
				System.out.print("Nhap 1 so nguyen: ");
				String str = kb.nextLine();
				// Gui chuoi yeu cau cho Server
				os.write(str.getBytes());
				// Kiem tra dk de thoat
				if(str.equals("EXIT")) break;
				// Nhan ket qua tra ve tu Server
				byte b[] = new byte[1000];
				int n = is.read(b);	// n la so byte nhan duoc
				// Hien thi ket qua
				String kq = new String(b,0,n);
				System.out.println("Nhan duoc: " + kq);
			}
			// Dong noi ket
			s.close();
		}
		catch(UnknownHostException e) {
			System.out.println("Sai dia chi Server");
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}
}