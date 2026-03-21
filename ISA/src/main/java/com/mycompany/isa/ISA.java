/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.isa;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;




/**
 *
 * @author elean
 */
public class ISA {
    
    public static boolean isRunning = true;
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        File file = new File("input-1.dat");
        
        try (Scanner scanner  = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
            }
        }   catch (IOException o){
            System.out.println("file not found");
        }
        
        //testing 
        Member m1 = new Member("name1","add1","e1");
        Member m2 = new Member("name2","add2","e2");
        
        MemberCollection collection = new MemberCollection();
        
        collection.addMember("john cena","64 zoo lane","js@gmail.com");
        
        for (Member member: collection.getMembers()){
            System.out.println(member.getName());
        }
        
        
        Scanner input = new Scanner(System.in);
        while (isRunning){
            System.out.println(); 
            System.out.println(); 
            System.out.println("***** Main Menu *****");                        
            System.out.println(); 
            System.out.println("Please choose a number from the following:");
            System.out.println("Press 1 to member function");
            System.out.println("Press 2 to ...");
            System.out.println("Press 3 to ...");
            System.out.println("Press 4 to ...");
            System.out.println("Press 5 to exit");
            System.out.print("Enter your choice here: ");
      
            String choice = input.next();
            
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
                                    collection.addMember(name, address, email);
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
    
    public static void exit(){
        System.out.println("application closing...");
        isRunning = false;   
    }
    
    
    /* takes a string of what the accepted options are (for example the main menu
    should only accept "12345"), and a string of what the user entered, then checks
    whether their input is in the accepted string or not. Returns true if it is, and
    false if it isn't */
    public static boolean isValidInput(String validOptions, String entered){
        boolean accepted = true;
        
        if (!validOptions.contains(entered)){
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
