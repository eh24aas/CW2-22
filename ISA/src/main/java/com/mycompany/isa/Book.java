/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa;

/**
 *
 * @author elean
 */
public class Book extends Item{
    
    private String author;
    private String isbn;
    
    public Book(String title, String arthur, Member donatedBy, String language, String isbn){
        super(title, language, donatedBy);
        this.author = author;
        this.isbn = isbn;
    }
    
    public void setAuthor(String author){
        this.author = author;
    }
    
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }
    
    public String toString(){
        return "Book |" + getTitle() + "|" + author + "|" + isbn + "|" + getLanguage() + "|" + getDonator().getEmail() ;
    }
}
