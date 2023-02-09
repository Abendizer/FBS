package Flight_Booking_System;

import Simple_Payment_API.Payment;

import java.text.DecimalFormat;

public class Task {

    //static variables to be used throughout the class method
    private static int id=100;
    private static int countryChoice;
    private static final int TAKEOFF = 1;
    private static final int LANDING = 2;
    private static final int TOTAL = 3;
    private static final int CURRENT = 4;
    private static final int COUNTRY = 1;
    private static final int CITY = 2;
   // a method name optionRange to choose between any number in between the two parameters.
    public static int optionRange(int min, int max){
        Input input = new Input();
        int x = input.insertInt();
        if(x<min || x>max){
            System.out.println("Enter only between "+ min +" and "+ max);
            return optionRange(min, max);
        }
        else{
           return x;
        }
    }
    // Id generator method
    public static String idGen(){
        Task.id++;
        return "FBS"+Task.id;
    }
    public static void setId(int id){
        Task.id = id;
    }
    public static int getId(){
        return id;
    }
    public static String chooseAirLine(){
        String[] airlines = {"Ethiopian Airlines","Delta Airlines","Qatar Airways","American Airlines","Emirates","Air Canada","Virgin Australia","Air France","British Airways","Turkish Airlines"};
        int i = 0;
        for(String airLine: airlines){
            i++;
            System.out.println("  "+i+". "+airLine);
        }
        return airlines[optionRange(1,10)-1];
    }
    public static String chooseNationality(){
        String[] nationalArray ={"Ethiopian", "American","Canadian", "English", "Australian"};
        int i = 0;
        for(String nation: nationalArray){
            i++;
            System.out.println(i+". "+nation);
        }
        return nationalArray[optionRange(1, 5) -1];
    }
    public static String chooseCountry(){
        int i =0;
        for (String countries : Location.countryList) {
            i++;
            System.out.println(i+". "+countries);
        }
        countryChoice = optionRange(1,Location.countryList.length);
        return Location.countryList[countryChoice -1];
    }
    public static String chooseCity(){
        int i =0;
        for (String cities : Location.cityList[countryChoice -1]){
            i++;
            System.out.println(i+". "+cities);
        }
        int cityChoice = optionRange(1,Location.cityList[countryChoice -1].length);
        return Location.cityList[countryChoice -1][cityChoice-1];
    }
    public static double distanceOf(String place1, String place2, int flag){
        int i = 0;
        int firstIndex = 0;
        int secondIndex = 0;
        if(flag==1){

            for (String country: Location.countryList){
                if(place1.equals(country)){
                    firstIndex = i;
                }
                if(place2.equals(country)){
                    secondIndex = i;
                }
                i++;
            }
            return Location.countryDistanceInKM[firstIndex][secondIndex];
        }
        else {
            for(String city: Location.cityList[countryChoice-1]) {
                if (place1.equals(city)) {
                    firstIndex = i;
                }
                if (place2.equals(city)) {
                    secondIndex = i;
                }
                i++;
            }
            return Location.cityDistanceInKM[countryChoice-1][firstIndex][secondIndex];
        }
    }
    public static double priceOf(double distance){
        double ratePerKM = 0.08;
        return ratePerKM * distance;
    }
    //a method for all the required information from the user and generate a ticket object for that
    public static Ticket getTicket(boolean isLocal){
        Input input = new Input();
        //take an input to create person object first
        System.out.println("Enter first name:");
        String firstName = input.insertOnlyAlpha();
        System.out.println("Enter last name:");
        String lastName = input.insertOnlyAlpha();
        System.out.println("Age:");
        int age = input.insertAge();
        System.out.println("Sex:\nChoose 1 or 2\n1. Male\n2. Female");
        String sex = (optionRange(1,2)==1) ? "Male": "Female";
        String passportId="";
        String nationality="";
        String visaNumber="";
        //if the flight is not a local flight two of the following additional information are needed
        if(!isLocal){
            System.out.println("Enter your passport ID:");
            passportId = input.insertStr();
            System.out.println("Select your Nationality:");
            nationality = chooseNationality();
            System.out.println("Enter your visa number");
            visaNumber=input.insertStr();
        }
        System.out.println("Your Address:\n\tSelect the Country where you live in now:");
        String country = chooseCountry();
        System.out.println("\n\tSelect City:");
        String city = chooseCity();
        System.out.println("email: ");
        String email = input.insertEmail();
        System.out.println("Phone number(International Format):");
        String phoneNumber = input.insertPhone();
        //Filling information for the two location objects
        String destinationCountry;
        String destinationCity;
        String departureCountry;
        String departureCity;
        double distance;
        if(!isLocal){
            System.out.println("Where do you want to go");
            destinationCountry = chooseCountry();
            System.out.println("choose city");
            destinationCity = chooseCity();
            System.out.println("Select A Departure Location");
            departureCountry = chooseCountry();
            if(destinationCountry.equals(departureCountry)){
                while(destinationCountry.equals(departureCountry)){
                    System.out.println("Departure and Destination location can't be the same on Abroad flights.\n Please select another country");
                    departureCountry = chooseCountry();
                }
            }
            System.out.println("choose city");
            departureCity = chooseCity();
            distance = distanceOf(departureCountry, destinationCountry, COUNTRY);
        } else {
            System.out.println("From which country you want the flight to be");
            departureCountry = chooseCountry();
            destinationCountry = departureCountry;
            System.out.println("Select departure city");
            departureCity = chooseCity();
            System.out.println("Select destination city");
            destinationCity = chooseCity();
            if(destinationCity.equals(departureCity)){
                while(destinationCity.equals(departureCity)){
                    System.out.println("Departure and Destination city can't be the same on.\n Please select another country");
                    destinationCity = chooseCity();
                }
            }
            distance = distanceOf(departureCity, destinationCity, CITY);
        }
        //filling time
        System.out.println("Select Airline");
        String airLine = chooseAirLine();
        String takeOffTime = TimeInfo.timeInfo(TAKEOFF,distance);
        String landingTime = TimeInfo.timeInfo(LANDING, distance);
        String totalFlightTime = TimeInfo.timeInfo(TOTAL,distance);
        String currentTime = TimeInfo.timeInfo(CURRENT, distance);
        System.out.println("Is it a one way or a round trip ticket that you want \n1. One Way Ticket\n2. Round Trip Ticket");
        boolean isOneWay = optionRange(1, 2) == 1;
        double price = priceOf(distance);
        System.out.println("\nYou have filled all the information successfully. The next step is to Pay.");       
        String ticketType = "a One Way ";
        String flightType = "Domestic Flight";
        if(!isOneWay){
            price *= 2;
            ticketType = "a Round trip ";
        }
        if(!isLocal){
            flightType = "Abroad Flight";
        }


        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("The price of "+ticketType+flightType+" from "+departureCity+", "+departureCountry+" to "+destinationCity+", "+destinationCountry+" is $"+df.format(price));
        //Payment processing goes here
        boolean isPaymentDone = Payment.hasPayed(price);
        if(isPaymentDone){
            Address address = new Address(country, city, email, phoneNumber);
            Person passenger;
            if(isLocal){
                passenger = new LocalPassenger(firstName, lastName, age, sex, address);//created a person objects using the above input
            }
            else{
                passenger = new AbroadPassenger(firstName, lastName, age, sex, address, passportId, nationality, visaNumber);
            }
            String ticketId = idGen();
            Location departure = new Location(departureCountry, departureCity);
            Location destination = new Location(destinationCountry, destinationCity);
            TimeInfo flightTime = new TimeInfo(takeOffTime, landingTime, totalFlightTime, currentTime);
            Ticket ticket = new Ticket(passenger, ticketId, departure, destination, flightTime, isOneWay, isLocal, price, airLine);
            Ticket.decrementTicketLeft();
            return ticket;
        }
        else
            return null;
    }
}