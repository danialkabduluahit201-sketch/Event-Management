package EventManagementSystem.Events;

import java.util.List;

public interface EventRepository {
    void create(Event event);

    Event readByID(int id);

    List<Event> readAll();

    void update(Event event);

    void delete(int id);
}
