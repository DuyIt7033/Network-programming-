package th2;

import java.net.*;
import java.io.*;

class ServerDiaChiIP {
    private static ServerSocket ss;

	public static void main(String[] args) {
        try {
            ss = new ServerSocket(20230);
            System.out.println("Server khoi tao thanh cong");
            
            while (true) { 
                try {
                    // Ch?p nh?n k?t n?i t? Client
                    Socket s = ss.accept();
                    System.out.println(" Client da ket noi tu  " + s.getInetAddress() + " Cong " + s.getPort());
                    
                    // L?y lu?ng in-out t? k?t n?i
                    InputStream is = s.getInputStream();
                    OutputStream os = s.getOutputStream();
                    
                    // Ğ?c d?a ch? IP t? Client
                    byte b[] = new byte[1000];
                    int n = is.read(b); // n là s? byte nh?n du?c
                    String diaChiIP = new String(b, 0, n).trim();
                    
                    // Ki?m tra và xác d?nh lo?i d?a ch? IP
                    String ketQua = xacDinhLoaiDiaChiIP(diaChiIP);
                    
                    // G?i k?t qu? v? Client
                    os.write(ketQua.getBytes());
                    os.write('\n'); // G?i kı t? xu?ng hàng d? k?t thúc k?t qu?
                    
                    // Ğóng k?t n?i v?i Client
                    s.close();
                } catch (IOException e) {
                    System.out.println("Loi phuc vu Client");
                }
            }
        } catch (IOException e) {
            System.out.println("Loi khoi tao Server");
        }
    }

	private static String xacDinhLoaiDiaChiIP(String diaChiIP) {
	    // Tách d?a ch? IP thành các octet
	    String[] octets = diaChiIP.split("\\.");
	    
	    // Ki?m tra xem có 4 octet không
	    if (octets.length != 4) {
	        return "Khong phai Ipv4";
	    }
	    
	    // Ki?m tra và xác d?nh l?p c?a d?a ch? IPv4
	    try {
	        int octet1 = Integer.parseInt(octets[0]);
	        
	        if (octet1 >= 1 && octet1 <= 126) {
	            return "Dia chi IP tren la thuoc lop A ";
	        } else if (octet1 >= 128 && octet1 <= 191) {
	            return "Dia chi IP tren la thuoc lop B";
	        } else if (octet1 >= 192 && octet1 <= 223) {
	            return "Dia chi IP tren la thuoc lop C ";
	        } else if (octet1 >= 224 && octet1 <= 239) {
	            return "Dia chi IP tren la thuoc lop D ";
	        } else if (octet1 >= 240 && octet1 <= 255) {
	            return "Dia chi IP tren la thuoc lop E ";
	        } else {
	            return "Khong xac dinh";
	        }
	    } catch (NumberFormatException e) {
	        return "Khong hop le";
	    }
	}
}
