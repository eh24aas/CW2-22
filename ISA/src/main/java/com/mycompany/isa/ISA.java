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
import java.util.ArrayList;




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
                    String borrowerEmail = ""; //taking borrower email length 
                    if (part.length > 5 && !part[5].isEmpty()){
                        borrowerEmail = part[5];
                    }
                    items.addDVD(part[1], part[3], currentMember, part[2], audioLanguage); //create item and then add borrower
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
        
        System.out.println("Starting program!");
        System.out.println("");
        
        //readFile("input-1.dat");
        Collection ISAitems = new Collection();
        MemberCollection ISAmembers = new MemberCollection();

        initFile(ISAitems,ISAmembers,"input-1.dat");
        
        mainMenu(ISAitems,ISAmembers); //call the main menu, when exit is slected the program ends naturally
 
        /*Scanner input = new Scanner(System.in);
        while (isRunning){
            System.out.println(); 
            System.out.println(); 
            System.out.println("***** Main Menu *****");                        
            System.out.println(); 
            System.out.println("Please choose a number from the following:");
            System.out.println("Press 1 to ermem");
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
       */
    } 
    
    //methods
    
    public static void mainMenu(Collection items, MemberCollection members){
        
        String choice; //has to be defined outside of the loop
        do {
        System.out.println("Please choose from the following options:");
        System.out.println("1: Search items");
        System.out.println("0: Exit program (without saving)");

        choice = sc.nextLine();
        
        switch(choice){
            case "1":
                System.out.println("search items selected!");
            
                String searchTerm;
                ArrayList<Item> searchResults = new ArrayList();
                
                do {
                    System.out.println("Please enter the title to search: ");
                    searchTerm = sc.nextLine();
                    searchResults = items.searchItems(searchTerm);
                  
                    //check if there are results
                    if (searchResults.size() > 0){
                        //print the results
                        for (int i = 0; i < searchResults.size(); i++){ //the begining of an intuative search system
                            System.out.println(i+1 + ": " + searchResults.get(i).getTitle()); //prints a number before eveyr entry
                        }
                        
                        System.out.println("Enter the number of the item: ");
                        String itemChoice = sc.nextLine();
                        
                        int numChoice = Integer.parseInt(itemChoice); //turn input into int
                        
                        while (!(numChoice >= 1 && numChoice <= searchResults.size())) {
                            System.out.println("invalid number!");
                            System.out.println("Enter the number of the item: ");
                            itemChoice = sc.nextLine();
                            numChoice = Integer.parseInt(itemChoice); //needs input validation
                        }
                        
                        Item selectedItem = searchResults.get(numChoice -1); //-1 to keep within index
                        System.out.println(selectedItem.getTitle() + " selected!");
                        
                        System.out.println();
                        if (!selectedItem.isAvailable()){
                            
                         
                            System.out.println(selectedItem.toString() + " is currently on loan to "
                                    + selectedItem.getOnLoanTo().getName());
                            String loanChoice;
                            
                            do{
                            System.out.println("please select an option: ");
                            System.out.println("1: return item");
                            System.out.println("2: update item");
                            System.out.println("3: remove item");
                            System.out.println("0: return to main menu");
                            
                            loanChoice = sc.nextLine();
                            
                            switch(loanChoice){
                                case "1":
                                    System.out.println(selectedItem.getTitle() + " returned!");
                                    selectedItem.returnLoan(); //setting borrower to null
                                    selectedItem.getOnLoanTo().returnItem(selectedItem); //removes it from borrowers list
                                    break;
                                    
                                case "2":
                                    System.out.println("updating item");
                                    break;
                                    
                                case "3":
                                    selectedItem.getOnLoanTo().returnItem(selectedItem); //removing from members borrowed list
                                    items.removeItem(selectedItem); //remove from collection
                                    System.out.println(selectedItem.getTitle()+" removed from system!");
                                    break;
                                    
                                default:
                                    System.out.println("please enter a valid input!");
                                   
                                   
                                
                                    
                                    
                            }
                                  
                            } while (!loanChoice.equals("0"));
                            
                            
                        }
                       
                       
                        
                        System.out.println(selectedItem.toString());
                        
                      
                        
                        
                        
                        

                        
                        
                    } else {
                        System.out.println("no results!");
                    }
                
                } while (searchTerm.isEmpty()); //needs futher checks but works for now
                
                
              
                
                
                break;
            case "0":
                System.out.println("Exiting application!"); break;
            default: 
                System.out.println("Please enter a valid input!");
        }
            
        } while (!choice.equals("0")); //0 will cause the program to exit and close
        
        
       
        
    }
    
    public static void searchItems(Collection items, MemberCollection members){
        
        String searchterm;
    
                
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

