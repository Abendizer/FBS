package Flight_Booking_System;

import Simple_Payment_API.Payment;
public class Task {

    //static variables to be used throughout the class method
    private static int id=100;
    private static int countryChoise;

    //static methods
    private static final int TAKEOFF = 1;
    private static final int LANDING = 2;
    private static final int TOTAL = 3;
    private static final int CURRENT = 4;
   
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
    public static String idGen(){
        Task.id++;
        String id ="FBS"+Task.id;
        return id;
    }
    public static void setId(int id){
        Task.id = id;
    }
    public static int getId(){
        return id;
    }
    public static String chooseNationality(){
        String nationalArray[] ={"Ethiopian", "American","Canadian", "English", "Australian"};
        int i = 0;
        for(String nation: nationalArray){
            i++;
            System.out.println(i+". "+nation);
        }
        int nationChoise = optionRange(1, 5);
        String nationality = nationalArray[nationChoise-1];
        return nationality;
    }
    public static String chooseCountry(){
        int i =0;
        for (String countries : Location.countryList) {
            i++;
            System.out.println(i+". "+countries);
        }
        countryChoise = optionRange(1,Location.countryList.length);
        String country = Location.countryList[countryChoise-1];
        return country;
    }
    public static String chooseCity(){
        int i =0;
        for (String cities : Location.cityList[countryChoise-1]){
            i++;
            System.out.println(i+". "+cities);
        }
        int cityChoise = optionRange(1,Location.cityList[countryChoise-1].length);
        String city = Location.cityList[countryChoise-1][cityChoise-1];
        return city;    
    }
    public static double distanceOf(String country1, String country2){
        int i = 0;
        int firstIndex = 0;
        int secondIndex = 0;

        for (String country: Location.countryList){
            if(country1.equals(country)){
                firstIndex = i;
            }
            if(country2.equals(country)){
                secondIndex = i;
            }
            i++;
        }
        return Location.distanceInKM[firstIndex][secondIndex];
    }
    public static double priceOf(double distance){
        double ratePerKM = 0.08;
        double price = ratePerKM * distance;
        return price;
    }
    //a method for all the required informations from the user and generate a ticket object for that
    public static Ticket getTicket(){
        
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
        System.out.println("Passport ID:");
        String passportId = input.insertStr();
        System.out.println("Nationality:");
        String nationality = chooseNationality();
        System.out.println("Your Address:\n\tSelect the Country where you live in now:");
        String country = chooseCountry();
        System.out.println("\n\tSelect City:");
        String city = chooseCity();
        System.out.println("email: ");
        String email = input.insertEmail();
        System.out.println("Phone number(International Format):");
        String phoneNumber = input.insertPhone();

//        System.out.println("Are you sure you want to leave?? you are going to lose all the information you entered in!!!");
        //Filling information for the two location objects
        System.out.println("Where do you want to go");
        String destinationCountry = chooseCountry();
        System.out.println("choose city");
        String destinationCity = chooseCity();
        System.out.println("Select A Departure Location");
        String departureCountry = chooseCountry();
        if(destinationCountry.equals(departureCountry)){
            while(destinationCountry.equals(departureCountry)){
                System.out.println("Deparure and Destination location can't be the same on international flights.\n Please choose another country");
                departureCountry = chooseCountry();
            }
        }
        System.out.println("choose city");
        String departureCity = chooseCity();
        double distance = distanceOf(departureCountry, destinationCountry);
        //filling time
        String takeOffTime = TimeInfo.timeInfo(TAKEOFF,distance);
        String landingTime = TimeInfo.timeInfo(LANDING, distance);
        String totaFlightTime = TimeInfo.timeInfo(TOTAL,distance);
        String currentTime = TimeInfo.timeInfo(CURRENT, distance);
        System.out.println("Is it a one way or a round trip ticket that you want \n1. One Way Ticket\n2. Round Trip Ticket");
        boolean isOneWay = (optionRange(1, 2)==1)?true:false;
        double price = priceOf(distance);
        System.out.println("\nYou have filled all the information successfully. The next step is to Pay.");       
        String ticketType = "a One Way Ticket";
        if(!isOneWay){
            price *= 2;
            ticketType = "a Round trip Ticket";
        }
        System.out.println("The price of "+ticketType+" from "+departureCountry+" to "+destinationCountry+" is $"+price);
        //Payment procesing goes here
        boolean isPaymentDone = Payment.hasPayed(price);
        if(isPaymentDone){
            Address address = new Address(country, city, email, phoneNumber);
            Person passenger = new Person(firstName, lastName, age, sex, passportId, nationality, address);//created a person objects using the above input
            String ticketId = idGen();
            Location departure = new Location(departureCountry, departureCity);
            Location destination = new Location(destinationCountry, destinationCity);
            TimeInfo flightTime = new TimeInfo(takeOffTime, landingTime, totaFlightTime, currentTime);
            Ticket ticket = new Ticket(passenger, ticketId, departure, destination, flightTime, isOneWay, price);
            Ticket.decrementTicketLeft();
            return ticket;
        }
        else{
            return null;
        }
    }
}