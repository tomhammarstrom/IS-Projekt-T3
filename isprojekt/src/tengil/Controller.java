package isprojekt.src.tengil;

import java.sql.*;

public class Controller {
    private ConnectBot cb;
    
    
    public Controller() throws SQLException {
        cb = new ConnectBot();
    }
    
    /**
     * Studentfunktioner
     * 
     */
    
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
    
    /**
     * Kursfunktioner
     * 
     */
    
    public int addCourse(String id, String name, String contents, int points) throws SQLException {
        return cb.addCourse(id, name, contents, points);
    }
    
    public int removeCourse(String id) throws SQLException {
        return cb.removeCourse(id);
    }
    
    public ResultSet getCourse(String id) throws SQLException {
        return cb.getCourse(id);
    }
    
    public ResultSet getCourses() throws SQLException {
        return cb.getCourses();
    }
    

}
