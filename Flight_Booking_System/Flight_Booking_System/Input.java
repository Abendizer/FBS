package Flight_Booking_System;
import java.util.Scanner;
//This is a class we defined to handle all the inputs we encounter.
public  class Input{
    Scanner input = new Scanner(System.in);
    public int insertInt(){
        System.out.print(">>");
        Scanner input= new Scanner(System.in);
        while(!input.hasNextInt()){
            System.out.println("Please enter an Integer only.");
            input.next();
        }
        return input.nextInt();
    }
    public int insertAge(){
        int age = insertInt();
        if(age<18 || age>100){
            while(age<18 || age>100){
                System.out.println("your age must be between 18 and 100");
                age = insertInt();
            }
        }
        return age;
    }
    public double insertDouble(){
        System.out.print(">>");
        double num=0.0;
        boolean isDouble = false;
        do{
            if(input.hasNextDouble()){
                num = input.nextDouble();
                isDouble=true;
            }
            else{
                System.out.print("Enter only a decimal or an integer\n>>");
                input.nextLine();
            }
        }while(!isDouble);
        return num;
    }
    public String insertStr(){ 
        System.out.print(">>");
        String str = input.next();
        return str;
    }
    public String insertOnlyAlpha(){
        System.out.print(">>");
        String str = input.next();
        if(!str.matches("[a-zA-Z]+")){
            while(!str.matches("[a-zA-Z]+")){
                System.out.println("you must enter alphabets only");
                str=input.next();
            }
        }
        return str;
    }
    public char insertChar(){
        System.out.print(">>");
        char charac=' ';
        boolean isChar = false;
        do{
            if(input.hasNext()){
                charac = input.next().charAt(0);
                isChar=true;
            }
            else{
                System.out.print("Enter only a decimal or an integer\n>>");
                //input.nextLine();
            }
        }while(!isChar);
        return charac;
    }
    public String insertEmail(){
        System.out.println(">>");
        String str = input.next();
        if(!str.matches("^[a-z0-9]+@[a-z]+\\.[a-z]+$")){
            while(!str.matches("^[a-z0-9]+@[a-z]+\\.[a-z]+$")){
                System.out.println("That's not the correct email format.(use small letters and the correct symbols)");
                str=input.next();
            }
        }
        return str;
    }
    public String insertPhone(){
        System.out.println(">>");
        String str = input.next();
        if(!str.matches("^\\+[0-9]{6,16}$")){
            while(!str.matches("^\\+[0-9]{6,16}$")){
                System.out.println("Wrong entry, use this format: +251911203040");
                str=input.next();
            }
        }
        return str;
    }
    public static void insertEnter(){
        Scanner inp = new Scanner(System.in);
        System.out.println("Press  any key and then Enter to continue...");
        inp.next();
    } 
}