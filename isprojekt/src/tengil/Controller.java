package isprojekt.src.tengil;

import java.sql.*;

public class Controller {
    private ConnectBot cb;
    
    
    public Controller() throws SQLException {
        cb = new ConnectBot();
        
       cb.getStudent(1);
    }
    

}
