package uppgift2;

import java.sql.*;

public class SQL {
	private Connection con;
	
	public SQL() throws SQLException{
		DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
	}
	
	private Connection connect() throws SQLException{
		con = DriverManager.getConnection("JDBC:ODBC:cronus");
		return con;
	}
	
	
	private ResultSet getCustomerInfo() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("aaa");
	}
	
	public DatabaseMetaData getMetaData () throws SQLException{
		return connect().getMetaData();
	}
	
	
	
	public ResultSet getKeys() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("SELECT DISTINCT Constraint_Name AS [Constraint] FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE");
	}
	
	private ResultSet getIndex() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("aaa");
	}
	
	private ResultSet getConstraints() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("aaa");
	}
	
	private ResultSet getTables() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("aaa");
	}
	
	private ResultSet getColumns() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("aaa");
	}
	
	private ResultSet getMaxRow(){
		
	}
	

	
	
	

}
