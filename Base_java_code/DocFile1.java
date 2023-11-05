import java.util.Scanner;
import java.io.*;
class DocFile1 {
	public static void main(String[] args) {
		try {
			// Nhap tu ban phim ten file can doc
			Scanner kb = new Scanner(System.in);
			System.out.print("Nhap ten file can doc: ");
			String tenfile = kb.nextLine();
			// Mo file
			FileInputStream f = new FileInputStream(tenfile);
			// Doc noi dung file - hien thi
			System.out.println("Noi dung file:");
			while(true) {
				int ch = f.read();
				if(ch==-1)	break;
				System.out.print((char)ch);
			}
			// Dong file
			f.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Khong tim thay file");
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}
}