package EventManagementSystem.Participants;

public class ParticipantFactory {
    public static Participant createParticipant(String firstName, String lastName,
                                                int age, String email,String gender, String tShirtSize) {
        if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male")) {
            return new MaleParticipant(firstName, lastName, age, email, gender,tShirtSize);
        } else if (gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Female")) {
            return new FemaleParticipant(firstName, lastName,age, email, gender, tShirtSize);
        }
        throw new IllegalArgumentException("Invalid gender: " + gender);
    }
}
