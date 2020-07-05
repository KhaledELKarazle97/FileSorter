/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files.sorter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner; 
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Khaled
 
 * */

public class FilesSorter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Boolean isRunning = true;
        String folderName = "";
        String extName = "";
        System.out.println("This program will organize your files into different folders depending on the file extension");
        while(isRunning == true){
        Scanner scan = new Scanner(System.in);
        //to ask for the directory
        System.out.println("Enter the files directory (e.g C:/bob/desktop):");
        String s = scan.nextLine();
        File folder = new File(s);
        File[] listOfFiles = folder.listFiles();
        //in which folder you wanna keep these documents?
        System.out.println("Enter your desired folder name:");
        folderName = scan.nextLine();
        //what sort of files you are sorting?
        System.out.println("Enter the extension of files that you want to move to "+ folderName+": ");
        extName = scan.nextLine();
        // creates folder
        File d = new File(s.concat("/").concat(folderName));
        d.mkdir();
        //a loop to move all files with the specified extension
            for (File file : listOfFiles){
            if (file.isFile()) {
                if(extName.equals(getFileExtension(file))){
                    try {
                        Files.move(Paths.get(s+"/"+file.getName()), Paths.get(d+"/"+file.getName()));
                        System.out.println("Moved "+extName+" file(s)");
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }
        
        //confirmation message
        System.out.println("Moving more files? (Y/N):");
        String choice = scan.nextLine();
        if(choice.equals("N")){
            isRunning = false;
            break;
        }
           
        }
    }
    //extract the extension of the file
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    
}

