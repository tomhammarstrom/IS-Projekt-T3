package isprojekt.src.tengil;

import isprojekt.src.controller.Controller;

import java.sql.SQLException;

public class Main {
    private Controller con;
    
    public Main() throws SQLException {
        con = new Controller();
    }
    
    public static void main (String []args) throws SQLException {
        
        new Main();
        


    }
}
