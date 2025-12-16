public class Event {
    private int event_id;
    private String name;
    private String location;
    private String date_time;
    private String description;
    public Event(int event_id,String name,String location,String date_time){
        this.event_id=event_id;
        this.name=name;
        this.location=location;
        this.date_time=date_time;
    }
    public Event(int event_id,String name,String location,String date_time,String description){
        this.event_id=event_id;
        this.name=name;
        this.location=location;
        this.date_time=date_time;
        this.description=description;
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
    public void displayInfo(){
        System.out.println("ID: "+event_id+"\n"+"Event: "+name+"\n"+"Date and Time: "+date_time+"\n"+"Location: "+location+"\n"+"Description:" +
                description);
    }
}
