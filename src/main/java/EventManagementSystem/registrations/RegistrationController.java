package EventManagementSystem.registrations;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin(origins = "*")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public String registerParticipantToEvent(@RequestBody Map<String, Object> data) {

        int eventId = (Integer) data.get("eventId");
        int participantId = (Integer) data.get("participantId");
        String ticketType = (String) data.get("ticketType");

        registrationService.registerParticipantToEvent(eventId, participantId, ticketType);

        return "Registration successful";
    }

    @GetMapping
    public List<EventManager> getAllRegistrations(){
        return registrationService.getAllRegistrations();
    }
    @GetMapping("/{id}")
    public EventManager getRegistrationById(@PathVariable int id) {

       return registrationService.getRegistrationById(id);
    }

    @GetMapping("/event/{eventId}")
    public List<EventManager> getEventRegistrations(@PathVariable int eventId) {
        return registrationService.getEventRegistrations(eventId);
    }

    @GetMapping("/participant/{participantId}")
    public List<EventManager> getParticipantRegistrations(@PathVariable int participantId) {
        return registrationService.getParticipantRegistrations(participantId);
    }

    @PutMapping("/{id}")
    public EventManager updateRegistration(@PathVariable int id, @RequestBody Map<String, Object> data) {

        EventManager registration = registrationService.getRegistrationById(id);

        if (registration == null) {
            throw new RuntimeException("Registration not found with ID: " + id);
        }

        if (data.containsKey("eventId")) {
            registration.setEventId((Integer) data.get("eventId"));
        }
        if (data.containsKey("participantId")) {
            registration.setParticipantId((Integer) data.get("participantId"));
        }
        if (data.containsKey("ticketType")) {
            registration.setTicketType((String) data.get("ticketType"));
            EventManager temp = new EventManager();
            temp.setTicketType((String) data.get("ticketType"));
            registration.setPaymentAmount(temp.getPaymentAmount());
        }
        if (data.containsKey("status")) {
            registration.setStatus((String) data.get("status"));
        }

        registrationService.updateRegistration(registration);

        return registration;
    }

    @PatchMapping("/{id}")
    public String cancelRegistration(@PathVariable int id) {

        registrationService.cancelRegistration(id);
        return "Registration cancelled successfully";
    }


    @DeleteMapping("/{id}")
    public String deleteRegistration(@PathVariable int id) {
        registrationService.deleteRegistration(id);
        return "Registration deleted successfully";
    }
}
