import java.util.Scanner;
import java.util.InputMismatchException;
class GiaiThua {
	public static void main(String[] args) {
		while(true) {
			try {
				// Nhap so nguyen a
				Scanner kb = new Scanner(System.in);
				System.out.print("Nhap so nguyen a: ");
				int a = kb.nextInt();
				// Tinh giai thua
				long ketqua = 1;
				if(a<=0) {
					System.out.println("Khong biet tinh");
					return;
				}
				else 
					for(int i = 1; i<=a; i++)
						ketqua = ketqua * i;
				// Hien thi ket qua
				System.out.println(a + "!= " + ketqua);
				break;
			}
			catch(InputMismatchException e) {
				System.out.println("Nhap sai - Nhap lai !!!");
			}
		}
	}
}