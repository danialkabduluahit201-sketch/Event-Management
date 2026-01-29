package eventmanagement;

import java.util.List;

public interface EventRepository {
    void save(Event event);

    Event findById(int id);

    List<Event> findAll();

    void update(Event event);

    void delete(int id);
}
