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
	
	public ResultSet getIndex() throws SQLException{
		return sql.getIndex();
	}
	
	public ResultSet getConstraints() throws SQLException{
		return sql.getConstraints();
	}
	
	public ResultSet getTables() throws SQLException{
		return sql.getTables();
	}
	
	public ResultSet getColumns() throws SQLException{
		return sql.getColumns();
	}
	
	public ResultSet getMaxRow() throws SQLException{
		return sql.getMaxRow();
	}
	
	
	public ResultSet getEmployeeData() throws SQLException{
		return sql.getEmployeeData();
	}
	
	public ResultSet getEmployeeAbsenceData() throws SQLException{
		return sql.getEmployeeAbsenceData();
	}

	
	public ResultSet getEmployeeQualificationData() throws SQLException{
		return getEmployeeQualificationData();
	}
	
	public ResultSet getEmployeeRelativeData() throws SQLException{
		return getEmployeeRelativeData();
	}
	
	
	
		
}
