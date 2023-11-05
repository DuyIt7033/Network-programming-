import java.net.*;
import java.io.*;
class ServerNhiPhan {
	public static void main(String[] args) {
		try {
			// Khoi tao Server Socket cong 20230
			ServerSocket ss = new ServerSocket(20230);
			System.out.println("Da khoi tao thanh cong Server");
			while(true) {	// Phuc vu cho nhieu Client
				try {
					// Chap nhan cho noi ket
					Socket s = ss.accept();
					System.out.println("++ Co 1 Client " + s.getInetAddress() + " cong "
								+ s.getPort() + " noi ket");
					// Lay ra 2 stream in-out
					InputStream is = s.getInputStream();
					OutputStream os = s.getOutputStream();
					while(true) {
						// Nhan yeu cau cua Client
						byte b[] = new byte[100];
						int n = is.read(b);
						String yeucau = new String(b,0,n);
						// Kiem tra dk de thoat
						if(yeucau.equals("EXIT")) break;
						// Xu ly yeu cau
						String kq = new String();
						try {
							int x = Integer.parseInt(yeucau);
							kq = Integer.toBinaryString(x);
						}
						catch(NumberFormatException e) {
							kq = "Khong phai la so nguyen";
						}
						// Gui ket qua cho Client
						os.write(kq.getBytes());
					}
					// Dong noi ket voi Client
					System.out.println("-- Co 1 Client " 
								+ s.getInetAddress() + " cong "
								+ s.getPort() + " dong noi ket");
					s.close();
				}
				catch(IOException e) {
					System.out.println("Co loi khi phuc vu 1 Client");
				}
			}
		}
		catch(IOException e) {
			System.out.println("Co loi khi khoi tao Server");
		}
	}
}