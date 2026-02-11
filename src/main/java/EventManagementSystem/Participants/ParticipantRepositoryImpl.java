package EventManagementSystem.Participants;

import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ParticipantRepositoryImpl implements ParticipantRepository {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/EventManagement";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "200888";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    public void createParticipant(Participant participant) {
        String sql = "INSERT INTO participants (first_name, last_name, email, age,gender,t_shirt_size) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, participant.getFirstName());
            pstmt.setString(2, participant.getLastName());
            pstmt.setString(3, participant.getEmail());
            pstmt.setInt(4, participant.getAge());
            pstmt.setString(5, participant.getGender());
            pstmt.setString(6, participant.getTShirtSize());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                participant.setParticipantId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving participant: " + e.getMessage());
        }
    }

    @Override
    public Participant readById(int id) {
        String sql = "SELECT * FROM participants WHERE participant_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToParticipant(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding participant: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Participant readByEmail(String email) {
        String sql = "SELECT * FROM participants WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToParticipant(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding participant: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Participant> readAll() {
        String sql = "SELECT * FROM participants ORDER BY first_name";
        List<Participant> participants = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                participants.add(mapResultSetToParticipant(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all participants: " + e.getMessage());
        }
        return participants;
    }

    @Override
    public void update(Participant participant) {
        String sql = "UPDATE participants SET first_name = ?, last_name = ?, email = ?, " +
                 "age = ?,gender = ?,t_shirt_size = ? WHERE participant_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, participant.getFirstName());
            pstmt.setString(2, participant.getLastName());
            pstmt.setString(3, participant.getEmail());
            pstmt.setInt(4, participant.getAge());
            pstmt.setString(5,participant.getGender());
            pstmt.setString(6, participant.getTShirtSize());
            pstmt.setInt(7, participant.getParticipantId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating participant: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM participants WHERE participant_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting participant: " + e.getMessage());
        }
    }

    private Participant mapResultSetToParticipant(ResultSet rs) throws SQLException {
        Participant participant = ParticipantFactory.createParticipant(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("age"),
                rs.getString("email"),
                rs.getString("gender"),
                rs.getString("t_shirt_size")
        );
        participant.setParticipantId(rs.getInt("participant_id"));
        return participant;
    }
}

