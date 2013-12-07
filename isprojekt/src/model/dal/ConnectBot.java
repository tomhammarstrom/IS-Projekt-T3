package isprojekt.src.model.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectBot {
    private Statement mainStatement;

    public void connect() throws SQLException {
        DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
        Connection connection1 = DriverManager.getConnection("JDBC:ODBC:isprojekt_new");
        
        mainStatement = connection1.createStatement();

    }


    public ResultSet query(String request) throws SQLException {
         return mainStatement.executeQuery(request);

    }
    
    public int update(String request) throws SQLException {
        return mainStatement.executeUpdate(request);
                                                      
    }

}
