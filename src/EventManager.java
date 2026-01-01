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
        this.registrationId=nextRegistrationId++;
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
    public static void CompareEvents(Event e1, Event e2){
        if(e1.getName().equals(e2.getName()))
            System.out.println("Events have the same name");
        else System.out.println("The name of two events differ");
    }
   public static void CompareParticipants(Participant p1,Participant p2){
        if(p1.getEmail().equals(p2.getEmail())){
            System.out.println("This is the same person");
        }
        else System.out.println("Not the same person");
    }
    @Override
    public String toString() {
        return "Registration{id=" + registrationId + ", participant=" + participant.getFull_name() +
                ", event=" + event.getName() + ", ticket='" + ticket_type + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EventManager that = (EventManager) obj;
        return registrationId == that.registrationId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(registrationId);
    }
    public static Event searchEventByName(Event[] events, int n, String name) {
        for (int i = 0; i < n; i++) {
            if (events[i].getName().equalsIgnoreCase(name)) {
                return events[i];
            }
        }
        return null;
    }
    public static void filterParticipantsByGender(Event event, String gender) {
        for (int i = 0; i < event.getCounter(); i++) {
            Participant p = event.getParticipant(i);
            if (p.getGender().equalsIgnoreCase(gender)) {
                p.displayInfo();
            }
        }
    }
    public static void sortParticipantsByAge(Event event) {
        for (int i = 0; i < event.getCounter() - 1; i++) {
            for (int j = 0; j < event.getCounter() - i - 1; j++) {
                if (event.getParticipant(j).getAge() >
                        event.getParticipant(j + 1).getAge()) {

                    Participant temp = event.getParticipant(j);
                    event.setParticipant(j, event.getParticipant(j + 1));
                    event.setParticipant(j + 1, temp);
                }
            }
        }
    }
}
