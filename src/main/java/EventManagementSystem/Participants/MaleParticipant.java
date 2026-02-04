package EventManagementSystem.Participants;

public class MaleParticipant extends Participant {
    private String Tshirt_size;
    private String gender;
    public MaleParticipant(String firstName,String lastName,int age,String email,String gender,String Tshirt_size){
        super(firstName,lastName, age, email);
        this.Tshirt_size=Tshirt_size;
        this.gender=gender;
    }
    @Override
    public String getTShirtSize(){
        return Tshirt_size;
    }
    public void setSize(){
        this.Tshirt_size=Tshirt_size;
    }
    @Override
    public String getGender(){
        return gender;
    }
    @Override
    public void displayDetailedInfo(){
        displayInfo();
        System.out.println("T-shirt size: "+Tshirt_size);
    }

    @Override
    public String toString() {
        return super.toString() + "T-shirt size" + Tshirt_size;
    }

}
