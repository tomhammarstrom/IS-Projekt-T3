package isprojekt.src.tengil;

import java.sql.*;

public class Main {
    
    public static void main (String [] args) throws SQLException {
            
            DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
            Connection connection1 = DriverManager.getConnection("JDBC:ODBC:isprojekt1");
            
            Statement testStatement = connection1.createStatement();
       
       /*   Vår testmetod
        * 
        * testedit:
        * 
            ResultSet a = hej.executeQuery("Select pname from Patient");
            
            while(a.next()){
                System.out.println(a.getString(1));
            }
            
        *
        */    
            
       //    int newStudent = testStatement.executeUpdate("insert into student values('444', 'BBBName')");
            
       //     System.out.println(newStudent);



    }
}
