package Flight_Booking_System;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeInfo implements Summary{
    private String takeOffTime;
    private String landingTime;
    private String totalFlightTime;
    private String bookingTime;
//static variables used for processing some data
    private static final double avgSpeedOfPlaneInKPH = 850;//to calculate the total flight time
    private static LocalDateTime takeOff;
    TimeInfo(){
    }
    TimeInfo(String takeOffTime, String landingTime, String totalFlightTime, String bookingTime){
        this.takeOffTime = takeOffTime;
        this.landingTime = landingTime;
        this.totalFlightTime = totalFlightTime;
        this.bookingTime = bookingTime;
    }

    public String getTakeOffTime(){
        return takeOffTime;
    }
    public String getLandingTime(){
        return landingTime;
    }
    public String getTotalFlightTime(){
        return totalFlightTime;
    }
    public String getBookingTime(){
        return bookingTime;
    }
    public String toString(){
        String takeOff = "\nFlight Take-off time: "+takeOffTime;
        String landing = "\nFlight Landing time: "+landingTime;
        String totalFlight = "\nFlight Duration: "+totalFlightTime;
        String booking = "\nBooking Time: "+bookingTime;
        return takeOff+landing+totalFlight+booking;
    }
    //The most time-related processes are undergone inside this method
    public static String timeInfo(int flag, double distance ){
        int totalFlightTime = (int)(distance / avgSpeedOfPlaneInKPH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm a");
        if(flag==1){
            LocalDateTime timeNow = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 30));
            System.out.println("Choose flight time");
            System.out.println(1+".  "+timeNow.plusDays(1).format(formatter)+(" (Tomorrow)"));
            for(long i=2; i<=7; i++){
                System.out.println(i+".  "+timeNow.plusDays(i).format(formatter));
            }
            int choise = Task.optionRange(1, 7);
            takeOff = timeNow.plusDays((long)choise);
            return takeOff.format(formatter);
        }
        else if(flag==2){
            String landingTime = takeOff.plusHours((long)totalFlightTime).format(formatter);
            return landingTime;
        }
        else if(flag==3){
            return totalFlightTime + " Hours";
        }
        else {
            return LocalDateTime.now().format(formatter);
        }

    }
}