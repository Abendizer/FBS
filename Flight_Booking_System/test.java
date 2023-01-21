import java.io.*;
import java.util.Scanner;
public class test {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        String path = "/home/ab/Desktop/file.txt";
        File x = new File(path);
        try{
            if(x.exists()){
                try{
                    FileWriter write = new FileWriter(path, false);
                    int id = 38;
                    write.write(String.valueOf(id));
                    write.close();
                    System.out.println("success");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("the problem is here");
                }
            }
            else{
                if(x.createNewFile()){
                    //recurse here
                }
                else{
                    System.out.println("unable to store the data due to file access persmission,\nthe data will only be stored on the ram temporarly.");
                }
            }
        }
            catch(IOException e){
            System.out.println("Unknown problem occured on storing the datas.\n but you still can access the data on this runtime");
        }  
    }  
}