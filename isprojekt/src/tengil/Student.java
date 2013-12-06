package tengil;

public class Student {
    private String civic;
    private String name;
    
    public Student(String civic, String name) {
        setCivic(civic);
        setName(name);
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
