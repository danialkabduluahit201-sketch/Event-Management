public class EventManager {
    private Event event;
    private Participant participant;
    private String ticket_type;
    private static int nextRegistrationId=1;
    private int registrationId;
    public EventManager(Event event,Participant participant,String ticket_type){
        this.event=event;
        this.participant=participant;
        this.ticket_type=ticket_type;
        registrationId=nextRegistrationId++;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event){
        this.event=event;
    }
    public Participant getParticipant(){
        return participant;
    }
    public void setParticipant(Participant participant){
        this.participant=participant;
    }
    public String getTicket_type(){
        return ticket_type;
    }
    public void setTicket_type(String ticket_type){
        this.ticket_type=ticket_type;
    }
    public void displayRegistrations(){
        System.out.println("Registration #"+registrationId+"-"+participant.getFull_name()+" registered for "+event.getName()+" with "+ticket_type+ " ticket");
    }
    public static void CompareEvents(Event e1,Event e2){
        if(e1.getName().equals(e2.getName()))
            System.out.println("Events have the same name");
        else System.out.println("The name of two events differ");
    }
    public static void CompareParticipants(Participant p1,Participant p2){
        if(p1.getFull_name().equals(p2.getFull_name())) System.out.println("This is the same client");
        else System.out.println("Other person");
    }
}
