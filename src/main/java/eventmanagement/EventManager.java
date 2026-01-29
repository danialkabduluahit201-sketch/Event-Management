package eventmanagement;

public class EventManager {
    private int registrationId;
    private int eventId;
    private int participantId;
    private String ticketType;  // "VIP", "REGULAR", "STUDENT", "FREE"
    private String registrationDate;
    private String status;  // "CONFIRMED", "CANCELLED", "ATTENDED"
    private double paymentAmount;

    public EventManager() {
    }

    public EventManager(int eventId, int participantId, String ticketType) {
        this.eventId = eventId;
        this.participantId = participantId;
        this.ticketType = ticketType;
        this.status = "CONFIRMED";
        this.paymentAmount = getTicketPrice(ticketType);
    }

    private double getTicketPrice(String ticketType) {
        switch (ticketType.toUpperCase()) {
            case "VIP": return 100.0;
            case "REGULAR": return 50.0;
            case "STUDENT": return 25.0;
            case "FREE": return 0.0;
            default: return 50.0;
        }
    }

    // Getters and setters
    public int getRegistrationId() { return registrationId; }
    public void setRegistrationId(int registrationId) { this.registrationId = registrationId; }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public int getParticipantId() { return participantId; }
    public void setParticipantId(int participantId) { this.participantId = participantId; }

    public String getTicketType() { return ticketType; }
    public void setTicketType(String ticketType) { this.ticketType = ticketType; }

    public String getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(String registrationDate) { this.registrationDate = registrationDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(double paymentAmount) { this.paymentAmount = paymentAmount; }
}
