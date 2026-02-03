package eventmanagement;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RegistrationRepositoryImpl implements RegistrationRepository{

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/EventManagement";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "200888";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    public void create(EventManager registration) {
        String sql = "INSERT INTO registrations (event_id, participant_id, ticket_type, registration_date, status, payment_amount) " +
                "VALUES (?, ?, ?, NOW()::TEXT, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, registration.getEventId());
            pstmt.setInt(2, registration.getParticipantId());
            pstmt.setString(3, registration.getTicketType());
            pstmt.setString(4, registration.getStatus());
            pstmt.setDouble(5, registration.getPaymentAmount());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                registration.setRegistrationId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving registration: " + e.getMessage());
        }
    }
    @Override
    public List<EventManager> readAllRegistrations(){
        String sql="SELECT * FROM registrations";
        List<EventManager> registrations = new ArrayList<>();
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                registrations.add(mapResultSetToRegistration(rs));
            }
        }
        catch(SQLException e){
            throw new RuntimeException("Error finding registrations: " + e.getMessage());
        }
        return registrations;
    }

    @Override
    public EventManager readById(int id) {
        String sql = "SELECT * FROM registrations WHERE registration_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToRegistration(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding registration: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<EventManager> readByEventId(int eventId) {
        String sql = "SELECT * FROM registrations WHERE event_id = ?";
        List<EventManager> registrations = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, eventId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                registrations.add(mapResultSetToRegistration(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding registrations: " + e.getMessage());
        }
        return registrations;
    }

    @Override
    public List<EventManager> readByParticipantId(int participantId) {
        String sql = "SELECT * FROM registrations WHERE participant_id = ?";
        List<EventManager> registrations = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, participantId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                registrations.add(mapResultSetToRegistration(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding registrations: " + e.getMessage());
        }
        return registrations;
    }

    @Override
    public void update(EventManager registration) {
        String sql = "UPDATE registrations SET status = ? WHERE registration_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, registration.getStatus());
            pstmt.setInt(2, registration.getRegistrationId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating registration: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM registrations WHERE registration_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting registration: " + e.getMessage());
        }
    }

    private EventManager mapResultSetToRegistration(ResultSet rs) throws SQLException {
        EventManager registration = new EventManager();
        registration.setRegistrationId(rs.getInt("registration_id"));
        registration.setEventId(rs.getInt("event_id"));
        registration.setParticipantId(rs.getInt("participant_id"));
        registration.setTicketType(rs.getString("ticket_type"));
        registration.setRegistrationDate(rs.getString("registration_date"));
        registration.setStatus(rs.getString("status"));
        registration.setPaymentAmount(rs.getDouble("payment_amount"));
        return registration;
    }
}
