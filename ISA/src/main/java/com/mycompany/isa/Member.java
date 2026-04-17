
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
public class Member {
    private String name;
    private String address;
    private String email;
    private int donatedQty;
    private ArrayList<Item> borrowing;
    private ArrayList<Item> dontatedItems;
    
    public Member(String name, String address, String email, int donatedQty){
        this.name = name;
        this.address = address;
        this.email = email;
        this.donatedQty = donatedQty;
        this.borrowing = new ArrayList<Item>();
        this.dontatedItems = new ArrayList<Item>();
        
        
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void decDonatedQty(){
        
        this.donatedQty = this.donatedQty -1 ;
    }
    
    public ArrayList<Item> getDonatedItems(){
        return this.dontatedItems;
    }
    
    public int getDonatedQty(){
        return this.donatedQty;
    }
    
    public int borrowingQty(){
        return borrowing.size();
    }
    
    public ArrayList<Item> getLoanItems(){
        return this.borrowing;
    }
    
    public void lend (Item item){
        this.borrowing.add(item);
    }
    
    public void addDonation(Item item){
        this.dontatedItems.add(item);
        this.donatedQty = this.donatedQty + 1;
    }
    
    public void removeDonation(Item item){
        this.dontatedItems.remove(item);
    }
    
    public void returnItem(Item item){
        this.borrowing.remove(item);
    }
    
    public String toString(){
        return "Member|" + name + "|" + address + "|" + email + "|" + donatedQty;      
    }
    
    
}
