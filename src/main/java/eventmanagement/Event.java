package eventmanagement;

public class Event {
    private int eventId;
    private String name;
    private String description;
    private String location;
    private String dateTime;  // Simplified as String
    private int maxCapacity;
    private String status;  // "UPCOMING", "COMPLETED", "CANCELLED"
    private String organizerCompany;
    private int currentParticipants;

    // Default constructor
    public Event() {
        this.status = "UPCOMING";
        this.currentParticipants = 0;
    }

    public Event(String name, String description, String location,
                 String dateTime, int maxCapacity, String organizerCompany) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
        this.maxCapacity = maxCapacity;
        this.organizerCompany = organizerCompany;
        this.status = "UPCOMING";
        this.currentParticipants = 0;
    }

    // Getters and setters
    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }

    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getOrganizerCompany() { return organizerCompany; }
    public void setOrganizerCompany(String organizerCompany) { this.organizerCompany = organizerCompany; }

    public int getCurrentParticipants() { return currentParticipants; }
    public void setCurrentParticipants(int currentParticipants) { this.currentParticipants = currentParticipants; }

    public boolean isFull() {
        return currentParticipants >= maxCapacity;
    }
}