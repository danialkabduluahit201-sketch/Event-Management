public class EventManager {
    private Event event;
    private Participant participant;
    private String ticket_type;
    private static int nextRegistrationId=1;
    public int registrationId;
    public EventManager(Event event,Participant participant,String ticket_type){
        this.event=event;
        this.participant=participant;
        this.ticket_type=ticket_type;
        registrationId=nextRegistrationId++;
    }
    public EventManager(Event event,Participant participant){
        this.event=event;
        this.participant=participant;
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
}
