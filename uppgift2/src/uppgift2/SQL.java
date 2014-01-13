package uppgift2;

import java.sql.*;

public class SQL {
	private Connection con;
	
	@SuppressWarnings("restriction")
	public SQL() throws SQLException{
		DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
	}
	
	private Connection connect() throws SQLException{
		con = DriverManager.getConnection("JDBC:ODBC:cronus");
		return con;
	}
	
	
	public ResultSet getCustomerInfo() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("aaa");  //att fixa
	}
	
	public DatabaseMetaData getMetaData () throws SQLException{
		return connect().getMetaData();
	}
	
	public ResultSet getKeys() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("SELECT DISTINCT Constraint_Name AS [Constraint] FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE");
	}
	
	public ResultSet getIndex() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select * from sys.indexes where name like 'CRONUS%'");
	}
	
	public ResultSet getConstraints() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS");
	}
	
	public ResultSet getTables() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select * from INFORMATION_SCHEMA.TABLES");
	}
	
	public ResultSet getColumns() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select * from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME = 'CRONUS Sverige AB$Employee'");
	}
	
	public ResultSet getMaxRow() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select top 1 TableName from (SELECT OBJECT_NAME(OBJECT_ID) TableName, st.row_count as antal FROM sys.dm_db_partition_stats st WHERE index_id < 2) x where TableName like 'CRONUS%' group by TableName, antal order by antal desc");
	}
	
	/**
	 * employee och "relaterade tabeller"
	 * 
	 */
	
	public ResultSet getEmployee() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select * from employee");
	}
	
	public ResultSet getEmployeeAbsence() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select * from employee");
	}
	
	public ResultSet getEmployeePortalSetup() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select * from employee");
	}
	
	public ResultSet getEmployeeQualification() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select * from employee");
	}
	
	public ResultSet getEmployeeRelative() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select * from employee");
	}
	
	public ResultSet getEmployeeStatisticGroup() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select * from employee");
	}
	
	public ResultSet getEmployeeContract() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select * from employee");
	}
	


}
