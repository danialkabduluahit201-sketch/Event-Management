package com.eventmanagement;

public abstract class Participant {
    private String full_name;
    private int age;
    private String email;
    private static int next_participant=1;
    private int participant_id;
    public Participant(String full_name,int age,String email){
        this.participant_id=next_participant++;
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
    public abstract String getGender();
    public abstract void displayDetailedInfo();

    @Override
    public String toString() {
        return "Participant{id=" + participant_id + ", name=" + full_name +
                ", age=" + age + ", email=" + email + "}";
    }

    @Override
    public boolean equals(Object obj){
        if (this==obj) return true;
        if (obj==null || getClass()!=obj.getClass()) return false;
        Participant that=(Participant) obj;
        return email.equalsIgnoreCase(that.getEmail());
    }

    @Override
    public int hashCode() {
        return email.toLowerCase().hashCode();
    }

    public void displayInfo(){
        System.out.println("ID: "+participant_id+"\n"+full_name+"\n"+"Age: "+age+"\n"+"Email: "+email);
    }
}
