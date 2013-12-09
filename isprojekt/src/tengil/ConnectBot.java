package isprojekt.src.tengil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectBot {
    private Connection connection1;


    public void connect() throws SQLException {
        DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
        connection1 = DriverManager.getConnection("JDBC:ODBC:isprojekt_new");
    }
    
    public PreparedStatement buildStatement(String request) throws SQLException {
        System.out.println(request);
        return connection1.prepareStatement(request);
    }


    public ResultSet query(PreparedStatement request) throws SQLException {
        return request.executeQuery();

    }
    
    public int update(PreparedStatement request) throws SQLException {
        return request.executeUpdate();
                                                      
    }

}
