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
    
    
    public void addBook(){}
    
    public void addDVD(){}
    
    public Item getItem(String title){
        for (Item item: items){
            if (item.getTitle().equals(title)){
                return item;
            }
        }
        return null;
    }
    
    public ArrayList<Item> searchItems(String searchTerm){
        /*String lowSearchTerm = searchTerm.toLowerCase();
        ArrayList<Item> foundItems = new ArrayList<Item>();
        
        int i = 0;
        while (i < items.size()){
            Item currentItem*/
        return null;
        
    }
    
    public void removeItem(Item item){
        items.remove(item);
    }
}
