import java.util.Scanner;
import java.io.*;
class ListThuMuc {
	public static void main(String[] args) {
		// Nhap ten thu muc
		Scanner kb = new Scanner(System.in);
		System.out.print("Nhap ten thu muc can liet ke: ");
		String tenthumuc = kb.nextLine();
		// Tao doi tuong File
		File f = new File(tenthumuc);
		// Kiem tra
		if(f.exists()) {
			if(f.isDirectory()) {
				// Lay ra ds ten va thu muc con
				String ds[] = f.list();
				// Hien thi ket qua
				for(int i=0; i<ds.length; i++) {
					File f2 = new File(tenthumuc + "/" + ds[i]);
					if(f2.isFile())
						System.out.println(ds[i]);
					else
						System.out.println("[" + ds[i] + "]");
				}
			}
			else
				System.out.println(tenthumuc + " khong la thu muc");
		}
		else
			System.out.println(tenthumuc + " khong ton tai");
	}
}