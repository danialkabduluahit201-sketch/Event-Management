public class Participant {
    private String full_name;
    private int age;
    private String email;
    public Participant(String full_name,int age,String email){
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
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void displayInfo(String full_name,int age,String setEmail){
        System.out.println("Full name: "+full_name+"\n"+"Age: "+age+"\n"+"Email: "+email);
    }
}
