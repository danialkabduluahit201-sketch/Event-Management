package eventmanagement;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    public ParticipantService(ParticipantRepository participantRepository){
        this.participantRepository=participantRepository;
    }
    public void create(Participant participant){
        participantRepository.createParticipant(participant);
    }
    public Participant readById(int id){
        return participantRepository.readById(id);
    }
    public List<Participant> readAllParticipants(){
        return participantRepository.readAll();
    }
    public void update(Participant participant){
        participantRepository.update(participant);
    }
    public void delete(int id){
        participantRepository.delete(id);
    }
}
