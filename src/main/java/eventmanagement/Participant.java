package eventmanagement;

public abstract class Participant {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private static int next_participant=1;
    private int participant_id;
    public Participant(String firstName,String lastName,int age,String email){
        this.participant_id=next_participant++;
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.email=email;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public String getFull_name(){
        return firstName+" "+lastName;
    }
    public int getAge(){return age;}
    public void setAge(int age){
        this.age=age;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public abstract String getGender();
    public abstract void displayDetailedInfo();
    public abstract String getTShirtSize();

    @Override
    public String toString() {
        return "Participant{id=" + participant_id + ", name=" + firstName+" "+lastName+
                ", age=" + age + ", email=" + email + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Participant that = (Participant) obj;
        return email.equalsIgnoreCase(that.getEmail());
    }

    @Override
    public int hashCode() {
        return email.toLowerCase().hashCode();
    }

    public void displayInfo(){
        System.out.println("ID: "+participant_id+"\n"+firstName+" "+lastName+"\n"+"Age: "+age+"\n"+"Email: "+email);
    }

    public void setParticipantId(int participantId) {
    }

    public int getParticipantId() {
        return participant_id;
    }
}
