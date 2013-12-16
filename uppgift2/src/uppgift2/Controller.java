package uppgift2;

import java.sql.*;

public class Controller {
	private SQL sql;
	
	public Controller() throws SQLException{
		sql = new SQL();
	}
	
	public ResultSet getKeys() throws SQLException{
		return sql.getKeys();
	}
	
	public DatabaseMetaData getMetaData() throws SQLException{
		return sql.getMetaData();
	}

}
