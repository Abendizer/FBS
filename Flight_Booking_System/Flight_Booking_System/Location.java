package Flight_Booking_System;

public class Location {
    protected String country;
    protected String city;
    public static final String[] countryList = {"ETHIOPIA",  "USA",    "CANADA",    "UK",   "AUSTRALIA"};
    public static final double[][] distanceInKM = {
                                        /* ETH*/  {    0,     13150,    12133,      6407,   10761},
                                        /*USA */  {13158,         0,     2260,      6826,   15183},
                                        /*CAN */  {12133,      2260,        0,      5827,   14151},
                                        /*UK */   { 6407,      6826,     5827,         0,   15205},
                                        /*AUS */  {10761,     15183,    14151,     15205,       0}
                                    };
    public static final String[][] cityList = {{"Addis Ababa","Bahir Dar","Jimma"},{"Los Angeles","Washington DC","New York"},{"Toronto","Alberta","Calgari"},{"London","Manchester", "Black Pool"},{"Hobart","Darwin","Gold Cost"}};
//constructor
    public Location(){

    }
    public Location(String country, String city){
        this.country = country;
        this.city = city;
    }
//getter methods
    public String getCountry(){
        return country;
    }
    public String getCity(){
        return city;
    }
//setter methods
    public void setCity(String city) {
        this.city = city;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
class Address extends Location{
    private String email;
    private String phoneNumber;
//constructor
    public Address(){
        
    }
    public Address(String country, String city, String email, String phoneNumber) {
        super(country, city);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
//getter methods
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
//setter methods
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}