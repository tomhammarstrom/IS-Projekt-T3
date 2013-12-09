package isprojekt.src.model.dal;

import isprojekt.src.controller.Controller;

import isprojekt.src.model.logic.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class Registry {
    private Controller con;
    private List<List<String>> students;

    public Registry(Controller con) {
        this.con = con;
        students = new ArrayList<List<String>>();
    }

    public int addStudent(String civic, String name) throws SQLException {
        PreparedStatement s = con.buildStatement("select * from student where civic = '?'");
        s.setString(1,civic);
        ResultSet exists = s.executeQuery();
        
        if(!exists.next()){
            s = con.buildStatement("insert into student values('?','?')");
            s.setString(1,civic);
            s.setString(2,name);
            return con.update(s);
        }
        else{
            s = con.buildStatement("update student set civic = '?', name ='?'");
            s.setString(1,civic);
            s.setString(1,name);
        }
       
    }
    
    public int removeStudent(Student student) throws SQLException {
        PreparedStatement s = con.buildStatement("delete from student where civic = '?'");
        s.setString(1,student.getCivic());
        return con.update(s);
    }

    public List<List<String>> getStudents() throws SQLException {
        PreparedStatement s = con.buildStatement("select * from student");
        ResultSet results = con.query(s);

        while (results.next()) {
            ArrayList<String> aStudent = new ArrayList<String>();
            aStudent.add(results.getString(1));
            aStudent.add(results.getString(2));
            students.add(aStudent);
        }
        return students;
    }
    
    public Student getStudent(String request) throws SQLException {
        PreparedStatement s = con.buildStatement("select * from student where civic = '?'");
        s.setString(1,request);
        ResultSet foundStudent = con.query(s);
        Student object = null;
        
        if(foundStudent.next()){
            object = new Student(foundStudent.getString(1));
            object.setName(foundStudent.getString(2));
        }
        
        return object;

    }
    
    
    /**
     * Kursfunktioner nedan
  
    
    
    
    public void addCourse(String id, String name) throws SQLException {
        String sqlString = "insert into course values('" + civic + "','" + name + "')";
        System.out.println(con.update(sqlString));
    }
    
    public void changeStudent(String someNewStuff){
        
    }

    public void removeStudent(Student student) throws SQLException {
        String civic = student.getCivic();
        String sqlString = "delete from student where civic = '" + civic + "'";
        System.out.println(con.update(sqlString));
        
    }

    public List<List<String>> getStudents() throws SQLException {
        ResultSet a = con.query("select * from student");

        while (a.next()) {
            ArrayList<String> aStudent = new ArrayList<String>();
            aStudent.add(a.getString(1));
            aStudent.add(a.getString(2));
            students.add(aStudent);
        }
        return students;
    }
    
    public Student getStudent(String request) throws SQLException {
        String sqlString = "select * from student where civic = '" + request + "'";
        ResultSet foundStudent = con.query(sqlString);
        Student object = null;
        
        if(foundStudent.next()){
            object = new Student(foundStudent.getString(1));
            object.setName(foundStudent.getString(2));
        }
        
        return object;

    }

    *
    */
    
}


