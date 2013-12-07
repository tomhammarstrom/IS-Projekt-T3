package isprojekt.src.controller;

import isprojekt.src.model.dal.ConnectBot;

import isprojekt.src.model.dal.CourseManager;
import isprojekt.src.model.dal.Registry;

import isprojekt.src.model.logic.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
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
        List<List<String>> students = reg.getStudents();
        
        for(int i=0;i<students.size();i++){
            System.out.println(students.get(i).get(0) +" "+ students.get(i).get(1));
        }
    }


}
