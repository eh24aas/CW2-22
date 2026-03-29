/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.isa;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;




/**
 *
 * @author elean
 */
public class ISA {
    
    //global scanner for user input
    public static Scanner sc = new Scanner(System.in);
    
    
    public static void initFile(Collection items, MemberCollection members, String fileName){
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] part = data.split("\\|");
                
                if (part[0].equals("Member")){
                    members.addMember(part[1], part[2], part[3], Integer.parseInt(part[4]));
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
        
        System.out.println("Starting program!");
        System.out.println("");
        
        //readFile("input-1.dat");
        Collection ISAitems = new Collection();
        MemberCollection ISAmembers = new MemberCollection();

        initFile(ISAitems,ISAmembers,"input-1.dat");
        
        //saveToFile(ISAitems,ISAmembers,"input-2.dat");
        
        mainMenu(ISAitems,ISAmembers); //call the main menu, when exit is slected the program ends naturally

    } 
    
    //methods
    
    public static void mainMenu(Collection items, MemberCollection members){
        
        String choice; //has to be defined outside of the loop
        do {
        System.out.println("Please choose from the following options:");
        System.out.println("1: Search items");
        System.out.println("2: Search member");
        System.out.println("3: Add member");
        System.out.println("4: Add item");
        System.out.println("5: Save to file");
        System.out.println("0: Exit program (without saving)");

        choice = sc.nextLine();
        
        switch(choice){
            case "1":
                System.out.println("search items selected!");

                String searchTerm;
                ArrayList<Item> searchResults = new ArrayList();
                boolean validSearch = false;

                while(!validSearch){
                    System.out.println("Please enter the title to search: ");
                    searchTerm = sc.nextLine();
                    searchResults = items.searchItems(searchTerm);
                    
                    if (!searchTerm.isBlank()){
                        validSearch = true;
                    }
                }
                
                    //check if there are results
                    if (searchResults.size() > 0){
                        //print the results
                        for (int i = 0; i < searchResults.size(); i++){ //the begining of an intuative search system
                            System.out.println(i+1 + ": " + searchResults.get(i).getTitle()); //prints a number before every entry
                        }

                        System.out.println("Enter the number of the item: ");
                        String itemChoice = sc.nextLine();

                        int numChoice = Integer.parseInt(itemChoice); //turn input into int

                        while (!(numChoice >= 1 && numChoice <= searchResults.size())) {
                            System.out.println("invalid number!");
                            System.out.println("Enter the number of the item: ");
                            for (int i = 0; i < searchResults.size(); i++){ //the begining of an intuative search system
                            System.out.println(i+1 + ": " + searchResults.get(i).getTitle()); //prints a number before every entry
                            }
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
                        System.out.println("Item details: " + selectedItem.toString());

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
                                        updateItem(items,members,selectedItem);
             
                                        break;

                                    case "3":
                                        selectedItem.getOnLoanTo().returnItem(selectedItem); //removing from members borrowed list
                                        items.removeItem(selectedItem); //remove from collection
                                        System.out.println(selectedItem.getTitle()+" removed from system!");
                                        break;
                                        
                                    case "0":
                                        System.out.println("returning to main menu...");
                                        break;

                                    default:
                                        System.out.println("please enter a valid input!"); 
                                }

                            } while (!loanChoice.contentEquals("0")&&!loanChoice.equals("1") &&!loanChoice.equals("2") && !(loanChoice.equals("3")));
                            
                            
                        }
                        else{
                            //if item is available
                            processAvailableItem(selectedItem, members);
                        }

                    }
                    
                    else {
                        System.out.println("no results!");
                    }
    
                 //needs futher checks but works for now

                break;
                
            case "2":
                Boolean update_loop = true;
                Boolean option_loop = true;
                Boolean member_search_loop = true;
                int member_index = 0;
                String option_ans = "";
                System.out.println("Member you want to search:");
                String ans = sc.next();
                System.out.println();
                ArrayList<Member> member_search = members.searchMember(ans);
                int i = 0;
                
                for (Member member : member_search) {
                    i = i + 1;
                    System.out.println(i + ": " + member);
                    System.out.println("borrowing number: " + member.borrowingQty());
                    for (Item item : member.getLoanItems()) {
                        System.out.println(item.getTitle());
                    }
                }
                
                if (member_search.isEmpty()){
                    System.out.println("Can't find any member");
                    sc.nextLine();
                }
                if (!member_search.isEmpty()){
                    
                    
                    while(member_search_loop){
                    try{
                        System.out.print("Which member you want to select? ");
                        member_index = sc.nextInt();
                        sc.nextLine();
                        if (member_index < 0 || member_index > member_search.size()){
                        System.out.println("input incorrect");
                        }
                        else{
                            member_search_loop = false;
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Input a valid number");
                        sc.nextLine();
                        }
                }
                    Member member_selected = member_search.get(member_index - 1);
                    do{
                    System.out.println("please select an option: ");
                    System.out.println("1: update member");
                    System.out.println("2: remove member");
                    option_ans = sc.nextLine();
                    if (option_ans.equals("1") || option_ans.equals("2")){
                        option_loop = false;
                    }
                    System.out.println();
                    }while(option_loop);
                    if (option_ans.equals("1")){
                        
                        do{
                            System.out.println("Please select an option: ");
                            System.out.println("1. update email");
                            System.out.println("2. update name");
                            System.out.println("3. update address");
                            String update_member_choice = sc.next();
                            sc.nextLine();
                            switch(update_member_choice){
                            case "1":
                                System.out.print("Type the email: ");
                                String member_email = sc.nextLine();
                                for (Member member: members.getMembers()){
                                    if (member.getEmail().equals(member_selected.getEmail())){
                                    member.setEmail(member_email);}
                                    System.out.println(member);
                                    update_loop = false;
                                }
                                
                                break;
                            case "2":
                                System.out.print("Type the name: ");
                                String member_name = sc.nextLine();
                                for (Member member: members.getMembers()){
                                    if (member.getName().equals(member_selected.getName())){
                                    member.setName(member_name);}
                                    System.out.println(member);
                                    update_loop = false;
                                }
                                break;
                            case "3":
                                System.out.print("Type the address: ");
                                String member_address = sc.nextLine();
                                for (Member member: members.getMembers()){
                                    if (member.getAddress().equals(member_selected.getAddress())){
                                    member.setAddress(member_address);}
                                    System.out.println(member);
                                    update_loop = false;
                                }
                                break;
                            default:
                                System.out.println("Please enter a valid input!");
                        }
                        }while(update_loop);
                        
                    }
                    else if (option_ans.equals("2")){
                        for (Item item:items.getItems()){
                            if(item.getDonator() == member_selected){
                                item.clearDonator();
                            }
                            if(item.getOnLoanTo() == member_selected){
                                item.returnLoan();
                            }
                        }
                        members.removeMember(member_selected);
                    }
                }
                break;
             
            case "3":
                System.out.print("Type the name of the member: ");
                String member_name = sc.nextLine();
                System.out.print("Type the address of the member: ");
                String member_address = sc.nextLine();
                System.out.print("Type the email of the member: ");
                String member_email = sc.nextLine();
                members.addMember(member_name, member_address, member_email, 0);
                System.out.println("Member added");
                break;
                
            case "4":
                String typeNum;
                do{
                    System.out.println("Add item selected");
                    System.out.println("What type of item are you adding?");
                    System.out.println("please choose from the following options:");
                    System.out.println("1: Book");
                    System.out.println("2: DVD");
                    typeNum = sc.nextLine();
       
                } while (!typeNum.equals("1")&&!typeNum.equals("2") );
                
                switch(typeNum){
                    case "1":
                        System.out.println("Adding a book");
                        String title;
                        do{
                            System.out.println("please enter the title:");
                            title = sc.nextLine();    
                        } while(title.isBlank());
                        
                        String author;
                        do{
                            System.out.println("please enter the author:");
                            author = sc.nextLine();    
                        } while(author.isBlank());
                        
                        String language;
                        do{
                            System.out.println("please enter the language:");
                            language = sc.nextLine();    
                        } while(language.isBlank());
                        
                        String isbn;
                        do{
                            System.out.println("please enter the isbn:");
                            isbn = sc.nextLine();    
                        } while(isbn.isBlank());
                        
                        Member m;
                        String donateChoice = "";
                        boolean realMember = true;
                        int donateNum;
                        
                        while(realMember){
                            displayMembers(members);
                            System.out.println("Enter the number of the donator: ");

                            
                            for (int j = 0; j < members.getMembers().size(); j++){ 
                            System.out.println(j+1 + ": " + members.getMembers().get(j).getName()); //prints a number before every entry
                            }
                            donateChoice = sc.nextLine();
                            
                            if(!parseable(donateChoice)){
                                printForInvalid();
                            }
                            else{
                                realMember = false;//breaks the loop
                            }
   
                        }
                        donateNum = Integer.parseInt(donateChoice);
                        Member donator = members.getMembers().get(donateNum -1);
                        
                        items.addBook(title, author, donator, language, isbn);
                        System.out.println( title + " added!");
                        
                        }
                
                        
                                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                       
                
                break;
                
            case "5":
                System.out.println("Save to file selected!");
                System.out.println("Please enter the name of the file to save to: ");
                String fileName = sc.nextLine();
                while(fileName.isBlank()|| fileName.isEmpty()){
                    System.out.println("Please enter the name of the file to save to: ");
                    fileName = sc.nextLine();
                }
                saveToFile(items,members,fileName);
                System.out.println(fileName + " saved!");
                System.out.println("returning to main menu...");
                System.out.println("");
                break;
               
                
                
            case "0":
                System.out.println("Exiting application!"); break;
            default: 
                System.out.println("Please enter a valid input!");
        }
       
        } while (!choice.equals("0")); //0 will cause the program to exit and close
        
    }
    
    //this will likely bre re-used in the test - use instanceof (new class)anf copy the required code into a new else if
    //shared methods for title/language
    public static void updateItem(Collection items, MemberCollection members, Item item){
        
        boolean updateLoop = true; //set to false when completed
        
        System.out.println("");
        
            if (item instanceof Book){
                Book book = (Book)  item;
                
                while(updateLoop){
                
                System.out.println("1: update title");
                System.out.println("2: update author");
                System.out.println("3: update ISBN");
                System.out.println("4: update language");
                System.out.println("");
                
                System.out.println("Please enter an option");
                String updateChoice = sc.nextLine();
                
                switch(updateChoice){
                    case "1":
                        System.out.println("update title selected");
                        updateTitle(item);
                        updateLoop = false;
                        break;
                        
                    case "2":
                        System.out.println("update author selected");
                        String newAuthor;
                        String oldAuthor = book.getAuthor();
                        
                        do {
                            System.out.println("Please enter the new author: ");
                            newAuthor = sc.nextLine();
                        } while (newAuthor.isEmpty());
                        
                        book.setAuthor(newAuthor);
                        System.out.println(oldAuthor + " was changed to "+ newAuthor);
                        System.out.println("returning to main menu...");
                        updateLoop = false;
                        break;
                        
                    case "3":
                        System.out.println("update ISBN selected");
                        String newISBN;
                        String oldISBN = book.getIsbn();
                        
                        do {
                            System.out.println("Please enter the new ISBN: ");
                            newISBN = sc.nextLine();
                        } while (newISBN.isEmpty());
                        
                        book.setIsbn(newISBN);
                        System.out.println(oldISBN + " was changed to "+ newISBN);
                        System.out.println("returning to main menu...");
                        updateLoop = false;
                        break;
                        
                    case "4":
                        System.out.println("update language selected");
                        updateLoop = false;
                        updateLanguage(item);
                        break;

                        
                    default:
                        System.out.println("please enter a digit between 1 and 5");
                        
                      
                }
                
                }
                
                
            } else if(item instanceof DVD){
                DVD dvd = (DVD) item; //polymorphism - it can use the updatetitle and other generic methods by passing item, bu can use specific by passing dvd - they are the same obj
                
                System.out.println("1: update title");
                System.out.println("2: update director");
                System.out.println("3: update audio languages");
                System.out.println("4: language");
                
                System.out.println("Please enter an option");
                String updateChoice = sc.nextLine();
                
                while(updateLoop){
                    
                    switch(updateChoice){
                    case "1":
                        System.out.println("update title selected");
                        updateTitle(item);
                        updateLoop = false;
                        break;
                        
                    case "2":
                        System.out.println("update director selected");
                        String newDirector;
                        String oldDirector = dvd.getDirector();
                        
                        do {
                            System.out.println("Please enter the new director: ");
                            newDirector = sc.nextLine();
                        } while (newDirector.isBlank() || newDirector.isEmpty());
                        
                        dvd.setDirector(newDirector);
                        System.out.println(oldDirector + " was renamed to "+ newDirector);
                        System.out.println("returning to main menu...");
                        
                        updateLoop = false;
                        
                        break;
                        
                    case "3":
                        System.out.println("update audio languages selected");
                        String[] oldLanguages = dvd.getAudioLanguages();
                        String newLanguageInput;
                        do{
                            System.out.println("Please enter the the list a available languages, "
                                    + "separated by a comma.");
                            newLanguageInput = sc.nextLine();
                        } while(newLanguageInput.isEmpty());
                        
                        String[] newLanguages = newLanguageInput.split(",");
                        dvd.setAudioLanguages(newLanguages);
                        System.out.println(Arrays.toString(oldLanguages)+" changed to "+ Arrays.toString(newLanguages));
                        System.out.println("returning to main menu....");
                        updateLoop = false;
                        break;
                        
                    case "4":
                        System.out.println("update language selected");
                        updateLoop = false;
                        updateLanguage(item);
                        break;
                        
                    default:
                        System.out.println("please choose from the options: ");
                        
                    }
                }
                
                
            }
        
    }
    
    
    
    private static void processAvailableItem(Item selectedItem, MemberCollection members)
    {
        String notOnLoanChoice;
        String memberChoice;
        do{
            System.out.println(selectedItem.getTitle() + " is available!");
            System.out.println("Press 1 to borrow " + selectedItem.getTitle());
            System.out.println("Press 0 to return to the menu");
            notOnLoanChoice = sc.nextLine();

            switch(notOnLoanChoice){
                case "1":
                    do{
                        System.out.println();
                        displayMembers(members);
                        System.out.println("Who is borrowing: ");
                        memberChoice = sc.nextLine();
                        if (!isInMemberList(members, memberChoice)){
                             printForInvalid();
                        }
                    } while(!isInMemberList(members, memberChoice));
                    //then check if member is eligable to borrow, and go from there!!
                    Member theChosenOne = members.getMembers().get((Integer.parseInt(memberChoice))-1);
                    if(!(theChosenOne.borrowingQty() < theChosenOne.getDonatedQty())){
                        System.out.println("Unfortunately, " + theChosenOne.getName() + " is not eligable to borrow anymore items as users can only borrow as many items they have donated.");
                        System.out.println(theChosenOne.getName() + " has borrowed "+ theChosenOne.borrowingQty()+ " items, and has donated " + theChosenOne.getDonatedQty() + " items.");
                        System.out.println("Please either return an item or donate first!");
                    }
                    else{
                        theChosenOne.lend(selectedItem);
                        selectedItem.loanTo(theChosenOne);
                        selectedItem.getOnLoanTo();
                        System.out.println(theChosenOne.getName() + " has successsfully borrowed " + selectedItem.getTitle() + "!");
                        System.out.println("Don't forget to return it!");
                    }

                    break;
                default:
                    System.out.println("please enter a valid input!");
            }
        } while((!notOnLoanChoice.equals("0")) && selectedItem.isAvailable());
    }
    
    
    public static void updateTitle(Item item){
        String newTitle;
        String oldTitle = item.getTitle();
        do {
            System.out.println("Please enter the new title: ");
            newTitle = sc.nextLine();
        } while (newTitle.isBlank() || newTitle.isEmpty());
        item.setTitle(newTitle);
        System.out.println(oldTitle + " was renamed to "+ newTitle);
        System.out.println("returning to main menu...");
        }
    
    public static void updateLanguage(Item item){
        String newLang;
        String oldLang = item.getLanguage();
        do {
            System.out.println("Please enter the new language: ");
            newLang = sc.nextLine();
        } while (newLang.isBlank() || newLang.isEmpty());
        item.setLanguage(newLang);
        System.out.println(oldLang + " was changed to "+ newLang);
        System.out.println("returning to main menu...");
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
    
    public static void displayMembers(MemberCollection members){
        if (members.getMembers().isEmpty()){
            System.out.println("No members found...");
            System.out.println();
        }
        else{        
            int i = 1;
            for (Member member: members.getMembers()){
                System.out.println(i + ": " + member.toString());
                i++;
            }
        }
    }
      
    public static boolean isInMemberList(MemberCollection members, String userInput){
        boolean accepted = false;
        if (parseable(userInput)){
            int userNum = Integer.parseInt(userInput.trim());
            if (userNum >= 1 && userNum <= members.getMembers().size()){
                accepted = true;
            }
        }
        else{
            accepted = false;
        }
        return accepted;
    }
    
    public static boolean isInItemList(Collection items, String userInput){
        boolean accepted = false;
        if (parseable(userInput)){
            int userNum = Integer.parseInt(userInput.trim());
            if (userNum >= 1 && userNum <= items.getItems().size()){
                accepted = true;
            }
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

