package EventManagementSystem.Events;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {
    private final EventService eventService;
    public EventController(EventService eventService){
        this.eventService=eventService;
    }

    @GetMapping
    public List<Event> getAllEvents(){
        return eventService.readAllEvents();
    }

    @GetMapping("/id/{id}")
    public Event getEventById(@PathVariable int id) {
        return eventService.readEventById(id);
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return event;
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable int id, @RequestBody Event event) {
        event.setEventId(id);
        eventService.updateEvent(event);
        return event;
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable int id) {
        eventService.deleteEvent(id);
        return "Event deleted successfully";
    }
}
