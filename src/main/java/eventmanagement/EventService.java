package eventmanagement;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository){
        this.eventRepository=eventRepository;
    }
    public void createEvent(Event event){
        eventRepository.create(event);
    }
    public Event readEventById(int id){
        return eventRepository.readByID(id);
    }
    public List<Event> readAllEvents(){
        return eventRepository.readAll();
    }
    public void updateEvent(Event event){
        eventRepository.update(event);
    }
    public void deleteEvent(int id){
        eventRepository.delete(id);
    }
}
