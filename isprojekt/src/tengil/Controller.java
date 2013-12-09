package isprojekt.src.tengil;


import java.rmi.registry.Registry;

import java.sql.*;

public class Controller {
    private ConnectBot cb;
    
    public Controller() throws SQLException {
        cb = new ConnectBot();
        cb.connect();
        
        printStudents();
    }
    
    public PreparedStatement buildStatement(String request) throws SQLException {
        System.out.println( cb.buildStatement(request));
        
        return cb.buildStatement(request);
    }
    
    
    public ResultSet query(PreparedStatement request) throws SQLException {
       return cb.query(request);
    }
    
    public int update(PreparedStatement request) throws SQLException{
        return cb.update(request);
    }
    


    /**
     * Testfunktioner
     *
     */

    private void printStudents() throws SQLException {
        PreparedStatement s = cb.buildStatement("select * from student");
        ResultSet rs = cb.query(s);
        
        while(rs.next()){
            System.out.println(rs.getString("civic") + " " + rs.getString("name"));
        }

    }


}
