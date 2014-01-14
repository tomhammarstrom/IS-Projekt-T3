package uppgift3;

import java.io.IOException;

public class FileManager {
	
	public void openForm1(){
		try {
			String[] cmdarray = new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\Rapport_customer.docx" };
			Runtime.getRuntime().exec(cmdarray);
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
	public void openForm2(){
		try {
			String[] cmdarray = new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\Rapport_customer.docx" };
			Runtime.getRuntime().exec(cmdarray);
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
	public void openForm3(){
		try {
			String[] cmdarray = new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\Rapport_customer.docx" };
			Runtime.getRuntime().exec(cmdarray);
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
	public void openForm4(){
		try {
			String[] cmdarray = new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\Rapport_customer.docx" };
			Runtime.getRuntime().exec(cmdarray);
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

}
