package isprojekt.src.model.logic;

public class Course {
    private String id;
    private String name;
    private String contents;
    private int points;
    
    public Course(String id, int points) {
        setId(id);
        setPoints(points);
    }  
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setContents(String contents){
        this.contents = contents;
    }
    public String getContents(){
        return contents;
    }
    public void setPoints(int points){
        this.points = points;
    }
    public int getPoints(){
        return points;
    }
    
}
