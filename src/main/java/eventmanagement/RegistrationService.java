package eventmanagement;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    public RegistrationService(RegistrationRepository registrationRepository){
        this.registrationRepository=registrationRepository;
    }
    public void create(EventManager registration){
        registrationRepository.create(registration);
    }
    public List<EventManager> readAllRegistrations(){
        return registrationRepository.readAllRegistrations();
    }
    public EventManager readById(int id){
        return registrationRepository.readById(id);
    }
    public List<EventManager> readByEventId(int id){
        return registrationRepository.readByEventId(id);
    }
    public List<EventManager> readByParticipantID(int id){
        return registrationRepository.readByParticipantId(id);
    }
    public void update(EventManager registration){
        registrationRepository.update(registration);
    }
    public void delete(int id){
        registrationRepository.delete(id);
    }
}
