/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.isa;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;




/**
 *
 * @author elean
 */
public class ISA {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        
        try (Scanner scanner  = new Scanner(new File("input-1.dat"))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }   catch (IOException o){
            System.out.println("file not found");
        }
        
        
        //testing 
        
        Member m1 = new Member("name1","add1","e1");
        Member m2 = new Member("name2","add2","e2");
        
        MemberCollection collection = new MemberCollection();
        
        collection.addMember("john cena","64 zoo lane","js@gmail.com");
        
        for (Member member: collection.getMembers()){
            System.out.println(member.getName());
        }
        
        
        
    }
}
