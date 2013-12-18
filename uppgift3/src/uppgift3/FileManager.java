package uppgift3;

import java.io.IOException;

public class FileManager {
	
	public void openExcel1(){
		try {
			// Runtime.getRuntime().exec("\"D:\\prog file\\090422.xls\"");
			String[] cmdarray = new String[] { "cmd.exe", "/c", "C:\\Users\\submarines\\git\\isprojekt\\uppgift3\\uppgift1.xls" };
			Runtime.getRuntime().exec(cmdarray);
			// or
			// Runtime.getRuntime().exec("cmd /c start \"\" \"D:\\prog file\\090422.xls\"");

		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

}
