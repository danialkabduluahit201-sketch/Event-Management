package com.eventmanagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String ConnectionURL="jdbc:postgresql://localhost:5432/Assignment(java)";
        Connection con=null;
        ResultSet rs=null;
        Statement stmt=null;
        try{
            con= DriverManager.getConnection(ConnectionURL,"postgres","200888");
            stmt=con.createStatement();
            rs= stmt.executeQuery("select * from female_participants");
            while(rs.next()) System.out.println(rs.getInt("female_id")+" "
                    +rs.getString("first_name")
                    +" "+rs.getString("last_name")
                    +" "+rs.getInt("age")
                    +" "+rs.getString("t_shirt_size"));
        }
        catch(Exception e){
            System.out.println("Exception occured!");
        } finally{
            try{
                con.close();
            } catch(Exception e){
                System.out.println("Exception occured!");
            }
        }

        /*Scanner sc = new Scanner(System.in);

        System.out.println("How many events do you need?");
        int n = sc.nextInt();
        sc.nextLine();

        Event[] events = new Event[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nDetails for event " + (i + 1));
            System.out.print("Event name: ");
            String name = sc.nextLine();
            System.out.print("Location: ");
            String location = sc.nextLine();
            System.out.print("Date and time: ");
            String date_time = sc.nextLine();
            System.out.print("Maximum amount of participants: ");
            int maximum_participants = sc.nextInt();
            sc.nextLine();

            events[i] = new Event(name, location, date_time, maximum_participants);
            DataPool.addEvent(events[i]);
        }

        for (int i = 0; i < n; i++) {
            System.out.println("\nAdd participants for " + events[i].getName());
            System.out.print("How many participants? ");
            int m = sc.nextInt();
            sc.nextLine();

            for (int j = 0; j < m; j++) {
                System.out.println("Participant " + (j + 1));
                System.out.print("Enter full name: ");
                String full_name = sc.nextLine();
                System.out.print("Age: ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Gender (M/F): ");
                String gender = sc.nextLine();

                Participant p;

                if (gender.equalsIgnoreCase("M")) {
                    System.out.print("T-shirt size: ");
                    String Tshirt_size = sc.nextLine();
                    p = new MaleParticipant(full_name, age, email,Tshirt_size);
                } else  {
                    String Tshirt_size_F=sc.nextLine();
                    p = new FemaleParticipant(full_name, age, email,Tshirt_size_F);
                }

                System.out.print("Ticket type: ");
                String ticket_type = sc.nextLine();

                events[i].addParticipant(p);
                DataPool.addParticipant(p);

                EventManager registration = new EventManager(events[i], p, ticket_type);
                DataPool.addRegistration(registration);
            }
        }

        System.out.println("\nDATA POOL OPERATIONS DEMONSTRATION\n");

        DataPool.displayAllEvents();
        DataPool.displayAllParticipants();
        DataPool.displayAllRegistrations();

        System.out.println("\nSEARCHING: Find Event by Name\n");
        System.out.print("Enter event name to search: ");
        String searchName = sc.nextLine();

        Event foundEvent = DataPool.searchEventByName(events, n, searchName);

        if (foundEvent != null) {
            System.out.println("\nEvent found:");
            System.out.println(foundEvent);
        } else {
            System.out.println("\nEvent not found");
        }

        System.out.println("\nFILTERING: Show Participants by Gender\n");
        System.out.print("Enter event name: ");
        String eventName = sc.nextLine();

        Event selectedEvent = DataPool.searchEventByName(events, n, eventName);

        if (selectedEvent != null) {
            System.out.print("Enter gender to filter (Male/Female): ");
            String filterGender = sc.nextLine();

            System.out.println("\nParticipants with gender: " + filterGender);
            DataPool.filterParticipantsByGender(selectedEvent, filterGender);
        } else {
            System.out.println("Event not found");
        }

        System.out.println("\nSORTING: Sort Participants by Age\n");
        System.out.print("Enter event name to sort its participants: ");
        String sortEventName = sc.nextLine();

        Event eventToSort = DataPool.searchEventByName(events, n, sortEventName);

        if (eventToSort != null) {
            System.out.println("\nParticipants BEFORE sorting:");
            eventToSort.displayParticipants();

            DataPool.sortParticipantsByAge(eventToSort);

            System.out.println("\nParticipants AFTER sorting by age:");
            eventToSort.displayParticipants();
        } else {
            System.out.println("Event not found");
        }
        sc.close();*/
    }
}
