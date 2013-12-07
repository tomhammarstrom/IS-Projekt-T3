package isprojekt.src.tengil;

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

    public void addStudent(String civic, String name) {

    }

    public void removeStudent() {

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
    
}


