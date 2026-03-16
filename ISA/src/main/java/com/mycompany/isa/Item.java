/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa;

/**
 *
 * @author elean
 */
public class Item {
    
    private String title;
    private String language;
    private Member donatedBy;
    private Member onLoanTo;
    
    public Item(String title, String language, Member donatedBy) {
        this.title = title;
        this.language = language;
        this.donatedBy = donatedBy;
    }
}

