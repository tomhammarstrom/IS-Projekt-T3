package controller;

import java.sql.*;

import data_access_layer.DAL;

public class Controller {
    private DAL cb;
    private Validator v;
    
    
    public Controller() throws SQLException {
        cb = new DAL();
        v = new Validator();
    }
    
    /**
     * Error
     * 
     */
    
    public String getCurrentErrorMessage(){
    	return cb.getCurrentErrorMessage();
    }
    
    
    /**
     * Studentfunktioner
     * 
     */
    
    public int addStudent(String civic, String name, String address) throws SQLException {
        return cb.addStudent("add", civic, name,address);
    }
    
    public int changeStudent(String civic, String name, String address) throws SQLException{
    	return cb.addStudent("change", civic, name, address);
    }
    
    public int removeStudent(String civic) throws SQLException {
        return cb.removeStudent(civic);
    }
    
    
    public ResultSet getStudent(String civic) throws SQLException {
        return cb.getStudent(civic);
    }
    
    public ResultSet getStudents() throws SQLException {
        return cb.getStudents();
    }
    public ResultSet getFinishedWithCourse(String id)throws SQLException{
    	return cb.getFinishedWithCourse(id);
    }
    public ResultSet getNotFinishedWithCourse(String id)throws SQLException{
    	return cb.getNotFinishedWithCourse(id);
    }
    
    /**
     * Kursfunktioner
     * 
     */
    
    public int addCourse(String id, String name, String description, int points) throws SQLException {
        return cb.addCourse("add",id, name, description, points);
    }
    public int changeCourse(String id, String name, String description, int points) throws SQLException {
    	return cb.addCourse("change",  id, name, description, points);
    }
    
    public int removeCourse(String id) throws SQLException {
        return cb.removeCourse(id);
    }
    
    public ResultSet getCourse(String id) throws SQLException {
        return cb.getCourse(id);
    }
    
    public ResultSet getCourses() throws SQLException {
        return cb.getCourses();
    }
    
    public ResultSet getCoursesForStudent(String civic) throws SQLException{
    	return cb.getCoursesForStudent(civic);
    }
    
    public ResultSet getFinishedCoursesForStudent(String civic) throws SQLException{
    	return cb.getFinishedCoursesForStudent(civic);
    }
    
    /**
     * Kurshantering
     * 
     */
    
    public int startCourse(String civic, String id) throws SQLException {
        return cb.startCourse(civic, id);
    }
    
    public int endCourse(String civic, String id, String grade) throws SQLException{
        return cb.endCourse(civic, id, grade);
    }
    
    public int cancelCourse(String civic, String id) throws SQLException{
        return cb.cancelCourse(civic, id);
    }
    
    /**
     * Statistik osv
     * 
     */
    
    public ResultSet studentResults(String civic, String id) throws SQLException { 
        return cb.studentResults(civic, id);
    }
    
    public ResultSet courseResults(String id) throws SQLException {
        return cb.courseResults(id);
    }
    
    public ResultSet studentsNotDone(String id) throws SQLException {
        return cb.studentsNotDone(id);
    }
    
    public float numberOfA(String id) throws SQLException {
       return cb.numberOfA(id);
    }
    
    public String highestFlow() throws SQLException {
       return cb.highestFlow();
    }
    
    /**
     * Validerare
     */
    
    public boolean validateNotNull(String input){
    	return v.validateNotNull(input);
    }
    
    public boolean validateNumbers(String input){
    	return v.validateNumbers(input);
    }
    

}
