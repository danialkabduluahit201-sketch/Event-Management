import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many events do you need?");
        int n = sc.nextInt();
        sc.nextLine();
        Event[] events = new Event[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for event " + (i + 1));
            System.out.println("Event name: ");
            String name = sc.nextLine();
            System.out.println("location: ");
            String location = sc.nextLine();
            System.out.println("date and time");
            String date_time = sc.nextLine();
            System.out.println("Enter the capacity: ");
            int maximum_participants = sc.nextInt();
            sc.nextLine();
            events[i] = new Event(name, location, date_time, maximum_participants);
        }
        EventManager[] registrations = new EventManager[100];
        int registration_count = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("Register participants for event: " + events[i].getName());
            System.out.println("How many participants for this event?");
            int m = sc.nextInt();
            sc.nextLine();
            for (int j = 0; j < m; j++) {
                System.out.println("Participant " + (j + 1));
                System.out.println("Enter full name: ");
                String full_name = sc.nextLine();
                System.out.println("Enter age: ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter email: ");
                String email = sc.nextLine();
                System.out.println("Enter ticket type: ");
                String ticket_type = sc.nextLine();
                Participant p = new Participant(full_name, age, email);
                events[i].addParticipant(p);
                EventManager registration = new EventManager(events[i], p, ticket_type);
                registrations[registration_count] = registration;
                registration_count++;
            }
        }
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
