/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.isa;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;




/**
 *
 * @author elean
 */
public class ISA {
    
    public static boolean isRunning = true;
    
    //global scanner for user input
    public static Scanner sc = new Scanner(System.in);
    
    
    public static void initFile(Collection items, MemberCollection members, String fileName){
        //this encapsulates the init of the file items neatly in a separate function
        File file = new File(fileName);
        Member currentMember = null;
        
        try (Scanner scanner  = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                //System.out.println(data);
                String[] part = data.split("\\|");
                //System.out.println(part[0]);
                
                if (part[0].equals("Member")){
                    currentMember = new Member(part[1], part[2], part[3]);
                    members.addMember(part[1], part[2], part[3]);
                }
                else if (part[0].equals("DVD")){
                    String[] audioLanguage = part[4].split(",");
                    items.addDVD(part[1], part[3], currentMember, part[2], audioLanguage);
                }
                else if (part[0].equals("Book")){
                    items.addBook(part[1], part[2], currentMember, part[4], part[3]);
                }
             
            }
        }   catch (IOException o){
            System.out.println("file not found");
        }
            
        
        }
    
    public static void saveToFile(Collection items, MemberCollection members, String fileName){
        
        try {PrintWriter writer = new PrintWriter(fileName);
                
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        
        
    }
    
    public static void readFile(String fileName){
        //test function to read in a file
        File file = new File(fileName);
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()){
            String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
                System.out.println("bleh");
                }
    }
    
    public static void main(String[] args) {
        
        System.out.println("Hello world!");
        
        //readFile("input-1.dat");
        Collection ISAitems = new Collection();
        MemberCollection ISAmembers = new MemberCollection();

        initFile(ISAitems,ISAmembers,"input-1.dat");
 
        Scanner input = new Scanner(System.in);
        while (isRunning){
            System.out.println(); 
            System.out.println(); 
            System.out.println("***** Main Menu *****");                        
            System.out.println(); 
            System.out.println("Please choose a number from the following:");
            System.out.println("Press 1 to search items");
            System.out.println("Press 2 to ...");
            System.out.println("Press 3 to ...");
            System.out.println("Press 4 to ...");
            System.out.println("Press 5 to exit");
            System.out.print("Enter your choice here: ");
      
            String choice = input.nextLine();
            
            if (!isValidInput("12345", choice)){
                printForInvalid();
            }
            else{
                switch(choice){
                    //cases 12345 etc...
                    case "1":
                        System.out.println("Please choose a number from the following:");
                        System.out.println("Press 1 to add member");
                        System.out.print("Enter your choice here: ");
      
                        String choice_1 = input.next();
            
                        if (!isValidInput("12345", choice)){
                            printForInvalid();}
                        else{
                            switch(choice_1){
                                case "1":
                                    System.out.print("Type the new member name: ");
                                    String name = input.nextLine();
                                    System.out.print("Type the new member address: ");
                                    String address = input.nextLine();
                                    System.out.print("Type the new member email: ");
                                    String email = input.nextLine();
                                    ISAmembers.addMember(name, address, email);
                                case "2":
                                     System.out.println("0");
                    
                            }
                        }
                        
                    
                    case "5": 
                        exit();
                        break;
                }
            }
        }
        
    }
    
    //methods
    
    public static void searchItems(Collection items, MemberCollection members){
        
    }
    
    public static void exit(){
        System.out.println("application closing...");
        isRunning = false;   
    }
    
    
    /* takes a string of what the accepted options are (for example the main menu
    should only accept "12345"), and a string of what the user entered, then checks
    whether their input is in the accepted string or not. Returns true if it is, and
    false if it isn't 
    Also it should only accept a length 1 char in the string (so not 12 for example)*/
    public static boolean isValidInput(String validOptions, String entered){
        boolean accepted = true;
        if (entered.length() == 1 && validOptions.contains(entered)){
            accepted = true;
        }
        else{
            accepted = false;
        }     
        
        return accepted;
    }
    
    public static void printForInvalid(){
        System.out.println();
        System.out.println("Please choose a *valid* option!");
        System.out.println(); 
    }
    
}

