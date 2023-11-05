import java.util.Scanner;
import java.io.*;
class CopyFile3 {
	public static void main(String[] args) {
		try {
			// Nhap tu ban phim ten file can doc
			Scanner kb = new Scanner(System.in);
			System.out.print("Nhap ten file can doc: ");
			String tenfiledoc = kb.nextLine();
			System.out.print("Nhap ten file can ghi: ");
			String tenfileghi = kb.nextLine();
			// Mo file
			FileInputStream f = new FileInputStream(tenfiledoc);
			FileOutputStream f2 = new FileOutputStream(tenfileghi);
			// Chuyen qua lop con (Data)
			DataInputStream dis = new DataInputStream(f);
			DataOutputStream dos = new DataOutputStream(f2);
			// Doc noi dung file - ghi vao file ket qua
			File f3 = new File(tenfiledoc);
			int size = (int)f3.length();
			byte b[] = new byte[size];
			dis.readFully(b);	// Doc het noi dung file
			dos.write(b);
			// Dong file
			f.close();
			f2.close();
			System.out.println("Da doc-ghi file thanh cong");
		}
		catch(FileNotFoundException e) {
			System.out.println("Khong tim thay file");
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}
}