/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.isa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author elean 
 */
public class DVDTest {

    private Member alice;
    private Member bob;
    private Collection collection;

    public DVDTest() {
    }

    @BeforeEach
    public void setUp() {
        alice = new Member("Alice Smith", "1 High St, Hatfield, AL10 1AB", "alice@example.com");
        bob   = new Member("Bob Jones",  "2 Low Rd, Hertford, SG13 1AB",  "bob@example.com");
        collection = new Collection();
    }

  
    /**
     * Test that a DVD is correctly added to the collection. testing
     */
    @Test
    public void testDVDAddedToCollection() {
        System.out.println("DVD added to collection");
        collection.addDVD("Inception", "Nolan", alice, "English", new String[]{"English", "French"});
        assertNotNull(collection.getItem("Inception"));
    }

    /**
     * Test that the added item is an instance of DVD.
     */
    @Test
    public void testDVDIsInstanceOfItem() {
        System.out.println("DVD instanceof Item");
        collection.addDVD("Inception", "Nolan", alice, "English", new String[]{"English"});
        assertTrue(collection.getItem("Inception") instanceof DVD);
    }

    /**
     * Test that a DVD is available by default (not on loan).
     */
    @Test
    public void testDVDIsAvailableByDefault() {
        System.out.println("DVD available by default");
        collection.addDVD("Inception", "Nolan", alice, "English", new String[]{"English"});
        assertTrue(collection.getItem("Inception").isAvailable());
    }

    /**
     * Test that lending a DVD makes it unavailable.
     */
    @Test
    public void testDVDLoanMakesUnavailable() {
        System.out.println("DVD loan makes unavailable");
        collection.addDVD("Inception", "Nolan", alice, "English", new String[]{"English"});
        Item item = collection.getItem("Inception");
        item.loanTo(bob);
        assertFalse(item.isAvailable());
    }

    /**
     * Test that returning a DVD makes it available again.
     */
    @Test
    public void testDVDReturnMakesAvailable() {
        System.out.println("DVD return makes available");
        collection.addDVD("Inception", "Nolan", alice, "English", new String[]{"English"});
        Item item = collection.getItem("Inception");
        item.loanTo(bob);
        item.returnLoan();
        assertTrue(item.isAvailable());
    }

    /**
     * Test that the donator is correctly set on a DVD.
     */
    @Test
    public void testDVDGetDonator() {
        System.out.println("DVD getDonator");
        collection.addDVD("Inception", "Nolan", alice, "English", new String[]{"English"});
        assertEquals(alice, collection.getItem("Inception").getDonator());
    }

    /**
     * Test of toString method, of class DVD.
     * Should start with "DVD|" and contain key fields.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        collection.addDVD("Inception", "Nolan", alice, "English", new String[]{"English"});
        DVD instance = (DVD) collection.getItem("Inception");
        String result = instance.toString();
        assertTrue(result.startsWith("DVD|Inception|"));
        assertTrue(result.contains("Nolan"));
    }

    /**
     * Test of setDirector method, of class DVD.
     */
    @org.junit.jupiter.api.Test
    public void testSetDirector() {
        System.out.println("setDirector");
        String director = "";
        DVD instance = null;
        instance.setDirector(director);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAudioLanguages method, of class DVD.
     */
    @org.junit.jupiter.api.Test
    public void testSetAudioLanguages() {
        System.out.println("setAudioLanguages");
        String[] languages = null;
        DVD instance = null;
        instance.setAudioLanguages(languages);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}