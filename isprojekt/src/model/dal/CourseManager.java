package isprojekt.src.model.dal;

import isprojekt.src.controller.Controller;
import isprojekt.src.model.logic.Course;
import isprojekt.src.model.logic.Student;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseManager {
    private Controller con;
    
    public CourseManager(Controller con){
        this.con = con;
    }
    
    public void startCourse(Student student, Course course) throws SQLException {
        PreparedStatement s = con.buildStatement("insert into studies_active values('?','?'");
        s.setString(1,student.getCivic());
        s.setString(2,course.getId());
        
        con.update(s);
        
    }
    
    public void endCourse(Course c, Student s){
        /**
         * Orkar verkligen inte ta tag i denna just nu
         */
    }

}
