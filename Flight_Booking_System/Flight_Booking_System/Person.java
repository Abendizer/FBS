package Flight_Booking_System;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME,
              include = JsonTypeInfo.As.PROPERTY,
              property = "type")

              @JsonSubTypes({
                @Type(value = LocalPassenger.class),
                @Type(value = AbroadPassenger.class),
                })
public abstract class Person{
    //data fields
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String sex;
    protected Address address;
    //constructor
    public Person(){
        
    }
    public Person(String firstName, String lastName, int age, String sex, Address address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
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
    public void setAge(int age){
        this.age=age;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    
}
class LocalPassenger extends Person implements Summary{
        public LocalPassenger(){}
        @JsonCreator
        public LocalPassenger(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("age") int age, @JsonProperty("sex") String sex, @JsonProperty("address") Address address){
        super(firstName, lastName, age, sex, address);
    }
    public String toString(){
        return "Full Name: "+firstName +" "+lastName+"\nAge: "+age+"\nSex: "+sex+"\nAddress:"+address.toString();
    }
}
class AbroadPassenger extends Person implements Summary{
    private String passportId;
    private String nationality;
    private String visaNumber;
    public AbroadPassenger(){}
    public AbroadPassenger(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("age") int age, @JsonProperty("sex") String sex, @JsonProperty("address") Address address, @JsonProperty("passportId") String passportId, @JsonProperty("nationality") String nationality, @JsonProperty("visaNumber") String visaNumber){
        super(firstName, lastName, age, sex, address);
        this.passportId = passportId;
        this.nationality = nationality;
        this.visaNumber = visaNumber;
    }
    public String getPassportId(){
        return passportId;
    }
    public String getNationality(){
        return nationality;
    }
    public String getVisaNumber(){
        return visaNumber;
    }

    public void setPassportId(String passportId){
        this.passportId=passportId;
    }
    public void setNationality(String nationality){
        this.nationality=nationality;
    }
    public void setVisaNumber(String visaNumber){
        this.visaNumber = visaNumber;
    }
    public String toString(){
        return "\t\nPersonal Information:\nFull Name: "+firstName +" "+lastName+"\nAge: "+age+"\nSex: "+sex+"\npassport ID: "+passportId+"\nNationlity: "+nationality+"\nvisa number: "+visaNumber+"\nAddress:"+address.toString();
    }
}