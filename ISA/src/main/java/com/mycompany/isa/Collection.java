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
        /*String lowTitle = title.toLowerCase();
        ArrayList<Song> foundSongs = new ArrayList();

        int i = 0;
        while (i < songs.size()){
            Song current = songs.get(i);
            String currentTitle = current.getTitle();
            String lowCurrentTitle = currentTitle.toLowerCase();
            if (lowCurrentTitle.contains(lowTitle)){
                foundSongs.add(current);
            }
            i = i + 1;
        }
        
       return foundSongs;   */
    }
    
    public void removeItem(Item item){
        items.remove(item);
    }
}
