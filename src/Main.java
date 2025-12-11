public class Main{
    public static void main(String[] args){
        Event event1=new Event("K-pop concert","Main Hall","17th January at 15:00");
        Event event2=new Event("Team training","Main Sports centre","20th January at 19:00","Only elder group!");
        Participant participant1=new Participant("Oralbai Zhanat",16,"ozhanat2009@gmail.com");
        Participant participant2=new Participant("Kabduluakhit Danial",17,"danialkabuluahit201@gmail.com");
        if(event1.equals(event2)) System.out.println("They are equal"); //comparing two events
        else System.out.println("They are not equal");
        EventManager eventmanager1=new EventManager(event1,participant1,"vip");
        EventManager eventmanager2=new EventManager(event2,participant2);
        if(eventmanager1.registrationId==eventmanager2.registrationId);
        else System.out.println("They are not equal");    }
}