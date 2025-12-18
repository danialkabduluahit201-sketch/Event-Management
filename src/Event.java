public class Event {
    private String name;
    private String location;
    private String date_time;
    private int event_id;
    private static int next_event=1;
    private Participant[] participants;
    private int counter;
    public Event(String name,String location,String date_time,int maximum_participants){
        this.event_id=next_event++;
        this.name=name;
        this.location=location;
        this.date_time=date_time;
        this.participants=new Participant[maximum_participants];
        this.counter=0;
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
    public int getCounter(){return counter;}
    public void setCounter(int counter){this.counter=counter;}
    public Participant getParticipant(int i){
        return participants[i];
    }
    public void setParticipant(int i,Participant participant){
        this.participants[i]=participant;
    }
    public void addParticipant(Participant p){
        if(counter<participants.length){
            participants[counter]=p;
            counter++;
            System.out.println(p.getFull_name()+" registered successfully!");
        }
        else System.out.println("The event is full");
    }

    public void displayParticipants() {
        if (counter == 0) System.out.println("No participant registered");
        else {
            for (int i = 0; i < counter; i++) participants[i].displayInfo();
        }
    }
    public void displayInfo(){
        System.out.println("ID: "+event_id+"\n"+"Event: "+name+"\n"+"Date and Time: "+date_time+"\n"+"Location: "+location);
    }
}
