public class Main{
    public static void main(String[] args){
        Event event1=new Event("K-pop concert","Main office","17th January at 15:00");
        Participant participant1=new Participant("Oralbai Zhanat",16,"ozhanat2009@gmail.com");
        EventManager eventManager=new EventManager(event1,participant1,"vip");
        eventManager.displayRegistrations();
    }
}
