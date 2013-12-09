package isprojekt.src.tengil;

import java.sql.*;
    
public class ConnectBot{
    
    public ConnectBot() throws SQLException {
        connectToDatabase();
    }
    
    /**
     * Generella funktioner
     *
     */

    public void connectToDatabase() throws SQLException {
        DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
    }
    
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("JDBC:ODBC:isprojekt_new");
    }
    
    
    /**
     * Studentfunktioner
     * 
     */
    
    public ResultSet getStudent(String civic) throws SQLException {
        Connection con = connect();
        
        PreparedStatement stmnt = con.prepareStatement("select * from student where civic = ?");
        stmnt.setString(1,civic); 
        ResultSet found = stmnt.executeQuery();
        
        return found;
    }
    
    public ResultSet getStudents() throws SQLException {
        Connection con = connect();
        
        PreparedStatement stmnt = con.prepareStatement("select * from student");
        ResultSet found = stmnt.executeQuery();
        
        return found;
    }
    
    public int addStudent(String civic, String name) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("select * from student where civic = ?");
        s.setString(1,civic);
        int temp;

        
        if(s.executeQuery().next()){
            System.out.println("student finns redan");
            temp = 0;
        }
        else{
            s = con.prepareStatement("insert into student values(?,?)");
            s.setString(1,civic);
            s.setString(2,name);
            temp =  s.executeUpdate();
        }
        
        return temp;
        
    }
    
    public int removeStudent(String civic) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("delete from student where civic = ?");
        s.setString(1,civic);
        int temp = s.executeUpdate();
        
        return temp;
    }
    
    /**
     * Kursfunktioner
     * 
     */
    
    public ResultSet getCourse(String id) throws SQLException {
        Connection con = connect();
        
        PreparedStatement stmnt = con.prepareStatement("select * from course where id = ?");
        stmnt.setString(1,id); 
        ResultSet found = stmnt.executeQuery();
        
        return found;
    }
    
    public ResultSet getCourses() throws SQLException {
        Connection con = connect();
        
        PreparedStatement stmnt = con.prepareStatement("select * from course");
        ResultSet found = stmnt.executeQuery();
        
        return found;
    }
    
    public int addCourse(String id, String name, String contents, int points) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("select * from course where id = ?");
        s.setString(1,id);
        int temp;

        
        if(s.executeQuery().next()){
            System.out.println("kurs finns redan");
            temp = 0;
        }
        else{
            s = con.prepareStatement("insert into course values(?,?,?,?)");
            s.setString(1,id);
            s.setString(2,name);
            s.setString(3,contents);
            s.setInt(4,points);
            temp =  s.executeUpdate();
        }
        
        return temp;
        
    }
    
    public int removeCourse(String id) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("delete from course where id = ?");
        s.setString(1,id);
        int temp = s.executeUpdate();
        
        return temp;
    }
    
    /**
     * Funktioner f�r att starta/avsluta kurser
     * 
     */
    
    private boolean maxPoints(String civic) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("select Sum(points) from course where id in (select courseId from studies_active where civic ='?')");
        s.setString(1,civic);
        
        if (s.executeQuery().getInt(1) >= 45){
            return false;
        }
        else{
            return true;
        }
    }
    
    public int startCourse(String civic, String id) throws SQLException {
        int temp = 0;
        
        if (!maxPoints(civic)){
            Connection con = connect();
            PreparedStatement s = con.prepareStatement("select * from studies_inactive where courseId = ? and civic = ?");
            s.setString(1,civic);
            s.setString(2, id);
            
            
            if(s.executeQuery().next()){
                System.out.println("kurs �r redan avslutat");
                temp = 0;
            }
            
            else{
                s = con.prepareStatement("select * from studies_active where courseId = ? and civic = ?");
                s.setString(1,civic);
                s.setString(2, id);
                
                if(s.executeQuery().next()){
                    System.out.println("student har redan p�b�rjat kurs");
                    temp = 0;
                }
                else{
                    s = con.prepareStatement("insert into studies_active values(?,?)");
                    s.setString(1,civic);
                    s.setString(2,id);

                    temp =  s.executeUpdate();
                }
            }
        }

        
        return temp;
    }
    
    public int endCourse(String civic, String id, String grade) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("delete from studies_active where civic = ? and courseId = ?");
        s.setString(1, civic);
        s.setString(2, id);
        s.executeUpdate();
        
        s = con.prepareStatement("insert into studies_inactive values(?,?,?)");
        s.setString(1, civic);
        s.setString(2, id);
        s.setString(3, grade);
        
        return s.executeUpdate();
    }

    public int cancelCourse(String civic, String id) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("delete from studies_active where civic = ? and courseId = ?");
        s.setString(1, civic);
        s.setString(2, id);
        return s.executeUpdate();
    }
    
    
    /**
     * Statistik osv
     * 
     */
    
    public ResultSet studentResults(String civic, String id) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement(" select s.civic, c.grade from student s join studies_inactive c on s.civic = c.civic where s.civic = (select civic from studies_inactive where civic = ? and courseId = ?)");
        s.setString(1, civic);
        s.setString(2, id);
        
        return s.executeQuery();
    }
    
    public ResultSet courseResults(String id) throws SQLException {
        Connection con = connect();
        PreparedStatement s = con.prepareStatement("select civic, grade from studies_inactive where courseId =?");
        s.setString(1, id);
        
        return s.executeQuery();
    }
    
    public ResultSet studentsNotDone(){
        
    }
    
    public ResultSet numberOfA(){
        
    }
    
    public ResultSet highestFlow(){
        
    }

}
