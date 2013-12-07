package isprojekt.src.model.logic;

import java.util.ArrayList;

public class Student {
    private String civic;
    private String name;
    
    private ArrayList<Course> activeCourses;

    public void setActiveCourses(ArrayList<Course> activeCourses) {
        this.activeCourses = activeCourses;
    }

    public ArrayList<Course> getActiveCourses() {
        return activeCourses;
    }
    public void addActiveCouse(Course c){
        activeCourses.add(c);
    }
    
    public void removeActiveCourse(Course c){
        
    }


    public Student(String civic) {
        setCivic(civic);
    }
    
    
    public void setCivic(String civic){
        this.civic = civic;
    }
    
    public String getCivic(){
        return civic;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
}
