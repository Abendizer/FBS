package Flight_Booking_System;
public class Person {
    //datafields
    private String firstName;
    private String lastName;
    private int age;
    private String sex;
    private String passportId;
    private String nationality;
    private Address address;
    //constructor
    public Person(String firstName, String lastName, int age, String sex, String passportId, String nationality, Address address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.passportId = passportId;
        this.nationality = nationality;
        this.address = address;
    }
//methods
    //getter methods
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public int getAge(){
        return age;
    }
    public String getSex(){
        return sex;
    }
    public String getPassportId(){
        return passportId;
    }
    public String getNationality(){
        return nationality;
    }
    public Address getAddress(){
        return address;
    }
    //setter methods
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setaAge(int age){
        this.age=age;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public void setPassportId(String passportId){
        this.passportId=passportId;
    }
    public void setNationality(String nationality){
        this.nationality=nationality;
    }
}