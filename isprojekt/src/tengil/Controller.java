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
    
    /**
     * Kurshantering
     * 
     */
    
    public int startCourse(String civic, String id) throws SQLException {
        return cb.startCourse(civic, id);
    }
    
    public int endCourse(String civic, String id, String grade) throws SQLException{
        return cb.endCourse(civic, id, grade);
    }
    
    public int cancelCourse(String civic, String id) throws SQLException{
        return cb.cancelCourse(civic, id);
    }
    
    /**
     * Statistik osv
     * 
     */
    
    public ResultSet studentResults(String civic, String id) throws SQLException { 
        return cb.studentResults(civic, id);
    }
    
    public ResultSet courseResults(String id) throws SQLException {
        return cb.courseResults(id);
    }
    
    public ResultSet studentsNotDone(String id) throws SQLException {
        return cb.studentsNotDone(id);
    }
    
    public float numberOfA(String id) throws SQLException {
       return cb.numberOfA(id);
    }
    
    public String highestFlow() throws SQLException {
       return cb.highestFlow();
    }
    

}
