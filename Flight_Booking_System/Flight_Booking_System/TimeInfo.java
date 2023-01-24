package Flight_Booking_System;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeInfo {
    private String takeOffTime;
    private String landingTime;
    private String totalFlightTime;
    private String bookingTime;

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
    public static String timeInfo(int flag, double distance ){
        int totalFlightTime = (int)(distance / avgSpeedOfPlaneInKPH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm a");
        if(flag==1){
            LocalDateTime timeNow = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 30));
            System.out.println("Choose flight time");
            System.out.println(1+".  "+timeNow.plusDays(1).format(formatter)+(" (Tommorow)"));
            for(long i=2; i<=7; i++){
                System.out.println(i+".  "+timeNow.plusDays(i).format(formatter));
            }
            int choise = Task.optionRange(1, 7);
            takeOff = timeNow.plusDays((long)choise);
            return takeOff.format(formatter);
            //return timeNow.plusDays((long)choise).format(formatter);
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
