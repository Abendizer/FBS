package Flight_Booking_System;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class FileHandler {
    static String idFileName = "/id.txt";
    static String dataFileName = "/data.json";
    static String foldrName = "/FBS";
    static String parentPath = "/home/ab/Desktop";
    static String idPath = parentPath+foldrName+idFileName;
    static String dataPath = parentPath+foldrName+idFileName;

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
            File file = new File(parentPath+foldrName);
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
        return ticketList;
    }
//-------------data exporter method-----------------------
    public static void exportData(ArrayList<Ticket> ticketList){

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
                //just delete the data
            }
            else if(fileId.exists()&&fileData.exists()){
                try {
                    Scanner reader = new Scanner(fileId);
                    String idStrForm="";
                    while (reader.hasNextLine()) {
                      idStrForm+=reader.nextLine();
                    }
                    int idIntForm = Integer.parseInt(idStrForm);
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
