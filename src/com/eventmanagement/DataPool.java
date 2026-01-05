package com.eventmanagement;

import java.util.ArrayList;
import java.util.List;

public class DataPool {
    private static List<Event> allEvents = new ArrayList<>();
    private static List<Participant> allParticipants = new ArrayList<>();
    private static List<EventManager> allRegistrations = new ArrayList<>();

    public static void addEvent(Event event) {
        if (!allEvents.contains(event)) {
            allEvents.add(event);
        }
    }

    public static void addParticipant(Participant participant) {
        if (!allParticipants.contains(participant)) {
            allParticipants.add(participant);
        }
    }

    public static void addRegistration(EventManager registration) {
        if (!allRegistrations.contains(registration)) {
            allRegistrations.add(registration);
        }
    }

    public static List<Event> getAllEvents() {
        return new ArrayList<>(allEvents);
    }

    public static List<Participant> getAllParticipants() {
        return new ArrayList<>(allParticipants);
    }

    public static List<EventManager> getAllRegistrations() {
        return new ArrayList<>(allRegistrations);
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
                if (event.getParticipant(j).getAge() > event.getParticipant(j + 1).getAge()) {
                    Participant temp = event.getParticipant(j);
                    event.setParticipant(j, event.getParticipant(j + 1));
                    event.setParticipant(j + 1, temp);
                }
            }
        }
    }

    public static void displayAllEvents() {
        System.out.println("\nALL EVENTS");

        if (allEvents.size() == 0) {
            System.out.println("No events available.");
        } else {
            for (int i = 0; i < allEvents.size(); i++) {
                Event event = allEvents.get(i);
                System.out.println(event);
            }
        }
        System.out.println();
    }

    public static void displayAllParticipants() {
        System.out.println("\nALL PARTICIPANTS");

        if (allParticipants.size() == 0) {
            System.out.println("No participants available.");
        } else {
            for (int i = 0; i < allParticipants.size(); i++) {
                Participant p = allParticipants.get(i);
                System.out.println(p);
            }
        }
        System.out.println();
    }

    public static void displayAllRegistrations() {
        System.out.println("\nALL REGISTRATIONS");

        if (allRegistrations.size() == 0) {
            System.out.println("No registrations available.");
        } else {
            for (int i = 0; i < allRegistrations.size(); i++) {
                EventManager reg = allRegistrations.get(i);
                System.out.println(reg);
            }
        }
        System.out.println();
    }
}
