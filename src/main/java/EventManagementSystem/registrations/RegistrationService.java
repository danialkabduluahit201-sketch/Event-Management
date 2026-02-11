package EventManagementSystem.registrations;

import EventManagementSystem.Events.Event;
import EventManagementSystem.Events.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;

    public RegistrationService(RegistrationRepository registrationRepository,
                               EventRepository eventRepository) {
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
    }

    public void registerParticipantToEvent(int eventId, int participantId, String ticketType) {

        EventManager registration = new EventManager(eventId, participantId, ticketType);

        registrationRepository.create(registration);


        Event event = eventRepository.readByID(eventId);
        if (event != null) {
            event.setCurrentParticipants(event.getCurrentParticipants() + 1);
            eventRepository.update(event);
        }
    }
    public List<EventManager> getAllRegistrations(){
        return registrationRepository.readAllRegistrations();
    }

    public EventManager getRegistrationById(int id) {
        return registrationRepository.readById(id);
    }

    public List<EventManager> getEventRegistrations(int eventId) {
        return registrationRepository.readByEventId(eventId);
    }

    public List<EventManager> getParticipantRegistrations(int participantId) {
        return registrationRepository.readByParticipantId(participantId);
    }

    public void updateRegistration(EventManager registration) {
        registrationRepository.update(registration);
    }

    public void cancelRegistration(int registrationId) {
        EventManager registration = registrationRepository.readById(registrationId);
        if (registration != null) {
            registration.setStatus("CANCELLED");
            registrationRepository.update(registration);

            Event event = eventRepository.readByID(registration.getEventId());
            if (event != null) {
                event.setCurrentParticipants(Math.max(0, event.getCurrentParticipants() - 1));
                eventRepository.update(event);
            }
        }
    }

    public void deleteRegistration(int id) {

        EventManager registration = registrationRepository.readById(id);

        if (registration != null) {
            registrationRepository.delete(id);

            if (!registration.getStatus().equals("CANCELLED")) {
                Event event = eventRepository.readByID(registration.getEventId());
                if (event != null) {
                    event.setCurrentParticipants(Math.max(0, event.getCurrentParticipants() - 1));
                    eventRepository.update(event);
                }
            }
        }
    }
}
