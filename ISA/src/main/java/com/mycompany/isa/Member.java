
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
    
    public Member(String name, String address, String email){
        this.name = name;
        this.address = address;
        this.email = email;
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
    
    }
    
    public void addDonation(){
    
    }
    
    public void returnItem(Item item){
    
    }
    
    public String toString(){
        return "0";
    }
}
