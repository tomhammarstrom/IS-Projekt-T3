package isprojekt.src.tengil;

import java.sql.*;

public class Controller {
    private ConnectBot cb;
    
    
    public Controller() throws SQLException {
        cb = new ConnectBot();
    }
    
    public int addStudent(String civic, String name) throws SQLException {
        return cb.addStudent(civic, name);
    }
    
    public int removeStudent(String civic) throws SQLException {
        return cb.removeStudent(civic);
    }
    
    public ResultSet getStudent(String civic) throws SQLException {
        return cb.getStudent(civic);
    }
    
    public ResultSet getStudents() throws SQLException {
        return cb.getStudents();
    }

}
