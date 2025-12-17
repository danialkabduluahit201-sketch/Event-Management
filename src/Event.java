public class Event {
    private String name;
    private String location;
    private String date_time;
    private String description;
    private static int next_event=1;
    private int event_id;
    public Event(String name,String location,String date_time){
        this.event_id=next_event++;
        this.name=name;
        this.location=location;
        this.date_time=date_time;
    }
    public Event(String name,String location,String date_time,String description){
        this.event_id=next_event++;
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
    public void displayInfo(){
        System.out.println("ID: "+event_id+"\n"+"Event: "+name+"\n"+"Date and Time: "+date_time+"\n"+"Location: "+location+"\n"+"Description:" +
                description);
    }
}
