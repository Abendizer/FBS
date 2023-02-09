package Flight_Booking_System;
import java.time.*;
import java.util.ArrayList;

public class Run {
    private static ArrayList<Ticket> ticketList = FileHandler.importData();// Importing previously saved data 
    public static void run(){
        Input input = new Input();
        FileHandler.idTracker();//This method checks the id from the stored ID tracker file in order not to set similar IDs for the different tickets
        System.out.println("\n\t\t\t\t\t\t"+LocalDate.now()+"\n\n\t---[Flight] [Booking] [System]---\n");
        System.out.println("\t\t1. Book A Flight\n\t\t2. See my ticket \n\t\t3. Manage The Booking List");
        int x = input.insertInt();
        if(x==1){
            if(Ticket.getTicketsLeft()<=1){
                System.out.println("Sorry we are out of tickets");
            }
            else{
                System.out.println("\t1. Local Flight\n\t2. Abroad Flight");
                boolean isLocal = (Task.optionRange(1,2)==1) ? true : false;
                Ticket ticket = Task.getTicket(isLocal);
                if(ticket==null){
                    System.out.println("Booking failed");
                    run();
                }else{
                    System.out.println("Congrats, You Have Now Booked a Ticket\nYour ticket ID is FBS"+Task.getId());
                    ticketList.add(ticket);
                    if(FileHandler.exportData(ticketList) && FileHandler.exportId(Task.getId())){System.out.println("The data has been saved successfully");}
                    else{
                        System.out.println("Unable to save the data on disk!");
                    }
                    System.out.println("do you want to see your ticket?\n1. Yes\n2. No");
                    int option = Task.optionRange(1, 2);
                    if(option==1){
                        System.out.println(ticket.toString());
                    }
                    Input.insertEnter();
                    run();
                }
            }
        }
        else if(x==2){
            if(!ticketList.isEmpty()){
                System.out.println("1. ");
                System.out.println("insert your ID to see your ticket");
                String id = input.insertStr();
                final Integer[] flag = new Integer[1]; //This is for the following foreach loop, which only can operate with final variables
                flag[0] = 0;
                boolean found = false;
                // ticketList.forEach((aTicket) -> {
                //     if(aTicket.getTicketId().equals(id)){
                //         flag[0]=1;
                //         System.out.println(aTicket.toString());
                        
                //     }
                    
                // });
                while(ticketList.iterator().hasNext()){
                    Ticket T = ticketList.iterator().next();
                    if(T.getTicketId().equals(id)){
                        System.out.println(T.toString());
                        found = true;
                        System.out.println("Press 1 and enter if you want to see the full detail of your ticket or 0 to go back.");
                        int option = Task.optionRange(0,1);
                        if(option==1){
                            System.out.println(T.fullInfo());
                        }
                        break;
                    }
                }
                if(!found){
                    System.out.println("The ID you entered doesn't match any ticket from the list");
                }
                // if(flag[0]==0){
                //     System.out.println("The ID you entered doesn't match any ticket from the list");
                // }
            }
            else{
                System.out.println("The list is currently empty.");
            }
            Input.insertEnter();   
            run();
        }
        else if(x==3){
            System.out.println("You need a privilege to manage the list\nenter an admin password(password: \"password\")");
            String pass = input.insertStr();
            if(!pass.equals("password")){ 
                while(!pass.equals("password")){
                    System.out.println("wrong password");
                    pass = input.insertStr();
                }
            }
            System.out.println("\t1. See the Booking List\n\t2. Delete tickets");
            int userInput = Task.optionRange(1, 2);
            if(userInput==1){
                if(!ticketList.isEmpty()){
                    ticketList.forEach((aTicket) -> {
                        System.out.println(aTicket.toString());
                    });
                }
                else{
                    System.out.println("The list is currently empty.");
                    Input.insertEnter();
                }
                Input.insertEnter();
            }
            else{
                if(!ticketList.isEmpty()){
                    System.out.println("Insert the ID of the ticket you want to delete");
                    String id = input.insertStr();
                    boolean isDeleted=false;
                    //The following code deletes the ticket object if match is found
                    while(ticketList.iterator().hasNext()){
                        Ticket T = ticketList.iterator().next();
                        if(T.getTicketId().equals(id)){
                            isDeleted = true;
                            ticketList.remove(T);
                        }
                    }
                    if(isDeleted){
                        if(ticketList.isEmpty()){
                            FileHandler.deleteFolder();
                        }
                        else{
                            FileHandler.exportData(ticketList);
                        }
                        System.out.println("Delete success!!");
                        Input.insertEnter();
                    }
                    else {
                        System.out.println("The ID you provided doesn't match with any ticket");
                        Input.insertEnter();
                    }
                }
                else{
                    System.out.println("The list is currently empty.");
                    Input.insertEnter();
                }
            }
            run();
        }else {
            System.out.println("No such option!");
            Input.insertEnter();
            run();
        }
    }
}