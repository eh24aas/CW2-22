/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.isa;

import java.util.ArrayList;
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
public class CollectionTest {

    private Member alice;
    private Collection collection;

    public CollectionTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        alice = new Member("Alice Smith", "1 High St, Hatfield, AL10 1AB", "alice@example.com");
        collection = new Collection();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addBook method, of class Collection.
     * Book should appear in collection after being added.
     */
    @Test
    public void testAddBook() {
        System.out.println("addBook");
        collection.addBook("1984", "Orwell", alice, "English", "978-0451524935");
        assertNotNull(collection.getItem("1984"));
    }

    /**
     * Test of addDVD method, of class Collection.
     * DVD should appear in collection after being added.
     */
    @Test
    public void testAddDVD() {
        System.out.println("addDVD");
        collection.addDVD("Inception", "Nolan", alice, "English", new String[]{"English"});
        assertNotNull(collection.getItem("Inception"));
    }

    /**
     * Test of getItem method, of class Collection.
     * Should return null if the item does not exist.
     */
    @Test
    public void testGetItemReturnsNullIfNotFound() {
        System.out.println("getItem - not found");
        assertNull(collection.getItem("NonExistentTitle"));
    }

    /**
     * Test of searchItems method, of class Collection.
     * Should return items whose title contains the search term.
     */
    @Test
    public void testSearchItems() {
        System.out.println("searchItems");
        collection.addBook("The Great Gatsby", "Fitzgerald", alice, "English", "666");
        collection.addBook("Great Expectations", "Dickens", alice, "English", "777");
        ArrayList<Item> results = collection.searchItems("Great");
        assertEquals(2, results.size());
    }

    /**
     * Test of searchItems method, of class Collection.
     * Search should be case insensitive.
     */
    @Test
    public void testSearchItemsCaseInsensitive() {
        System.out.println("searchItems - case insensitive");
        collection.addBook("The Hobbit", "Tolkien", alice, "English", "888");
        assertEquals(1, collection.searchItems("hobbit").size());
    }

    /**
     * Test of searchItems method, of class Collection.
     * Should return an empty list when no items match.
     */
    @Test
    public void testSearchItemsNoResults() {
        System.out.println("searchItems - no results");
        collection.addBook("The Hobbit", "Tolkien", alice, "English", "888");
        assertTrue(collection.searchItems("zzznomatch").isEmpty());
    }

    /**
     * Test of removeItem method, of class Collection.
     * Item should no longer be found after removal.
     */
    @Test
    public void testRemoveItem() {
        System.out.println("removeItem");
        collection.addBook("To Remove", "Author", alice, "English", "999");
        Item item = collection.getItem("To Remove");
        collection.removeItem(item);
        assertNull(collection.getItem("To Remove"));
    }

}