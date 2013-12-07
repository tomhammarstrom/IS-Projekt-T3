package isprojekt.src.controller;

import isprojekt.src.model.dal.ConnectBot;

import isprojekt.src.model.dal.Registry;

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
      
        getStudents();
    }
    
    public ResultSet query(String request) throws SQLException {
       return cb.query(request);
    }
    
    public void getStudents() throws SQLException {
        List<List<String>> students = reg.getStudents();
        
        for(int i=0;i<students.size();i++){
            System.out.println(students.get(i).get(0) +" "+ students.get(i).get(1));
        }
    }
}
