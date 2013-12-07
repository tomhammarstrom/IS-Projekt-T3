package isprojekt.src.model.dal;

import isprojekt.src.controller.Controller;
import isprojekt.src.model.logic.Course;
import isprojekt.src.model.logic.Student;

import java.sql.SQLException;

public class CourseManager {
    private Controller con;
    
    public CourseManager(Controller con){
        this.con = con;
    }
    
    public void startCourse(Student s, Course c) throws SQLException {
        String civic = s.getCivic();
        String id = c.getId();
        String sqlString = "insert into studies_active values('" + civic + "','" + id +"')";
        
        con.update(sqlString);
        
    }
    
    public void endCourse(Course c, Student s){
        
    }

}
