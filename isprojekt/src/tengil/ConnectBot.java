package isprojekt.src.tengil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectBot {
    private Connection connection1;

    /**
     * Ansluter till databasen, körs vid uppstart
     * @throws SQLException
     */
    public void connect() throws SQLException {
        DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
        connection1 = DriverManager.getConnection("JDBC:ODBC:isprojekt_new");
    }
    
    /**
     * Bygger preparedstatments med connection1
     * @param request
     * @return
     * @throws SQLException
     */
    public PreparedStatement buildStatement(String request) throws SQLException {
        System.out.println(request);
        return connection1.prepareStatement(request);
    }

    /**
     * Läs-operationer
     * @param request
     * @return
     * @throws SQLException
     */
    public ResultSet query(PreparedStatement request) throws SQLException {
        return request.executeQuery();
    }
    
    /**
     * Skriv-operationer
     * @param request
     * @return
     * @throws SQLException
     */
    public int update(PreparedStatement request) throws SQLException {
        return request.executeUpdate();                                       
    }

}
