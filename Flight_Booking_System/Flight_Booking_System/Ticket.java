package Flight_Booking_System;
public class Ticket{
    //datafields
//instance variables
    private Person passenger;
    private String ticketId;//to be generated automatically
    private Location departure;
    private Location destination;
    private TimeInfo flightTime;
    private boolean isOneWay;
    private double price;
//static variables
    private static final String airLineName = "Ethiopian Airlines";
    private static int ticketsLeft=100;
//constructors
    Ticket(){ 
    }
    Ticket(Person passenger, String ticketId, Location departure, Location destination, TimeInfo flightTime, boolean isOneWay, double price){
        this.passenger = passenger;
        this.ticketId = ticketId;
        this.departure = departure;
        this.destination = destination;
        this.flightTime = flightTime;
        this.isOneWay = isOneWay;
        this.price = price;
        ticketsLeft--;
    }    
//instance methods
    //getters
    public Person getTciketBuyer(){
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
    public TimeInfo getTotalFlightTime(){
        return flightTime;
    }
    public boolean isOneWay(){
        return isOneWay;
    }
    public double getPrice(){
        return price;
    }
    public String toString(){
        String name = "\n\nPassenger Full Name: "+passenger.getFirstName() + " "+ passenger.getLastName();
        String fromTo; 
        String oneOrTwoWay = "One-Way";
        if(!isOneWay){
            oneOrTwoWay = "Round-Trip";
        }
        fromTo = "\n\n#"+oneOrTwoWay+"A One-Way Flight from "+departure.getCity()+", "+departure.getCountry()+" to "+destination.getCity()+", "+destination.getCountry();
        String takeOffTime = "\nFlight Take-off time: "+flightTime.getTakeOffTime();
        String bookingTime = "\n\t\tBooked at: "+flightTime.getBookingTime();
        String airLine = "\n\n//-----------"+Ticket.airLineName+"-----------//";
        String tickId = "\n\n\t#Ticket Id:"+ticketId+"\n";
        String formattedInfo = bookingTime+airLine+fromTo+name+takeOffTime+tickId;
        return formattedInfo;
    }
    public static int getTicketsLeft(){
        return Ticket.ticketsLeft;
    }
    public static void decrementTicketLeft(){
        Ticket.ticketsLeft--;
    }
    //no setter mehtods but it is still mutable class
}
class LocalTicket extends Ticket{
    
    LocalTicket(){

    }
}
