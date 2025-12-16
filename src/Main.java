public class Main{
    public static void main(String[] args){
        Event event1=new Event(1,"K-pop concert","Main Hall","17th January at 15:00");
        Event event2=new Event(2,"Team training","Main Sports centre","20th January at 19:00","Only elder group!");
        Participant participant1=new Participant(1,"Oralbai Zhanat",16,"ozhanat2009@gmail.com");
        Participant participant2=new Participant(2,"Kabduluakhit Danial",17,"danialkabuluahit201@gmail.com");
        EventManager eventmanager1=new EventManager(event1,participant1,"vip");
        EventManager eventmanager2=new EventManager(event2,participant2,"standard");
        event1.displayInfo();
        event2.displayInfo();
        participant1.displayInfo();
        participant2.displayInfo();
        eventmanager1.displayRegistrations();
        eventmanager2.displayRegistrations();
        EventManager.CompareEvents(event1,event2);
        EventManager.CompareParticipants(participant1,participant2);
    }
}