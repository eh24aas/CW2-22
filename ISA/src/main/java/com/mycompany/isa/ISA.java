/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.isa;
import java.util.Scanner;
import java.io.*;
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
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] part = data.split("\\|");
                
                if (part[0].equals("Member")){
                    members.addMember(part[1], part[2], part[3]);
                }                 
            }
        } catch (IOException o){
            System.out.println("file not found!");
        }
        
        Member currentMember = null;
        
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] part = line.split("\\|");
                
                if(part[0].equals("Member")){ //iterating over members to match via email
                    for (Member member: members.getMembers()){
                        if(member.getEmail().equals(part[3])){
                        currentMember = member;
                        break;
                        }//this is to ge who donated the items
                    }
                    
                } else if (part[0].equals("Book")) {
                    Book book = new Book(part[1], part[2], currentMember, part[4], part[3]);
                    //if (book.getDonator() != null){
                    //System.out.println(book.getDonator().getName());}
                    
                    if (part.length > 5 && !part[5].isEmpty()){
                        for (Member member : members.getMembers()){
                            if (member.getEmail().equals(part[5])) {//part 5 being the email of borrower
                                book.loanTo(member);
                                member.lend(book);
                                break;
                            }
                        }
                    }//have a silly if here, 
                    
                    items.addBook(book);

                } else if (part[0].equals("DVD")){
                    String[] audioLanguage = part[4].split(",");
                    DVD dvd = new DVD(part[1], part[3], currentMember, part[2], audioLanguage);
                    
                    if (part.length > 5 && !part[5].isEmpty()){
                        for (Member member : members.getMembers()){
                            if (member.getEmail().equals(part[5])){ //part 5 being the email of borrower
                                dvd.loanTo(member);
                                member.lend(dvd);
                                break;
                            }
                        }
                    }
                    items.addDVD(dvd);
                }
                
                
               
                
            }
            
        } catch (IOException e) {
            System.out.println("not found");
        }
    }
    
    /*
    public static void initFileOld(Collection items, MemberCollection members, String fileName){
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
                    
                    DVD dvd = new DVD(part[1], part[3], currentMember, part[2], audioLanguage);
                    
                    if (part.length > 5 && !part[5].isEmpty()){
                        borrowerEmail = part[5];
                        
                    }
                    
                    items.addDVD(dvd); //create item and then add borrower
                }
                else if (part[0].equals("Book")){
                    items.addBook(part[1], part[2], currentMember, part[4], part[3]);
                }
             
            }
        }   catch (IOException o){
            System.out.println("file not found");
        }
            
        
        }*/
    
    public static void saveToFile(Collection items, MemberCollection members, String fileName){
        
        try {PrintWriter writer = new PrintWriter(fileName);
            for (Item item: items.getItems()){
                if (item.getDonator() == null){
                    writer.println(item.toString());
                }
            }
            for (Member member: members.getMembers()){
                writer.println(member.toString());
                for (Item item: items.getItems()){
                    if (item.getDonator() == member && item.getDonator() != null){
                        writer.println(item.toString());
                    }
                }
            }
            writer.close();
            
        } catch (Exception e) {
            System.out.println("file not found");
        }
        
        
    }
    
    
    public static void main(String[] args) {
        
        System.out.println("hellooooo");
        
        System.out.println("Starting program!");
        System.out.println("");
        
        //readFile("input-1.dat");
        Collection ISAitems = new Collection();
        MemberCollection ISAmembers = new MemberCollection();

        initFile(ISAitems,ISAmembers,"input-1.dat");
        
        saveToFile(ISAitems,ISAmembers,"input-2.dat");
        
        mainMenu(ISAitems,ISAmembers); //call the main menu, when exit is slected the program ends naturally
 
       
    } 
    
    //methods
    
    public static void mainMenu(Collection items, MemberCollection members){
        
        System.out.println("example revert");
        
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
                            
                            if(!parseable(itemChoice)){
                                printForInvalid();
                            }
                            else{
                                numChoice = Integer.parseInt(itemChoice); //needs input validation
                            }
                        }

                        Item selectedItem = searchResults.get(numChoice -1); //-1 to keep within index
                        System.out.println(selectedItem.getTitle() + " selected!");

                        System.out.println();
                        if (!selectedItem.isAvailable()){
                            
                         
                            System.out.println(selectedItem.getTitle() + " is currently on loan to "
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
                                    selectedItem.getOnLoanTo().returnItem(selectedItem); //removes it from borrowers list
                                    selectedItem.returnLoan(); //setting borrower to null
                                    
                                    break;

                                case "2":
                                    System.out.println("update item selected");
                                    
                                    System.out.println("1: update ");
                                    
                                    break;

                                case "3":
                                    selectedItem.getOnLoanTo().returnItem(selectedItem); //removing from members borrowed list
                                    items.removeItem(selectedItem); //remove from collection
                                    System.out.println(selectedItem.getTitle()+" removed from system!");
                                    break;

                                default:
                                    System.out.println("please enter a valid input!"); 
                            }
                                  
                            } while (!loanChoice.equals("1") &&!loanChoice.equals("2") && !(loanChoice.equals("3")));
                            
                            
                        }
   
                        System.out.println(selectedItem.toString());


                    }
                    
                    
                    else {
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
    
    public static void updateItem(Collection items, MemberCollection members, Item item){
        
        boolean loop = true;
        
        System.out.println("");
        while(loop){
            if (item instanceof Book){
                
                System.out.println("1: update title");
                System.out.println("2: update author");
                System.out.println("3: update ISBN");
                System.out.println("4: update language");
            }
        }
    }

    
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
    
    public static boolean parseable(String entered){
        if (entered == null){
            return false;
        }
        try{
            Integer.parseInt(entered.trim());
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    
    public static void printForInvalid(){
        System.out.println();
        System.out.println("Please choose a *valid* option!");
        System.out.println(); 
    }
}

