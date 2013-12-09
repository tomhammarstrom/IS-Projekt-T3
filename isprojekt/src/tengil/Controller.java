package isprojekt.src.tengil;

import java.sql.*;

public class Controller {
    private Connection connection1;
    
    public Controller() throws SQLException {
        connect();
        
    //    addStudent("777","olof");
        getStudents();
    }
    

    public void connect() throws SQLException {
        DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
        connection1 = DriverManager.getConnection("JDBC:ODBC:isprojekt_new");
    }
    
  
    
    public void addStudent(String civic, String name) throws SQLException {
        PreparedStatement s = connection1.prepareStatement("insert into student values(?,?)");
        s.setString(1,civic);
        s.setString(2,name);
        s.executeUpdate();
    
    }
    
    public void getStudents() throws SQLException {
        Statement s = connection1.createStatement();
        ResultSet rs = s.executeQuery("select * from student");
        
        while (rs.next()){
            System.out.println(rs.getString("civic") + " " + rs.getString("name"));
        }
        
    }
    




}
