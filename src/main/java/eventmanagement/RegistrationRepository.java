package eventmanagement;

import java.util.List;

public interface RegistrationRepository {
    void create(EventManager registration);
    List<EventManager> readAllRegistrations();
    EventManager readById(int id);
    List<EventManager> readByEventId(int eventId);
    List<EventManager> readByParticipantId(int participantId);
    void update(EventManager registration);
    void delete(int id);
}
