import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many events do you need?");
        int n=sc.nextInt();
        sc.nextLine();
        Event[] events=new Event[n];
        for(int i=0;i<n;i++){
            System.out.println("Details for event"+(i+1));
            System.out.println("Event name: ");
            String name=sc.nextLine();
            System.out.println("location: ");
            String location=sc.nextLine();
            System.out.println("Date and time: ");
            String date_time=sc.nextLine();
            System.out.println("Maximum amount of participants: ");
            int maximum_participants=sc.nextInt();
            sc.nextLine();
            events[i]=new Event(name,location,date_time,maximum_participants);
        }
        EventManager[] registrations=new EventManager[100];
        int registration_count=0;
        for(int i=0;i<n;i++){
            System.out.println("Add participants for"+events[i].getName());
            System.out.println("How many participants? ");
            int m=sc.nextInt();
            sc.nextLine();
            for(int j=0;j<m;j++){
                System.out.println("Enter details for participant"+(j+1));
                System.out.println("Enter full_name: ");
                String full_name=sc.nextLine();
                System.out.println("Age: ");
                int age=sc.nextInt();
                sc.nextLine();
                System.out.println("Email: ");
                String email=sc.nextLine();
                System.out.println("Ticket type: ");
                String ticket_type=sc.nextLine();
                System.out.println("Gender (M or F");
                String gender=sc.nextLine();
                Participant p;
                if(gender.equalsIgnoreCase("M")) {
                    System.out.println("T-shirt size: ");
                    String Tshirt_size=sc.nextLine();
                    p=new MaleParticipant(full_name,age,email,Tshirt_size);
                }
                else {
                    System.out.println("T-shirt size: ");
                    String Tshirt_size = sc.nextLine();
                    p = new FemaleParticipant(full_name, age, email, Tshirt_size);
                }
                events[i].addParticipant(p);
                EventManager registration=new EventManager(events[i],p,ticket_type);
                registrations[registration_count]=registration;
                registration_count++;
            }
        }
        sc.close();

            for (int i = 0; i < n; i++) {
                events[i].displayInfo();
                System.out.print("Participants: ");
                events[i].displayParticipants();
            }
            for (int i = 0; i < registration_count; i++) {
                registrations[i].displayRegistrations();
            }
        if (n >= 2) {
            EventManager.CompareEvents(events[0], events[1]);
            if (events[0].getCounter() > 0 && events[1].getCounter() > 0) {
                EventManager.CompareParticipants(events[0].getParticipant(0), events[1].getParticipant(0));
            }
        }
    }
}
