package Flight_Booking_System;
import java.time.*;
import java.util.ArrayList;
public class Run {
    private static ArrayList<Ticket> ticketList = FileHandler.importData();
//method for options    
    public static void run(){
        Input input = new Input();
        FileHandler.idTracker();
        System.out.println("\n\t\t\t\t\t\t"+LocalDate.now()+"\n\n\t---[Flight] [Booking] [System]---\n");
        System.out.println("\t\t1.Book A Flight\n\t\t2.See The Booking List");
        int x = input.insertInt();
        if(x==1){
            if(Ticket.getTicketsLeft()<=1){
                System.out.println("Sorry we are out of tickets");
            }
            else{
                Ticket ticket = Task.getTicket();
                if(ticket==null){
                    System.out.println("Booking failed");
                    run();
                }else{
                    System.out.println("Congrats, You Have Now Booked a Ticket\nYour ticket ID is FBS"+Task.getId());
                    ticketList.add(ticket);
                    System.out.println("added");
                    FileHandler.exportId(Task.getId());
                    if(FileHandler.exportData(ticketList)){System.out.println("The data has been saved successfully");}
                    else{
                        System.out.println("Unable to save the data on disk!");
                    }
                    //write id to file here
                    //write data to file here
                    System.out.println("do you want to see the full detail of your ticket?\n1. Yes\n2. No");
                    int option = Task.optionRange(1, 2);
                    if(option==1){
                        System.out.println(ticket.toString());
                    }
                    Input.insertEnter();
                    //push the ticket object in the ticketList here
                    run();
                }
            }
        }
        else if(x==2){
            // there should be a way to delete some tickets
            System.out.println("u need to have a previlage to see the booking list\nenter an admin password");
            String pass = input.insertStr();
            if(!pass.equals("password")){ 
                while(!pass.equals("password")){
                    System.out.println("wrong password");
                    pass = input.insertStr();
                }
            }
            if(!ticketList.isEmpty()){
                ticketList.forEach((aTicket) -> {
                    System.out.println(aTicket.toString());
                });
            }
            else{
                System.out.println("The list is currently empty.");
            }
            Input.insertEnter();
            run();
        }else {
            System.out.println("No such option!");
            Input.insertEnter();
            run();
        }
    }
}