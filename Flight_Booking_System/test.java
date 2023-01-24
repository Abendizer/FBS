import java.io.*;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
public class test {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        String path = "/home/ab/Desktop/file.txt";
        String name = "hello.txt";
        File x = new File(name);
        try {
            if(x.createNewFile()){
                System.out.println("yes");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }  
}