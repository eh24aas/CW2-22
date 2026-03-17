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
    private ArrayList<Item> Items;
    
    public Collection(){
        Items = new ArrayList<>();
    }
    
    public void addBook(String title, String author, Member donateor, String language, String isbn){
        Item book = new Item(title, author);
    }
}
