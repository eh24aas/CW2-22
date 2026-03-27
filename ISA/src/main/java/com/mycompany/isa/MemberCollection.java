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
public class MemberCollection {
    
    private ArrayList<Member> members;
    
    public MemberCollection(){
        this.members = new ArrayList();
    }
    
    public void addMember(String name, String address, String email, int donatedQty){
        Member newMember =  new Member(name,address,email, donatedQty);
        this.members.add(newMember);
    }
    
    public void addMember(Member member){
        //alternate constructor for pre-existing member objects 
        this.members.add(member);
    }
    
    public void removeMember(Member member){
        this.members.remove(member);
    }
    
    public ArrayList<Member> searchMember(String searchTerm){
        ArrayList<Member> results = new ArrayList();
        for (Member member:this.members){
            if(member.getName().toLowerCase().contains(searchTerm.toLowerCase())){
                results.add(member);
            }
        }
        return results;
    }
    
    public ArrayList<Member> getMembers(){  //temp for testing 
       return this.members;
    }
}
