package eventmanagement;

public class FemaleParticipant extends Participant {
    private String Tshirt_size;
    public FemaleParticipant(String firstName,String lastName,int age, String email,String Tshirt_size){
        super(firstName,lastName,age,email);
        this.Tshirt_size=Tshirt_size;
    }
    public void setSize(String Tshirt_size){
        this.Tshirt_size=Tshirt_size;
    }
    @Override
    public String getTShirtSize(){
        return Tshirt_size;
    }
    @Override
    public String getGender(){
        return "Female";
    }
    @Override
    public void displayDetailedInfo(){
        displayInfo();
        System.out.println("T-shirt size: "+Tshirt_size);
    }
    @Override
    public String toString(){
        return super.toString()+"T-shirt size"+Tshirt_size;
    }
}