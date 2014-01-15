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
	

	public ResultSet getKeys() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select distinct Constraint_Name as [Constraint] from INFORMATION_SCHEMA.KEY_COLUMN_USAGE");
	}
	
	public ResultSet getIndex() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select * from sys.indexes where name like 'CRONUS%'");
	}
	
	public ResultSet getConstraints() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select * from INFORMATION_SCHEMA.TABLE_CONSTRAINTS");
	}
	
	public ResultSet getTables() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select *from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME = 'CRONUS Sverige AB$Employee'");
		
		/**
		  ELLER:
		  
		  use [Demo Database NAV (5-0)]
			select name from  sys.tables

		 */
	}
	
	
	public ResultSet getColumns() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select * from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME = 'CRONUS Sverige AB$Employee'");
		
		/**
		 * ELLER:
		 * select c.name from sys.columns c
inner join sys.tables t on c.object_id = t.object_id
where t.name = 'CRONUS Sverige AB$Employee'
order by t.name
		 * 
		 * 
		 */
	}
	
	public ResultSet getMaxRow() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select top 1 * from (SELECT OBJECT_NAME(OBJECT_ID) TableName, st.row_count as antal from sys.dm_db_partition_stats st where index_id < 2) x where TableName like 'CRONUS%' group by TableName, antal order by antal desc");

	}
	
	/**
	 * employee och "relaterade tabeller"
	 * 
	 */
	
	public ResultSet getEmployeeData() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select [No_],[First Name],[Last Name],[Address],[Phone No_]FROM [Demo Database NAV (5-0)].[dbo].[CRONUS Sverige AB$Employee]");
	}
	
	public ResultSet getEmployeeAbsenceData() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select [Entry No_],[Employee No_],[Cause of Absence Code],[Description],[Quantity] from [Demo Database NAV (5-0)].[dbo].[CRONUS Sverige AB$Employee Absence]");
	}
	

	public ResultSet getEmployeeQualificationData() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select [Employee No_],[From Date],[To Date],[Type],[Description] from [Demo Database NAV (5-0)].[dbo].[CRONUS Sverige AB$Employee Qualification]");
	}
	
	public ResultSet getEmployeeRelativeData() throws SQLException{
		Statement s = connect().createStatement();
		return s.executeQuery("select [Employee No_],[Relative Code],[First Name],[Last Name],[Phone No_] from [Demo Database NAV (5-0)].[dbo].[CRONUS Sverige AB$Employee Relative]");
	}
	
	


}
