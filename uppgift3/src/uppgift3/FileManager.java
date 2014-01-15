package uppgift3;

import java.io.IOException;

public class FileManager {
	
	private void openFile(String[] cmdarray){
		try {
			Runtime.getRuntime().exec(cmdarray);
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
	/*
	 * Excel
	 */
	public void openExcel1(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\excel1.dqy" });
	}
	public void openExcel2(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\excel2.dqy" });
	}
	public void openExcel3(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\excel3.dqy" });
	}
	public void openExcel4(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\excel4.dqy" });
	}
	public void openExcel5(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\excel5.dqy" });
	}
	public void openExcel6(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\excel6.dqy" });
	}
	public void openExcel7(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\excel7.dqy" });
	}

	
	/*
	 * Access
	 */
	public void openAccess1(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}
	public void openAccess2(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}
	public void openAccess3(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}
	public void openAccess4(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}
	public void openAccess5(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}
	public void openAccess6(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}
	public void openAccess7(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}

	
	/*
	 * SQL
	 */
	public void openSql1(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}
	public void openSql2(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}
	public void openSql3(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}
	public void openSql4(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}
	public void openSql5(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}
	public void openSql6(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}
	public void openSql7(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\XXX" });
	}
	
	
	/*
	 * Forms
	 */
	public void openForm1(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\form1.xlsx" });
	}
	public void openForm2(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\form2.xlsx" });
	}
	public void openForm3(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\form3.xlsx" });
	}
	public void openForm4(){
		openFile(new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\Filer\\form4.xlsx" });
	}

	

}
