package Flight_Booking_System;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;

public class FileHandler {
    static String idPath = "FBSdata/id.txt";
    static String dataPath = "FBSdata/data.json";
    static String folderName = "FBSdata";
    class FileException extends IOException{
        public void folderCreateError(){
            System.out.println("Folder create error: the data couldn't be stored.\n But run time data access is still possible");
        }
        public void fileWriteError(){
            System.out.println("File write error: unable to write the file");
        }
        public void totalError(){
            System.out.println("Unknown Error: the data can't be stored on disk.\nBut run time data access is still possible");
        }
    }
    static boolean createFolder(){
        try{
            File file = new File(folderName);
            if(file.exists()){
                return true;
            }
            else {            
                if(file.mkdir()){
                    return true;
                }   
                else 
                    return false;
                }
        }
            catch(Exception e){ return false; }
    }
//-------------data importer method-----------------------
    public static ArrayList<Ticket> importData(){
        ArrayList<Ticket> ticketList = new ArrayList<>();
        File dataFile = new File(dataPath);
        if(!dataFile.exists()){
            return ticketList;
        }
        else{
            try {
                ObjectMapper jsonMapper = new ObjectMapper();
                ticketList = jsonMapper.readValue(dataFile, new TypeReference<ArrayList<Ticket>>(){});
                return ticketList;
            }
            catch (StreamReadException e) {
                System.out.println("Unable to read the data. The program will store the new data replacing the existing one...");
                ticketList = new ArrayList<>();
            }
            catch (DatabindException e) {
                System.out.println("previously stored data is messed up. The program will store the new data replacing the existing one...");
                ticketList = new ArrayList<>();
            }
            catch (IOException e) {
                System.out.println("Unable to read the stored data. The program will store the new data replacing the existing one...");
                ticketList = new ArrayList<>();
            }
            return ticketList;
    }
}
//-------------data exporter method-----------------------
    public static boolean exportData(ArrayList<Ticket> ticketList){
        if(!createFolder()){
            return false;
        }
        else{
            ObjectMapper jsonMapper = new ObjectMapper();
            File file = new File(dataPath);
            try {
                jsonMapper.writeValue(file, ticketList);
                return true;
            } catch (IOException e) {
                return false;
            }
        }
    }
//-------------id exporter method-----------------------
    public static boolean exportId(int id){
        if(!createFolder()){
            return false;
        }
        else{
            File file = new File(idPath);
            try{
                if(file.exists()){
                    try{
                        FileWriter writer = new FileWriter(idPath, false);
                        writer.write(String.valueOf(id));
                        writer.close();
                        return true;
                    } catch (FileException e) {
                        e.fileWriteError();
                        return false;
                    }
                }
                else{
                    if(file.createNewFile()){
                        exportId(id);
                    }
                    else{
                        System.out.println("unable to store the data due to file access persmission,\nthe data will only be stored on the ram temporarly.");
                        return false;
                    }
                }
            }catch(IOException e){
                System.out.println("Unknown problem occured when storing the data.\nbut you still can access the data on this runtime");
                return false;
            }
            System.out.println("error storing file data");
            return false;
        }
    }
//-------------tracks the status of the id changes it when necessacy, usefull to track all the datas too-----------------------
    public static void idTracker(){
        // the program must work only if both data file and id tracker exist.. so the existance of the two files is checked
        File fileId = new File(idPath);
        File fileData = new File(dataPath);
        try{
            if((fileId.exists()==true&&fileData.exists()==false)||(fileId.exists()==false&&fileData.exists()==true)){
                System.out.println("A data has been deleted from the exported file");
                //delete here
            }
            else if(fileId.exists()&&fileData.exists()){
                try {
                    Scanner reader = new Scanner(fileId);
                    String idStrForm="";
                    while (reader.hasNextLine()) {
                      idStrForm+=reader.nextLine();
                    }
                    int idIntForm = Integer.parseInt(idStrForm);// if failed here, the files is messed up so it should be deleted here
                    Task.setId(idIntForm);
                    reader.close();
                  } catch (FileNotFoundException e) {
                    System.out.println("File read Error!");
                  }
            }
        }
        catch(UncheckedIOException e){}
    }
}
