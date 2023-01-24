package Simple_Payment_API;

import Flight_Booking_System.Input;
import Flight_Booking_System.Task;

public class Payment {
    //display sth like simple payment api
    //sample user accounts with their
    public static String[] sampleUserId = {"user01","user02","user03","user04","user05","user06","user07","user08","user09","user10"};
    public static String[] sampleUserPassword = {"password01","password02","password03","password04","password05","password06","password07","password08","password09","password10"};
    public static double[] balance = {70,3000,620,200,7900,45450,30,37858,200000,5523};
    //A method for verifying the user payment
    public static boolean hasPayed(double price){
        Input input = new Input();
        System.out.println("\t---Payment API---\n\t-----------------\n");
        System.out.println("Enter your user ID for this payment system");
        String userId = input.insertStr();
        int index = 0;
        boolean found = false;
        for(int i =0; i<Payment.sampleUserId.length;i++){
            if(userId.equals(Payment.sampleUserId[i])){
                index = i;
                found = true;
                break;
            }
        }
        if(found==false){
            while(found==false){
                System.out.println("couldn't find the user id you provided reenter correctly");
                userId = input.insertStr();
                for(int i =0; i<Payment.sampleUserId.length;i++){
                    if(userId.equals(Payment.sampleUserId[i])){
                        index = i;
                        found = true;
                        break;
                    }
                }
                
            }
        }
        System.out.println("Enter a password for the account you entered");
        String password = input.insertStr();
        
        if(!password.equals(Payment.sampleUserPassword[index])){
            while(!password.equals(Payment.sampleUserPassword[index])){
                System.out.println("wrong password please re-enter correctly");
                password = input.insertStr();
            }
        }
        if(Payment.balance[index]<price){
            System.out.println("Insufficient balance, try another account?\n1.Yes \n2.No(Stop booking)");
            int option = Task.optionRange(1, 2);
            if(option==1){return hasPayed(price);}
            else return false;
        }
        else{

            System.out.println("confirm by pressing 1 to pay or 0 to cancel\n1. Pay \n0. Cancel");
            int choice = Task.optionRange(0, 1);
            if(choice==1){
                Payment.balance[index] -= price;
                System.out.println("payment successful");
                return true;
            }
            else return false;
        } 
    }
}