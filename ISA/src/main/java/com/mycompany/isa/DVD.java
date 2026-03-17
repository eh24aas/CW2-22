package com.mycompany.isa;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elean
 */
public class DVD extends Item{
    
    private String director;
    private String[] audioLanguages;
    
    public DVD (String title, String director, Member donatedBy, String Language, String[] audioLanguages) {
        super(language, title, donatedBy);
        this.audioLanguages = audioLanguages[];
        this.director = director;
    }
   
    
}
