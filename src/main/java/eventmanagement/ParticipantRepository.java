package eventmanagement;

import java.util.List;

public interface ParticipantRepository {
    void createParticipant(Participant participant);
    Participant readById(int id);
    Participant readByEmail(String email);
    List<Participant> readAll();
    void update(Participant participant);
    void delete(int id);
}
