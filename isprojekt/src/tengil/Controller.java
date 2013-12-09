package isprojekt.src.tengil;

import java.sql.*;

public class Controller {
    
    
    public Controller() throws SQLException {
        connectToDatabase();
        
        //addStudent("999","olof");
        removeStudent("777");
        getStudents();
    }
    

    public void connectToDatabase() throws SQLException {
        DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
    }
    
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("JDBC:ODBC:isprojekt_new");
    }
    
  
    
    public void addStudent(String civic, String name) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("select * from student where civic = ?");
        s.setString(1,civic);

        
        if(s.executeQuery().next()){
            System.out.println("finns redan");
        }
        else{
            s = con.prepareStatement("insert into student values(?,?)");
            s.setString(1,civic);
            s.setString(2,name);
            s.executeUpdate();
        }
        
        con.close();
        
    }
    
    public void removeStudent(String civic) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("delete from student where civic = ?");
        s.setString(1,civic);
        s.executeUpdate();
        con.close();
    }
    
    public void getStudents() throws SQLException {
        Connection con = connect();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("select * from student");
        
        while (rs.next()){
            System.out.println(rs.getString("civic") + " " + rs.getString("name"));
        }
        con.close();
        
    }
    




}
