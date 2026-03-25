/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa;

import java.util.ArrayList;

/**
 *
 * @author elean
 */
public class Collection {
    private ArrayList<Item> items;
    
    public Collection(){
    items = new ArrayList<>();
    };
    
    
    public void addBook(String title, String author, Member donator, String language, String isbn){
        Book book = new Book(title, author, donator, language, isbn);
        items.add(book);
    }
    
    public void addDVD(String title, String director, Member donatedBy, String language, String[] audioLanguages){
        DVD dvd = new DVD(title, director, donatedBy, language, audioLanguages);
        items.add(dvd);
    }
    
    public Item getItem(String title){
        for (Item item: items){
            if (item.getTitle().equals(title)){
                return item;
            }
        }
        return null;
    }
    
    public ArrayList<Item> searchItems(String searchTerm){
        String lowSearchTerm = searchTerm.toLowerCase();
        ArrayList<Item> foundItems = new ArrayList<Item>();
        for (Item item:items){
            String currentItemsTitle = item.getTitle().toLowerCase();
            if (currentItemsTitle.contains(lowSearchTerm)){
                foundItems.add(item);
            }
        }
        return foundItems;
        
    }
    
    public void removeItem(Item item){
        items.remove(item);
    }
    
    public void getItems(){ ///TESTING ONLY!!
        
        for (Item item: items){
            System.out.println(item.getDonator());
        }
    }
}
