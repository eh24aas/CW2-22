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
    
    public DVD (String title, String director, Member donatedBy, String language, String[] audioLanguages) {
        super(title, language, donatedBy);
        this.audioLanguages = audioLanguages;
        this.director = director;
    }
   
    public void setDirector(String director){
        this.director = director;
    }
    
    public void setAudioLanguages(String[] languages){
        this.audioLanguages = languages;
    }
    
    public String toString(){
        return "DVD|" + super.getTitle() + "|" + super.getLanguage() + "|" + director + "|" + audioLanguages + "|" + super.getDonator().getEmail();
    }
    
}
