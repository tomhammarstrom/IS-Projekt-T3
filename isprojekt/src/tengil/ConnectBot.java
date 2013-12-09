package isprojekt.src.tengil;

import java.sql.*;
    
public class ConnectBot{
    
    public ConnectBot() throws SQLException {
        connectToDatabase();
    }
    
    /**
     * Generella funktioner
     *
     */

    public void connectToDatabase() throws SQLException {
        DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
    }
    
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("JDBC:ODBC:isprojekt_new");
    }
    
    
    /**
     * Studentfunktioner
     * 
     */
    
    public ResultSet getStudent(String civic) throws SQLException {
        Connection con = connect();
        
        PreparedStatement stmnt = con.prepareStatement("select * from student where civic = ?");
        stmnt.setString(1,civic); 
        ResultSet found = stmnt.executeQuery();
        
        return found;
    }
    
    public ResultSet getStudents() throws SQLException {
        Connection con = connect();
        
        PreparedStatement stmnt = con.prepareStatement("select * from student");
        ResultSet found = stmnt.executeQuery();
        
        return found;
    }
    
    public int addStudent(String civic, String name) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("select * from student where civic = ?");
        s.setString(1,civic);
        int temp;

        
        if(s.executeQuery().next()){
            System.out.println("student finns redan");
            temp = 0;
        }
        else{
            s = con.prepareStatement("insert into student values(?,?)");
            s.setString(1,civic);
            s.setString(2,name);
            temp =  s.executeUpdate();
        }
        
        return temp;
        
    }
    
    public int removeStudent(String civic) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("delete from student where civic = ?");
        s.setString(1,civic);
        int temp = s.executeUpdate();
        
        return temp;
    }
    
    /**
     * Kursfunktioner
     * 
     */
    
    public ResultSet getCourse(String id) throws SQLException {
        Connection con = connect();
        
        PreparedStatement stmnt = con.prepareStatement("select * from course where id = ?");
        stmnt.setString(1,id); 
        ResultSet found = stmnt.executeQuery();
        
        return found;
    }
    
    public ResultSet getCourses() throws SQLException {
        Connection con = connect();
        
        PreparedStatement stmnt = con.prepareStatement("select * from course");
        ResultSet found = stmnt.executeQuery();
        
        return found;
    }
    
    public int addCourse(String id, String name, String contents, int points) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("select * from course where id = ?");
        s.setString(1,id);
        int temp;

        
        if(s.executeQuery().next()){
            System.out.println("kurs finns redan");
            temp = 0;
        }
        else{
            s = con.prepareStatement("insert into course values(?,?,?,?)");
            s.setString(1,id);
            s.setString(2,name);
            s.setString(3,contents);
            s.setInt(4,points);
            temp =  s.executeUpdate();
        }
        
        return temp;
        
    }
    
    public int removeCourse(String id) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("delete from course where id = ?");
        s.setString(1,id);
        int temp = s.executeUpdate();
        
        return temp;
    }
    
    /**
     * Funktioner för att starta/avsluta kurser
     * 
     */
    
    public int startCourse(String civic, String id) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("select * from studies_active where id = ? and civic = ?");
        s.setString(1,civic);
        s.setString(2, id);
        int temp;

        
        if(s.executeQuery().next()){
            System.out.println("student har redan påbörjat kurs");
            temp = 0;
        }
        else{
            s = con.prepareStatement("insert into studies_active values(?,?)");
            s.setString(1,civic);
            s.setString(2,id);

            temp =  s.executeUpdate();
        }
        
        return temp;
    }
    
    public void endCourse(){
        
    }


}
