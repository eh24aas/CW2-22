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
public class BookTest {

    private Member alice;
    private Member bob;
    private Collection collection;

    public BookTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        alice = new Member("Alice Smith", "1 High St, Hatfield, AL10 1AB", "alice@example.com", 0);
        bob   = new Member("Bob Jones",  "2 Low Rd, Hertford, SG13 1AB",  "bob@example.com", 0);
        collection = new Collection();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * test that a Book is correctly added to the collection
     */
    @Test
    public void testBookAddedToCollection() {
        System.out.println("Book added to collection");
        collection.addBook("1984", "Orwell", alice, "English", "978-0451524935");
        assertNotNull(collection.getItem("1984"));
    }

    /**
     * test that the added item is an instance of Book
     */
    @Test
    public void testBookIsInstanceOfItem() {
        System.out.println("Book instanceof Item");
        collection.addBook("1984", "Orwell", alice, "English", "978-0451524935");
        assertTrue(collection.getItem("1984") instanceof Book);
    }

    /**
     * test that getTitle returns the correct title
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        collection.addBook("1984", "Orwell", alice, "English", "978-0451524935");
        assertEquals("1984", collection.getItem("1984").getTitle());
    }

    /**
     * test that getLanguage returns the correct language
     */
    @Test
    public void testGetLanguage() {
        System.out.println("getLanguage");
        collection.addBook("1984", "Orwell", alice, "English", "978-0451524935");
        assertEquals("English", collection.getItem("1984").getLanguage());
    }

    /**
     * test that a Book is available by default (not on loan)
     */
    @Test
    public void testBookIsAvailableByDefault() {
        System.out.println("Book available by default");
        collection.addBook("1984", "Orwell", alice, "English", "978-0451524935");
        assertTrue(collection.getItem("1984").isAvailable());
    }

    /**
     * test that lending a Book makes it unavailable
     */
    @Test
    public void testBookLoanMakesUnavailable() {
        System.out.println("Book loan makes unavailable");
        collection.addBook("1984", "Orwell", alice, "English", "978-0451524935");
        Item item = collection.getItem("1984");
        item.loanTo(bob);
        assertFalse(item.isAvailable());
    }

    /**
     * test that returning a Book makes it available again
     */
    @Test
    public void testBookReturnMakesAvailable() {
        System.out.println("Book return makes available");
        collection.addBook("1984", "Orwell", alice, "English", "978-0451524935");
        Item item = collection.getItem("1984");
        item.loanTo(bob);
        item.returnLoan();
        assertTrue(item.isAvailable());
    }

    /**
     * test that the donator is correctly set on a Book
     */
    @Test
    public void testBookGetDonator() {
        System.out.println("Book getDonator");
        collection.addBook("1984", "Orwell", alice, "English", "978-0451524935");
        assertEquals(alice, collection.getItem("1984").getDonator());
    }

    /**
     * test of toString method, of class Book
     * should start with "Book|" and contain key fields
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        collection.addBook("1984", "Orwell", alice, "English", "978-0451524935");
        Book instance = (Book) collection.getItem("1984");
        String result = instance.toString();
        assertTrue(result.startsWith("Book|1984|"));
        assertTrue(result.contains("Orwell"));
    }

}