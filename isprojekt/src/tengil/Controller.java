package isprojekt.src.tengil;

import java.sql.*;

public class Controller {
    private ConnectBot cb;
    
    
    public Controller() throws SQLException {
        cb = new ConnectBot();
        
        cb.addStudent("1", "sven");
        showAllStudents();
        cb.removeStudent("666");
    }
    
    
    /**
     * Testfunktioner
     * 
     */
    
    private void showAllStudents() throws SQLException {
        ResultSet test = cb.getStudents();
        
        while(test.next()){
            System.out.println(test.getString(1) + " " + test.getString(2));
        }
    }

}
