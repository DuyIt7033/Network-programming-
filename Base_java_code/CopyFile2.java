import java.util.Scanner;
import java.io.*;
class CopyFile2 {
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
			byte b[] = new byte[10000];
			File f3 = new File(tenfiledoc);
			int size = (int)f3.length();
			int tong = 0;
			while(true) {
				int n = f.read(b);	// Doc noi dung file vao b[]
				if( n>0 ) {
					f2.write(b,0,n);	// Ghi n byte vao file kq
					tong += n;
					System.out.println("Da den byte thu " + tong);
					if(tong==size)	break;	// Da het file
				}
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