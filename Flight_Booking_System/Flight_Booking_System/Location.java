package Flight_Booking_System;

public class Location implements Summary{
    protected String country;
    protected String city;
    public static final String[] countryList = {"ETHIOPIA",  "USA",    "CANADA",    "UK",   "AUSTRALIA"};
    public static final double[][] countryDistanceInKM = {
                                        /* ETH*/  {    0,     13150,    12133,      6407,   10761},
                                        /*USA */  {13158,         0,     2260,      6826,   15183},
                                        /*CAN */  {12133,      2260,        0,      5827,   14151},
                                        /*UK */   { 6407,      6826,     5827,         0,   15205},
                                        /*AUS */  {10761,     15183,    14151,     15205,       0}
                                    };
    public static final String[][] cityList = {{"Addis Ababa","Bahir Dar","Jimma"},{"Los Angeles","Washington DC","New York"},{"Toronto","Montreal","Calgary"},{"London","Manchester", "Black Pool"},{"Hobart","Darwin","Gold Cost"}};
    public static final double[][][] cityDistanceInKM = {
                                                            {               /*AA*/      /*BD*/     /*JI*/
                                                                /*AA*/  {       0,        590,      350},
                                                                /*BD*/  {     590,          0,      940},
                                                                /*JI*/  {     350,        940,        0}
                                                            },
                                                            {               /*LA*/      /*DC*/     /*NY*/
                                                                /*LA*/  {       0,       3700,     3936},
                                                                /*DC*/  {    3700,          0,      328},
                                                                /*NY*/  {    3936,         328,       0}
                                                            },
                                                            {               /*TO*/      /*MO*/     /*CA*/
                                                                /*TO*/  {       0,        541,     3400},
                                                                /*MO*/  {     541,          0,     3600},
                                                                /*CA*/  {    3400,       3600,        0}
                                                            },
                                                            {               /*LO*/      /*MA*/     /*BP*/
                                                                /*LO*/  {       0,        322,      384},
                                                                /*MA*/  {     322,          0,       82},
                                                                /*BP*/  {     384,         82,        0}
                                                            },
                                                            {               /*HO*/      /*DA*/     /*GC*/
                                                                /*HO*/  {       0,        4300,    1739},
                                                                /*DA*/  {    4300,           0,    2912},
                                                                /*GC*/  {    1739,        2912,       0}
                                                            },
    };
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
public void setCountry(String country) {
    this.country = country;
}
    public void setCity(String city) {
        this.city = city;
    }
    public String toString(){
        return city+", "+country;
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
    @Override
    public String toString(){
        return "\n"+city+", "+country+"\nEmail: "+email+"\nPhone nmuber: "+phoneNumber;
    }
}