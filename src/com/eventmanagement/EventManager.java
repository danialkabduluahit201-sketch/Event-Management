package com.eventmanagement;

public class EventManager {
    private Event event;
    private Participant participant;
    private String ticket_type;
    private static int nextRegistrationId=1;
    private int registrationId;
    public EventManager(Event event, Participant participant, String ticket_type){
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
    }
