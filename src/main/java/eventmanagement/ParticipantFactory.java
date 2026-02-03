package eventmanagement;

public class ParticipantFactory {
    public static Participant createParticipant(String gender, String firstName, String lastName,
                                                 int age,String email, String tShirtSize) {
        if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male")) {
            return new MaleParticipant(firstName, lastName, age, email, tShirtSize);
        } else if (gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Female")) {
            return new FemaleParticipant(firstName, lastName,age, email, tShirtSize);
        }
        throw new IllegalArgumentException("Invalid gender: " + gender);
    }
}
