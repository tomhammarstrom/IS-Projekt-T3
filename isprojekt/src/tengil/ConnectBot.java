package isprojekt.src.tengil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectBot {

    public void connect() throws SQLException {
        DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
        Connection connection1 = DriverManager.getConnection("JDBC:ODBC:isprojekt1");

        Statement testStatement = connection1.createStatement();
    }


}
