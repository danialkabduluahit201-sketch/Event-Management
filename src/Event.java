import java.sql.SQLOutput;

public class Event {
    private String name;
    private String location;
    private String date_time;
    public Event(String name,String location,String date_time){
        this.name=name;
        this.location=location;
        this.date_time=date_time;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location=location;
    }
    public String getDate_time(){
        return date_time;
    }
    public void setDate_time(String date_time){
        this.date_time=date_time;
    }
    public void displayInfo(String name,String location,String date_time){
        System.out.println("Event: "+name+"\n"+"Date and Time: "+date_time+"\n"+"Location: "+location);
    }
}
