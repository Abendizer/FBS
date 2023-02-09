package Flight_Booking_System;

import java.text.DecimalFormat;

public class Ticket implements Summary{
    //data fields
//instance variables
    private Person passenger;
    private String ticketId;//to be generated automatically
    private Location departure;
    private Location destination;
    private TimeInfo flightTime;
    private boolean isOneWay;
    private boolean isLocal;
    private double price;
    private String airLine;
    //static variables
    private static int ticketsLeft=100;
//constructors
    Ticket(){
    }
    Ticket(Person passenger, String ticketId, Location departure, Location destination, TimeInfo flightTime, boolean isOneWay, boolean isLocal, double price, String airLine){
        this.passenger = passenger;
        this.ticketId = ticketId;
        this.departure = departure;
        this.destination = destination;
        this.flightTime = flightTime;
        this.isOneWay = isOneWay;
        this.isLocal = isLocal;
        this.price = price;
        this.airLine = airLine;
        ticketsLeft--;
    }    
//instance methods
    //getters
    public Person getPassenger(){
        return passenger;
    }
    public String getTicketId(){
        return ticketId;
    }
    public Location getDeparture(){
        return departure;
    }
    public Location getDestination(){
        return destination;
    }
    public TimeInfo getFlightTime(){
        return flightTime;
    }
    public boolean getIsOneWay(){
        return isOneWay;
    }
    public boolean getIsLocal(){
        return isLocal;
    }
    public double getPrice(){
        return price;
    }
    public String getAirLine(){
        return airLine;
    }

    public String toString(){
        String name = "\n\nPassenger Full Name: "+passenger.getFirstName() + " "+ passenger.getLastName();
        String fromTo; 
        String oneOrTwoWay = "One-Way";
        if(!isOneWay){
            oneOrTwoWay = "Round-Trip";
        }
        fromTo = "\n\n# A "+oneOrTwoWay+" from "+departure.getCity()+", "+departure.getCountry()+" to "+destination.getCity()+", "+destination.getCountry();
        String takeOffTime = "\nFlight Take-off time: "+flightTime.getTakeOffTime();
        String bookingTime = "\n\n\t\tBooked at: "+flightTime.getBookingTime();
        String airLineName = "\n\n//----------------------"+airLine+"----------------------//";
        String tickId = "\n\n\t#Ticket Id:"+ticketId+"\n";
        String lastLine = "\n__________________________________________________________\n";
        String formattedInfo = bookingTime+airLineName+fromTo+name+takeOffTime+tickId+lastLine;
        return formattedInfo;
    }
    public String fullInfo(){
        String passengerInfo = "\n\tPassenger Inforamtion:  \n"+passenger.toString();
        String locationInfo = "\nDeparture: "+departure.toString()+"\nDestination: "+destination.toString();
        String timeInforamtion = "\n"+flightTime.toString();
        String ticketType;
        String flightType;
        if(isOneWay){ticketType="\nTicket type: One-Way Flight";}
        else ticketType = "\nTicket type: Round-Trip Flight";
        if(isLocal){flightType = "\nFlight type: Local Flight";}
        else flightType = "\nFlight type: Abroad Flight";
        DecimalFormat df = new DecimalFormat("0.00");
        String price = "\nTicket Price: $"+ df.format(this.price);
        String airLine = "\nAir Service Provider: "+this.airLine;
        return "\n\t\tTicket ID: "+ticketId+passengerInfo+"\n\tFlight Information: "+locationInfo+timeInforamtion+ticketType+flightType+price+airLine;
    }
//static methods
    public static int getTicketsLeft(){
        return Ticket.ticketsLeft;
    }
    public static void decrementTicketLeft(){
        Ticket.ticketsLeft--;
    }
    //no setter methods but it is still mutable class and some properties don't need modification at all once they are created.
}