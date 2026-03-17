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
    
    public Collection(){};
    
    public void addBook(){}
    
    public void addDVD(){}
    
    public Item getItem(){}
    
    public ArrayList<Item> searchItems(String searchTerm){
        String lowSearchTerm = searchTerm.toLowerCase();
        ArrayList<Item> foundItems = new ArrayList<Item>();
        
        int i = 0;
        while (i < items.size()){
            Item currentItem
        }
    }
    
    public void removeItem(Item item){
        items.remove(item);
    }
}
