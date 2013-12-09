package isprojekt.src.tengil;

import java.sql.*;

public class Controller {
    
    
    public Controller() throws SQLException {
        connectToDatabase();
        
        /**
         * 
        addStudent("999","olof");
        removeStudent("777");
        getStudents();
        *
        *
        */
    }
    

    public void connectToDatabase() throws SQLException {
        DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
    }
    
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("JDBC:ODBC:isprojekt_new");
    }
    
    public ResultSet getStudent(boolean multi, String civic) throws SQLException {
        Connection con = connect();
        ResultSet found;
        PreparedStatement stmnt;
    
        if(multi){
            stmnt = con.prepareStatement("select * from student");
        }
        
        else{ 
            stmnt = con.prepareStatement("select * from student where civic = ?");
            stmnt.setString(1,civic); 
        }
        
        found = stmnt.executeQuery();
        con.close();
        return found;
    }
    
    public int addStudent(String civic, String name) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("select * from student where civic = ?");
        s.setString(1,civic);
        int temp;

        
        if(s.executeQuery().next()){
            System.out.println("finns redan");
            temp = 0;
        }
        else{
            s = con.prepareStatement("insert into student values(?,?)");
            s.setString(1,civic);
            s.setString(2,name);
            temp =  s.executeUpdate();
        }
        
        con.close();
        return temp;
        
    }
    
    public int removeStudent(String civic) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("delete from student where civic = ?");
        s.setString(1,civic);
        int temp = s.executeUpdate();
        con.close();
        
        return temp;
    }
    



}
