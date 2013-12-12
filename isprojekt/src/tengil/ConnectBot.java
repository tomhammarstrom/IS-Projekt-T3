package tengil;

import java.sql.*;
    
public class ConnectBot{
    
    public ConnectBot(){
			registerDriver();
    }
    
    
    /**
     * Generella funktioner
     *
     */

    public void registerDriver(){
        try {
			DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
		} catch (SQLException e) {
			System.out.println("Problem med att ladda driver");
			e.printStackTrace();
		}
    }
    
    public Connection connect() {
    	Connection con = null;
        try {
			con =  DriverManager.getConnection("JDBC:ODBC:isprojekt_cool");
		} catch (SQLException e) {
			System.out.println("Problem med att skapa en connection");
			e.printStackTrace();
		}
        return con;
    }
    
    
    /**
     * Studentfunktioner
     * 
     */
    
    public ResultSet getStudent(String civic) throws SQLException{
    	PreparedStatement ps = connect().prepareStatement("select * from student where pnr = ?");
        ps.setString(1,civic); 
        
        return ps.executeQuery();
    }

    
    public ResultSet getStudents () throws SQLException{
    	PreparedStatement ps = connect().prepareStatement("select * from student");
        return ps.executeQuery();
        }

    
    
    public int addStudent(String intent, String civic, String name, String address) throws SQLException {
        int affectedRows = 0;

    	if(intent.equals("add")){
    		if(getStudent(civic).next()){
    			affectedRows = 0;
    		}
    		else{
    			PreparedStatement s = connect().prepareStatement("insert into student values(?,?,?)");
                s.setString(1,civic.trim());
                s.setString(2,name.trim());
                s.setString(3, address.trim());
                affectedRows =  s.executeUpdate();
    		}	
    	}
    	
    	else if (intent.equals("change")){
    		PreparedStatement s = connect().prepareStatement("update student set pnr = ?, name = ?, adr = ? where pnr = ?");
            s.setString(1,civic.trim());
            s.setString(2,name.trim());
            s.setString(3,address.trim());
            s.setString(4,civic.trim());
            affectedRows =  s.executeUpdate();
    	}
        
        return affectedRows;
    }
    
    public int removeStudent(String civic) throws SQLException {
        PreparedStatement s = connect().prepareStatement("delete from student where pnr = ?");
        s.setString(1,civic);
        return s.executeUpdate();
    }
    public ResultSet getNotFinishedWithCourse (String id)throws SQLException{
        PreparedStatement ps = connect().prepareStatement("select pnr from studies where id = ?");
        ps.setString(1, id);            
        ResultSet r = ps.executeQuery();
        return r;
    }
    public ResultSet getFinishedWithCourse (String id)throws SQLException{
    	PreparedStatement ps = connect().prepareStatement("select pnr from studied where id = ?");
    	ps.setString(1, id);
    	ResultSet r = ps.executeQuery();
    	return r;
    }
    
    /**
     * Kursfunktioner
     * 
     */
    
    public ResultSet getCourse(String id) throws SQLException {
        PreparedStatement stmnt = connect().prepareStatement("select * from course where id = ?");
        stmnt.setString(1,id); 
        return stmnt.executeQuery();
    }
    
    public ResultSet getCourses() throws SQLException {
        PreparedStatement stmnt = connect().prepareStatement("select * from course");
        return stmnt.executeQuery();
    }
    
    public int addCourse(String intent, String id, String name, String description, int points) throws SQLException {
    	   int affectedRows = 0;

       	if(intent.equals("add")){
       		if(getCourse(id).next()){
       			System.out.println("kurs finns redan");
       			affectedRows = 0;
       		}
       		else{
       			PreparedStatement s = connect().prepareStatement("insert into course values(?,?,?,?)");
       		 	s.setString(1,id.trim());
       		 	s.setString(2,name.trim());
       		 	s.setInt(3,points);
       		 	s.setString(4,description.trim());
             
             affectedRows =  s.executeUpdate();
       		}	
       	}
       	
       	else if (intent.equals("change")){
       		PreparedStatement s = connect().prepareStatement("update course set id = ?, name = ?, point = ?, descr = ? where id = ?");
            s.setString(1,id.trim());
            s.setString(2,name.trim());
            s.setInt(3,points);
            s.setString(4,description.trim());
            s.setString(5,id.trim());
            affectedRows =  s.executeUpdate();
       	}
           
           return affectedRows;
    }
    
    public int removeCourse(String id) throws SQLException {
        PreparedStatement s = connect().prepareStatement("delete from course where id = ?");
        s.setString(1,id);
        return s.executeUpdate();
    }
    
    public ResultSet getCoursesForStudent(String civic) throws SQLException{
        PreparedStatement stmnt = connect().prepareStatement("select * from studies where pnr = ?");
        stmnt.setString(1, civic);
        return stmnt.executeQuery();
    }
    
    public ResultSet getFinishedCoursesForStudent(String civic) throws SQLException{
    	 PreparedStatement stmnt = connect().prepareStatement("select * from studied where pnr = ?");
         stmnt.setString(1, civic);
         return stmnt.executeQuery();
    }
    
    
    /**
     * Funktioner för att starta/avsluta kurser
     * @throws SQLException 
     * 
     */
    
    private boolean isActive(String civic) throws SQLException{
    	PreparedStatement s = connect().prepareStatement("select * from studies where pnr = ?");
    	s.setString(1, civic);
    	ResultSet r = s.executeQuery();
    	if (r.next()){
    		return false;
    	}
    	else{
    		return true;
    	}
    	
    }
    
    private boolean maxPoints(String civic) throws SQLException {
    	boolean ok = true;
        PreparedStatement s = connect().prepareStatement("select Sum(point) from course where id in (select id from studies where pnr = ?)");
        s.setString(1,civic);
        ResultSet r = s.executeQuery();
        while(r.next()){
        	int amount = r.getInt(1);
        	if (amount >= 45){
        		ok =  false;
        	}
        	else{
        		ok =  true;
        	}
        }
		return ok;
    }
    
    public int startCourse(String civic, String id) throws SQLException {
    	ResultSet alt = getCourses();
    	boolean option = false;
    	while(alt.next()){
    		if (alt.getString(1).equals(id)){
    			option = true;
    		}
    	}
    	if(!option){
    		return 0;
    	}
    	alt = getFinishedCoursesForStudent(civic);
    	option = false;
    	while(alt.next()){
    		if (alt.getString(1).equals(id)){
    			option = true;
    		}
    	}
    	if(!option){
    		return 0;
    	}
    	
    	int temp = 0;
    	if (isActive(civic)){

            if (maxPoints(civic)){
                Connection con = connect();
                PreparedStatement s = con.prepareStatement("select * from studied where id = ? and pnr = ?");
                s.setString(1,civic);
                s.setString(2, id);
                
                
                if(s.executeQuery().next()){
                    System.out.println("kurs är redan avslutat");
                    temp = 0;
                }
                
                else{
                    s = con.prepareStatement("select * from studies where id = ? and pnr = ?");
                    s.setString(1,civic);
                    s.setString(2, id);
                    
                    if(s.executeQuery().next()){
                        System.out.println("student har redan påbörjat kurs");
                        temp = 0;
                    }
                    else{
                        s = con.prepareStatement("insert into studies values(?,?)");
                        s.setString(1,civic);
                        s.setString(2,id);

                        temp =  s.executeUpdate();
                    }
                }
            }
    	}
    	else{
    		temp = 0;
    	}
        
        return temp;
    }
    
    public int endCourse(String civic, String id, String grade) throws SQLException {
        PreparedStatement s = connect().prepareStatement("delete from studies where pnr = ? and id = ?");
        s.setString(1, civic.trim());
        s.setString(2, id.trim());
        s.executeUpdate();
        
        s = connect().prepareStatement("insert into studied values(?,?,?)");
        s.setString(1, civic.trim());
        s.setString(2, id.trim());
        s.setString(3, grade.trim());
        
        return s.executeUpdate();
    }

    public int cancelCourse(String civic, String id) throws SQLException {
        PreparedStatement s = connect().prepareStatement("delete from studies where pnr = ? and id = ?");
        s.setString(1, civic);
        s.setString(2, id);
        return s.executeUpdate();
    }
    
    
    /**
     * Statistik osv
     * 
     */
    
    public ResultSet studentResults(String civic, String id) throws SQLException {
        PreparedStatement s = connect().prepareStatement("select s.pnr, c.grade from student s join studied c on s.pnr = c.pnr where s.pnr = (select pnr from studied where pnr = ? and id = ?)");
        s.setString(1, civic);
        s.setString(2, id);
        
        return s.executeQuery();
    }
    
    public ResultSet courseResults(String id) throws SQLException {
        PreparedStatement s = connect().prepareStatement("select pnr, grade from studied where id =?");
        s.setString(1, id);
        
        return s.executeQuery();
    }
    
    public ResultSet studentsNotDone(String id) throws SQLException {
        PreparedStatement s = connect().prepareStatement("select pnr from studies where id =?");
        s.setString(1, id);
        
        return s.executeQuery();
    }
    
    public float numberOfA(String id) throws SQLException {
        PreparedStatement s = connect().prepareStatement("select grade from studied where id = ?");
        s.setString(1, id);
        
        ResultSet calc =  s.executeQuery();
        int a = 0;
        int theRest = 0;
        
        while(calc.next()){
            if (calc.getString(1).equals("A")){
                a++;
            }
            else{
                theRest++;
            }
        }
        
        return  a/(a + theRest);
    }
    
    public String highestFlow() throws SQLException {
        PreparedStatement s = connect().prepareStatement("select id, count(*) as passes from studied where grade != 'U' group by id");
        ResultSet calc = s.executeQuery();
        
        String best = null;
        int value = 0;
        
        while(calc.next()){
            if (calc.getInt(2) > value){
                best = calc.getString(1);
            }
        }
        
        return best;
    }

}
