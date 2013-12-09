package isprojekt.src.controller;

import isprojekt.src.model.ConnectBot;

import isprojekt.src.model.CourseManager;
import isprojekt.src.model.Registry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

public class Controller {
    private ConnectBot cb;
    private Registry reg;
    private CourseManager cm;
    
    public Controller() throws SQLException {
        cb = new ConnectBot();
        reg = new Registry(this);
        cm = new CourseManager(this);
        
        cb.connect();

        /**
         * 
         * 
        Student die = reg.getStudent("444");
        reg.removeStudent(die);
        
        Student a = reg.getStudent("444");
        System.out.println(a.getCivic() + " " + a.getName());
        reg.addStudent("444", "jonas");
        *
        *
        */
        printStudents();
    }
    
    public PreparedStatement buildStatement(String request) throws SQLException {
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
       ResultSet students = reg.getStudents();
        
        while(students.next()){
            System.out.println(students.getString("civic") +" "+ students.getString("name"));
        }
    }


}
