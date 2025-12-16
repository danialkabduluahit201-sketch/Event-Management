public class Participant {
    private int participant_id;
    private String full_name;
    private int age;
    private String email;
    public Participant(int participant_id,String full_name,int age,String email){
        this.participant_id=participant_id;
        this.full_name=full_name;
        this.age=age;
        this.email=email;
    }
    public String getFull_name(){
        return full_name;
    }
    public void setFull_name(String full_name){
        this.full_name=full_name;
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
    public void displayInfo(){
        System.out.println("ID: "+participant_id+"\n"+full_name+"\n"+"Age: "+age+"\n"+"Email: "+email);
    }
}
