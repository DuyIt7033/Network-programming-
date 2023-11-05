package th2;

import java.util.Scanner;
import java.net.*;
import java.io.*;

class ClientDiaChiIP {
    private static Scanner kb;

	public static void main(String[] args) {
        try {
            kb = new Scanner(System.in);
            System.out.print("Nhap dia chi: ");
            String diaChiIP = kb.nextLine();
            
            // K?t n?i d?n m�y ch?
            Socket s = new Socket("localhost", 20230); // �?i localhost th�nh d?a ch? IP c?a m�y ch? n?u c?n
            
            // L?y ra c�c lu?ng in-out
            OutputStream os = s.getOutputStream();
            InputStream is = s.getInputStream();
            
            // G?i d?a ch? IP qua m�y ch?
            os.write(diaChiIP.getBytes());
            os.write('\n'); // G?i k� t? xu?ng h�ng d? k?t th�c y�u c?u
            
            // Nh?n v� hi?n th? k?t qu? t? m�y ch?
            byte b[] = new byte[1000];
            int n = is.read(b);
            String ketQua = new String(b, 0, n);
            System.out.println("Ket qua: " + ketQua);
            
            // ��ng k?t n?i
            s.close();
        } catch (UnknownHostException e) {
            System.out.println("Sai dia chi");
        } catch (IOException e) {
            System.out.println("Loi nhap/xuat");
        }
    }
}

