package eventmanagement;

import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/EventManagement";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "200888";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    public void create(Event event) {
        String sql = "INSERT INTO events (name, description, location, date_time, max_capacity, status, organizer_company) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, event.getName());
            pstmt.setString(2, event.getDescription());
            pstmt.setString(3, event.getLocation());
            pstmt.setString(4, event.getDateTime());
            pstmt.setInt(5, event.getMaxCapacity());
            pstmt.setString(6, event.getStatus());
            pstmt.setString(7, event.getOrganizerCompany());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                event.setEventId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving event: " + e.getMessage());
        }
    }

    @Override
    public Event readByID(int id) {
        String sql = "SELECT e.*, COUNT(r.registration_id) as participant_count " +
                "FROM events e " +
                "LEFT JOIN registrations r ON e.event_id = r.event_id AND r.status != 'CANCELLED' " +
                "WHERE e.event_id = ? " +
                "GROUP BY e.event_id";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToEvent(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding event: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Event> readAll() {
        String sql = "SELECT e.*, COUNT(r.registration_id) as participant_count " +
                "FROM events e " +
                "LEFT JOIN registrations r ON e.event_id = r.event_id AND r.status != 'CANCELLED' " +
                "GROUP BY e.event_id " +
                "ORDER BY e.event_id";
        List<Event> events = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                events.add(mapResultSetToEvent(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all events: " + e.getMessage());
        }
        return events;
    }

    @Override
    public void update(Event event) {
        String sql = "UPDATE events SET name = ?, description = ?, location = ?, date_time = ?, " +
                "max_capacity = ?, status = ?, organizer_company = ? WHERE event_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, event.getName());
            pstmt.setString(2, event.getDescription());
            pstmt.setString(3, event.getLocation());
            pstmt.setString(4, event.getDateTime());
            pstmt.setInt(5, event.getMaxCapacity());
            pstmt.setString(6, event.getStatus());
            pstmt.setString(7, event.getOrganizerCompany());
            pstmt.setInt(8, event.getEventId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating event: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM events WHERE event_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting event: " + e.getMessage());
        }
    }

    private Event mapResultSetToEvent(ResultSet rs) throws SQLException {
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setName(rs.getString("name"));
        event.setDescription(rs.getString("description"));
        event.setLocation(rs.getString("location"));
        event.setDateTime(rs.getString("date_time"));
        event.setMaxCapacity(rs.getInt("max_capacity"));
        event.setStatus(rs.getString("status"));
        event.setOrganizerCompany(rs.getString("organizer_company"));
        event.setCurrentParticipants(rs.getInt("participant_count"));
        return event;
    }
}
