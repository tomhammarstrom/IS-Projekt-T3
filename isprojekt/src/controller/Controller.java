package isprojekt.src.controller;

import isprojekt.src.model.dal.ConnectBot;

import isprojekt.src.model.dal.Registry;

import isprojekt.src.model.logic.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private ConnectBot cb;
    private Registry reg;
    
    public Controller() throws SQLException {
        cb = new ConnectBot();
        reg = new Registry(this);
        
        cb.connect();
      
        //getStudents();
        /**
         * Testfunktioner körs härifrån
         * 
         */
        Student die = reg.getStudent("444");
        reg.removeStudent(die);
        printStudents();
        
      //  Student a = reg.getStudent("444");
     //  System.out.println(a.getCivic() + " " + a.getName());
    //    reg.addStudent("444", "jonas");
    }
    
    
    public ResultSet query(String request) throws SQLException {
       return cb.query(request);
    }
    
    public int update(String request) throws SQLException{
        return cb.update(request);
    }
    

    private void printStudents() throws SQLException {
        List<List<String>> students = reg.getStudents();
        
        for(int i=0;i<students.size();i++){
            System.out.println(students.get(i).get(0) +" "+ students.get(i).get(1));
        }
    }


}
