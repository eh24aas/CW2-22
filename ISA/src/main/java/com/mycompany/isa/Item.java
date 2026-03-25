/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa;

/**
 *
 * @author elean
 */
abstract class Item {
    
    private String title;
    private String language;
    private Member donatedBy;
    private Member onLoanTo;
    
    public Item(String title, String language, Member donatedBy) {
        this.title = title;
        this.language = language;
        this.donatedBy = donatedBy;
        this.onLoanTo =null;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setLanguage(String language){
        this.language = language;
    }
    
    public void loanTo(Member borrower){ ///unsure - this seems reasonable
        this.onLoanTo = borrower;
    }
    
    public boolean isAvailable(){ //unsure - this may need another look
        if (this.onLoanTo == null){ //if not on loan (onLoanTo = Null)
            return true;
        } else {
            return false;
        }
    }
    
    public void returnLoan(){ //unsure - see above for reasoning
        this.onLoanTo = null;
    }
    
    public Member getDonator() {
        return this.donatedBy;
    }
    
    public void clearDonator() { //unsure if attribute should be null
        this.donatedBy = null;
    }
    
    public String getLanguage(){
        return this.language;
    }
    
    public Member getOnLoanTo(){
        return this.onLoanTo;
    }
    
    
}

