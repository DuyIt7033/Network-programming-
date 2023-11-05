import java.util.Scanner;
import java.io.*;
class CopyFile1 {
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
			// Doc noi dung file - ghi vao file ket qua
			while(true) {
				int ch = f.read();	// Doc 1 byte
				if(ch==-1)	break;	// Kt het file hay chua?
				f2.write(ch);
			}
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
