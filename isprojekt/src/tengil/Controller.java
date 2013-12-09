package isprojekt.src.tengil;

import java.sql.*;

public class Controller {
    private ConnectBot cb;
    
    
    public Controller() throws SQLException {
        cb = new ConnectBot();
        
        showAllStudents();
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
