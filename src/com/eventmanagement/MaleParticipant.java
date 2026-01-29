package com.eventmanagement;

public class MaleParticipant extends Participant {
    private String Tshirt_size;
    public MaleParticipant(String full_name,int age,String email,String Tshirt_size){
        super(full_name, age, email);
        this.Tshirt_size=Tshirt_size;
    }
    public String getSize(){
        return Tshirt_size;
    }
    public void setSize(){
        this.Tshirt_size=Tshirt_size;
    }
    @Override
    public String getGender(){
        return "Male";
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
