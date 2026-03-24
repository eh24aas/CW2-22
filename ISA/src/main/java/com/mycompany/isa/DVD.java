package com.mycompany.isa;
import java.util.Arrays;

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
    
    @Override
    public String toString(){
        String lang = "";
        for (int i = 0; i < audioLanguages.length; i++){
            lang = lang + audioLanguages[i];
            if (i < (audioLanguages.length - 1)){
                lang = lang + ", ";
            }
        }
        return "DVD|" + super.getTitle() + "|" + super.getLanguage() + "|" + director + "|" + lang + "|" + super.getDonator().getEmail(); 
    }
    
}
